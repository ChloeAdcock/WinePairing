package com.qa.bae.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.bae.domain.Food;
import com.qa.bae.service.FoodService;

@RestController
@RequestMapping("/winepairingapp")
public class FoodController {

	private FoodService foodService;
	
	public FoodController(FoodService foodService) {
		this.foodService = foodService;
	}
	
	@GetMapping("/food")
	public List<Food> getAllFoods() {
		return foodService.getAllFood();
	}
	
	@PostMapping("/food")
	public Food addNewFood(@RequestBody Food food) {
		return foodService.addNewFood(food);
	}
	
	@DeleteMapping("/food/{id}")
	public String deleteFood(@PathVariable(value = "id") Long id) {
		return foodService.deleteFood(id);
	}
}
