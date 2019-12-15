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
		return foodRepo.findAll();
	}
	
	public Food addNewFood(Food food) {
		return foodRepo.save(food);
	}
	
	public boolean deleteFood(Long id) throws FoodNotFoundException {
		if (!this.foodRepo.existsById(id)) {
			throw new FoodNotFoundException();
		}
		this.foodRepo.deleteById(id);
		return this.foodRepo.existsById(id);
	}
	
	public Food findFoodByID(Long id) throws FoodNotFoundException {
		return this.foodRepo.findById(id).orElseThrow(
				() -> new FoodNotFoundException());
	}
	
	public Food updateFood(Food food, Long id) throws FoodNotFoundException {
		Food toUpdate = findFoodByID(id);
		toUpdate.setName(food.getName());
		toUpdate.setAllergens(food.getAllergens());
		toUpdate.setDescription(food.getDescription());
		toUpdate.setLikes(food.getLikes());
		return this.foodRepo.save(toUpdate);
	}
}

