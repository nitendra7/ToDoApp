package com.example.todoapp.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class ToDoRequestDTO {
    @NotBlank(message = "Title cannot be empty")
    private String title;

    private String description;

    private boolean completed;

    private String createdAt;
    private String updatedAt;
}
