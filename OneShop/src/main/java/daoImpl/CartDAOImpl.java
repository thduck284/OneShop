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
		// TODO Auto-generated method stub
		String sql = "INSERT INTO cart (cartId, userId, fullName, totalPrice, createdDate, status) VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection connection = ConnectDB.getConnection();
	    		
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, cart.getCartId());
	        statement.setString(2, cart.getUserId());
	        statement.setString(3, cart.getFullName());
	        statement.setInt(4, cart.getTotalPrice());
	        java.sql.Timestamp currentTimestamp = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
	        statement.setTimestamp(5, currentTimestamp);
	        statement.setBoolean(6, cart.getStatus());

	        statement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}

	@Override
	public void updateCart(Cart cart) {
		// TODO Auto-generated method stub
		String sql = "UPDATE cart SET userId = ?, fullName = ?, totalPrice = ?, createdDate = ?, status = ? WHERE cartId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, cart.getUserId());
	        statement.setString(2, cart.getFullName());
	        statement.setInt(3, cart.getTotalPrice());
	        statement.setTimestamp(4, java.sql.Timestamp.valueOf(cart.getCreatedDate()));
	        statement.setInt(5, (cart.getStatus() == true) ? 1 : 0);
	        statement.setString(6, cart.getCartId());

	        statement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }		
	}

	@Override
	public void deleteCart(String cartId) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
	    String sql = "SELECT COUNT(*) FROM cart WHERE userId = ? and status = 0";
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
		// TODO Auto-generated method stub
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
	public Cart getCurrentCartByUserId(String userId) {
		// TODO Auto-generated method stub
	    String sql = "SELECT * FROM cart WHERE userId = ? AND status = ?"; 
	    Cart cart = null;  
	    
	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, userId);
	        statement.setBoolean(2, false);  

	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            cart = new Cart(
	                resultSet.getString("cartId"),
	                resultSet.getString("userId"),
	                resultSet.getString("fullName"),
	                resultSet.getInt("totalPrice"),
	                resultSet.getTimestamp("createdDate").toLocalDateTime(),
	                resultSet.getBoolean("status")
	            );
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return cart;  
	}

	
	@Override
	public Cart getCartById(String cartId) {
		// TODO Auto-generated method stub
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
	                resultSet.getTimestamp("createdDate").toLocalDateTime(),
	                resultSet.getBoolean("status")
	            );
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return cart;
	}
	
	@Override
	public Cart getCartByUserId(String userId) {
		// TODO Auto-generated method stub
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
	                resultSet.getTimestamp("createdDate").toLocalDateTime(),
	                resultSet.getBoolean("status")
	            );
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return cart;
	}

	@Override
	public List<Cart> getAllCarts() {
		// TODO Auto-generated method stub
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
	                resultSet.getTimestamp("createdDate").toLocalDateTime(),
	                resultSet.getBoolean("status")
	            );
	            carts.add(cart);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return carts;
	}

	@Override
	public List<Cart> searchCart(String searchQuery) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM cart WHERE cartId LIKE ? OR userId LIKE ? OR CAST(status AS CHAR) LIKE ?";
	    List<Cart> carts = new ArrayList<>();

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        
	        String query = "%" + searchQuery + "%";
	        statement.setString(1, query);
	        statement.setString(2, query);
	        statement.setString(3, query); 

	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Cart cart = new Cart(
	                resultSet.getString("cartId"),
	                resultSet.getString("userId"),
	                resultSet.getString("fullName"),
	                resultSet.getInt("totalPrice"),
	                resultSet.getTimestamp("createdDate").toLocalDateTime(),
	                resultSet.getBoolean("status")
	            );
	            carts.add(cart);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return carts;
	}
}
