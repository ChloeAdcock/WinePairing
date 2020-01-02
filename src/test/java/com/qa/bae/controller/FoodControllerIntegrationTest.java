package com.qa.bae.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.qa.bae.domain.Food;
import com.qa.bae.repo.FoodRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FoodControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private FoodRepository foodRepo;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private long id;
	
	private Food testFood;
	
	private Food testFoodWithID;
	
	@Before
	public void init() {
		this.foodRepo.deleteAll();

		this.testFood = new Food("test name", "test allergens", "test description", 1); 
		this.testFoodWithID = this.foodRepo.save(this.testFood);
		this.id = this.testFoodWithID.getId();
	}
	
	@Test
	public void testGetAllFoods() throws Exception {
		List<Food> foodList = new ArrayList<>();
		foodList.add(this.testFoodWithID);

		String content = this.mock.perform(request(HttpMethod.GET, "/winepairingapp/getWines").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(foodList), content);
	}
	
	@Test
	public void testAddNewFood() throws Exception {
		String result = this.mock
				.perform(request(HttpMethod.POST, "/winepairingapp/addFood").contentType(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(testFood)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		assertEquals(this.mapper.writeValueAsString(testFoodWithID), result);
	}
	
	@Test
	public void testDeleteFood() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/winepairingapp/deleteFood/" + this.id)).andExpect(status().isOk());
	}
	
	@Test
	public void testGetFood() throws Exception {
		this.mock.perform(request(HttpMethod.GET, "/winepairingapp/getFood/" + this.id)).andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateFood() throws Exception {
		Food newFood = new Food("test name", "test allergens", "test description", 1); 
		Food updatedFood = new Food(newFood.getName(), newFood.getAllergens(), newFood.getDescription(), 
				newFood.getLikes());
		updatedFood.setId(this.id);

		String result = this.mock
				.perform(request(HttpMethod.PUT, "/winepairingapp/updateFood/?id=" + this.id).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newFood)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		assertEquals(this.mapper.writeValueAsString(updatedFood), result);
	}

}
