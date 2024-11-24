package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.CartDAO;
import models.Cart;

public class CartDAOImpl implements CartDAO{

	@Override
	public void addCart(Cart cart) {
		String sql = "INSERT INTO cart (cartId, userId, fullName, totalPrice, createdDate) VALUES (?, ?, ?, ?, ?)";

	    try (Connection connection = ConnectDB.getConnection();
	    		
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, cart.getCartId());
	        statement.setString(2, cart.getUserId());
	        statement.setString(3, cart.getFullName());
	        statement.setInt(4, cart.getTotalPrice());
	        java.sql.Timestamp currentTimestamp = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
	        statement.setTimestamp(5, currentTimestamp);

	        statement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}

	@Override
	public void updateCart(Cart cart) {
		
		String sql = "UPDATE cart SET userId = ?, userName = ?, totalPrice = ?, createdDate = ? WHERE cartId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, cart.getUserId());
	        statement.setString(2, cart.getFullName());
	        statement.setInt(3, cart.getTotalPrice());
	        statement.setTimestamp(4, java.sql.Timestamp.valueOf(cart.getCreatedDate()));
	        statement.setString(5, cart.getCartId());

	        statement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }		
	}

	@Override
	public void deleteCart(String cartId) {
		
		String sql = "DELETE FROM cart WHERE cartId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, cartId);

	        statement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public boolean isCartExistByUserId(String userId) {
	    String sql = "SELECT COUNT(*) FROM cart WHERE userId = ?";
	    boolean exists = false;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	         
	        statement.setString(1, userId);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            int count = resultSet.getInt(1); 
	            exists = (count > 0); 
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return exists;
	}


	@Override
	public int countCarts() {

		String sql = "SELECT COUNT(*) FROM cart";
	    int count = 0;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            count = resultSet.getInt(1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return count;
	}
	
	@Override
	public Cart getCartById(String cartId) {
		
		String sql = "SELECT * FROM cart WHERE cartId = ?";
	    Cart cart = null;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        
	        statement.setString(1, cartId);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            cart = new Cart(
	                resultSet.getString("cartId"),
	                resultSet.getString("userId"),
	                resultSet.getString("fullName"),
	                resultSet.getInt("totalPrice"),
	                resultSet.getTimestamp("createdDate").toLocalDateTime()
	            );
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return cart;
	}
	
	@Override
	public Cart getCartByUserId(String userId) {
		String sql = "SELECT * FROM cart WHERE userId = ?"; 
	    Cart cart = null;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, userId);
	        
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            cart = new Cart(
	                resultSet.getString("cartId"),
	                resultSet.getString("userId"),
	                resultSet.getString("fullName"),
	                resultSet.getInt("totalPrice"),
	                resultSet.getTimestamp("createdDate").toLocalDateTime()
	            );
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return cart;
	}

	@Override
	public List<Cart> getAllCarts() {
		
		String sql = "SELECT * FROM cart";
	    List<Cart> carts = new ArrayList<>();

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Cart cart = new Cart(
	                resultSet.getString("cartId"),
	                resultSet.getString("userId"),
	                resultSet.getString("fullName"),
	                resultSet.getInt("totalPrice"),
	                resultSet.getTimestamp("createdDate").toLocalDateTime()
	            );
	            carts.add(cart);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return carts;
	}
}
