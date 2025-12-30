package com.example.demo.jwt.service;

import com.example.demo.jwt.dto.AuthDTO;
import com.example.demo.jwt.provider.JwtProvider;
import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.model.UserEntity;
import com.example.demo.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public UserDTO.PostOutput register(UserDTO.PostInput input) {
        // Validate required fields
        if (input.getEmail() == null || input.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (input.getPassword() == null || input.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (input.getFullName() == null || input.getFullName().isEmpty()) {
            throw new IllegalArgumentException("Full name is required");
        }

        // Check if user already exists
        if (userRepository.findByEmail(input.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with this email");
        }

        // Create new user
        UserEntity user = UserEntity.builder()
                .email(input.getEmail())
                .fullName(input.getFullName())
                .password(passwordEncoder.encode(input.getPassword()))
                .hidden(input.getHidden() != null ? input.getHidden() : false)
                .build();

        UserEntity saved = userRepository.save(user);
        return UserMapper.toOutput(saved);
    }

    public AuthDTO.LoginResponse login(AuthDTO.LoginRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtProvider.generateToken(user.getEmail());
        return AuthDTO.LoginResponse.builder()
                .token(token)
                .email(user.getEmail())
                .build();
    }
}