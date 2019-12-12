package com.qa.bae.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.bae.domain.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

}
