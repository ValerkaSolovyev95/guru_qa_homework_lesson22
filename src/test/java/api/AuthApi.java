package api;

import models.AuthModel;
import models.LoginModel;

import static io.restassured.RestAssured.given;
import static specs.AuthSpec.authRequestSpecification;
import static specs.AuthSpec.authResponseSpecification;

public class AuthApi {
    private static String authUri = "/Account/v1/Login";
    public static LoginModel getLoginData(AuthModel authModel) {
        LoginModel loginModel = given(authRequestSpecification)
                .body(authModel)
                .when()
                .post(authUri)
                .then()
                .spec(authResponseSpecification)
                .extract()
                .as(LoginModel.class);
        return loginModel;
    }
}
