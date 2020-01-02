package com.qa.bae.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.bae.domain.User;
import com.qa.bae.service.UserNotFoundException;
import com.qa.bae.service.UserService;

@RunWith(SpringRunner.class)
public class UserControllerUnitTest {

	@InjectMocks
	private UserController controller;
	
	@Mock
	private UserService service;
	
	private List<User> userList;
	
	private User testUser;

	private User testUserWithId;

	final long id = 1L;
	
	@Before
	public void start() {
		this.userList = new ArrayList<>();
		this.userList.add(testUser);
		this.testUser = new User("test name", "test email", "test password"); 
		this.testUserWithId = new User(testUser.getName(), testUser.getEmail(), 
				testUser.getPassword());
		this.testUserWithId.setId(id);
	}
	
	@Test
	public void createUserTest() {
		when(this.service.addNewUser(testUser)).thenReturn(testUserWithId);

		assertEquals(this.testUserWithId, this.controller.addNewUser(testUser));

		verify(this.service, times(1)).addNewUser(this.testUser);
	}
	
	@Test
	public void deleteUserTest() throws UserNotFoundException {
		this.controller.deleteUser(id);

		verify(this.service, times(1)).deleteUser(id);
	}
	
	@Test
	public void findUserByIDTest() throws UserNotFoundException {
		when(this.service.findUserByID(this.id)).thenReturn(this.testUserWithId);

		assertEquals(this.testUserWithId, this.controller.getUser(this.id));

		verify(this.service, times(1)).findUserByID(this.id);
	}
}
