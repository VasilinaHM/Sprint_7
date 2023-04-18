package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class СreateCourierNoLoginTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Создание курьера без заполнения login")
    @Description("Нельзя создать курьера без заполнения login.Код ответа - 400")
    public void createCourierNoLoginAndCheck() {
        File json = new File("src/test/resources/noLogin.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().statusCode(400).and().body("message", is("Недостаточно данных для создания учетной записи"));
    }
}


