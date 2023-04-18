import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.OrdersList;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class СheckBodyOrderListTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Список заказов")
    @Description("Проверка, что в теле ответа возвращается список заказов")
    public void checkListOrders() {
        OrdersList ordersList = given()
                .get("/api/v1/orders")
                .body().as(OrdersList.class);
        MatcherAssert.assertThat(ordersList, notNullValue());
    }
}




