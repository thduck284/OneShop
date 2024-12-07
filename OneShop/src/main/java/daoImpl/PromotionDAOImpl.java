package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import dao.PromotionDAO;
import models.Promotion;

public class PromotionDAOImpl implements PromotionDAO{

	@Override
	public void addPromotion(Promotion promotion) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO promotion (promoId, userId, price, expirationDate, status) VALUES (?, ?, ?, ?, ?)";
		String id = "PR" + UUID.randomUUID().toString().replace("-", "").substring(0, 7);

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	    	
	        statement.setString(1, id);
	        statement.setString(2, promotion.getUserId());
	        
	        if(promotion.getPrice() > 50) {
	        	if(promotion.getPrice() > 500000) {
		        	statement.setInt(3, 10);
		        } else if (promotion.getPrice() > 1000000){
		        	statement.setInt(3, 15);
		        } else if (promotion.getPrice() > 2000000){
		        	statement.setInt(3, 20);
		        } else {
		        	return;
		        }
	        } else {
	        	statement.setInt(3, promotion.getPrice());
	        }
	     
	        java.sql.Date expirationDate = null;
	        if (promotion.getExpirationDate() == null) {
	            java.util.Date currentDate = new java.util.Date();
	            java.util.Calendar calendar = java.util.Calendar.getInstance();
	            calendar.setTime(currentDate);
	            calendar.add(java.util.Calendar.DAY_OF_MONTH, 30); 
	            expirationDate = new java.sql.Date(calendar.getTimeInMillis());
	        } else {
	            expirationDate = new java.sql.Date(promotion.getExpirationDate().getTime());
	        }
	        
	        statement.setDate(4, expirationDate);
	        statement.setBoolean(5, false);

	        statement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void updatePromotion(Promotion promotion) {
		// TODO Auto-generated method stub
		String sql = "UPDATE promotion SET userId = ?, price = ?, expirationDate = ?, status = ? WHERE promoId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, promotion.getUserId());
	        statement.setInt(2, promotion.getPrice());
	        
	        if (promotion.getExpirationDate() != null) {
	            statement.setDate(3, new java.sql.Date(promotion.getExpirationDate().getTime()));
	        } else {
	            statement.setNull(3, java.sql.Types.DATE);
	        }

	        statement.setBoolean(4, promotion.getStatus());
	        statement.setString(5, promotion.getPromotionId());
	        statement.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void deletePromotion(String promotionId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM promotion WHERE promoId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, promotionId);

	        statement.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public Promotion getPromotionById(String promotionId) {
		// TODO Auto-generated method stub
		String sql = "SELECT promoId, userId, price, expirationDate, status FROM promotion WHERE promoId = ?";
	    Promotion promotion = null;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, promotionId);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                String promoId = resultSet.getString("promoId");
	                String userId = resultSet.getString("userId");
	                int price = resultSet.getInt("price");
	                Date expirationDate = resultSet.getDate("expirationDate");
	                boolean status = resultSet.getBoolean("status");

	                promotion = new Promotion(promoId, userId, price, expirationDate, status);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return promotion;
	}
	
	@Override
	public List<Promotion> getAllPromotion() {
		// TODO Auto-generated method stub
		List<Promotion> promotions = new ArrayList<>();
	    String sql = "SELECT * FROM promotion";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        try (ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	                String promoId = resultSet.getString("promoId");
	                String userId = resultSet.getString("userId");
	                int price = resultSet.getInt("price");
	                Date expirationDate = resultSet.getDate("expirationDate");
	                boolean status = resultSet.getBoolean("status");

	                promotions.add(new Promotion(promoId, userId, price, expirationDate, status));
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return promotions;
	}

	@Override
	public List<Promotion> getAllPromotion(String userId) {
		// TODO Auto-generated method stub
		List<Promotion> promotions = new ArrayList<>();
	    String sql = "SELECT promoId, userId, price, expirationDate, status FROM promotion " +
	                 "WHERE status = ? AND expirationDate > ? AND userId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	    	
	    	java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
	    	
	    	statement.setBoolean(1, false);
	    	statement.setDate(2, currentDate);
	        statement.setString(3, userId);

	        try (ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	                String promoId = resultSet.getString("promoId");
	                String user = resultSet.getString("userId");
	                int price = resultSet.getInt("price");
	                Date expirationDate = resultSet.getDate("expirationDate");
	                boolean status = resultSet.getBoolean("status");

	                promotions.add(new Promotion(promoId, user, price, expirationDate, status));
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return promotions;
	}
}
