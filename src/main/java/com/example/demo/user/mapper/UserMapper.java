package com.example.demo.user.mapper;


import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.model.UserEntity;

public class UserMapper {

	private UserMapper() {
	}

	// DTO → Entity (POST / PUT)
	public static UserEntity toEntity(UserDTO.PostInput dto) {

		if (dto == null) {
			return null;
		}

		return UserEntity.builder().email(dto.getEmail()).fullName(dto.getFullName()).hidden(dto.getHidden()).build();
	}

	// Entity → DTO (GET / POST response)
	public static UserDTO.PostOutput toOutput(UserEntity entity) {
		if (entity == null) {
			return null;
		}

		return UserDTO.PostOutput.builder().id(entity.getId()).email(entity.getEmail()).fullName(entity.getFullName())
				.build();
	}
}