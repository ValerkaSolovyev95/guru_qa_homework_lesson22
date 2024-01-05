package api;

import models.AuthModel;
import models.LoginModel;

import static io.restassured.RestAssured.given;
import static specs.DemoQaSpec.demoQaRequestSpecification;
import static specs.DemoQaSpec.demoQaResponseSpecification;

public class AuthApi {
    private static final String AUTH_URI = "/Account/v1/Login";
    public static LoginModel getLoginData(AuthModel authModel) {
        LoginModel loginModel = given(demoQaRequestSpecification)
                .body(authModel)
                .when()
                .post(AUTH_URI)
                .then()
                .spec(demoQaResponseSpecification)
                .extract()
                .as(LoginModel.class);
        return loginModel;
    }
}
