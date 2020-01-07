package com.qa.bae.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.bae.domain.User;
import com.qa.bae.repo.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	private User testUser;
	
	private User testUserWithID;
	
	@Before
	public void init() {
		this.testUser = new User("test name", "test email", "test password"); 
		this.userRepo.deleteAll();
		this.testUserWithID = this.userRepo.save(this.testUser);
	}
	
	@Test
	public void testAddNewUser() {
		assertEquals(this.testUserWithID, this.userService.addNewUser(testUser));
	}
	
	@Test
	public void testDeleteUser() throws UserNotFoundException {
		assertThat(this.userService.deleteUser(this.testUserWithID.getId())).isFalse();
	}
	
	@Test
	public void testFindUserByID() throws UserNotFoundException {
		assertThat(this.userService.findUserByID(this.testUserWithID.getId())).isEqualTo(this.testUserWithID);
	}

}
