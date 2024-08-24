package MyUtils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

public class Specification {
    public static void InstallSpecification (int code) {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(MyUtils.URL_REQRES)
                .setContentType(ContentType.JSON)
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(code)
                .build();
    }
}
