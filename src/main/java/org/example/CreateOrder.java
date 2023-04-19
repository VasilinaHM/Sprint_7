package org.example;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CreateOrder {
    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";

    public CreateOrder() {
        RestAssured.baseURI = BASE_URI;
    }

    @Step("Создание заказа POST api/v1/orders")
    public ValidatableResponse create(Order order) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post("/api/v1/orders")
                .then();
    }

    @Step("Удаление заказа DELETE")
    public ValidatableResponse delete(int trackOrder) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .put("/api/v1/orders/cancel?track=" + trackOrder)
                .then();
    }
}