package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    private static final SelenideElement userName = $("#userName-value");
    private static final SelenideElement gitBookGuide = $("[id='see-book-Git Pocket Guide']");
    public void checkUserNameProfile(String text) {
        open("/profile");
        userName.shouldHave(Condition.text(text));
    }

    public void checkGitBookGuideVisible() {
        open("/profile");
        gitBookGuide.shouldBe(Condition.visible);
    }

    public void checkGitBookGuideNotVisible() {
        open("/profile");
        gitBookGuide.shouldNot(Condition.visible);
    }
}
