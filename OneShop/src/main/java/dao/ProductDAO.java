package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import models.Product;

public class ProductDAO {
	
	public void addProduct(Product product, String imagePath) throws SQLException {
        String sql = "INSERT INTO product (productId, productName, description, price, quantity, categoryId, image, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             FileInputStream inputStream = new FileInputStream(imagePath)) {

            statement.setString(1, product.getProductId());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getQuantity());
            statement.setString(6, product.getCategoryId());

            byte[] imageData = new byte[inputStream.available()];
            inputStream.read(imageData);
            statement.setBytes(7, imageData);
            
            statement.setTimestamp(8, Timestamp.valueOf(product.getCreatedDate()));  

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
                    resultSet.getString("image"),
                    resultSet.getTimestamp("createdDate").toLocalDateTime()
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
	
	public int countProducts() {
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
}
