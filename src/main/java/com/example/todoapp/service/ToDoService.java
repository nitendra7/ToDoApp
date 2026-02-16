package com.example.todoapp.service;

import com.example.todoapp.dto.ToDoRequestDTO;
import com.example.todoapp.dto.ToDoResponseDTO;
import com.example.todoapp.model.ToDoModel;
import com.example.todoapp.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    private final ToDoRepository repo;

    public ToDoService(ToDoRepository repo) {
        this.repo = repo;
    }

    public ToDoResponseDTO add(ToDoRequestDTO todoRequest) {
        ToDoModel todo = new ToDoModel();
        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setCompleted(todoRequest.isCompleted());
        String now = java.time.Instant.now().toString();
        todo.setCreatedAt(now);
        todo.setUpdatedAt(now);
        ToDoModel saved = repo.save(todo);
        return new ToDoResponseDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.isCompleted(),
                saved.getCreatedAt(),
                saved.getUpdatedAt()
        );
    }

    public List<ToDoResponseDTO> getAllToDos() {
        return repo.findAll()
                .stream()
                .map(todo -> new ToDoResponseDTO(
                        todo.getId(),
                        todo.getTitle(),
                        todo.getDescription(),
                        todo.isCompleted(),
                        todo.getCreatedAt(),
                        todo.getUpdatedAt()
                ))
                .toList();
    }

    public ToDoResponseDTO update(String id, ToDoRequestDTO todoRequest) {
        ToDoModel todo = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("ToDo not found"));
        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setCompleted(todoRequest.isCompleted());
        todo.setUpdatedAt(java.time.Instant.now().toString());

        ToDoModel saved = repo.save(todo);
        return new ToDoResponseDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.isCompleted(),
                saved.getCreatedAt(),
                saved.getUpdatedAt()
        );
    }

    public void delete(String id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("ToDo with this ID doesn't exist.");
        }
        repo.deleteById(id);
    }
}
