package api;

import models.AuthModel;
import models.LoginModel;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static specs.DemoQaSpec.demoQaRequestSpecification;
import static specs.DemoQaSpec.demoQaResponseSpecification200;

public class AuthApi {
    private static final String AUTH_URI = "/Account/v1/Login";

    public static void setCookie(LoginModel loginModel) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginModel.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginModel.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginModel.getExpires()));
    }
    public static LoginModel getLoginData(AuthModel authModel) {
        LoginModel loginModel = given(demoQaRequestSpecification)
                .body(authModel)
                .when()
                .post(AUTH_URI)
                .then()
                .spec(demoQaResponseSpecification200)
                .extract()
                .as(LoginModel.class);
        return loginModel;
    }
}
