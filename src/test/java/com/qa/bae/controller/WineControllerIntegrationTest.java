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
import com.qa.bae.domain.Wine;
import com.qa.bae.repo.WineRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WineControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private WineRepository wineRepo;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private long id;
	
	private Wine testWine;
	
	private Wine testWineWithID;
	
	@Before
	public void init() {
		this.wineRepo.deleteAll();

		this.testWine = new Wine("test name", "test grape", "test description", "test tasting", 1);
		this.testWineWithID = this.wineRepo.save(this.testWine);
		this.id = this.testWineWithID.getId();
	}
	
	@Test
	public void testGetAllWines() throws Exception {
		List<Wine> wineList = new ArrayList<>();
		wineList.add(this.testWineWithID);

		String content = this.mock.perform(request(HttpMethod.GET, "/wine/getWines").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.mapper.writeValueAsString(wineList), content);
	}
	
	@Test
	public void testAddNewWine() throws Exception {
		String result = this.mock
				.perform(request(HttpMethod.POST, "/wine/addWine").contentType(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(testWine)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		assertEquals(this.mapper.writeValueAsString(testWineWithID), result);
	}
	
	@Test
	public void testDeleteWine() throws Exception {
		this.mock.perform(request(HttpMethod.DELETE, "/wine/deleteWine/" + this.id)).andExpect(status().isOk());
	}
	
	@Test
	public void testGetWine() throws Exception {
		this.mock.perform(request(HttpMethod.GET, "/wine/getWine/" + this.id)).andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateWine() throws Exception {

		Wine updatedWine = new Wine(testWine.getName(), testWine.getGrape(), testWine.getDescription(),
				testWine.getTastingNotes(), testWine.getLikes());
		updatedWine.setLikes(testWine.getLikes()+1);
		updatedWine.setId(this.testWineWithID.getId());

		String result = this.mock
				.perform(request(HttpMethod.PUT, "/wine/updateWine/?id=" + this.id).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(testWine)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();	
		assertEquals(this.mapper.writeValueAsString(updatedWine), result);
	}

}
