package com.qa.bae.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.bae.domain.User;
import com.qa.bae.service.UserNotFoundException;
import com.qa.bae.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/addUser")
	public User addNewUser(@RequestBody User user) {
		return this.userService.addNewUser(user);
	}

	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable Long id) throws UserNotFoundException {
		this.userService.deleteUser(id);
	}
	
	@GetMapping("/getUser/{id}")
	public User getUser(@PathVariable Long id) throws UserNotFoundException {
		return this.userService.findUserByID(id);
	}
	
}
