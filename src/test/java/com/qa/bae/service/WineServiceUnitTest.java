package com.qa.bae.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.el.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.bae.domain.Food;
import com.qa.bae.domain.Wine;
import com.qa.bae.repo.WineRepository;

@RunWith(MockitoJUnitRunner.class)
public class WineServiceUnitTest {
	
	@InjectMocks
	private WineService service;
	
	@Mock
	private FoodService foodService;
	
	@Mock
	private WineRepository repo;
	
	private List<Wine> wineList;
	private List<Food> foodList;
	
	private Wine testWine;
	private Food testFood;

	private Wine testWineWithId;
	private Food testFoodWithId;

	final long id = 1L;
	final long foodId = 1L;
	
	
	@Before
	public void start() {
		
		this.wineList = new ArrayList<>();
		this.wineList.add(testWine);
		this.testWine = new Wine("test name", "test grape", "test description", "test tasting", 1);
		this.testWineWithId = new Wine(testWine.getName(), testWine.getGrape(), 
				testWine.getDescription(), testWine.getTastingNotes(), testWine.getLikes());
		this.testWineWithId.setId(id);
		
		this.testFood = new Food("test name", "test allergens", "test description", 1); 
		this.foodList = new ArrayList<>();
		this.foodList.add(testFood);
		this.testFoodWithId = new Food(testFood.getName(), testFood.getAllergens(), 
				testFood.getDescription(), testFood.getLikes());
		this.testFoodWithId.setId(foodId);
	}
	
	@Test
	public void createWineTest() {
		
		when(this.repo.save(testWine)).thenReturn(testWineWithId);

		assertEquals(this.testWineWithId, this.service.addNewWine(testWine));

		verify(this.repo, times(1)).save(this.testWine);
	}
	
	@Test
	public void deleteWineTest() throws WineNotFoundException {
		when(this.repo.existsById(id)).thenReturn(true, false);
		when(this.foodService.getAllFood()).thenReturn(this.foodList);
		this.service.deleteWine(id);

		verify(this.repo, times(1)).deleteById(id);
	}
	
	@Test
	public void getAllWineTest() {

		when(repo.findAll()).thenReturn(this.wineList);

		assertFalse("Controller has found no wines", this.service.getAllWine().isEmpty());

		verify(repo, times(1)).findAll();
	}
	
	@Test
	public void findWineByIDTest() throws WineNotFoundException {
		
		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testWineWithId));

		assertEquals(this.testWineWithId, this.service.findWineById(this.id));

		verify(this.repo, times(1)).findById(this.id);
	}
	
	@Test
	public void updateWineTest() throws WineNotFoundException {
		
	   when(this.repo.findById(this.testWine.getId())).thenReturn(Optional.of(this.testWineWithId));
	   when(this.repo.saveAndFlush(testWineWithId)).thenReturn(testWineWithId);
	   assertEquals((testWineWithId.getLikes())+1, this.service.updateWine(testWine.getId()).getLikes());
	   verify(this.repo, times(1)).saveAndFlush(testWineWithId);
	}
}

