package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDAO;
import models.Product;

public class ProductDAOImpl implements ProductDAO{
	
	@Override
	public void addProduct(Product product)  {
	    String sql = "INSERT INTO product (productId, productName, description, price, quantity, categoryId, image, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    String id = "SP" + (countProducts() + 1);
	    
	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) { 

	        statement.setString(1, id);
	        statement.setString(2, product.getProductName());
	        statement.setString(3, product.getDescription());
	        statement.setDouble(4, product.getPrice()); 
	        statement.setInt(5, product.getQuantity());
	        statement.setString(6, product.getCategoryId());

	        if (product.getImage() != null) {
	            statement.setBytes(7, product.getImage());
	        } else {
	            statement.setNull(7, java.sql.Types.BLOB);
	        }
	        
	        statement.setDate(8, new java.sql.Date(product.getCreatedDate().getTime()));

	        statement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void deleteProduct(String productId) {
	    String sql = "DELETE FROM product WHERE productId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, productId); 
	        statement.executeUpdate(); 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void updateProduct(Product product) {
	    String sql = "UPDATE product SET productName = ?, description = ?, price = ?, quantity = ?, categoryId = ?, image = ?, createdDate = ? WHERE productId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, product.getProductName());
	        statement.setString(2, product.getDescription());
	        statement.setDouble(3, product.getPrice());
	        statement.setInt(4, product.getQuantity());
	        statement.setString(5, product.getCategoryId());

	        if (product.getImage() != null) {
	            statement.setBytes(6, product.getImage());
	        } else {
	            statement.setNull(6, java.sql.Types.BLOB);
	        }

	        statement.setDate(7, new java.sql.Date(product.getCreatedDate().getTime()));
	        statement.setString(8, product.getProductId());

	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
            	
            	byte[] image = resultSet.getBytes("image");
            	
                Product product = new Product(
                    resultSet.getString("productId"),
                    resultSet.getString("productName"),
                    resultSet.getString("description"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("quantity"),
                    resultSet.getString("categoryId"),
                    image,
                    resultSet.getDate("createdDate")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
	
	@Override
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
	
	@Override
	public byte[] getImageById(String productId) {
	    String sql = "SELECT image FROM product WHERE productId = ?";
	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, productId);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            
	            return resultSet.getBytes("image");
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    return null;  
	}
	
	@Override
	public Product getProductById(String productId) {
	    String sql = "SELECT * FROM product WHERE productId = ?";
	    Product product = null;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, productId);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            product = new Product(
	                resultSet.getString("productId"),
	                resultSet.getString("productName"),
	                resultSet.getString("description"),
	                resultSet.getDouble("price"),
	                resultSet.getInt("quantity"),
	                resultSet.getString("categoryId"),
	                resultSet.getBytes("image"),  
	                resultSet.getDate("createdDate")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return product;
	}
}
