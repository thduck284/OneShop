package service;

import java.util.List;

import models.Cart;

public interface CartService {
	
	void addCart(Cart cart);
	void updateCart(Cart cart);
	void deleteCart(String cartId);
	Cart getCartById(String cartId);
	public boolean isCartExistByUserId(String userId);
	int countCarts();
	List<Cart> getAllCarts();
}
