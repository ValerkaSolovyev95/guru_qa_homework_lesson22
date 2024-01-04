package pages;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BasePage {
    public void removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }
}
