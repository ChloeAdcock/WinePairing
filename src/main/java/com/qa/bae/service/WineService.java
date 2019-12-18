package com.qa.bae.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.bae.domain.Wine;
import com.qa.bae.repo.WineRepository;

@Service
public class WineService {
	
	private WineRepository wineRepo;

	public WineService(WineRepository wineRepo) {
		super();
		this.wineRepo = wineRepo;
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
		this.wineRepo.deleteById(id);
		return this.wineRepo.existsById(id);
	}
	
	public Wine findWineById(Long id) throws WineNotFoundException {
		return this.wineRepo.findById(id).orElseThrow(
				() -> new WineNotFoundException());
	}
	
	public Wine updateWine(Wine wine, Long id) throws WineNotFoundException {
		Wine toUpdate = findWineById(id);
		toUpdate.setName(wine.getName());
		toUpdate.setGrape(wine.getGrape());
		toUpdate.setDescription(wine.getDescription());
		toUpdate.setTastingNotes(wine.getTastingNotes());		
		toUpdate.setLikes(wine.getLikes());
		return this.wineRepo.save(toUpdate);
	}
}
