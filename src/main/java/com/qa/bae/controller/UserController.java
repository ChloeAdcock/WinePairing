package com.qa.bae.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.bae.domain.User;
import com.qa.bae.service.UserService;

@RestController
@RequestMapping("/winepairingapp")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/user")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping("/user")
	public User addNewUser(@RequestBody User user) {
		return userService.addNewUser(user);
	}
	
	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable(value = "id") Long id) {
		return userService.deleteUser(id);
	}
}
