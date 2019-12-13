package com.qa.bae.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.bae.domain.Food;
import com.qa.bae.repo.FoodRepository;

@Service
public class FoodService {

	private FoodRepository foodRepo;

	public FoodService(FoodRepository foodRepo) {
		this.foodRepo = foodRepo;
	}
	
	public List<Food> getAllFood() {
		if (foodRepo.findAll().isEmpty()) {
			setUpFoods();
		}
		return foodRepo.findAll();
	}
	
	private void setUpFoods() {
		Food food = new Food();
		foodRepo.save(food);
		}
	
	public Food addNewFood(Food food) {
		return foodRepo.save(food);
	}
	
	public String deleteFood(Long id) {
		foodRepo.deleteById(id);
		return "Food succesfully deleted";
	}
}
