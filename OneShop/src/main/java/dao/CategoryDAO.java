package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Category;

public class CategoryDAO {
	
	public void addCategory(String categoryId, String categoryName, String description) throws SQLException {
	    
		String sql = "INSERT INTO category (categoryId, categoryName, description) VALUES (?, ?, ?)";		
        
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
        	
            statement.setString(1, categoryId); 
            statement.setString(2, categoryName);
            statement.setString(3, description);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void updateCategory(String categoryId, String categoryName, String description) throws SQLException {
 
        String sql = "UPDATE category SET categoryName = ?, description = ? WHERE categoryId = ?";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, categoryName);
            statement.setString(2, description);
            statement.setString(3, categoryId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error! Can't update category.");
        }
    }
	
	public void deleteCategory(String categoryId) throws SQLException {
     
        String sql = "DELETE FROM category WHERE categoryId = ?";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, categoryId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public int countCategories() throws SQLException {
        String sql = "SELECT COUNT(*) FROM category";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error! Can't count categories.");
        }
        return 0;
    }
	
	public List<Category> getAllCategories() {
        String sql = "SELECT * FROM category";
        List<Category> categories = new ArrayList<>();

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String categoryId = resultSet.getString("categoryId");
                String categoryName = resultSet.getString("categoryName");
                String description = resultSet.getString("description");

                Category category = new Category(categoryId, categoryName, description);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
