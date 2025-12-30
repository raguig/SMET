package com.example.demo.jwt.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserContext {
    private Long id;
    private String email;
    private String fullName;

    public boolean isAuthenticated() {
        return this.id != null && this.email != null;
    }
}