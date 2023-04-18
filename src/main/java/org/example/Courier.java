package org.example;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class Courier {

    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";

    public Courier() {
        RestAssured.baseURI = BASE_URI;
    }
@Step("Создание курьера POST api/v1/courier")
    public ValidatableResponse create(CreateCourier createCourier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(createCourier)
                .when()
                .post("api/v1/courier")
                .then();

    }
@Step("Авторизация курьера POST api/v1/courier/login")
    public ValidatableResponse login(LoginCourier loginCourier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(loginCourier)
                .when()
                .post("api/v1/courier/login")
                .then();
    }
@Step("Удаление курьера DELETE /api/v1/courier/")
    public ValidatableResponse delete(int courierId) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .delete("/api/v1/courier/" + courierId)
                .then();

    }
}

