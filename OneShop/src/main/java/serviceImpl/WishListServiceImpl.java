package serviceImpl;

import java.util.List;

import dao.WishListDAO;
import daoImpl.WishListDAOImpl;
import models.WishList;
import service.WishListService;

public class WishListServiceImpl implements WishListService{

	private WishListDAO wishListDAO = new WishListDAOImpl();
	
	@Override
	public void addWishList(WishList wishList) {
		// TODO Auto-generated method stub
		wishListDAO.addWishList(wishList);
	}

	@Override
	public void deleteWishList(String userId, String productId) {
		// TODO Auto-generated method stub
		wishListDAO.deleteWishList(userId, productId);
	}

	@Override
	public boolean checkWishList(String userId, String productId) {
		// TODO Auto-generated method stub
		return wishListDAO.checkWishList(userId, productId);
	}

	@Override
	public List<String> getAllWishListByUserId(String userId) {
		// TODO Auto-generated method stub
		return wishListDAO.getAllWishListByUserId(userId);
	}

}
