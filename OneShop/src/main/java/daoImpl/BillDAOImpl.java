package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BillDAO;
import models.Bill;

public class BillDAOImpl implements BillDAO{
	
	@Override
	public void addBill(Bill bill) {
	    String sql = "INSERT INTO bill (billId, userId, totalPrice, status, paymentMethod, shippingAddress, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    


	    String billId = "ĐH" + (countBills() + 1); 

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, billId);
	        statement.setString(2, bill.getUserId());
	        statement.setDouble(3, bill.getTotalPrice());
	        statement.setString(4, bill.getStatus());
	        statement.setString(5, bill.getPaymentMethod());
	        statement.setString(6, bill.getShippingAddress());
	        
	        statement.setDate(7, new java.sql.Date(System.currentTimeMillis())); 

	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void updateBill(Bill bill) {
	    String sql = "UPDATE bill SET userId = ?, totalPrice = ?, status = ?, paymentMethod = ?, shippingAddress = ? WHERE billId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	    	
	    	statement.setString(1, bill.getUserId());
	        statement.setDouble(2, bill.getTotalPrice());
	        statement.setString(3, bill.getStatus());
	        statement.setString(4, bill.getPaymentMethod());
	        statement.setString(5, bill.getShippingAddress());
	        statement.setString(6, bill.getBillId());

	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated == 0) {
	            throw new SQLException("No bill found with the provided billId."); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void deleteBill(String billId) {
	    String sql = "DELETE FROM bill WHERE billId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, billId);

	        int rowsDeleted = statement.executeUpdate();
	        if (rowsDeleted == 0) {
	            throw new SQLException("No bill found with the provided billId.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public int countBills()  {
	    String sql = "SELECT COUNT(*) FROM bill";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        if (resultSet.next()) {
	            return resultSet.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0; 
	}
	
	@Override
	public Bill getBillById(String billId) {
	    String sql = "SELECT * FROM bill WHERE billId = ?";
	    Bill bill = null;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, billId);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            String userId = resultSet.getString("userId");
	            double totalPrice = resultSet.getDouble("totalPrice");
	            String status = resultSet.getString("status");
	            String paymentMethod = resultSet.getString("paymentMethod");
	            String shippingAddress = resultSet.getString("shippingAddress");
	            String createdDate = resultSet.getString("createdDate");

	            bill = new Bill(billId, userId, totalPrice, status, paymentMethod, shippingAddress, createdDate);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return bill;
	}

	@Override
	public List<Bill> getAllBills() {
	    String sql = "SELECT * FROM bill";
	    List<Bill> bills = new ArrayList<>();

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
	            String billId = resultSet.getString("billId");
	            String userId = resultSet.getString("userId");
	            double totalPrice = resultSet.getDouble("totalPrice");
	            String status = resultSet.getString("status");
	            String paymentMethod = resultSet.getString("paymentMethod");
	            String shippingAddress = resultSet.getString("shippingAddress");
	            String createdDate = resultSet.getString("createdDate");

	            Bill bill = new Bill(billId, userId, totalPrice, status, paymentMethod, shippingAddress, createdDate);
	            bills.add(bill);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return bills;
	}

}
