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
		// TODO Auto-generated method stub
		orderDAO.addOrder(order);
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		orderDAO.updateOrder(order);
	}

	@Override
	public void deleteOrder(String orderId) {
		// TODO Auto-generated method stub
		orderDAO.deleteOrder(orderId);
	}

	@Override
	public Order getOrderById(String orderId) {
		// TODO Auto-generated method stub
		return orderDAO.getOrderById(orderId);
	}

	@Override
	public int countOrders() {
		// TODO Auto-generated method stub
		return orderDAO.countOrders();
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return orderDAO.getAllOrders();
	}

}
