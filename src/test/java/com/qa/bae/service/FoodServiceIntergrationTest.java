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
import com.qa.bae.domain.Wine;
import com.qa.bae.repo.FoodRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodServiceIntergrationTest {
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private FoodRepository foodRepo;
	
	private Food testFood;
	
	private Food testFoodWithID;
	
	@Before
	public void init() {
		this.testFood = new Food("Test name", "Test allergens", "Test description", 1);
		this.foodRepo.deleteAll();
		this.testFoodWithID = this.foodRepo.save(this.testFood);
	}
	
	@Test
	public void testGetAllFoods() {
		assertThat(this.foodService.getAllFood()).isEqualTo(Arrays.asList(new Food[] { this.testFoodWithID }));
	}
	
	@Test
	public void testAddNewFoods() {
		assertEquals(this.testFoodWithID, this.foodService.addNewFood(testFood));
	}
	
	@Test
	public void testDeleteFood() throws FoodNotFoundException {
		assertThat(this.foodService.deleteFood(this.testFoodWithID.getId())).isFalse();
	}
	
	@Test
	public void testFindFoodByID() throws FoodNotFoundException {
		assertThat(this.foodService.findFoodByID(this.testFoodWithID.getId())).isEqualTo(this.testFoodWithID);
	}
	
	@Test
	public void testUpdateFood() throws FoodNotFoundException {
		Food newFood = new Food("Test name", "Test allergens", "Test description", 1);
		Food updatedFood = new Food();
		updatedFood.setLikes(newFood.getLikes()+1);

		assertThat(this.foodService.updateFood(newFood).getLikes()).isEqualTo(updatedFood.getLikes());
	}
}
