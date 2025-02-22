package com.project1.service;

import java.util.List;

import com.project1.model.Coin;

public interface CoinService {
	List<Coin>getCoinsList(int page) throws Exception;
	String getMarketChart(String coinId,  int days) throws Exception;
	String getCoinDetails(String coinId) throws Exception;
	Coin findById(String coinId) throws Exception;
	String searchCoin(String keyword) throws Exception;
	String getTop50CoinsByMarketCapRank() throws Exception;
	String getTreadingCoins() throws Exception;
	

}
