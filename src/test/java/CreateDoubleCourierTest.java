import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.example.Courier;
import org.example.CreateCourier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.example.LoginCourier.credsFrom;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class CreateDoubleCourierTest {
    private Courier courier = new Courier();
    private int courierId;
    private CreateCourier createCourier = new CreateCourier("vasa", "12345", "Vasa");

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
        ValidatableResponse response = courier.create(createCourier);
        assertEquals("Статус код неверный при создании курьера",
                HttpStatus.SC_CREATED, response.extract().statusCode());
    }

    @Test
    @DisplayName("Создание курьера с повторяющимся логином")
    @Description("Нельзя создать курьера с повторяющимся логином.Код ответа - 409")

    public void createDoubleCourierAndCheck() {
        ValidatableResponse response = courier.create(createCourier);
        assertEquals("Статус код неверный при попытке создания курьера с повторяющимся логином",
                HttpStatus.SC_CONFLICT, response.extract().statusCode());
        response.assertThat().body("message", is("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void tearDown() {
        ValidatableResponse loginResponse = courier.login(credsFrom(createCourier));
        courierId = loginResponse.extract().path("id");
        ValidatableResponse deleteResponse = courier.delete(courierId);
        deleteResponse.assertThat().statusCode(200).body("ok", is(true));
    }
}