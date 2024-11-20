package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dao.CategoryDAO;
import models.Category;

public class CategoryDAOImpl implements CategoryDAO{
	
	@Override
	public void addCategory(Category category) {
	    
		String sql = "INSERT INTO category (categoryId, categoryName, description) VALUES (?, ?, ?)";		
        
		String id = "CATE" + UUID.randomUUID().toString().replace("-", "").substring(0, 5);
		
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
        	
            statement.setString(1, id); 
            statement.setString(2, category.getCategoryName());
            statement.setString(3, category.getDescription());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
	
	@Override
	public void updateCategory(Category category) {
 
        String sql = "UPDATE category SET categoryName = ?, description = ? WHERE categoryId = ?";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

        	statement.setString(3, category.getCategoryId()); 
            statement.setString(1, category.getCategoryName());
            statement.setString(2, category.getDescription());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
	
	@Override
	public void deleteCategory(String categoryId) {
     
        String sql = "DELETE FROM category WHERE categoryId = ?";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, categoryId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	@Override
	public int countCategories()  {
        String sql = "SELECT COUNT(*) FROM category";

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

	@Override
	public Category getCategoryById(String categoryId)  {
	    String sql = "SELECT * FROM category WHERE categoryId = ?";
	    Category category = null;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, categoryId);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                String categoryName = resultSet.getString("categoryName");
	                String description = resultSet.getString("description");

	                category = new Category(categoryId, categoryName, description);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return category;
	}

	@Override
	public List<String> getAllCategoryIds() {
		// TODO Auto-generated method stub
		String sql = "SELECT categoryId FROM category";
	    List<String> categoryIds = new ArrayList<>();

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
	            String categoryId = resultSet.getString("categoryId");
	            categoryIds.add(categoryId);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return categoryIds;
	}
}
