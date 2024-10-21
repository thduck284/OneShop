package serviceImpl;

import java.util.List;

import dao.BillDAO;
import daoImpl.BillDAOImpl;
import models.Bill;
import service.BillService;

public class BillServiceImpl implements BillService{
	
	BillDAO billDAO = new BillDAOImpl();
	
	@Override
	public void addBill(Bill bill) {
		billDAO.addBill(bill);
	}

	@Override
	public void updateBill(Bill bill) {
		billDAO.updateBill(bill);
	}

	@Override
	public void deleteBill(String billId) {
		billDAO.deleteBill(billId);
	}

	@Override
	public Bill getBillById(String billId) {
		return billDAO.getBillById(billId);
	}

	@Override
	public int countBills() {
		return billDAO.countBills();
	}

	@Override
	public List<Bill> getAllBills() {
		return billDAO.getAllBills();
	}

}
