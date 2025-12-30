package com.example.demo.jwt.service;

import com.example.demo.jwt.dto.AuthDTO;
import com.example.demo.jwt.provider.JwtProvider;
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

    public AuthDTO.LoginResponse login(AuthDTO.LoginRequest request) {
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