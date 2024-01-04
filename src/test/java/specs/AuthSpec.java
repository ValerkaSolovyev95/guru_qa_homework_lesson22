package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class AuthSpec {
    public static RequestSpecification authRequestSpecification = with()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON)
            .log().method()
            .log().body()
            .log().uri();

    public static ResponseSpecification authResponseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}
