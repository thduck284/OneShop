package dao;

import java.util.List;

import models.Order;

public interface OrderDAO {
	
	void addOrder(Order order);
	void updateOrder(Order order);
	void deleteOrder(String orderId);
	int countOrders();
	Order getOrderById(String orderId);
	List<Order> getAllOrders();
}
