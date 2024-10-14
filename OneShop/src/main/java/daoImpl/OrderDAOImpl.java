package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Order;

public class OrderDAOImpl {
	
	public void addOrder(String userId, double totalPrice, String status, String paymentMethod, String shippingAddress) throws SQLException {
	    String sql = "INSERT INTO order (orderId, userId, totalPrice, status, paymentMethod, shippingAddress, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?)";


	    String orderId = "ĐH" + (countOrders() + 1); 

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, orderId);
	        statement.setString(2, userId);
	        statement.setDouble(3, totalPrice);
	        statement.setString(4, status);
	        statement.setString(5, paymentMethod);
	        statement.setString(6, shippingAddress);
	        
	        statement.setDate(7, new java.sql.Date(System.currentTimeMillis())); 

	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error! Can't add order."); 
	    }
	}
	
	public void updateOrder(String orderId, String userId, double totalPrice, String status, String paymentMethod, String shippingAddress) throws SQLException {
	    String sql = "UPDATE orders SET userId = ?, totalPrice = ?, status = ?, paymentMethod = ?, shippingAddress = ? WHERE orderId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, userId);
	        statement.setDouble(2, totalPrice);
	        statement.setString(3, status);
	        statement.setString(4, paymentMethod);
	        statement.setString(5, shippingAddress);
	        statement.setString(6, orderId);

	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated == 0) {
	            throw new SQLException("No order found with the provided orderId."); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error! Can't update order."); 
	    }
	}

	public void deleteOrder(String orderId) throws SQLException {
	    String sql = "DELETE FROM orders WHERE orderId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, orderId);

	        int rowsDeleted = statement.executeUpdate();
	        if (rowsDeleted == 0) {
	            throw new SQLException("No order found with the provided orderId.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error! Can't delete order."); 
	    }
	}

	
	public int countOrders() throws SQLException {
	    String sql = "SELECT COUNT(*) FROM order";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        if (resultSet.next()) {
	            return resultSet.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error! Can't count orders.");
	    }
	    return 0; 
	}

	public Order getOrderById(String orderId) throws SQLException {
	    String sql = "SELECT * FROM orders WHERE orderId = ?";
	    Order order = null;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, orderId);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            String userId = resultSet.getString("userId");
	            double totalPrice = resultSet.getDouble("totalPrice");
	            String status = resultSet.getString("status");
	            String paymentMethod = resultSet.getString("paymentMethod");
	            String shippingAddress = resultSet.getString("shippingAddress");
	            String createdDate = resultSet.getString("createdDate");

	            order = new Order(orderId, userId, totalPrice, status, paymentMethod, shippingAddress, createdDate);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error! Can't retrieve order by orderId.");
	    }

	    return order;
	}

	public List<Order> getAllOrders() throws SQLException {
	    String sql = "SELECT * FROM orders";
	    List<Order> orders = new ArrayList<>();

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
	            String orderId = resultSet.getString("orderId");
	            String userId = resultSet.getString("userId");
	            double totalPrice = resultSet.getDouble("totalPrice");
	            String status = resultSet.getString("status");
	            String paymentMethod = resultSet.getString("paymentMethod");
	            String shippingAddress = resultSet.getString("shippingAddress");
	            String createdDate = resultSet.getString("createdDate");

	            Order order = new Order(orderId, userId, totalPrice, status, paymentMethod, shippingAddress, createdDate);
	            orders.add(order);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error! Can't retrieve all orders.");
	    }

	    return orders;
	}

}
