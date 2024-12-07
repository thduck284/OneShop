package dao;

import java.util.List;

import models.Shop;

public interface ShopDAO {
	
	void addShop(Shop shop);
	void updateShop(Shop shop);
	void deleteShop(String shopId);
	int countShops();
	Shop getShopById(String shopId);
	List<String> getAllShopId();
	List<String> getAllShopIdByUserId(String userId);
	List<Shop> getAllShops();
	List<Shop> getAllShopsByUserId(String userId);
	List<Shop> searchShop(String keyword);
}
