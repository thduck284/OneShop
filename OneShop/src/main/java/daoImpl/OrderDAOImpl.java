package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dao.OrderDAO;
import models.Order;

public class OrderDAOImpl implements OrderDAO{

	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
	    String sql = "INSERT INTO [order] (orderId, userId, cartId, promoId, shippingId, status, paymentMethod, paymentStatus, paymentDate, totalCost) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, order.getOrderId());
	        statement.setString(2, order.getUserId());
	        statement.setString(3, order.getCartId());
	        statement.setString(4, order.getPromoId());
	        statement.setString(5, order.getShippingId());
	        statement.setBoolean(6, order.getStatus());
	        statement.setString(7, order.getPaymentMethod());
	        statement.setBoolean(8, order.getPaymentStatus());
	        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());
	        statement.setTimestamp(9, currentTimestamp); 
	        statement.setInt(10, order.getTotalCost());

	        statement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		String sql = "UPDATE [order] SET userId = ?, cartId = ?, promoId = ?, shippingId = ?, status = ?, paymentMethod = ?, paymentStatus = ?, paymentDate = ?, totalCost = ? WHERE orderId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, order.getUserId());
	        statement.setString(2, order.getCartId());
	        statement.setString(3, order.getPromoId());
	        statement.setString(4, order.getShippingId());
	        statement.setBoolean(5, order.getStatus());
	        statement.setString(6, order.getPaymentMethod());
	        statement.setBoolean(7, order.getPaymentStatus());
	        if (order.getPaymentDate() != null) {
	            statement.setTimestamp(8, new java.sql.Timestamp(order.getPaymentDate().getTime()));
	        } else {
	            statement.setNull(8, java.sql.Types.TIMESTAMP);
	        }
	        statement.setInt(9, order.getTotalCost());
	        statement.setString(10, order.getOrderId());

	        statement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void deleteOrder(String orderId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM [order] WHERE orderId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, orderId);

	        statement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public Order getOrderById(String orderId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM [order] WHERE orderId = ?";
	    Order order = null;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, orderId);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            order = new Order(
	                resultSet.getString("orderId"),
	                resultSet.getString("userId"),
	                resultSet.getString("cartId"),
	                resultSet.getString("promoId"),
	                resultSet.getString("shippingId"),
	                resultSet.getBoolean("status"),
	                resultSet.getString("paymentMethod"),
	                resultSet.getBoolean("paymentStatus"),
	                resultSet.getTimestamp("paymentDate"),
	                resultSet.getInt("totalCost")
	            );
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return order;
	}

	@Override
	public int countOrders() {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM [order]";
	    int count = 0;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            count = resultSet.getInt(1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return count;
	}
	
	@Override
	public int getVenueByDayMonthYear(String userId, String paymentDate, String timePeriod) {
	    String sql = """
	            SELECT 
	                SUM(o.totalCost) AS totalRevenue
	            FROM 
	                shop s
	            INNER JOIN product p ON s.shopId = p.shopId
	            INNER JOIN cartDetail cd ON p.productId = cd.productId
	            INNER JOIN [dbo].[order] o ON cd.cartId = o.cartId
	            WHERE 
	                s.userId = ? 
	                AND o.paymentDate IS NOT NULL
	                AND (
	                    (? = 'day' AND CONVERT(DATE, o.paymentDate) = CONVERT(DATE, ?))
	                    OR
	                    (? = 'month' AND DATEPART(MONTH, o.paymentDate) = ? AND DATEPART(YEAR, o.paymentDate) = DATEPART(YEAR, GETDATE()))
	                    OR
	                    (? = 'quarter' AND DATEPART(QUARTER, o.paymentDate) = ? AND DATEPART(YEAR, o.paymentDate) = DATEPART(YEAR, GETDATE()))
	                    OR
	                    (? = 'year' AND DATEPART(YEAR, o.paymentDate) = ?)
	                )
	        """;

	    int totalRevenue = 0;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	    	
	    	statement.setString(1, userId);
	        statement.setString(2, timePeriod);
	    	
	    	if ("day".equals(timePeriod)) {
	            statement.setDate(3, java.sql.Date.valueOf(paymentDate));
	            statement.setNull(4, java.sql.Types.VARCHAR);
	            statement.setNull(5, java.sql.Types.INTEGER);
	            statement.setNull(6, java.sql.Types.VARCHAR);
	            statement.setNull(7, java.sql.Types.INTEGER);
	            statement.setNull(8, java.sql.Types.VARCHAR);
	            statement.setNull(9, java.sql.Types.INTEGER);
	        } else if ("month".equals(timePeriod)) {
	            statement.setNull(3, java.sql.Types.DATE);
	            statement.setString(4, timePeriod);
	            statement.setInt(5, Integer.parseInt(paymentDate));
	            statement.setNull(6, java.sql.Types.VARCHAR);
	            statement.setNull(7, java.sql.Types.INTEGER);
	            statement.setNull(8, java.sql.Types.VARCHAR);
	            statement.setNull(9, java.sql.Types.INTEGER);
	        } else if ("quarter".equals(timePeriod)) {
	            statement.setNull(3, java.sql.Types.DATE);
	            statement.setNull(4, java.sql.Types.VARCHAR);
	            statement.setNull(5, java.sql.Types.INTEGER);
	            statement.setString(6, timePeriod);
	            statement.setInt(7, Integer.parseInt(paymentDate)); 
	            statement.setNull(8, java.sql.Types.VARCHAR);
	            statement.setNull(9, java.sql.Types.INTEGER);
	        } else if ("year".equals(timePeriod)) {
	            statement.setNull(3, java.sql.Types.DATE);
	            statement.setNull(4, java.sql.Types.VARCHAR);
	            statement.setNull(5, java.sql.Types.INTEGER);
	            statement.setNull(6, java.sql.Types.VARCHAR);
	            statement.setNull(7, java.sql.Types.INTEGER);
	            statement.setString(8, timePeriod);
	            statement.setInt(9, Integer.parseInt(paymentDate));
	        }
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            totalRevenue = resultSet.getInt("totalRevenue");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return totalRevenue;
	}


	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM [order]";
	    List<Order> orders = new ArrayList<>();

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Order order = new Order(
	                resultSet.getString("orderId"),
	                resultSet.getString("userId"),
	                resultSet.getString("cartId"),
	                resultSet.getString("promoId"),
	                resultSet.getString("shippingId"),
	                resultSet.getBoolean("status"),
	                resultSet.getString("paymentMethod"),
	                resultSet.getBoolean("paymentStatus"),
	                resultSet.getTimestamp("paymentDate"),
	                resultSet.getInt("totalCost")
	            );
	            orders.add(order);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return orders;
	}


	@Override
	public List<Order> getAllOrdersByUserId(String userId) {
	    String sql = """
	        SELECT 
	            o.orderId, o.userId, o.cartId, o.promoId, o.paymentMethod, 
	            o.totalCost, o.paymentStatus, o.paymentDate 
	        FROM 
	            shop s
	        INNER JOIN product p ON s.shopId = p.shopId
	        INNER JOIN cartDetail cd ON p.productId = cd.productId
	        INNER JOIN [dbo].[order] o ON cd.cartId = o.cartId
	        WHERE 
	            s.userId = ?
	        """;

	    List<Order> orders = new ArrayList<>();

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, userId);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Order order = new Order(
	                resultSet.getString("orderId"),
	                resultSet.getString("userId"),
	                resultSet.getString("cartId"),
	                resultSet.getString("promoId"),
	                null, 
	                false, 
	                resultSet.getString("paymentMethod"),
	                resultSet.getBoolean("paymentStatus"),
	                resultSet.getTimestamp("paymentDate"),
	                resultSet.getInt("totalCost")
	            );
	            orders.add(order);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return orders;
	}

	@Override
	public List<Order> searchOrder(String searchQuery) {
	    String sql = """
	        SELECT * 
	        FROM [dbo].[order]
	        WHERE orderId LIKE ? 
	        OR userId LIKE ? 
	        OR cartId LIKE ?
	    """;
	    
	    List<Order> orders = new ArrayList<>();

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        String searchPattern = "%" + searchQuery + "%";  
	        statement.setString(1, searchPattern); 
	        statement.setString(2, searchPattern); 
	        statement.setString(3, searchPattern);   

	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Order order = new Order(
	                resultSet.getString("orderId"),
	                resultSet.getString("userId"),
	                resultSet.getString("cartId"),
	                resultSet.getString("promoId"),
	                resultSet.getString("shippingId"),
	                resultSet.getBoolean("status"),
	                resultSet.getString("paymentMethod"),
	                resultSet.getBoolean("paymentStatus"),
	                resultSet.getTimestamp("paymentDate"),
	                resultSet.getInt("totalCost")
	            );
	            orders.add(order);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return orders;
	}

	@Override
	public DefaultTableModel getAllDetailOrdersByUserId(String userId) {
	    String sql = """
	        SELECT 
	            o.orderId, 
	            u.fullName, 
	            p.productName, 
	            s.shopName, 
	            p.price, 
	            cd.quantity, 
	            o.paymentDate
	        FROM 
	            shop s
	        INNER JOIN product p ON s.shopId = p.shopId
	        INNER JOIN cartDetail cd ON p.productId = cd.productId
	        INNER JOIN [dbo].[order] o ON cd.cartId = o.cartId
	        INNER JOIN [dbo].[user] u ON o.userId = u.userId
	        WHERE 
	            s.userId = ?
	    """;
	    
	    String[] columnNames = {
	        "Mã ĐH", "Tên khách hàng", "Tên sản phẩm", 
	        "Tên cửa hàng", "Giá", "Số lượng", "Ngày thanh toán"
	    };

	    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, userId);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Vector<Object> row = new Vector<>();
	            row.add(resultSet.getString("orderId"));
	            row.add(resultSet.getString("fullName"));
	            row.add(resultSet.getString("productName"));
	            row.add(resultSet.getString("shopName"));
	            row.add(resultSet.getDouble("price"));
	            row.add(resultSet.getInt("quantity"));
	            row.add(resultSet.getTimestamp("paymentDate"));

	            tableModel.addRow(row);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return tableModel;
	}
}
