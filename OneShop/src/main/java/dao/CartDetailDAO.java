package dao;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import models.CartDetail;

public interface CartDetailDAO {
	
	void addCartDetail(CartDetail cartDetail);
	void updateCartDetail(CartDetail cartDetail);
	void deleteCartDetail(String cartId, String productId);
	int countCartDetail();
	CartDetail getCartDetailById(String cartId, String productId);
	List<CartDetail> getCartDetailByCartId(String cartId);
	List<CartDetail> getAllCartDetail();
	DefaultTableModel getAllCartDetailByUserId(String userId);
	DefaultTableModel getCartDetailsTable(String cartId);
}
