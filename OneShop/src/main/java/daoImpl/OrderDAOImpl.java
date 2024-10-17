package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDAO;
import models.Order;

public class OrderDAOImpl implements OrderDAO{
	
	@Override
	public void addOrder(Order order) {
	    String sql = "INSERT INTO order (orderId, userId, totalPrice, status, paymentMethod, shippingAddress, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    


	    String orderId = "ĐH" + (countOrders() + 1); 

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, orderId);
	        statement.setString(2, order.getUserId());
	        statement.setDouble(3, order.getTotalPrice());
	        statement.setString(4, order.getStatus());
	        statement.setString(5, order.getPaymentMethod());
	        statement.setString(6, order.getShippingAddress());
	        
	        statement.setDate(7, new java.sql.Date(System.currentTimeMillis())); 

	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void updateOrder(Order order) {
	    String sql = "UPDATE order SET userId = ?, totalPrice = ?, status = ?, paymentMethod = ?, shippingAddress = ? WHERE orderId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	    	
	    	statement.setString(1, order.getUserId());
	        statement.setDouble(2, order.getTotalPrice());
	        statement.setString(3, order.getStatus());
	        statement.setString(4, order.getPaymentMethod());
	        statement.setString(5, order.getShippingAddress());
	        statement.setString(6, order.getOrderId());

	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated == 0) {
	            throw new SQLException("No order found with the provided orderId."); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void deleteOrder(String orderId) {
	    String sql = "DELETE FROM order WHERE orderId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, orderId);

	        int rowsDeleted = statement.executeUpdate();
	        if (rowsDeleted == 0) {
	            throw new SQLException("No order found with the provided orderId.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public int countOrders()  {
	    String sql = "SELECT COUNT(*) FROM order";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        if (resultSet.next()) {
	            return resultSet.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0; 
	}
	
	@Override
	public Order getOrderById(String orderId) {
	    String sql = "SELECT * FROM order WHERE orderId = ?";
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
	    }

	    return order;
	}

	@Override
	public List<Order> getAllOrders() {
	    String sql = "SELECT * FROM order";
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
	    }

	    return orders;
	}

}
