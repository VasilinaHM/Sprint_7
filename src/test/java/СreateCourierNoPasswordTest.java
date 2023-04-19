import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.Courier;
import org.example.CreateCourier;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class СreateCourierNoPasswordTest {
    private Courier courier;
    private CreateCourier createCourier;

    @Before
    public void setUp() {
        courier = new Courier();
    }

    @Test
    @DisplayName("Создание курьера без заполнения password")
    @Description("Нельзя создать курьера без заполнения password.Код ответа - 400")
    public void createCourierNoPasswordAndCheck() {
        createCourier = new CreateCourier("1234", "", "1234");
        ValidatableResponse response = courier.create(createCourier);
        response.assertThat().statusCode(400).body("message", is("Недостаточно данных для создания учетной записи"));
    }
}
