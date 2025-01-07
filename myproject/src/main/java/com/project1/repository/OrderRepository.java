package com.project1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project1.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
	List<Order> findByUserId(Long Id);

}
