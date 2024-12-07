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
		// TODO Auto-generated method stub
		cartDAO.addCart(cart);
	}

	@Override
	public void updateCart(Cart cart) {
		// TODO Auto-generated method stub
		cartDAO.updateCart(cart);
	}

	@Override
	public void deleteCart(String cartId) {
		// TODO Auto-generated method stub
		cartDAO.deleteCart(cartId);
	}
	
	@Override
	public boolean isCartExistByUserId(String userId) {
		// TODO Auto-generated method stub
		return cartDAO.isCartExistByUserId(userId);
	}

	@Override
	public int countCarts() {
		return cartDAO.countCarts();
	}
	
	@Override
	public Cart getCurrentCartByUserId(String userId) {
		// TODO Auto-generated method stub
		return cartDAO.getCurrentCartByUserId(userId);
	}
	
	@Override
	public Cart getCartById(String cartId) {
		// TODO Auto-generated method stub
		return cartDAO.getCartById(cartId);
	}
	
	@Override
	public Cart getCartByUserId(String userId) {
		// TODO Auto-generated method stub
		return cartDAO.getCartByUserId(userId);
	}

	@Override
	public List<Cart> getAllCarts() {
		// TODO Auto-generated method stub
		return cartDAO.getAllCarts();
	}

	@Override
	public List<Cart> searchCart(String searchQuery) {
		// TODO Auto-generated method stub
		return cartDAO.searchCart(searchQuery);
	}
}
