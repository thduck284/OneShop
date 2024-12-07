package serviceImpl;

import java.util.List;

import javax.swing.table.DefaultTableModel;

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
	public int getVenueByDayMonthYear(String userId, String paymentDate, String timePeriod) {
		// TODO Auto-generated method stub
		return orderDAO.getVenueByDayMonthYear(userId, paymentDate, timePeriod);
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return orderDAO.getAllOrders();
	}

	@Override
	public List<Order> getAllOrdersByUserId(String userId) {
		// TODO Auto-generated method stub
		return orderDAO.getAllOrdersByUserId(userId);
	}
	
	@Override
	public List<Order> searchOrder(String searchQuery) {
		// TODO Auto-generated method stub
		return orderDAO.searchOrder(searchQuery);
	}
	
	@Override
	public DefaultTableModel getAllDetailOrdersByUserId(String userId) {
		// TODO Auto-generated method stub
		return orderDAO.getAllDetailOrdersByUserId(userId);
	}
}
