package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dao.ShopDAO;
import models.Shop;

public class ShopDAOImpl implements ShopDAO{

	@Override
	public void addShop(Shop shop) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO shop (shopId, userId, shopName, description, status, createdDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
 
            String shopId = "SHOP" + UUID.randomUUID().toString().replace("-", "").substring(0, 5);
            
            statement.setString(1, shopId);
            statement.setString(2, shop.getUserId());
            statement.setString(3, shop.getShopName());
            statement.setString(4, shop.getDescription());
            statement.setString(5, shop.getStatus());
            statement.setTimestamp(6, Timestamp.valueOf(shop.getCreatedDate()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void updateShop(Shop shop) {
		// TODO Auto-generated method stub
		String sql = "UPDATE shop SET userId = ?, shopName = ?, description = ?, status = ?, createdDate = ? WHERE shopId = ?";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, shop.getUserId());
            statement.setString(2, shop.getShopName());
            statement.setString(3, shop.getDescription());
            statement.setString(4, shop.getStatus());
            statement.setTimestamp(5, Timestamp.valueOf(shop.getCreatedDate()));
            statement.setString(6, shop.getShopId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void deleteShop(String shopId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM shop WHERE shopId = ?";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, shopId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public int countShops() {
		// TODO Auto-generated method stub
		int count = 0;
        String sql = "SELECT COUNT(*) FROM shop";
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
	public Shop getShopById(String shopId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM shop WHERE shopId = ?";
        Shop shop = null;
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, shopId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                shop = new Shop(
                    resultSet.getString("shopId"),
                    resultSet.getString("userId"),
                    resultSet.getString("shopName"),
                    resultSet.getString("description"),
                    resultSet.getString("status"),
                    resultSet.getTimestamp("createdDate").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shop;
	}
	
	@Override
	public List<String> getAllShopId() {
		// TODO Auto-generated method stub
		List<String> shopIds = new ArrayList<>();
	    String sql = "SELECT shopId FROM shop";
	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
	            shopIds.add(resultSet.getString("shopId"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return shopIds;
	}
	
	@Override
	public List<String> getAllShopIdByUserId(String userId) {
		// TODO Auto-generated method stub
		List<String> shopIds = new ArrayList<>();
        String sql = "SELECT shopId FROM shop WHERE userId = ?";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                shopIds.add(resultSet.getString("shopId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shopIds;
	}

	@Override
	public List<Shop> getAllShops() {
		// TODO Auto-generated method stub
		List<Shop> shopList = new ArrayList<>();
        String sql = "SELECT * FROM shop";
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Shop shop = new Shop(
                    resultSet.getString("shopId"),
                    resultSet.getString("userId"),
                    resultSet.getString("shopName"),
                    resultSet.getString("description"),
                    resultSet.getString("status"),
                    resultSet.getTimestamp("createdDate").toLocalDateTime()
                );
                shopList.add(shop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shopList;
	}
	
	@Override
	public List<Shop> getAllShopsByUserId(String userId) {
	    List<Shop> shopList = new ArrayList<>();
	    String sql = "SELECT * FROM shop WHERE userId = ?";
	    
	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        
	        statement.setString(1, userId);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Shop shop = new Shop(
	                resultSet.getString("shopId"),
	                resultSet.getString("userId"),
	                resultSet.getString("shopName"),
	                resultSet.getString("description"),
	                resultSet.getString("status"),
	                resultSet.getTimestamp("createdDate").toLocalDateTime()
	            );
	            shopList.add(shop);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return shopList;
	}
}
