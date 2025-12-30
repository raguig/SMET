package com.example.demo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PostInput {
        private String email;
        private String fullName;
        private String password;  // ← ADD THIS
        private Boolean hidden;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PostOutput {
        private Long id;
        private String email;
        private String fullName;
    }
}
