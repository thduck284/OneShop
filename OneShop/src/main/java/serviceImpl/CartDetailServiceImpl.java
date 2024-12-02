package serviceImpl;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import dao.CartDetailDAO;
import daoImpl.CartDetailDAOImpl;
import models.CartDetail;
import service.CartDetailService;

public class CartDetailServiceImpl implements CartDetailService{
	
	CartDetailDAO cartDetailDAO = new CartDetailDAOImpl();
	
	@Override
	public void addCartDetail(CartDetail cartDetail) {
		// TODO Auto-generated method stub
		cartDetailDAO.addCartDetail(cartDetail);
	}

	@Override
	public void updateCartDetail(CartDetail cartDetail) {
		// TODO Auto-generated method stub
		cartDetailDAO.updateCartDetail(cartDetail);
	}

	@Override
	public void deleteCartDetail(String cartId, String productId) {
		// TODO Auto-generated method stub
		cartDetailDAO.deleteCartDetail(cartId, productId);
	}

	@Override
	public int countCartDetail() {
		// TODO Auto-generated method stub
		return cartDetailDAO.countCartDetail();
	}
	
	@Override
	public CartDetail getCartDetailById(String cartId, String productId) {
		// TODO Auto-generated method stub
		return cartDetailDAO.getCartDetailById(cartId, productId);
	}
	
	@Override
	public List<CartDetail> getCartDetailByCartId(String cartId) {
		// TODO Auto-generated method stub
		return cartDetailDAO.getCartDetailByCartId(cartId);
	}

	@Override
	public List<CartDetail> getAllCartDetail() {
		// TODO Auto-generated method stub
		return cartDetailDAO.getAllCartDetail();
	}

	@Override
	public DefaultTableModel getCartDetailsTable(String cartId) {
		// TODO Auto-generated method stub
		return cartDetailDAO.getCartDetailsTable(cartId);
	}

	@Override
	public DefaultTableModel getAllCartDetailByUserId(String userId) {
		// TODO Auto-generated method stub
		return cartDetailDAO.getAllCartDetailByUserId(userId);
	}
}
