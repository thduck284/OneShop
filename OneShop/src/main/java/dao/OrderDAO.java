package dao;

import java.util.List;

import models.Order;

public interface OrderDAO {
	
	void addBill(Order bill);
	void updateBill(Order bill);
	void deleteBill(String billId);
	Order getBillById(String billId);
	int countBills();
	List<Order> getAllBills();
}
