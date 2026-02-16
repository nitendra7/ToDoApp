package com.example.todoapp.service;

import com.example.todoapp.dto.*;
import com.example.todoapp.model.UserModel;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.util.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repository;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    public TokenResponseDTO login(LoginRequestDTO dto) {
        UserModel user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new TokenResponseDTO(token);
    }

    public TokenResponseDTO register(RegisterRequestDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        UserModel newUser = new UserModel();
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(dto.getPassword());

        repository.save(newUser);

        String token = jwtUtil.generateToken(newUser.getEmail());
        return new TokenResponseDTO(token);
    }
}
