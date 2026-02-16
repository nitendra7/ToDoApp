package com.example.todoapp.controller;

import com.example.todoapp.dto.*;
import com.example.todoapp.service.ToDoService;
import com.example.todoapp.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class ToDoController {

    private final ToDoService service;
    private final JwtUtil jwtUtil;

    public ToDoController(ToDoService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    private void checkToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid Token");
        }
        String token = authHeader.substring(7);
        jwtUtil.validateTokenAndGetEmail(token);
    }

    @GetMapping("/todos")
    public String getTodosPage() {
        return "todos";
    }

    @GetMapping("/api/todos")
    @ResponseBody
    public List<ToDoResponseDTO> getAllToDos(
            @RequestHeader("Authorization") String authHeader) {
        checkToken(authHeader);
        return service.getAllToDos();
    }

    @PostMapping("/api/todos")
    @ResponseBody
    public ToDoResponseDTO addToDo(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @Valid @RequestBody ToDoRequestDTO todo) {
        checkToken(authHeader);
        return service.add(todo);
    }

    @PutMapping("/api/todos/{id}")
    @ResponseBody
    public ToDoResponseDTO updateToDo(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String id,
            @Valid @RequestBody ToDoRequestDTO todo) {
        checkToken(authHeader);
        return service.update(id, todo);
    }

    @DeleteMapping("/api/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteToDo(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String id) {
        checkToken(authHeader);
        service.delete(id);
    }
}
