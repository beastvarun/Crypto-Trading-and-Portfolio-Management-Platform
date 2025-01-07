package com.project1.service;

import com.project1.model.Order;
import com.project1.model.User;
import com.project1.model.Wallet;

public interface WalletService {

   Wallet getUserWallet(User user);
   Wallet addBalance(Wallet wallet,Long money);
   Wallet findWalletByIdt(Long id) throws Exception;
   Wallet walletToWalletTranssfer(User sender,Wallet receiverWallet,Long amount) throws Exception;
   Wallet payOrderPayment(Order order, User user) throws Exception;
	
	
}
