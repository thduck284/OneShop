package dao;

import java.util.List;

import models.WishList;

public interface WishListDAO {
	
	public void addWishList(WishList wishList);
	public void deleteWishList(String userId, String productId);
	public boolean checkWishList(String userId, String productId);
	public List<String> getAllWishListByUserId(String userId);
}
