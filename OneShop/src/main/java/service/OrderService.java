package service;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import models.Order;

public interface OrderService {
	
	void addOrder(Order order);
	void updateOrder(Order order);
	void deleteOrder(String orderId);
	Order getOrderById(String orderId);
	int countOrders();
	int getVenueByDayMonthYear(String userId, String paymentDate, String timePeriod);
	List<Order> getAllOrders();
	List<Order> getAllOrdersByUserId(String userId);
	List<Order> searchOrder(String searchQuery);
	DefaultTableModel getAllDetailOrdersByUserId(String userId);
}
