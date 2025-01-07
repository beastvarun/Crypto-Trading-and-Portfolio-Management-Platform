package com.project1.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.project1.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{


}
