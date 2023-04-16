package ru.antoncharov.messenger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
public class Message {
    @Id
    private String id;

    @DBRef
    private User sender;

    @DBRef
    private List<User> recipients;

    private String content;
}
