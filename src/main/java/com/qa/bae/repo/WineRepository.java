package com.qa.bae.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.bae.domain.Wine;

@Repository
public interface WineRepository extends JpaRepository<Wine, Long> {

}