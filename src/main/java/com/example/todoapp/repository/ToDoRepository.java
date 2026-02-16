package com.example.todoapp.repository;

import com.example.todoapp.model.ToDoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDoModel, String> {
}
