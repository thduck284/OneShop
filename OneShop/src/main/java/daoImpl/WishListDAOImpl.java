package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.WishListDAO;
import models.WishList;

public class WishListDAOImpl implements WishListDAO{

	@Override
	public void addWishList(WishList wishList) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO wishList (userId, productId, userName, productName) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, wishList.getUserId());
            statement.setString(2, wishList.getProductId());
            statement.setString(3, wishList.getUserName());
            statement.setString(4, wishList.getProductName());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void deleteWishList(String userId, String productId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM wishList WHERE userId = ? AND productId = ?";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userId);
            statement.setString(2, productId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public boolean checkWishList(String userId, String productId) {
		// TODO Auto-generated method stub
		String sql = "SELECT 1 FROM wishList WHERE userId = ? AND productId = ?";
	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, userId);
	        statement.setString(2, productId);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            return resultSet.next();  
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	@Override
	public List<String> getAllWishListByUserId(String userId) {
		// TODO Auto-generated method stub
		String sql = "SELECT productId FROM wishList WHERE userId = ?";
	    List<String> productIds = new ArrayList<>();

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, userId);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                String productId = resultSet.getString("productId");
	                productIds.add(productId);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return productIds;
	}

}
