package APItestforReqres.UpdateTest;

import MyUtils.MyUtils;
import MyUtils.Specification;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

@Owner("Дима")
public class UpdateUserData {
    @Test
    @Description("Обновить пользователя")
    public void UpdateUser() {
        Specification.InstallSpecification(200);

        BeingUpdatedUserPojo user = new BeingUpdatedUserPojo("morpheus", "zion resident");

        UpdatedUserPojo result = given()
                .body(user)
                .when()
                .patch("/api/users/2")
                .then().log().all()
                .extract().as(UpdatedUserPojo.class);

        Assertions.assertNotNull(result);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Assertions.assertEquals(LocalDate.now().toString(), new SimpleDateFormat("yyyy-MM-dd").format(result.getUpdatedAt()));

        MyUtils.TextLog(result.toString());
    }
}
