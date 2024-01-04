package models;

import lombok.Data;

import java.util.List;

@Data
public class BooksListModel {
    private List<BookModel> books;
}
