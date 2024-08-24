package APItestforReqres.GetTest;

import MyUtils.*;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@Owner("Дима")
public class GetUser {
    @Test
    @Description("Вывод списка пользователей")
    public void GetListUsers() {
        Specification.InstallSpecification(200);
        List<UserPojo> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().jsonPath().getList("data", UserPojo.class);

        Assertions.assertNotNull(users);

        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

        users.forEach(x -> MyUtils.TextLog(x.toString()));
    }
}
