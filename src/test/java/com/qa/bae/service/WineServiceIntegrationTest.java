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

import com.qa.bae.domain.Wine;
import com.qa.bae.repo.WineRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WineServiceIntegrationTest {

	@Autowired
	private WineService wineService;
	
	@Autowired
	private WineRepository wineRepo;
	
	private Wine testWine;
	
	private Wine testWineWithID;
	
	@Before
	public void init() {
		this.testWine = new Wine("test name", "test grape", "test description", "test tasting", 1);
		this.wineRepo.deleteAll();
		this.testWineWithID = this.wineRepo.save(this.testWine);
	}
	
	@Test
	public void testGetAllWines() {
		assertThat(this.wineService.getAllWine()).isEqualTo(Arrays.asList(new Wine[] { this.testWineWithID }));
	}
	
	@Test
	public void testAddNewWine() {
		assertEquals(this.testWineWithID, this.wineService.addNewWine(testWine));
	}
	
	@Test
	public void testDeleteWine() throws WineNotFoundException {
		assertThat(this.wineService.deleteWine(this.testWineWithID.getId())).isFalse();
	}
	
	@Test
	public void testFindWineByID() throws WineNotFoundException {
		assertThat(this.wineService.findWineById(this.testWineWithID.getId())).isEqualTo(this.testWineWithID);
	}
	
	@Test
	public void testUpdateWine() throws WineNotFoundException {
		Wine newWine = new Wine("test name", "test grape", "test description", "test tasting", 1);
		Wine updatedWine = new Wine(newWine.getName(), newWine.getGrape(), newWine.getDescription(),
				newWine.getTastingNotes(), newWine.getLikes());
		updatedWine.setId(this.testWineWithID.getId());

		assertThat(this.wineService.updateWine(newWine, this.testWineWithID.getId())).isEqualTo(updatedWine);
	}
}
