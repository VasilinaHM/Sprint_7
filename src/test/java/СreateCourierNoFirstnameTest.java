import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.Courier;
import org.example.CreateCourier;
import org.junit.Before;
import org.junit.Test;

public class СreateCourierNoFirstnameTest {
    private Courier courier;
    private CreateCourier createCourier;

    @Before
    public void setUp() {
        courier = new Courier();
    }

    @Test
    @DisplayName("Создание курьера без заполнения firstName")
    @Description("Нельзя создать курьера без заполнения firstName.Код ответа - 409")
    public void createCourierNoFirstNameAndCheck() {
        createCourier = new CreateCourier("1234", "1234", "");
        ValidatableResponse response = courier.create(createCourier);
        response.assertThat().statusCode(409);
    }
}
