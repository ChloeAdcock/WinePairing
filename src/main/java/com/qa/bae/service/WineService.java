package com.qa.bae.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.bae.domain.Wine;
import com.qa.bae.repo.WineRepository;

@Service
public class WineService {
	
	private WineRepository wineRepo;

	public WineService(WineRepository wineRepo) {
		this.wineRepo = wineRepo;
	}
	
	public List<Wine> getAllWine() {
		return wineRepo.findAll();
	}
	
	public Wine addNewWine(Wine wine) {
		return wineRepo.save(wine);
	}
	
	public String deleteWine(Long id) {
		wineRepo.deleteById(id);
		return "Wine succesfully deleted";
	}
}
