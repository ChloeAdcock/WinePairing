package com.qa.bae.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

import com.qa.bae.domain.Food;
import com.qa.bae.domain.Wine;
import com.qa.bae.service.FoodNotFoundException;
import com.qa.bae.service.FoodService;
import com.qa.bae.service.WineNotFoundException;

@RunWith(SpringRunner.class)
public class FoodControllerUnitTest {
	
	@InjectMocks
	private FoodController controller;
	
	@Mock
	private FoodService service;
	
	private List<Food> foodList;
	
	private Food testFood;
	private Wine testWine;

	private Food testFoodWithId;
	private Wine testWineWithId;

	final long id = 1L;
	final long wineId = 1L;
	
	@Before
	public void start() {
		this.foodList = new ArrayList<>();
		this.foodList.add(testFood);
		this.testFood = new Food("test name", "test allergens", "test description", 1); 
		this.testWine = new Wine("test name", "test grape", "test description", "test tasting", 1);
		this.testFoodWithId = new Food(testFood.getName(), testFood.getAllergens(), 
				testFood.getDescription(), testFood.getLikes());
		this.testWineWithId = new Wine(testWine.getName(), testWine.getGrape(), 
				testWine.getDescription(), testWine.getTastingNotes(), testWine.getLikes());
		this.testWineWithId.setId(id);
		this.testFoodWithId.setId(id);
	}
	
	@Test
	public void createFoodTest() throws WineNotFoundException {
		when(this.service.addNewFood(testFood)).thenReturn(testFoodWithId);

		assertEquals(this.testFoodWithId, this.controller.addNewFood(testFood, this.wineId));

		verify(this.service, times(1)).addNewFood(this.testFood);
	}
	
	@Test
	public void deleteFoodTest() throws FoodNotFoundException {
		this.controller.deleteFood(id);

		verify(this.service, times(1)).deleteFood(id);
	}
	
	@Test
	public void getAllFoodTest() {

		when(service.getAllFood()).thenReturn(this.foodList);

		assertFalse("Controller has found no foods", this.controller.getAllFoods().isEmpty());

		verify(service, times(1)).getAllFood();
	}
	
	@Test
	public void findFoodByIDTest() throws FoodNotFoundException {
		when(this.service.findFoodByID(this.id)).thenReturn(this.testFoodWithId);

		assertEquals(this.testFoodWithId, this.controller.getFood(this.id));

		verify(this.service, times(1)).findFoodByID(this.id);
	}
	
	@Test
	public void updateFoodsTest() throws FoodNotFoundException {
		
		Food newFood = new Food("Food Name", "Allergens", "Description", 2);
		Food updatedFood = new Food(newFood.getName(), newFood.getAllergens(), newFood.getDescription(), 
				newFood.getLikes());
		updatedFood.setId(this.id);

		when(this.service.updateFood(newFood, this.id)).thenReturn(updatedFood);

		assertEquals(updatedFood, this.controller.updateFood(this.id, newFood));

		verify(this.service, times(1)).updateFood(newFood, this.id);
	}
}
