package com.example.todoapp.dto;

public record ToDoResponseDTO(String id, String title, String description, boolean completed, String createdAt, String updatedAt) {
}
