package com.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project1.model.Coin;

public interface CoinRepository extends JpaRepository<Coin, String>{
	
	

}
