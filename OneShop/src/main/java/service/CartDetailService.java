package service;

import java.util.List;

import models.CartDetail;

public interface CartDetailService {
	
	void addCartDetail(CartDetail cartDetail);
	void updateCartDetail(CartDetail cartDetail);
	void deleteCartDetail(String cartId, String productId);
	int countCartDetail();
	CartDetail getCartDetailById(String cartId, String productId);
	public List<CartDetail> getCartDetailByCartId(String cartId);
	List<CartDetail> getAllCartDetail();
}
