package serviceImpl;

import java.util.List;

import dao.OrderDAO;
import daoImpl.OrderDAOImpl;
import models.Order;
import service.OrderService;

public class OrderServiceImpl implements OrderService{
	
	OrderDAO orderDAO = new OrderDAOImpl();
	
	@Override
	public void addOrder(Order order) {
		orderDAO.addOrder(order);
	}

	@Override
	public void updateOrder(Order order) {
		orderDAO.updateOrder(order);
	}

	@Override
	public void deleteOrder(String orderId) {
		orderDAO.deleteOrder(orderId);
	}

	@Override
	public Order getOrderById(String orderId) {
		return orderDAO.getOrderById(orderId);
	}

	@Override
	public int countOrders() {
		return orderDAO.countOrders();
	}

	@Override
	public List<Order> getAllOrders() {
		return orderDAO.getAllOrders();
	}

}
