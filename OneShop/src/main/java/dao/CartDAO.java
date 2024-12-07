package dao;

import java.util.List;

import models.Cart;

public interface CartDAO {
	
	void addCart(Cart cart);
	void updateCart(Cart cart);
	void deleteCart(String cartId);
	public boolean isCartExistByUserId(String userId);
	int countCarts();
	Cart getCurrentCartByUserId(String userId);
	Cart getCartById(String cartId);
	Cart getCartByUserId(String userId);
	List<Cart> getAllCarts();
	List<Cart> searchCart(String searchQuery);
}
