import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.CreateOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.example.Order;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class OrderCheckColorsTest {
    private CreateOrder createOrder;
    private final String[] color;
    public OrderCheckColorsTest (String[] color) {
        this.color = color;}
    private int trackOrder;
    private ValidatableResponse response;



    @Parameterized.Parameters(name="{0}=no color,{1}=GREY,{2}=BLACK,{3}=TWO COLOR")
    public static Object[][] colorData() {
        Object[][] objects;
        objects = new Object[][]{
                {null},
                {new String[]{"GREY"}},
                {new String[]{"BLACK"}},
                {new String[]{"BLACK","GREY"}}
        };
        return objects;
    }

    @Before
    public void setUp() {
        createOrder = new CreateOrder();
    }

    @Test
    @DisplayName("Создание 4 заказов с указанием разного цвета самоката")
    public void createOrderWithMultipleColors() {
        Order order = new Order("Катя",
            "Попова",
            "Планерная 5",
            "4",
            "+7 800 355 35 35",
            5,
            "2020-06-06",
            "Приветственный комментарий",
            color);
        ValidatableResponse response = createOrder.create(order);
        response.assertThat().statusCode(201).body("track", notNullValue());
        trackOrder = response.extract().path("track");
    }
    @After
    public void tearDown() {
        ValidatableResponse responseDelete = createOrder.delete(trackOrder);
        responseDelete.assertThat().statusCode(200).body("ok", is(true));
    }
}