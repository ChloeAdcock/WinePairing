package com.qa.bae.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.bae.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}