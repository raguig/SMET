package com.example.demo.user.controller;

import com.example.demo.jwt.context.UserContextHolder;

import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.model.UserEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import com.example.demo.user.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO.PostOutput> getAllUsers() {
        return userService.getUsers()
                .stream()
                .map(UserMapper::toOutput)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDTO.PostOutput getUserById(@PathVariable Long id) {
        UserEntity user = userService.getUserById(id);
        return UserMapper.toOutput(user);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO.PostOutput> getCurrentUser() {
        // Use UserContext instead of fetching from DB
        Long userId = UserContextHolder.getUserId();
        UserEntity user = userService.getUserById(userId);
        return ResponseEntity.ok(UserMapper.toOutput(user));
    }

    @PostMapping
    public ResponseEntity<UserDTO.PostOutput> createUser(
            @RequestBody UserDTO.PostInput input) {

        UserEntity entity = UserMapper.toEntity(input);
        UserEntity saved = userService.cerateUser(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserMapper.toOutput(saved));
    }

    @PutMapping("/{id}")
    public UserDTO.PostOutput updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO.PostInput input) {

        UserEntity entity = UserMapper.toEntity(input);
        UserEntity updated = userService.updateUser(id, entity);

        return UserMapper.toOutput(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
