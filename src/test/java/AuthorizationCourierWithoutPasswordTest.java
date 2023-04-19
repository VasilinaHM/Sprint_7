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
public class AuthorizationCourierWithoutPasswordTest {
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
    @DisplayName("Авторизация курьера без password")
    public void authorizationCourierWithoutPassword() {
        CreateCourier createCouriers = new CreateCourier(createCourier.getLogin(), "", createCourier.getFirstName());
        ValidatableResponse loginResponse = courier.login(credsFrom(createCouriers));
        loginResponse.assertThat().statusCode(400).body("message", is("Недостаточно данных для входа"));
    }

    @After
    public void tearDown() {
        ValidatableResponse loginResponse = courier.login(credsFrom(createCourier));
        courierId = loginResponse.extract().path("id");
        ValidatableResponse deleteResponse = courier.delete(courierId);
        deleteResponse.assertThat().statusCode(200).body("ok", is(true));
    }
}



