package com.qa.bae.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.bae.domain.Food;
import com.qa.bae.repo.FoodRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodServiceIntergrationTest {
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private FoodRepository foodRepo;
	
	private Food testFood;
	
	private Food testFoodWithId;
	
	@Before
	public void init() {
		this.testFood = new Food("Test name", "Test allergens", "Test description", 1);
		this.foodRepo.deleteAll();
		this.testFoodWithId = this.foodRepo.save(this.testFood);
	}
	
	@Test
	public void testGetAllFoods() {
		assertThat(this.foodService.getAllFood()).isEqualTo(Arrays.asList(new Food[] { this.testFoodWithId }));
	}
	
	@Test
	public void testAddNewFoods() {
		assertEquals(this.testFoodWithId, this.foodService.addNewFood(testFood));
	}
	
	@Test
	public void testDeleteFood() throws FoodNotFoundException {
		assertThat(this.foodService.deleteFood(this.testFoodWithId.getId())).isFalse();
	}
	
	@Test
	public void testFindFoodByID() throws FoodNotFoundException {
		assertThat(this.foodService.findFoodByID(this.testFoodWithId.getId())).isEqualTo(this.testFoodWithId);
	}
	
	@Test
	public void testUpdateFood() throws FoodNotFoundException {
		Food newFood = new Food("Test name", "Test allergens", "Test description", 1);
		Food updatedFood = new Food(newFood.getName(), newFood.getAllergens(), newFood.getDescription(),
				newFood.getLikes());
		updatedFood.setId(this.testFoodWithId.getId());

		assertThat(this.foodService.updateFood(newFood, this.testFoodWithId.getId())).isEqualTo(updatedFood);
	}
}
