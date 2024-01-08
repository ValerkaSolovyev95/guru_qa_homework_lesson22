package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage extends BasePage{
    private SelenideElement
            userName = $("#userName-value"),
            gitBookGuide = $("[id='see-book-Git Pocket Guide']");

    public void openProfilePage() {
        open("/profile");
    }

    public void checkUserNameProfile(String text) {
        openProfilePage();
        userName.shouldHave(Condition.text(text));
    }

    public void checkGitBookGuideVisible() {
        openProfilePage();
        gitBookGuide.shouldBe(Condition.visible);
    }

    public void checkGitBookGuideNotVisible() {
        openProfilePage();
        gitBookGuide.shouldNot(Condition.visible);
    }
}
