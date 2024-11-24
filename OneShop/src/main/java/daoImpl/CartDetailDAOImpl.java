package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CartDetailDAO;
import models.CartDetail;

public class CartDetailDAOImpl implements CartDetailDAO{
	
	@Override
	public void addCartDetail(CartDetail detailCart) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO cartDetail (cartId, productId, productName, quantity, price) VALUES (?, ?, ?, ?, ?)";
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setString(1, detailCart.getCartId());
	        ps.setString(2, detailCart.getProductId());
	        ps.setString(3, detailCart.getProductName());
	        ps.setInt(4, detailCart.getQuantity());
	        ps.setInt(5, detailCart.getPrice());
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void updateCartDetail(CartDetail detailCart) {		
		// TODO Auto-generated method stub
		String query = "UPDATE cartDetail SET productName = ?, quantity = ?, price = ? WHERE cartId = ? AND productId = ?";
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	    	ps.setString(1, detailCart.getProductName());
	        ps.setInt(2, detailCart.getQuantity());
	        ps.setInt(3, detailCart.getPrice());
	        ps.setString(4, detailCart.getCartId());
	        ps.setString(5, detailCart.getProductId());
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void deleteCartDetail(String cartId, String productId) {		
		// TODO Auto-generated method stub
		String query = "DELETE FROM cartDetail WHERE cartId = ? AND productId = ?";
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setString(1, cartId);
	        ps.setString(2, productId);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public int countCartDetail() {		
		// TODO Auto-generated method stub
		String query = "SELECT COUNT(*) FROM cartDetail";
	    int count = 0;
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return count;
	}
	
	@Override
	public CartDetail getCartDetailById(String cartId, String productId) {		
		// TODO Auto-generated method stub
		String query = "SELECT * FROM cartDetail WHERE cartId = ? AND productId = ?";
	    CartDetail cartDetail = null;
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setString(1, cartId);
	        ps.setString(2, productId);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                cartDetail = new CartDetail(
	                    rs.getString("cartId"),
	                    rs.getString("productId"),
	                    rs.getString("productName"),
	                    rs.getInt("quantity"),
	                    rs.getInt("price")
	                );
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cartDetail;
	}
	
	@Override
	public List<CartDetail> getCartDetailByCartId(String cartId) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM cartDetail WHERE cartId = ?";
	    List<CartDetail> cartDetails = new ArrayList<>();
	    
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	        
	        ps.setString(1, cartId);  
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                CartDetail detailCart = new CartDetail(
	                    rs.getString("cartId"),
	                    rs.getString("productId"),
	                    rs.getString("productName"),
	                    rs.getInt("quantity"),
	                    rs.getInt("price")
	                );
	                cartDetails.add(detailCart);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return cartDetails;
	}

	@Override
	public List<CartDetail> getAllCartDetail() {		
		// TODO Auto-generated method stub
		List<CartDetail> list = new ArrayList<>();
	    String query = "SELECT * FROM cartDetail";
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {
	        while (rs.next()) {
	            CartDetail detailCart = new CartDetail(
	                rs.getString("cartId"),
	                rs.getString("productId"),
	                rs.getString("productName"),
	                rs.getInt("quantity"),
	                rs.getInt("price")
	            );
	            list.add(detailCart);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}
