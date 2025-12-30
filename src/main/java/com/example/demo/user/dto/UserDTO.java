package com.example.demo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class UserDTO {

	@Data
	@AllArgsConstructor
	@Builder
	public static class PostInput {

		private String email;
		private String fullName;
        private Boolean hidden;


    }
	
	@Data
    @AllArgsConstructor
    @Builder
    public static class PostOutput {
        private Long id;
        private String email;
        private String fullName;
    }

}
