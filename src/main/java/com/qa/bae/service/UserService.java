package com.qa.bae.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.bae.domain.User;
import com.qa.bae.repo.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public List<User> getAllUsers() {
		if (userRepo.findAll().isEmpty()) {
			setUpUsers();
		}
		return userRepo.findAll();
	}
	
	private void setUpUsers() {
		User user = new User();
		userRepo.save(user);
		}
	
	public User addNewUser(User user) {
		return userRepo.save(user);
	}
	
	public User updateUser(User user) {
		return userRepo.save(user);
	}
	
	public String deleteUser(Long id) {
		userRepo.deleteById(id);
		return "User succesfully deleted";
	}
}
