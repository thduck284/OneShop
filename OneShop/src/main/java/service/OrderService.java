package service;

import java.util.List;

import models.Order;

public interface OrderService {
	
	void addOrder(Order order);
	void updateOrder(Order order);
	void deleteOrder(String orderId);
	Order getOrderById(String orderId);
	int countOrders();
	List<Order> getAllOrders();
}
