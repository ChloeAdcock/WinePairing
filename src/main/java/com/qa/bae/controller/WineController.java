package com.qa.bae.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.bae.domain.Wine;
import com.qa.bae.service.WineService;

@RestController
@RequestMapping("/winepairingapp")
public class WineController {
	
	private WineService wineService;
	
	public WineController(WineService wineService) {
		this.wineService = wineService;
	}

	@GetMapping("/wine")
	public List<Wine> getAllWine() {
		return wineService.getAllWine();
	}
	
	@PostMapping("/wine")
	public Wine addNewWine(@RequestBody Wine wine) {
		return wineService.addNewWine(wine);
	}
	
	@DeleteMapping("/wine/{id}")
	public String deleteWine(@PathVariable(value = "id") Long id) {
		return wineService.deleteWine(id);
	}
}
