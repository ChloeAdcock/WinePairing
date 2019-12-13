package com.qa.bae.service;

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
	public void deleteWineTest() {
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
}
