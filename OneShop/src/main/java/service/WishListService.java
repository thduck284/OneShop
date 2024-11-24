package service;

import java.util.List;

import models.WishList;

public interface WishListService {
	
	public void addWishList(WishList wishList);
	public void deleteWishList(String userId, String productId);
	public boolean checkWishList(String userId, String productId);
	public List<String> getAllWishListByUserId(String userId);
}
