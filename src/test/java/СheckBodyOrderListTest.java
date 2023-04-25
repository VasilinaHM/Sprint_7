import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.CreateOrder;
import org.example.OrdersList;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class СheckBodyOrderListTest {
    private CreateOrder createOrder;

    @Before
    public void setUp() {
        createOrder = new CreateOrder();
    }

    @Test
    @DisplayName("Список заказов")
    @Description("Проверка, что в теле ответа возвращается список заказов")
    public void checkListOrders() {
        OrdersList ordersList = given().get("/api/v1/orders").body().as(OrdersList.class);
        MatcherAssert.assertThat(ordersList, notNullValue());
    }
}




