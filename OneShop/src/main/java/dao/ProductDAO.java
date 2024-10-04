package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Product;

public class ProductDAO {
	
	public void addProduct(String productName, String description, Float price, int quantity, String category, byte[] image, Date created) throws SQLException {
	    String sql = "INSERT INTO product (productId, productName, description, price, quantity, categoryId, image, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    String id = "SP" + (countProduct() + 1);
	    
	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) { 

	        statement.setString(1, id);
	        statement.setString(2, productName);
	        statement.setString(3, description);
	        statement.setFloat(4, price); 
	        statement.setInt(5, quantity);
	        statement.setString(6, category);

	        if (image != null) {
	            statement.setBytes(7, image);
	        } else {
	            statement.setNull(7, java.sql.Types.BLOB);
	        }
	        
	        statement.setDate(8, new java.sql.Date(created.getTime()));

	        statement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Product product = new Product(
                    resultSet.getString("productId"),
                    resultSet.getString("productName"),
                    resultSet.getString("description"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("quantity"),
                    resultSet.getString("categoryId"),
                    null,
                    resultSet.getTimestamp("createdDate").toLocalDateTime()
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
	
	public int countProduct() {
	    int count = 0;
	    String sql = "SELECT COUNT(*) FROM product";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        if (resultSet.next()) {
	            count = resultSet.getInt(1); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return count;
	}
	
	public byte[] getImageById(String productId) throws SQLException {
	    String sql = "SELECT image FROM product WHERE productId = ?";
	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, productId);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            
	            return resultSet.getBytes("image");
	        }
	    }
	    return null;  
	}

}
