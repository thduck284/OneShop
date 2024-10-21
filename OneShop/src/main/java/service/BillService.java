package service;

import java.util.List;

import models.Bill;

public interface BillService {
	
	void addBill(Bill bill);
	void updateBill(Bill bill);
	void deleteBill(String billId);
	Bill getBillById(String billId);
	int countBills();
	List<Bill> getAllBills();
}
