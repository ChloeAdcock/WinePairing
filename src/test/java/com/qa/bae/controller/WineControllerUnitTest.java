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

import com.qa.bae.domain.Wine;
import com.qa.bae.service.WineNotFoundException;
import com.qa.bae.service.WineService;

@RunWith(SpringRunner.class)
public class WineControllerUnitTest {

	@InjectMocks
	private WineController controller;

	@Mock
	private WineService service;

	private List<Wine> wineList;

	private Wine testWine;

	private Wine testWineWithId;

	final long id = 1L;
	
	@Before
	public void start() {
		
		this.wineList = new ArrayList<>();
		this.wineList.add(testWine);
		this.testWine = new Wine("test name", "test grape", "test description", "test tasting", 1);
		this.testWineWithId = new Wine(testWine.getName(), testWine.getGrape(), 
				testWine.getDescription(), testWine.getTastingNotes(), testWine.getLikes());
		this.testWineWithId.setId(id);
	}
	
	@Test
	public void createWineTest() {
		when(this.service.addNewWine(testWine)).thenReturn(testWineWithId);

		assertEquals(this.testWineWithId, this.controller.addNewWine(testWine));

		verify(this.service, times(1)).addNewWine(this.testWine);
	}
	
	@Test
	public void deleteWineTest() throws WineNotFoundException {
		this.controller.deleteWine(id);

		verify(this.service, times(1)).deleteWine(id);
	}
	
	@Test
	public void getAllWineTest() {

		when(service.getAllWine()).thenReturn(this.wineList);

		assertFalse("Controller has found no wines", this.controller.getAllWines().isEmpty());

		verify(service, times(1)).getAllWine();
	}
	
	@Test
	public void findWineByIDTest() throws WineNotFoundException {
		when(this.service.findWineById(this.id)).thenReturn(this.testWineWithId);

		assertEquals(this.testWineWithId, this.controller.getWine(this.id));

		verify(this.service, times(1)).findWineById(this.id);
	}

	@Test
	public void updateWinesTest() throws WineNotFoundException {
		Wine newWine = new Wine("Wine Name", "Grape", "Description", "Teasting notes", 2);
		Wine updatedWine = new Wine(newWine.getName(), newWine.getGrape(), newWine.getDescription(), 
				newWine.getTastingNotes(), newWine.getLikes());
		updatedWine.setId(this.id);

		when(this.service.updateWine(this.id)).thenReturn(updatedWine);

		assertEquals(updatedWine, this.controller.updateWine(this.id, newWine));

		verify(this.service, times(1)).updateWine(this.id);
	}
}
