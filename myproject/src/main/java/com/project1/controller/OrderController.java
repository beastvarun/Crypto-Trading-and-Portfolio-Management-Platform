package com.project1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project1.domain.OrderType;
import com.project1.model.Coin;
import com.project1.model.Order;
import com.project1.model.User;
import com.project1.request.CreateOrderRequest;
import com.project1.service.CoinService;
import com.project1.service.OrderService;
import com.project1.service.UserService;


@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private OrderService orderService;
	private UserService userService;
	
	@Autowired
	private CoinService coinService;
	
	//@Autowired
	//private WalletTransactionService walletTransactionService;
	
	
	@PostMapping("/pay")
	public ResponseEntity<Order> payOrderPaymnet(@RequestHeader("Authorization") String jwt,@RequestBody CreateOrderRequest req) throws Exception{
		User user=userService.findUserProfileByJwt(jwt);
		Coin coin=coinService.findById(req.getCoinId());
		
		Order order=orderService.processOrder(coin, req.getQuantity(), req.getOrderType()
				, user);
		
	return ResponseEntity.ok(order);
	
	
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrderById(
	    @RequestHeader("Authorization") String jwtToken,
	    @PathVariable Long orderId
	) throws Exception {

	    

	    User user = userService.findUserProfileByJwt(jwtToken);
	    Order order = orderService.getOrderById(orderId);

	    if (order.getUser().getId() == user.getId()) {
	        return ResponseEntity.ok(order);
	    } else {
	        throw new Exception("You don't have access");
	    }
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<Order>> getAllOrdersForUser(
	    @RequestHeader("Authorization") String jwt,
	    @RequestParam(required = false) OrderType order_Type,
	    @RequestParam(required = false) String asset_Symbol
	) throws Exception {

	    Long userId = userService.findUserProfileByJwt(jwt).getId();
	    List<Order> userOrders = orderService.getAllOrdersOfUser(userId, order_Type, asset_Symbol);
	    return ResponseEntity.ok(userOrders);
	}

	

}
