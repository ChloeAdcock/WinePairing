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
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.bae.domain.Wine;
import com.qa.bae.repo.WineRepository;

@RunWith(SpringRunner.class)
public class WineServiceUnitTest {
	
	@InjectMocks
	private WineService service;
	
	@Mock
	private WineRepository repo;
	
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
		
		when(this.repo.save(testWine)).thenReturn(testWineWithId);

		assertEquals(this.testWineWithId, this.service.addNewWine(testWine));

		verify(this.repo, times(1)).save(this.testWine);
	}
	
	@Test
	public void deleteWineTest() throws WineNotFoundException {
		when(this.repo.existsById(id)).thenReturn(true, false);

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
	
		Wine newWine = new Wine("Wine Name", "Grape", "Description", "Teasting notes", 2);
		Wine updatedWine = new Wine(newWine.getName(), newWine.getGrape(), newWine.getDescription(), 
				newWine.getTastingNotes(), newWine.getLikes());
		updatedWine.setId(this.id);

		when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testWineWithId));
		when(this.repo.save(updatedWine)).thenReturn(updatedWine);

		assertEquals(updatedWine, this.service.updateWine(newWine, this.id));

		verify(this.repo, times(1)).findById(1L);
		verify(this.repo, times(1)).save(updatedWine);
	}
}
