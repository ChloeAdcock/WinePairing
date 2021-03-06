package com.qa.bae.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.bae.domain.Food;
import com.qa.bae.repo.FoodRepository;

@RunWith(MockitoJUnitRunner.class)
public class FoodServiceUnitTest {

	@InjectMocks
	private FoodService service;
	
	@Mock
	private FoodRepository repo;
	
	private List<Food> foodList;
	
	private Food testFood;

	private Food testFoodWithId;

	final long id = 1L;
	
	@Before
	public void start() {
		this.foodList = new ArrayList<>();
		this.foodList.add(testFood);
		this.testFood = new Food("test name", "test allergens", "test description", 1); 
		this.testFoodWithId = new Food(testFood.getName(), testFood.getAllergens(), 
				testFood.getDescription(), testFood.getLikes());
		this.testFoodWithId.setId(id);
	}
	
	@Test
	public void createFoodTest() {
		when(this.repo.save(testFood)).thenReturn(testFoodWithId);

		assertEquals(this.testFoodWithId, this.service.addNewFood(testFood));

		verify(this.repo, times(1)).save(this.testFood);
	}
	
	@Test
	public void deleteFoodTest() throws FoodNotFoundException {
		when(this.repo.existsById(id)).thenReturn(true, false);

		this.service.deleteFood(id);

		verify(this.repo, times(1)).deleteById(id);
	}
	
	@Test(expected=FoodNotFoundException.class)
	public void deleteFoodNotFound() throws FoodNotFoundException {
		when(this.repo.existsById(0L)).thenReturn(false);
		
		this.service.deleteFood(0L);
	}
	
	@Test(expected=FoodNotFoundException.class)
	public void findFoodNotFound() throws FoodNotFoundException {
		
		this.service.findFoodByID(0L);
	}
	
	@Test
	public void getAllFoodTest() {

		when(repo.findAll()).thenReturn(this.foodList);

		assertFalse("Controller has found no foods", this.service.getAllFood().isEmpty());

		verify(repo, times(1)).findAll();
	}
	
	@Test
	public void findFoodByIDTest() throws FoodNotFoundException {
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testFoodWithId));

		assertEquals(this.testFoodWithId, this.service.findFoodByID(this.id));

		verify(this.repo, times(1)).findById(this.id);
	}
	
	@Test
	public void updateFoodTest() throws FoodNotFoundException {
	
		   when(this.repo.findById(this.testFood.getId())).thenReturn(Optional.of(this.testFoodWithId));
		   when(this.repo.saveAndFlush(testFoodWithId)).thenReturn(testFoodWithId);
		   assertEquals((testFoodWithId.getLikes())+1, this.service.updateFood(testFood.getId()).getLikes());
		   verify(this.repo, times(1)).saveAndFlush(testFoodWithId);
	}
}
