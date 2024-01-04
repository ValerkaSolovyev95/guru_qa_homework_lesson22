import api.BookApi;
import models.AuthModel;
import models.BookModel;
import models.BooksListModel;
import models.LoginModel;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProfilePage;

import static api.AuthApi.getLoginData;
import static api.BookApi.addBook;
import static api.BookApi.deleteAllBooks;
import static api.BookApi.deleteBook;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

class BookStoreTests extends BaseTest {
    private final LoginPage LOGIN_PAGE = new LoginPage();
    private final ProfilePage PROFILE_PAGE = new ProfilePage();


    @Test
    void authorizationInBookStorageTest() {
        AuthModel authRequestBody = new AuthModel();
        authRequestBody.setUserName(TestData.userName);
        authRequestBody.setPassword(TestData.password);

        LoginModel loginResponseData = step("Get authorization data", () -> getLoginData(authRequestBody));
        step("Set cookie from authorization", () -> {
            LOGIN_PAGE.setCookie(loginResponseData);
            LOGIN_PAGE.removeBanner();
        });
        step("Check userId on profile page", () -> PROFILE_PAGE.checkUserNameProfile(TestData.userName));
    }

    @Test
    void addBookTest() {
        AuthModel authRequestBody = new AuthModel();
        authRequestBody.setUserName(TestData.userName);
        authRequestBody.setPassword(TestData.password);

        LoginModel loginResponseData = step("Get authorization data", () -> getLoginData(authRequestBody));
        step("Set cookie from authorization", () -> {
            LOGIN_PAGE.setCookie(loginResponseData);
            LOGIN_PAGE.removeBanner();
        });
        BooksListModel booksList = step("Get list book from book store", BookApi::getBooksList);
        BookModel book = step("Get first book from book store", () -> booksList.getBooks().get(0));
        step("Add book", () -> addBook(loginResponseData, book));
        step("Check book on profile page", PROFILE_PAGE::checkGitBookGuideVisible);
        step("Clear profile book table", () -> deleteAllBooks(loginResponseData));
    }

    @Test
    void deleteBookFromCartTest() {
        AuthModel authRequestBody = new AuthModel();
        authRequestBody.setUserName(TestData.userName);
        authRequestBody.setPassword(TestData.password);

        LoginModel loginResponseData = step("Get authorization data", () -> getLoginData(authRequestBody));
        step("Set cookie from authorization", () -> {
            LOGIN_PAGE.setCookie(loginResponseData);
            LOGIN_PAGE.removeBanner();
        });
        BooksListModel booksList = step("Get list book from book store", BookApi::getBooksList);
        BookModel book = step("Get first book from book store", () -> booksList.getBooks().get(0));
        step("Add book", () -> addBook(loginResponseData, book));
        step("Check book visible on profile page", PROFILE_PAGE::checkGitBookGuideVisible);
        step("Delete book", () -> {
            deleteBook(loginResponseData, book.getIsbn());
            open("/profile");
        });
        step("Check book not visible on profile page", PROFILE_PAGE::checkGitBookGuideNotVisible);
    }
}
