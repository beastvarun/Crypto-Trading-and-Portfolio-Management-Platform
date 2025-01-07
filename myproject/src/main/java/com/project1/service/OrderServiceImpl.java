package com.project1.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project1.domain.OrderStatus;
import com.project1.domain.OrderType;
import com.project1.model.Asset;
import com.project1.model.Coin;
import com.project1.model.Order;
import com.project1.model.OrderItem;
import com.project1.model.User;
import com.project1.repository.OrderItemRepository;
import com.project1.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService{

	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private WalletService walletService;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private AssetService assetService;
	@Override
	public Order createOrder(User user, OrderItem orderItem, OrderType oredrOrderType) {
		// TODO Auto-generated method stub
		
		double price =orderItem.getCoin().getCurrentPrice()*orderItem.getQuantity();
		Order order=new Order();
		
		order.setUser(user);
		order.setOrderItem(orderItem);
		order.setOrderType(oredrOrderType);
		order.setPrice(BigDecimal.valueOf(price));
		order.setTimestamp(LocalDateTime.now());
		order.setStatus(OrderStatus.PENDING);
		return orderRepository.save(order);

	}

	@Override
	public Order getOrderById(Long orderId) throws Exception {
		// TODO Auto-generated method stub
		
		return orderRepository.findById(orderId).orElseThrow(()->new Exception("order not found"));
	}
	
	
	

	@Override
	public List<Order> getAllOrdersOfUser(Long userId, OrderType orderType, String assetSymbol) {
		// TODO Auto-generated method stub
		return orderRepository.findByUserId(userId);
	}
	
	
	private OrderItem createOrderItem(Coin coin, double quantity,double byePrice,double sellPrice) {
		OrderItem orderItem=new OrderItem();
		orderItem.setCoin(coin);
		orderItem.setQuantity(quantity);
		orderItem.setBuyPrice(byePrice);
		orderItem.setSellPrice(sellPrice);
		
		return orderItemRepository.save(orderItem);
	}
	
	
	@Transactional
	public Order buyAsset(Coin coin, double quantity,User user)throws Exception {
		if(quantity<=0) {
			throw new Exception("quantity should be >0");
		}
		double buyPrice =coin.getCurrentPrice();
		OrderItem orderItem=createOrderItem(coin, quantity, buyPrice, 0);
		Order order =createOrder(user, orderItem, OrderType.BUY);
		orderItem.setOrder(order);
		
		walletService.payOrderPayment(order, user);
		
		order.setStatus(OrderStatus.SUCCESS);
		order.setOrderType(OrderType.BUY);
		Order savedOrder=orderRepository.save(order);
		
				
		//create asset
	    Asset oldAsset =assetService.findAssetByUserIdAndCoinId(order.getUser().getId(), order.getOrderItem().getCoin().getId());
	    if(oldAsset==null) {
	    	assetService.createAsset(user, orderItem.getCoin(), orderItem.getQuantity());
	    }
	    else {
			assetService.updateAsset(oldAsset.getId(), quantity);
		}
	    	
		return savedOrder;
	}
	
	
	
	@Transactional
	public Order sellAsset(Coin coin, double quantity,User user)throws Exception {
		if(quantity<=0) {
			throw new Exception("quantity should be >0");
		}
		double sellPrice =coin.getCurrentPrice();
		
		Asset assetToSell=assetService.findAssetByUserIdAndCoinId(user.getId(), coin.getId());
		double buyPrice=assetToSell.getBuyPrice();
		if(assetToSell!=null) {
			OrderItem orderItem=createOrderItem(coin, quantity, buyPrice, sellPrice);
			
	

		Order order =createOrder(user, orderItem, OrderType.SELL);
		orderItem.setOrder(order);
		
		if(assetToSell.getQuantity()>=quantity) {
			order.setStatus(OrderStatus.SUCCESS);
			order.setOrderType(OrderType.SELL);
			Order savedOrder=orderRepository.save(order);
		walletService.payOrderPayment(order, user);
		Asset updateAsset=assetService.updateAsset(assetToSell.getId(), -quantity);
		
		      if(updateAsset.getQuantity()*coin.getCurrentPrice()<=1) {
			      assetService.deleteAsset(updateAsset.getId());
		    	  
			
		      }
		      return savedOrder;
		
		}
		throw new Exception("insufficent quantity to sell");
		
		}
		throw new Exception("asset not found");
		
	}

	@Override
	@Transactional
	public Order processOrder(Coin coin, double quantity, OrderType orderType, User user) throws Exception {
		// TODO Auto-generated method stub
		if(orderType.equals(OrderType.BUY)) {
			return buyAsset(coin, quantity, user);
		}
		else if(orderType.equals(OrderType.SELL)) {
			return sellAsset(coin, quantity, user);
		}
		throw new Exception("invalid order type");
	}

}
