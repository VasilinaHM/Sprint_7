import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.Courier;
import org.example.CreateCourier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.example.CourierGenerator.randomCourier;
import static org.example.LoginCourier.credsFrom;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


public class AuthorizationCourierTest {
    private Courier courier;
    private int courierId;
    private CreateCourier createCourier;

    @Before
    public void setUp() {
        createCourier = randomCourier();
        courier = new Courier();
        courier.create(createCourier);
    }

    @Test
    @DisplayName("Авторизация курьера с валидными login и password")
    public void authorizationCourierAndAndCheckResponse() {
        ValidatableResponse loginResponse = courier.login(credsFrom(createCourier));
        loginResponse.assertThat().statusCode(200).body("id", notNullValue());
        courierId = loginResponse.extract().path("id");
    }

    @After
    public void tearDown() {
        ValidatableResponse deleteResponse = courier.delete(courierId);
        deleteResponse.assertThat().statusCode(200).body("ok", is(true));
    }
}
