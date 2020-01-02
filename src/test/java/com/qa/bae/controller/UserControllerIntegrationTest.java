package com.qa.bae.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.bae.domain.User;
import com.qa.bae.repo.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private UserRepository userRepo;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private long id;
	
	private User testUser;
	
	private User testUserWithID;
	
	@Before
	public void init() {
		this.userRepo.deleteAll();

		this.testUser = new User("test name", "test email", "test password"); 
		this.testUserWithID = this.userRepo.save(this.testUser);
		this.id = this.testUserWithID.getId();
	}
	
	@Test
	public void testGetUser() throws Exception {
		this.mock.perform(request(HttpMethod.GET, "/winepairingapp/getUser/" + this.id)).andExpect(status().isOk());
	}
	
	@Test
	public void testAddNewUser() throws Exception {
		String result = this.mock
				.perform(request(HttpMethod.POST, "/winepairingapp/addUser").contentType(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(testUser)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		assertEquals(this.mapper.writeValueAsString(testUserWithID), result);
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/winepairingapp/deleteUser/" + this.id)).andExpect(status().isOk());
	}
}
