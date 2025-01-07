package com.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project1.model.Asset;
import com.project1.model.Coin;
import com.project1.model.User;
import com.project1.repository.AssetRepository;
@Service
public class AssetServiceImpl implements AssetService{

	@Autowired
	private AssetRepository assetRepository;
	@Override
	public Asset createAsset(User user, Coin coin, double quantity) {
		// TODO Auto-generated method stub
		
		Asset asset=new Asset();
		asset.setUser(user);
		asset.setCoin(coin);
		asset.setQuantity(quantity);
		asset.setBuyPrice(coin.getCurrentPrice());
		
		
		return assetRepository.save(asset);
	}

	@Override
	public Asset getAssetById(long assetId) throws Exception{
		// TODO Auto-generated method stub
		return assetRepository.findById(assetId).orElseThrow(()->new Exception("Asset not found"));
	}

	@Override
	public Asset getAssetByUserIdAndId(long userId, long assetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Asset> getUsersAssets(long userId) {
		// TODO Auto-generated method stub
		return assetRepository.findByUserId(userId);
	}

	@Override
	public Asset updateAsset(long assetId, double quantity) throws Exception {
		// TODO Auto-generated method stub
		Asset oldAsset=getAssetById(assetId);
		oldAsset.setQuantity(quantity+oldAsset.getQuantity());
		return assetRepository.save(oldAsset);
	}

	@Override
	public Asset findAssetByUserIdAndCoinId(long userId, String coinId) {
		// TODO Auto-generated method stub
		return assetRepository.findByUserIdAndCoinId(userId,coinId);
	}

	@Override
	public void deleteAsset(long assetId) {
		// TODO Auto-generated method stub
		assetRepository.deleteById(assetId);
		
	}

}
