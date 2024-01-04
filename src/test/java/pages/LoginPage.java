package pages;

import models.LoginModel;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginPage extends BasePage{
    public LoginPage setCookie(LoginModel loginModel) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginModel.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", loginModel.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginModel.getExpires()));
        return this;
    }


}
