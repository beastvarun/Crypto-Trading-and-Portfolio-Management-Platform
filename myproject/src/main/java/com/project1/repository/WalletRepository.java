package com.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project1.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long>{
	
	Wallet findByUserId(Long userId);

}
