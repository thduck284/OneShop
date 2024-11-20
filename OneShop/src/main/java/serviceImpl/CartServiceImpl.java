package serviceImpl;

import java.util.List;

import dao.CartDAO;
import daoImpl.CartDAOImpl;
import models.Cart;
import service.CartService;

public class CartServiceImpl implements CartService{

	CartDAO cartDAO = new CartDAOImpl();
	
	@Override
	public void addCart(Cart cart) {
		cartDAO.addCart(cart);
	}

	@Override
	public void updateCart(Cart cart) {
		cartDAO.updateCart(cart);
	}

	@Override
	public void deleteCart(String cartId) {
		cartDAO.deleteCart(cartId);
	}

	@Override
	public Cart getCartById(String cartId) {
		return cartDAO.getCartById(cartId);
	}

	@Override
	public int countCarts() {
		return cartDAO.countCarts();
	}

	@Override
	public List<Cart> getAllCarts() {
		return cartDAO.getAllCarts();
	}

	@Override
	public boolean isCartExistByUserId(String userId) {
		// TODO Auto-generated method stub
		return cartDAO.isCartExistByUserId(userId);
	}

}
