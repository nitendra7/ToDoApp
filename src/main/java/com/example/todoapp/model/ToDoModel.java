package com.example.todoapp.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")
public class ToDoModel {

    @Id
    private String id;

    private String title;
    private String description;
    private boolean completed;
    private String createdAt;
    private String updatedAt;
}
