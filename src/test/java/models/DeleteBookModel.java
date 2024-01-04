package models;

import lombok.Data;

@Data
public class DeleteBookModel {
    private String isbn;
    private String userId;
}
