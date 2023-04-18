import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.example.CreateOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class OrderCheckColorsTest {
    String pathForTest;
    private int trackOrder;
    private ValidatableResponse response;
    private final CreateOrder createOrder = new CreateOrder();


    public OrderCheckColorsTest(String pathForTest) {
        this.pathForTest = pathForTest;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"src/test/resources/colorBlack.json"},
                {"src/test/resources/colorBlackAndGrey.json"},
                {"src/test/resources/colorGrey.json"},
                {"src/test/resources/noColor.json"}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Создание 4 заказов с указанием разного цвета самоката")
    public void createOrderWithMultipleColors() {
        File json = new File(pathForTest);
        response = createOrder.create(json);
        response.assertThat().statusCode(201).body("track", notNullValue());

    }

    @After
    public void tearDown() {
        trackOrder = response.extract().path("track");
        ValidatableResponse responseDelete = createOrder.delete(trackOrder);
        responseDelete.assertThat().statusCode(200).body("ok", is(true));
    }
}