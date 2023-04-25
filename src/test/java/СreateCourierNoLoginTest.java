package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;


public class СreateCourierNoLoginTest {
    private Courier courier;
    private CreateCourier createCourier;

    @Before
    public void setUp() {
        courier = new Courier();
    }

    @Test
    @DisplayName("Создание курьера без заполнения login")
    @Description("Нельзя создать курьера без заполнения login.Код ответа - 400")
    public void createCourierNoLoginAndCheck() {
        createCourier = new CreateCourier("", "1234", "1234");
        ValidatableResponse response = courier.create(createCourier);
        response.assertThat().statusCode(400).body("message", is("Недостаточно данных для создания учетной записи"));
    }
}