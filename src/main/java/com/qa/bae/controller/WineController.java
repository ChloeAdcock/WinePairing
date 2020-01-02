package com.qa.bae.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.bae.domain.Wine;
import com.qa.bae.service.WineNotFoundException;
import com.qa.bae.service.WineService;

@RestController
@RequestMapping("/winepairingapp")
public class WineController {
	
	private WineService wineService;
	
	@Autowired
	public WineController(WineService wineService) {
		super();
		this.wineService = wineService;
	}

	@GetMapping("/getWines")
	public List<Wine> getAllWines() {
		return wineService.getAllWine();
	}
	
	@PostMapping("/addWine")
	public Wine addNewWine(@RequestBody Wine wine) {
		return this.wineService.addNewWine(wine);
	}
	
	@DeleteMapping("/deleteWine/{id}")
	public void deleteWine(@PathVariable Long id) throws WineNotFoundException {
		this.wineService.deleteWine(id);
	}

	@GetMapping("/getWine/{id}")
	public Wine getWine(@PathVariable Long id) throws WineNotFoundException {
		return this.wineService.findWineById(id);
	}
	
	@PutMapping("/updateWine")
	public Wine updateWine(@PathParam("id") Long id, @RequestBody Wine wine) throws WineNotFoundException {
		return this.wineService.updateWine(wine, id);
	}
}
