package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserDAO {
	
	public void addUser(String fullName, String email, String numberPhone, String address, String accountName, String password, String role) throws SQLException {
		String sql = "INSERT INTO [dbo].[user] (userId, fullName, email, phone, address, userName, password, role, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String userId = "KH" + (countUsers() + 1);
        
        LocalDateTime now = LocalDateTime.now();
        
        Timestamp timestamp = Timestamp.valueOf(now);

        try (Connection connection = ConnectDB.getConnection();
        		
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userId); 
            statement.setString(2, fullName);
            statement.setString(3, email);
            statement.setString(4, numberPhone);
            statement.setString(5, address);
            statement.setString(6, accountName);
            statement.setString(7, password);
            statement.setString(8, role);
			statement.setTimestamp(9, timestamp);

            statement.executeUpdate();
        } catch (SQLException e) {      	
			 e.printStackTrace(); throw new SQLException("Error! Cant add user"); 	 
        }
    }
	
	public int countUsers() {
	    int count = 0;
	    String sql = "SELECT COUNT(*) FROM [dbo].[user]";

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
	
	public boolean checkDuplicate(String column, String value) {
	    String query = "SELECT COUNT(*) FROM [dbo].[user] WHERE " + column + " = ?";
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setString(1, value);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0; 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}
