package com.qa.bae.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.bae.domain.Food;
import com.qa.bae.service.FoodNotFoundException;
import com.qa.bae.service.FoodService;

@RestController
@RequestMapping("/food")
public class FoodController {

	private FoodService foodService;
	
	public FoodController(FoodService foodService) {
		super();
		this.foodService = foodService;
	}
	
	@GetMapping("/getFoods")
	public List<Food> getAllFoods() {
		return foodService.getAllFood();
	}
	
	@PostMapping("/addFood")
	public Food addNewFood(@RequestBody Food food) {
		return foodService.addNewFood(food);
	}
	
	@DeleteMapping("/deleteFood/{id}")
	public void deleteFood(@PathVariable Long id) throws FoodNotFoundException {
		this.foodService.deleteFood(id);
	}

	@GetMapping("/getFood/{id}")
	public Food getFood(@PathVariable Long id) throws FoodNotFoundException {
		return this.foodService.findFoodByID(id);
	}
	
	@PutMapping("/updateFood")
	public Food updateFood(@PathParam("id") Long id, @RequestBody Food food) throws FoodNotFoundException {
		return this.foodService.updateFood(food, id);
	}
}
