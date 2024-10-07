package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserDAO {
	
	public void addUser(String fullName, String email, String numberPhone, String address, String accountName, String password, String role) throws SQLException {
	    String sql = "INSERT INTO [dbo].[user] (userId, fullName, email, phone, address, userName, password, role, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    String userId = null;
	    
	    if(role.equals("customer"))
	    {
	    	userId = "KH" + (countUsersByRole(role) + 1);
	    } else {
	    	userId = "M" + (countUsersByRole(role) + 1);
	    }

	    LocalDate now = LocalDate.now();
	    java.sql.Date sqlDate = java.sql.Date.valueOf(now); 

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
	        statement.setDate(9, sqlDate);  

	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error! Can't add user.");
	    }
	}

	
	public void updateUserById(String userId, String fullName, String email, String numberPhone, String address, String accountName, String password, String role) throws SQLException {
	    String sql = "UPDATE [dbo].[user] SET fullName = ?, email = ?, phone = ?, address = ?, userName = ?, password = ?, role = ? WHERE userId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, fullName);
	        statement.setString(2, email);
	        statement.setString(3, numberPhone);
	        statement.setString(4, address);
	        statement.setString(5, accountName);
	        statement.setString(6, password);
	        statement.setString(7, role);
	        statement.setString(8, userId);

	        int rowsUpdated = statement.executeUpdate();

	        if (rowsUpdated > 0) {
	            System.out.println("User updated successfully!");
	        } else {
	            throw new SQLException("No user found with the given ID.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error! Can't update user.");
	    }
	}
	
	public void deleteUserById(String userId) throws SQLException {
	    String sql = "DELETE FROM [dbo].[user] WHERE userId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, userId);

	        int rowsDeleted = statement.executeUpdate();

	        if (rowsDeleted > 0) {
	            System.out.println("User deleted successfully!");
	        } else {
	            throw new SQLException("No user found with the given ID.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error! Can't delete user.");
	    }
	}
	
	public void resetPassword(String email, String password) throws SQLException
	{
		 String sql = "UPDATE [dbo].[user] SET password = ? WHERE email = ?";

		    try (Connection connection = ConnectDB.getConnection();
		         PreparedStatement statement = connection.prepareStatement(sql)) {

		        statement.setString(1, password); 
		        statement.setString(2, email);  

		        int rowsUpdated = statement.executeUpdate();

		        if (rowsUpdated > 0) {
		            System.out.println("Cập nhật mật khẩu thành công.");
		        } else {
		            throw new SQLException("Không tìm thấy người dùng với email: " + email);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new SQLException("Error! Unable to update user password.");
		    }
	}
	
	public User getUserById(String userId) throws SQLException {
	    String sql = "SELECT userId, fullName, email, phone, address, userName, password, role, createdDate FROM [dbo].[user] WHERE userId = ?";
	    
	    User user = null;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, userId);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                
	                user = new User();
	                user.setUserId(resultSet.getString("userId"));
	                user.setFullName(resultSet.getString("fullName"));
	                user.setEmail(resultSet.getString("email"));
	                user.setPhoneNumber(resultSet.getString("phone"));
	                user.setAddress(resultSet.getString("address"));
	                user.setAccountName(resultSet.getString("userName"));
	                user.setPassword(resultSet.getString("password"));
	                user.setRole(resultSet.getString("role"));
	                user.setCreatedDate(resultSet.getDate("createdDate"));
	            } else {
	                throw new SQLException("No user found with the given ID.");
	            }
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Error! Can't retrieve user.");
	    }
	    
	    return user;
	}

	
	public List<User> getAllUserByRole(String role) {
		List<User> userList = new ArrayList<>();
	    String sql = "SELECT * FROM [dbo].[user] WHERE role = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	    	
	        statement.setString(1, role);
	        
	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                User user = new User(
	                    resultSet.getString("userId"),
	                    resultSet.getString("userName"),
	                    resultSet.getString("email"),
	                    resultSet.getString("password"),
	                    resultSet.getString("fullName"),
	                    resultSet.getString("phone"),
	                    resultSet.getString("address"),
	                    resultSet.getString("role"),
	                    resultSet.getDate("createdDate")
	                );
	                userList.add(user);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return userList;
    }
	
	public int countUsersByRole(String role) {
	    int count = 0;
	    String sql = "SELECT COUNT(*) FROM [dbo].[user] WHERE role = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, role);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                count = resultSet.getInt(1);
	            }
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
	
	public User getInforUser(String username) {
        String sql = "SELECT * FROM [dbo].[user] WHERE username = ?";
        User user = null;

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String userID = resultSet.getString("userID");
                String fullName = resultSet.getString("fullName");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String userName = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                Date createdDate = resultSet.getDate("createdDate");

                user = new User(userID, userName, email, password, fullName, phone, address, role, createdDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
