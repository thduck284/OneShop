package dao;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import models.Order;

public interface OrderDAO {
	
	void addOrder(Order order);
	void updateOrder(Order order);
	void deleteOrder(String orderId);
	int countOrders();
	int getVenueByDayMonthYear(String userId, String paymentDate, String timePeriod);
	Order getOrderById(String orderId);
	List<Order> getAllOrders();
	List<Order> getAllOrdersByUserId(String userId);
	List<Order> searchOrder(String searchQuery);
	DefaultTableModel getAllDetailOrdersByUserId(String userId);
}
