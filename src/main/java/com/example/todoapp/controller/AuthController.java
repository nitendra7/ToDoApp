package com.example.todoapp.controller;

import com.example.todoapp.dto.LoginRequestDTO;
import com.example.todoapp.dto.RegisterRequestDTO;
import com.example.todoapp.dto.TokenResponseDTO;
import com.example.todoapp.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody LoginRequestDTO dto) {
        return service.login(dto);
    }

    @PostMapping("/register")
    public TokenResponseDTO register(@RequestBody RegisterRequestDTO dto) {
        return service.register(dto);
    }
}
