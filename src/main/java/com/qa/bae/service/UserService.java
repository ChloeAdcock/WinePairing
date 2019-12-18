package com.qa.bae.service;

import org.springframework.stereotype.Service;

import com.qa.bae.domain.User;
import com.qa.bae.repo.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	public User addNewUser(User user) {
		return userRepo.save(user);
	}
	
	public boolean deleteUser(Long id) throws UserNotFoundException {
		if (!this.userRepo.existsById(id)) {
			throw new UserNotFoundException();
		}
		this.userRepo.deleteById(id);
		return this.userRepo.existsById(id);
	}
	
	public User findUserByID(Long id) throws UserNotFoundException {
		return this.userRepo.findById(id).orElseThrow(
				() -> new UserNotFoundException());
	}
}
