package APItestforReqres.RegisterTest;

import MyUtils.*;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Owner("Дима")
public class RegisterUser {
    @Test
    @Description("Успешная регистрация пользователя")
    public void SuccessfulRegister () {
        Specification.InstallSpecification(200);

        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";

        RegisterPojo user = new RegisterPojo("eve.holt@reqres.in", "pistol");

        SuccessRegPojo register = given()
                .body(user)
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().as(SuccessRegPojo.class);

        Assertions.assertNotNull(register.getId());
        Assertions.assertNotNull(register.getToken());
        Assertions.assertEquals(id, register.getId());
        Assertions.assertEquals(token, register.getToken());

        MyUtils.TextLog(register.toString());
    }

    @Test
    @Description("Неудачная регистрация пользователя")
    public void UnsuccessfulRegister () {
        Specification.InstallSpecification(400);

        RegisterPojo user = new RegisterPojo("sydney@fife", "");

        ErrorPojo result = given()
                .body(user)
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().as(ErrorPojo.class);

        Assertions.assertNotNull(result.getError());
        Assertions.assertEquals("Missing password", result.getError());

        MyUtils.TextLog(result.toString());
    }
}
