package com.project1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project1.model.Coin;
import com.project1.service.CoinService;

@RestController
@RequestMapping("/coins")
class CoinController {
	@Autowired
	private CoinService coinService;
	@Autowired
	private ObjectMapper objectMapper;
	
	@GetMapping("/")
	ResponseEntity<List<Coin>> getCoinsList(@RequestParam("page") int page) throws Exception{
		List<Coin> coins=coinService.getCoinsList(page);
		return new ResponseEntity<>(coins,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{coinId}/chart")
	ResponseEntity<JsonNode> getMarketChart(@PathVariable String coinId, @RequestParam ("days")int days)
			throws Exception{
		String res=coinService.getMarketChart(coinId,days);
		JsonNode jsonNode=objectMapper.readTree(res);
		return new ResponseEntity<>(jsonNode,HttpStatus.ACCEPTED);
	}
	
	
	
	@GetMapping("/search")
	ResponseEntity<JsonNode> searchCoin(@RequestParam("q")String keyword) throws Exception{
		String coin=coinService.searchCoin(keyword);
		JsonNode jsonNode=objectMapper.readTree(coin);
		return ResponseEntity.ok(jsonNode);
	}
	
	
	@GetMapping("/top50")
	ResponseEntity<JsonNode> getTop50CoinBtMarketCapRank() throws Exception{
		String coin=coinService.getTop50CoinsByMarketCapRank();
		JsonNode jsonNode=objectMapper.readTree(coin);
		return ResponseEntity.ok(jsonNode);
	}
	
	@GetMapping("/treading")
	ResponseEntity<JsonNode> getTreadingCoin() throws Exception{
		String coin=coinService.getTreadingCoins();
		JsonNode jsonNode=objectMapper.readTree(coin);
		return ResponseEntity.ok(jsonNode);
	}
	
	
	@GetMapping("/details/{coinId}")
	ResponseEntity<JsonNode> getCoinDetails(@PathVariable String coinId) throws Exception{
		String coin=coinService.getCoinDetails(coinId);
		JsonNode jsonNode=objectMapper.readTree(coin);
		return ResponseEntity.ok(jsonNode);
		
	}
	
	
	
	
	
	
	
	
	

}
