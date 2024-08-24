package APItestforReqres.DeleteTest;

import MyUtils.MyUtils;
import MyUtils.Specification;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Owner("Дима")
public class DeleteUser {
    @Test
    @Description("Удалить пользователя")
    public void DeleteUser() {
        Specification.InstallSpecification(204);

        ValidatableResponse user = given()
                .when()
                .delete("api/users/2")
                .then().log().all();

        MyUtils.TextLog(user.toString());
    }
}
