package com.project1.service;

import java.util.List;

import com.project1.model.Asset;
import com.project1.model.Coin;
import com.project1.model.User;

public interface AssetService {
	 Asset createAsset(User user, Coin coin, double quantity);

	    Asset getAssetById(long assetId) throws Exception;

	    Asset getAssetByUserIdAndId(long userId, long assetId);

	    List<Asset> getUsersAssets(long userId);

	    Asset updateAsset(long assetId, double quantity) throws Exception;

	    Asset findAssetByUserIdAndCoinId(long userId, String coinId);

	    void deleteAsset(long assetId);

}
