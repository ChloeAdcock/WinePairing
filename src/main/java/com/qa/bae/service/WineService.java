package com.qa.bae.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.bae.domain.Wine;
import com.qa.bae.repo.WineRepository;
import com.qa.bae.service.FoodService;

@Service
public class WineService {

	private WineRepository wineRepo;
	
	private FoodService foodService;

	public WineService(WineRepository wineRepo, FoodService foodService) {
		super();
		this.wineRepo = wineRepo;
		this.foodService = foodService;
	}

	public List<Wine> getAllWine() {
		return wineRepo.findAll();
	}

	public Wine addNewWine(Wine wine) {
		return wineRepo.save(wine);
	}

	public boolean deleteWine(Long id) throws WineNotFoundException {
		if (!this.wineRepo.existsById(id)) {
			throw new WineNotFoundException();
		}
		this.foodService.getAllFood().stream().filter(f -> f.getWine() != null && f.getWine().getId().equals(id))
				.forEach(f -> f.setWine(null));
		this.wineRepo.deleteById(id);
		return this.wineRepo.existsById(id);
	}

	public Wine findWineById(Long id) throws WineNotFoundException {
		return this.wineRepo.findById(id).orElseThrow(() -> new WineNotFoundException());
	}

	public Wine updateWine(Wine wine) {
		   wine.setLikes(wine.getLikes()+1);
		   return this.wineRepo.saveAndFlush(wine);
		}
}
