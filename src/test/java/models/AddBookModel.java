package models;

import lombok.Data;

import java.util.List;

@Data
public class AddBookModel {
    private String userId;
    private List<CollectionOfIsbnModel> collectionOfIsbns;
}
