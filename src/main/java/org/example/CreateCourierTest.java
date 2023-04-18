package org.example;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.example.LoginCourier.credsFrom;
import static org.hamcrest.Matchers.is;


public class CreateCourierTest {
    private int courierId;
    private Courier courier = new Courier();
    private CreateCourier createCourier = new CreateCourier("vasa", "12345", "Vasa");

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }


    @Test
    @DisplayName("Создание курьера")
    public void createNewCourierAndCheckResponse() {
        ValidatableResponse response = courier.create(createCourier);
        response.assertThat().statusCode(201).body("ok", is(true));
    }

    @After
    public void tearDown() {
        ValidatableResponse loginResponse = courier.login(credsFrom(createCourier));
        courierId = loginResponse.extract().path("id");
        ValidatableResponse deleteResponse = courier.delete(courierId);
        deleteResponse.assertThat().statusCode(200).body("ok", is(true));
    }
}