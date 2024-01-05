package api;

import io.restassured.http.Header;
import models.AddBookModel;
import models.BookModel;
import models.BooksListModel;
import models.CollectionOfIsbnModel;
import models.DeleteBookModel;
import models.LoginModel;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static specs.DemoQaSpec.demoQaRequestSpecification;
import static specs.DemoQaSpec.demoQaResponseSpecification;

public class BookApi {

    public static final String BOOK_STORE_V_1_BOOKS = "/BookStore/v1/Books";
    public static final String BOOK_STORE_V_1_BOOK = "/BookStore/v1/Book";

    public static BooksListModel getBooksList() {
        BooksListModel booksListModel = given(demoQaRequestSpecification)
                .when()
                .get(BOOK_STORE_V_1_BOOKS)
                .then()
                .spec(demoQaResponseSpecification)
                .extract().as(BooksListModel.class);
        return booksListModel;
    }

    public static void addBook(LoginModel loginData, BookModel book) {
        CollectionOfIsbnModel collectionOfIsbnFirst = new CollectionOfIsbnModel();
        collectionOfIsbnFirst.setIsbn(book.getIsbn());
        List<CollectionOfIsbnModel> collectionOfIsbnList = new ArrayList<>();
        collectionOfIsbnList.add(collectionOfIsbnFirst);
        AddBookModel requestBody = new AddBookModel();
        requestBody.setUserId(loginData.getUserId());
        requestBody.setCollectionOfIsbns(collectionOfIsbnList);
        given(demoQaRequestSpecification)
                .body(requestBody)
                .header(new Header("Authorization", "Bearer " + loginData.getToken()))
                .when()
                .post(BOOK_STORE_V_1_BOOKS)
                .then()
                .spec(demoQaResponseSpecification);
    }

    public static void deleteAllBooks(LoginModel loginData) {
        given(demoQaRequestSpecification)
                .header(new Header("Authorization", "Bearer " + loginData.getToken()))
                .when()
                .delete(BOOK_STORE_V_1_BOOKS + "?UserId=" + loginData.getUserId())
                .then()
                .spec(demoQaResponseSpecification);
    }

    public static void deleteBook(LoginModel loginData, String isbn) {
        DeleteBookModel requestBody = new DeleteBookModel();
        requestBody.setUserId(loginData.getUserId());
        requestBody.setIsbn(isbn);
        given(demoQaRequestSpecification)
                .header(new Header("Authorization", "Bearer " + loginData.getToken()))
                .body(requestBody)
                .when()
                .delete(BOOK_STORE_V_1_BOOK)
                .then()
                .spec(demoQaResponseSpecification);
    }
}
