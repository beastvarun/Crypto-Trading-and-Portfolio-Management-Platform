package com.project1.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.ThrowableCauseExtractor;
import org.springframework.stereotype.Service;

import com.project1.domain.OrderType;
import com.project1.model.Order;
import com.project1.model.User;
import com.project1.model.Wallet;
import com.project1.repository.WalletRepository;
@Service
public class WalletServiceImpl implements WalletService{

	
	@Autowired
	WalletRepository walletRepository;
	
	
	
	@Override
	public Wallet getUserWallet(User user) {
		// TODO Auto-generated method stub
		Wallet wallet=walletRepository.findByUserId(user.getId());
		if(wallet==null) {
			wallet=new Wallet();
			wallet.setUser(user);
			
		}
		return wallet;
	}

	@Override
	public Wallet addBalance(Wallet wallet, Long money) {
		// TODO Auto-generated method stub
		BigDecimal balance=wallet.getBalance();
		BigDecimal newBalance=balance.add(BigDecimal.valueOf(money));
		wallet.setBalance(newBalance);
		return walletRepository.save(wallet);
	}

	@Override
	public Wallet findWalletByIdt(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<Wallet> wallet=walletRepository.findById(id);
		if(wallet.isPresent()) {
			return wallet.get();
		}
		throw new Exception("Wallet not found");
	}

	@Override
	public Wallet walletToWalletTranssfer(User sender, Wallet receiverWallet, Long amount) throws Exception {
		// TODO Auto-generated method stub
		Wallet senderWallet=getUserWallet(sender);
		if(senderWallet.getBalance().compareTo(BigDecimal.valueOf(amount))<0) {
			throw new Exception("Insufficent Balance");
		}
		BigDecimal senderBalance=senderWallet.getBalance().subtract(BigDecimal.valueOf(amount));
		senderWallet.setBalance(senderBalance);
		walletRepository.save(senderWallet);
		
		
		
		BigDecimal receiverBalance=receiverWallet.getBalance().add(BigDecimal.valueOf(amount));
		receiverWallet.setBalance(receiverBalance);
		walletRepository.save(receiverWallet);
				
		return senderWallet;
	}

	@Override
	public Wallet payOrderPayment(Order order, User user) throws Exception {
		// TODO Auto-generated method stub
		Wallet wallet=getUserWallet(user);
		if(order.getOrderType().equals(OrderType.BUY))
		{
			BigDecimal newBalance=wallet.getBalance().subtract(order.getPrice());
			if(newBalance.compareTo(order.getPrice())<0) {
				throw new Exception("Insufficient Funds for this transaction");
			}
			wallet.setBalance(newBalance);
		}
		else {
			BigDecimal newBalance= wallet.getBalance().add(order.getPrice());
			wallet.setBalance(newBalance);
		}
		walletRepository.save(wallet);
		
		return wallet;
	}

}
