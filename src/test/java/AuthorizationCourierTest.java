import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.example.Courier;
import org.example.CreateCourier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.example.LoginCourier.credsFrom;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


public class AuthorizationCourierTest {
    private Courier courier = new Courier();
    private int courierId;
    private CreateCourier createCourier = new CreateCourier("vasa", "12345", "Vasa");

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
        ValidatableResponse response = courier.create(createCourier);
        response.assertThat().statusCode(201).body("ok", is(true));
    }

    @Test
    @DisplayName("Авторизация курьера с валидными login и password")
    public void authorizationCourierAndAndCheckResponse() {
        ValidatableResponse loginResponse = courier.login(credsFrom(createCourier));
        loginResponse.assertThat().statusCode(200).body("id", notNullValue());
    }

    @After
    public void tearDown() {
        ValidatableResponse loginResponse = courier.login(credsFrom(createCourier));
        courierId = loginResponse.extract().path("id");
        ValidatableResponse deleteResponse = courier.delete(courierId);
        deleteResponse.assertThat().statusCode(200).body("ok", is(true));
    }
}
