package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import dao.CartDetailDAO;
import models.CartDetail;

public class CartDetailDAOImpl implements CartDetailDAO{
	
	@Override
	public void addCartDetail(CartDetail detailCart) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO cartDetail (cartId, productId, productName, quantity, price, status) VALUES (?, ?, ?, ?, ?, ?)";
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setString(1, detailCart.getCartId());
	        ps.setString(2, detailCart.getProductId());
	        ps.setString(3, detailCart.getProductName());
	        ps.setInt(4, detailCart.getQuantity());
	        ps.setInt(5, detailCart.getPrice());
	        ps.setBoolean(6, detailCart.getStatus());
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void updateCartDetail(CartDetail detailCart) {
		// TODO Auto-generated method stub
		String query = "UPDATE cartDetail SET productName = ?, quantity = ?, price = ? WHERE cartId = ? AND productId = ? AND status = ?";
		
		CartDetail cartDetail = findCartDetail(detailCart.getCartId(), detailCart.getProductId());
		
		if(cartDetail == null) {
			String deleteQuery = "DELETE FROM cartDetail WHERE cartId = ? AND productId = ? AND status = ?";
		    String insertQuery = "INSERT INTO cartDetail (cartId, productId, productName, quantity, price, status) VALUES (?, ?, ?, ?, ?, ?)";
		    
		    try (Connection conn = ConnectDB.getConnection();
		            PreparedStatement deletePs = conn.prepareStatement(deleteQuery);
		            PreparedStatement insertPs = conn.prepareStatement(insertQuery)) {

		           deletePs.setString(1, detailCart.getCartId());
		           deletePs.setString(2, detailCart.getProductId());
		           deletePs.setBoolean(3, false);
		           deletePs.executeUpdate();

		           insertPs.setString(1, detailCart.getCartId());
		           insertPs.setString(2, detailCart.getProductId());
		           insertPs.setString(3, detailCart.getProductName());
		           insertPs.setInt(4, detailCart.getQuantity());
		           insertPs.setInt(5, detailCart.getPrice());
		           insertPs.setBoolean(6, detailCart.getStatus());
		           insertPs.executeUpdate();

		       } catch (SQLException e) {
		           e.printStackTrace();
		       }
		    
		} else {
			String deleteQuery = "DELETE FROM cartDetail WHERE cartId = ? AND productId = ? AND status = ?";
		    try (Connection conn = ConnectDB.getConnection();
		    	 PreparedStatement deletePs = conn.prepareStatement(deleteQuery);
		         PreparedStatement ps = conn.prepareStatement(query)) {
		    	
		    	deletePs.setString(1, detailCart.getCartId());
		    	deletePs.setString(2, detailCart.getProductId());
		    	deletePs.setBoolean(3, false);
		    	deletePs.executeUpdate();
		           
		    	ps.setString(1, cartDetail.getProductName());
		        ps.setInt(2, cartDetail.getQuantity() + detailCart.getQuantity());
		        ps.setInt(3, cartDetail.getPrice() + detailCart.getPrice());
		        ps.setString(4, cartDetail.getCartId());
		        ps.setString(5, cartDetail.getProductId());
		        ps.setBoolean(6, cartDetail.getStatus());
		        ps.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	}

	@Override
	public void deleteCartDetail(String cartId, String productId) {		
		// TODO Auto-generated method stub
		String query = "DELETE FROM cartDetail WHERE cartId = ? AND productId = ? AND status = ?";
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setString(1, cartId);
	        ps.setString(2, productId);
	        ps.setInt(3, 0);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public int countCartDetail() {		
		// TODO Auto-generated method stub
		String query = "SELECT COUNT(*) FROM cartDetail";
	    int count = 0;
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return count;
	}
	
	@Override
	public CartDetail getCartDetailById(String cartId, String productId) {		
		// TODO Auto-generated method stub
		String query = "SELECT * FROM cartDetail WHERE cartId = ? AND productId = ? AND status = ? ";
	    CartDetail cartDetail = null;
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setString(1, cartId);
	        ps.setString(2, productId);
	        ps.setBoolean(3, false);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                cartDetail = new CartDetail(
	                    rs.getString("cartId"),
	                    rs.getString("productId"),
	                    rs.getString("productName"),
	                    rs.getInt("quantity"),
	                    rs.getInt("price"),
	                    rs.getBoolean("status")
	                );
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cartDetail;
	}
	
	@Override
	public List<CartDetail> getCartDetailByCartId(String cartId) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM cartDetail WHERE cartId = ?";
	    List<CartDetail> cartDetails = new ArrayList<>();
	    
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	        
	        ps.setString(1, cartId);  
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                CartDetail detailCart = new CartDetail(
	                    rs.getString("cartId"),
	                    rs.getString("productId"),
	                    rs.getString("productName"),
	                    rs.getInt("quantity"),
	                    rs.getInt("price"),
	                    rs.getBoolean("status")
	                );
	                cartDetails.add(detailCart);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return cartDetails;
	}

	@Override
	public List<CartDetail> getAllCartDetail() {		
		// TODO Auto-generated method stub
		List<CartDetail> list = new ArrayList<>();
	    String query = "SELECT * FROM cartDetail";
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {
	        while (rs.next()) {
	            CartDetail detailCart = new CartDetail(
	                rs.getString("cartId"),
	                rs.getString("productId"),
	                rs.getString("productName"),
	                rs.getInt("quantity"),
	                rs.getInt("price"),
	                rs.getBoolean("status")
	            );
	            list.add(detailCart);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	@Override
	public DefaultTableModel getCartDetailsTable(String cartId) {
		// TODO Auto-generated method stub
		String sql = "SELECT p.image AS image, p.productId, p.productName AS productName, cd.price AS price, cd.quantity AS quantity, cd.status AS status " +
                "FROM cartDetail cd " +
                "JOIN product p ON cd.productId = p.productId " +
                "WHERE cd.cartId = ? AND cd.status = ?";
		
	   DefaultTableModel tableModel = new DefaultTableModel();
	   tableModel.addColumn("image");
	   tableModel.addColumn("productId");
	   tableModel.addColumn("productName");
	   tableModel.addColumn("price");
	   tableModel.addColumn("quantity");
	   tableModel.addColumn("status");
	
	   try (Connection connection = ConnectDB.getConnection();
	        PreparedStatement statement = connection.prepareStatement(sql)) {
	
	       statement.setString(1, cartId);
	       statement.setBoolean(2, false);
	       ResultSet resultSet = statement.executeQuery();
	
	       while (resultSet.next()) {
	           byte[] imageBytes = resultSet.getBytes("image");
	           String base64Image = (imageBytes != null) ? Base64.getEncoder().encodeToString(imageBytes) : "N/A";
	           String productId = resultSet.getString("productId");
	           String productName = resultSet.getString("productName");
	           int price = resultSet.getInt("price");
	           int quantity = resultSet.getInt("quantity");
	           int status = resultSet.getInt("status");
	
	           tableModel.addRow(new Object[]{base64Image, productId, productName, price, quantity, status});
	       }
	
	   } catch (Exception e) {
	       e.printStackTrace();
	   }
	
	   return tableModel;
	}
	
	@Override
	public DefaultTableModel getAllCartDetailByUserId(String userId) {
		// TODO Auto-generated method stub   
		String query = "SELECT p.image, cd.productId, cd.productName, cd.quantity, cd.price, o.paymentMethod "
	             + "FROM cartDetail cd "
	             + "LEFT JOIN [dbo].[order] o ON cd.cartId = o.cartId "
	             + "LEFT JOIN product p ON cd.productId = p.productId "
	             + "WHERE o.userId = ? AND cd.status = ?";
	    
	       DefaultTableModel tableModel = new DefaultTableModel();
	       tableModel.addColumn("image");
	       tableModel.addColumn("productId");
		   tableModel.addColumn("productName");
		   tableModel.addColumn("price");
		   tableModel.addColumn("quantity");
		   tableModel.addColumn("paymentMethod");
		   
		
		   try (Connection connection = ConnectDB.getConnection();
		        PreparedStatement statement = connection.prepareStatement(query)) {
		
		       statement.setString(1, userId);
		       statement.setBoolean(2, true);
		       ResultSet resultSet = statement.executeQuery();
		
		       while (resultSet.next()) {
		    	   byte[] imageBytes = resultSet.getBytes("image");
		           String base64Image = (imageBytes != null) ? Base64.getEncoder().encodeToString(imageBytes) : "N/A";
		    	   String productId = resultSet.getString("productId");
		           String productName = resultSet.getString("productName");
		           int price = resultSet.getInt("price");
		           int quantity = resultSet.getInt("quantity");
		           String paymentMethod = resultSet.getString("paymentMethod");
		
		           tableModel.addRow(new Object[]{base64Image, productId, productName, price, quantity, paymentMethod});
		       }
		
		   } catch (Exception e) {
		       e.printStackTrace();
		   }
	    
	    return tableModel;
	}
	
	private CartDetail findCartDetail(String cartId, String productId)
	{
		String query = "SELECT * FROM cartDetail WHERE cartId = ? AND productId = ? AND status = ?";
	    CartDetail cartDetail = null;
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {
	    	
	        ps.setString(1, cartId);
	        ps.setString(2, productId);
	        ps.setBoolean(3, true);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                cartDetail = new CartDetail(
	                    rs.getString("cartId"),
	                    rs.getString("productId"),
	                    rs.getString("productName"),
	                    rs.getInt("quantity"),
	                    rs.getInt("price"),
	                    rs.getBoolean("status")
	                );
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cartDetail;
	}
}
