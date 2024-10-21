package dao;

import java.util.List;

import models.Cart;

public interface CartDAO {
	
	void addCart(Cart cart);
	void updateCart(Cart cart);
	void deleteCart(String cartId);
	Cart getCartById(String cartId);
	int countCarts();
	List<Cart> getAllCarts();
}
