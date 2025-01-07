package com.project1.service;
import java.util.List;

import com.project1.domain.OrderType;
import com.project1.model.Coin;
import com.project1.model.Order;
import com.project1.model.OrderItem;
import com.project1.model.User;

public interface OrderService {
	Order createOrder(User user,OrderItem orderItem,OrderType oredrOrderType);
	Order getOrderById(Long orderId) throws Exception;
	List<Order> getAllOrdersOfUser(Long userId,OrderType orderType,String assetSymbol);
	Order processOrder(Coin coin,double quantity,OrderType orderType,User user) throws Exception;

}
