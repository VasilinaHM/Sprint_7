import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.example.Courier;
import org.example.CreateCourier;
import org.junit.Before;
import org.junit.Test;

import static org.example.LoginCourier.credsFrom;
import static org.hamcrest.Matchers.is;

public class AuthorizationNoExistCourierTest {
    private Courier courier = new Courier();

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Авторизация не существующего курьера")
    public void authorizationNoExistCourier() {
        CreateCourier createCourier = new CreateCourier("BHg567jhrki5jhhc8765zA74", "AAlkjhgBHgj46875jxd", "mfbnvfnf");
        ValidatableResponse loginResponse = courier.login(credsFrom(createCourier));
        loginResponse.assertThat().statusCode(404).body("message", is("Учетная запись не найдена"));
    }
}

