package com.qa.bae.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.bae.domain.Food;
import com.qa.bae.domain.User;
import com.qa.bae.repo.UserRepository;

@RunWith(SpringRunner.class)
public class UserServiceUnitTest {
	
	@InjectMocks
	private UserService service;
	
	@Mock
	private UserRepository repo;
	
	private List<User> userList;
	
	private User testUser;

	private User testUserWithId;

	final long id = 1L;
	
	@Before
	public void start() {
		this.userList = new ArrayList<>();
		this.userList.add(testUser);
		this.testUser = new User("test name", "test email", "test password", 1); 
		this.testUserWithId = new User(testUser.getName(), testUser.getEmail(), 
				testUser.getPassword(), testUser.getAdminCode());
		this.testUserWithId.setId(id);
	}
	
	@Test
	public void createUserTest() {
		when(this.repo.save(testUser)).thenReturn(testUserWithId);

		assertEquals(this.testUserWithId, this.service.addNewUser(testUser));

		verify(this.repo, times(1)).save(this.testUser);
	}
	
	@Test
	public void deleteUserTest() throws UserNotFoundException {
		when(this.repo.existsById(id)).thenReturn(true, false);

		this.service.deleteUser(id);

		verify(this.repo, times(1)).deleteById(id);
	}

	@Test
	public void findUserByIDTest() throws UserNotFoundException {
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testUserWithId));

		assertEquals(this.testUserWithId, this.service.findUserByID(this.id));

		verify(this.repo, times(1)).findById(this.id);
	}
}
