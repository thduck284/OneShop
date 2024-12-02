package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDAO;
import models.Order;

public class OrderDAOImpl implements OrderDAO{

	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
	    String sql = "INSERT INTO [order] (orderId, userId, cartId, promoId, shippingId, status, paymentMethod, paymentStatus, paymentDate, totalCost) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, order.getOrderId());
	        statement.setString(2, order.getUserId());
	        statement.setString(3, order.getCartId());
	        statement.setString(4, order.getPromoId());
	        statement.setString(5, order.getShippingId());
	        statement.setBoolean(6, order.getStatus());
	        statement.setString(7, order.getPaymentMethod());
	        statement.setBoolean(8, order.getPaymentStatus());
	        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());
	        statement.setTimestamp(9, currentTimestamp); 
	        statement.setInt(10, order.getTotalCost());

	        statement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		String sql = "UPDATE [order] SET userId = ?, cartId = ?, promoId = ?, shippingId = ?, status = ?, paymentMethod = ?, paymentStatus = ?, paymentDate = ?, totalCost = ? WHERE orderId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, order.getUserId());
	        statement.setString(2, order.getCartId());
	        statement.setString(3, order.getPromoId());
	        statement.setString(4, order.getShippingId());
	        statement.setBoolean(5, order.getStatus());
	        statement.setString(6, order.getPaymentMethod());
	        statement.setBoolean(7, order.getPaymentStatus());
	        if (order.getPaymentDate() != null) {
	            statement.setTimestamp(8, new java.sql.Timestamp(order.getPaymentDate().getTime()));
	        } else {
	            statement.setNull(8, java.sql.Types.TIMESTAMP);
	        }
	        statement.setInt(9, order.getTotalCost());
	        statement.setString(10, order.getOrderId());

	        statement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void deleteOrder(String orderId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM [order] WHERE orderId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, orderId);

	        statement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public Order getOrderById(String orderId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM [order] WHERE orderId = ?";
	    Order order = null;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, orderId);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            order = new Order(
	                resultSet.getString("orderId"),
	                resultSet.getString("userId"),
	                resultSet.getString("cartId"),
	                resultSet.getString("promoId"),
	                resultSet.getString("shippingId"),
	                resultSet.getBoolean("status"),
	                resultSet.getString("paymentMethod"),
	                resultSet.getBoolean("paymentStatus"),
	                resultSet.getTimestamp("paymentDate"),
	                resultSet.getInt("totalCost")
	            );
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return order;
	}

	@Override
	public int countOrders() {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM [order]";
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
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM [order]";
	    List<Order> orders = new ArrayList<>();

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Order order = new Order(
	                resultSet.getString("orderId"),
	                resultSet.getString("userId"),
	                resultSet.getString("cartId"),
	                resultSet.getString("promoId"),
	                resultSet.getString("shippingId"),
	                resultSet.getBoolean("status"),
	                resultSet.getString("paymentMethod"),
	                resultSet.getBoolean("paymentStatus"),
	                resultSet.getTimestamp("paymentDate"),
	                resultSet.getInt("totalCost")
	            );
	            orders.add(order);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return orders;
	}
}
