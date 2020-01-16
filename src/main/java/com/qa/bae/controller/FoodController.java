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
import com.qa.bae.service.WineNotFoundException;
import com.qa.bae.service.WineService;

@RestController
@RequestMapping("/food")
public class FoodController {

	private FoodService foodService;
	private WineService wineService;
	
	public FoodController(FoodService foodService, WineService wineService) {
		super();
		this.foodService = foodService;
		this.wineService = wineService;
	}
	
	@GetMapping("/getFoods")
	public List<Food> getAllFoods() {
		return foodService.getAllFood();
	}
	
	@PostMapping("/addFood")
	public Food addNewFood(@RequestBody Food food, @PathParam(value = "id") Long id ) throws WineNotFoundException {
		food.setWine(wineService.findWineById(id));
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
		return this.foodService.updateFood(id);
	}
}
