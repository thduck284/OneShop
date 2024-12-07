package serviceImpl;

import java.util.List;

import dao.ShopDAO;
import daoImpl.ShopDAOImpl;
import models.Shop;
import service.ShopService;

public class ShopServiceImpl implements ShopService{
	
	private ShopDAO shopDAO = new ShopDAOImpl();

	@Override
	public void addShop(Shop shop) {
		// TODO Auto-generated method stub
		shopDAO.addShop(shop);
	}

	@Override
	public void updateShop(Shop shop) {
		// TODO Auto-generated method stub
		shopDAO.updateShop(shop);
	}

	@Override
	public void deleteShop(String shopId) {
		// TODO Auto-generated method stub
		shopDAO.deleteShop(shopId);
	}
	
	@Override
	public int countShops() {
		// TODO Auto-generated method stub
		return shopDAO.countShops();
	}

	@Override
	public Shop getShopById(String shopId) {
		// TODO Auto-generated method stub
		return shopDAO.getShopById(shopId);
	}
	
	@Override
	public List<String> getAllShopId() {
		// TODO Auto-generated method stub
		return shopDAO.getAllShopId();
	}
	
	@Override
	public List<String> getAllShopIdByUserId(String userId) {
		// TODO Auto-generated method stub
		return shopDAO.getAllShopIdByUserId(userId);
	}

	@Override
	public List<Shop> getAllShops() {
		// TODO Auto-generated method stub
		return shopDAO.getAllShops();
	}

	@Override
	public List<Shop> getAllShopsByUserId(String userId) {
		// TODO Auto-generated method stub
		return shopDAO.getAllShopsByUserId(userId);
	}

	@Override
	public List<Shop> searchShop(String keyword) {
		// TODO Auto-generated method stub
		return shopDAO.searchShop(keyword);
	}
}
