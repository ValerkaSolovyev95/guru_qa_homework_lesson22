import api.BookApi;
import models.AuthModel;
import models.BookModel;
import models.BooksListModel;
import models.LoginModel;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static api.AuthApi.getLoginData;
import static api.AuthApi.setCookie;
import static api.BookApi.addBook;
import static api.BookApi.deleteAllBooks;
import static api.BookApi.deleteBook;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

class BookStoreTests extends BaseTest {
    private ProfilePage profilePage = new ProfilePage();


    @Test
    void authorizationInBookStorageTest() {
        AuthModel authRequestBody = new AuthModel();
        authRequestBody.setUserName(TestData.userName);
        authRequestBody.setPassword(TestData.password);

        LoginModel loginResponseData = step("Get authorization data", () -> getLoginData(authRequestBody));
        step("Set cookie from authorization", () -> setCookie(loginResponseData));
        step("Check userId on profile page", () -> {
            profilePage.removeBanner();
            profilePage.checkUserNameProfile(TestData.userName);
        });
    }

    @Test
    void addBookTest() {
        AuthModel authRequestBody = new AuthModel();
        authRequestBody.setUserName(TestData.userName);
        authRequestBody.setPassword(TestData.password);

        LoginModel loginResponseData = step("Get authorization data", () -> getLoginData(authRequestBody));
        step("Set cookie from authorization", () -> setCookie(loginResponseData));
        BooksListModel booksList = step("Get list book from book store", BookApi::getBooksList);
        BookModel book = step("Get first book from book store", () -> booksList.getBooks().get(0));
        step("Add book", () -> addBook(loginResponseData, book));
        step("Check book on profile page", () -> {
            profilePage.removeBanner();
            profilePage.checkGitBookGuideVisible();
        });
        step("Clear profile book table", () -> deleteAllBooks(loginResponseData));
    }

    @Test
    void deleteBookFromCartTest() {
        AuthModel authRequestBody = new AuthModel();
        authRequestBody.setUserName(TestData.userName);
        authRequestBody.setPassword(TestData.password);

        LoginModel loginResponseData = step("Get authorization data", () -> getLoginData(authRequestBody));
        step("Set cookie from authorization", () -> setCookie(loginResponseData));
        BooksListModel booksList = step("Get list book from book store", BookApi::getBooksList);
        BookModel book = step("Get first book from book store", () -> booksList.getBooks().get(0));
        step("Add book", () -> addBook(loginResponseData, book));
        step("Check book visible on profile page", () -> {
            profilePage.removeBanner();
            profilePage.checkGitBookGuideVisible();
        });
        step("Delete book", () -> {
            deleteBook(loginResponseData, book.getIsbn());
            profilePage.removeBanner();
            profilePage.openProfilePage();
        });
        step("Check book not visible on profile page", profilePage::checkGitBookGuideNotVisible);
    }
}
