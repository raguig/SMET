package com.example.demo.user.service;

import java.util.List;

import com.example.demo.user.model.UserEntity;
import com.example.demo.user.persistence.UserRepository;
import org.springframework.stereotype.Service;



@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserEntity> getUsers() {
		return userRepository.findAll();
	}

	public UserEntity getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	}

	public UserEntity cerateUser(UserEntity user) {
		return userRepository.save(user);
	}

	public UserEntity updateUser(Long id, UserEntity user) {
		UserEntity existing = getUserById(id);
		existing.setFullName(user.getFullName());
		existing.setEmail(user.getEmail());
		return userRepository.save(existing);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
