package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

import dao.UserDAO;
import models.User;

public class UserDAOImpl implements UserDAO{
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
	    String sql = "INSERT INTO [dbo].[user] (userId, fullName, email, phone, address, userName, password, role, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    String userId = null;
	    
	    if(user.getRole().equals("customer"))
	    {
	    	userId = "KH" + UUID.randomUUID().toString().replace("-", "").substring(0, 7);
	    } else {
	    	userId = "V" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
	    }

	    LocalDate now = LocalDate.now();
	    java.sql.Date sqlDate = java.sql.Date.valueOf(now); 

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	    	
	    	String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	    	
	        statement.setString(1, userId); 
	        statement.setString(2, user.getFullName());
	        statement.setString(3, user.getEmail());
	        statement.setString(4, user.getPhoneNumber());
	        statement.setString(5, user.getAddress());
	        statement.setString(6, user.getUserName());
	        statement.setString(7, hashedPassword);
	        statement.setString(8, user.getRole());
	        statement.setDate(9, sqlDate);  

	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
	    String sql = "UPDATE [dbo].[user] SET fullName = ?, email = ?, phone = ?, address = ?, userName = ?, password = ?, role = ? WHERE userId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	    	String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	    	
	        statement.setString(1, user.getFullName());
	        statement.setString(2, user.getEmail());
	        statement.setString(3, user.getPhoneNumber());
	        statement.setString(4, user.getAddress());
	        statement.setString(5, user.getUserName());
	        statement.setString(6, hashedPassword);
	        statement.setString(7, user.getRole());
	        statement.setString(8, user.getUserId());

	        int rowsUpdated = statement.executeUpdate();

	        if (rowsUpdated > 0) {
	            System.out.println("User updated successfully!");
	        } else {
	            throw new SQLException("No user found with the given ID.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
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
	    }
	}
	
	@Override
	public void resetPassword(String email, String password){
		// TODO Auto-generated method stub
		 String sql = "UPDATE [dbo].[user] SET password = ? WHERE email = ?";

		    try (Connection connection = ConnectDB.getConnection();
		         PreparedStatement statement = connection.prepareStatement(sql)) {
		    	
		    	String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

		        statement.setString(1, hashedPassword); 
		        statement.setString(2, email);  

		        int rowsUpdated = statement.executeUpdate();

		        if (rowsUpdated > 0) {
		            System.out.println("Cập nhật mật khẩu thành công.");
		        } else {
		            throw new SQLException("Không tìm thấy người dùng với email: " + email);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	
	
	@Override
	public int countUsersByRole(String role) {
		// TODO Auto-generated method stub
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
	
	@Override
	public boolean checkDuplicate(String column, String value) {
		// TODO Auto-generated method stub
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
	
	@Override
	public boolean validUser(String userName, String password) {
		// TODO Auto-generated method stub
		String sql = "SELECT password FROM [dbo].[user] WHERE userName = ?";
	    boolean isValid = false;

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, userName);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                String hashedPassword = resultSet.getString("password"); 

	                if (BCrypt.checkpw(password, hashedPassword)) {
	                    isValid = true;  
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return isValid;
	}
	
	@Override
	public User getInforUser(String username) {
		// TODO Auto-generated method stub
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
	
	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
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
	                user.setUserName(resultSet.getString("userName"));
	                user.setPassword(resultSet.getString("password"));
	                user.setRole(resultSet.getString("role"));
	                user.setCreatedDate(resultSet.getDate("createdDate"));
	            } else {
	                throw new SQLException("No user found with the given ID.");
	            }
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return user;
	}

	@Override
	public List<User> getAllUserByRole(String role) {
		// TODO Auto-generated method stub
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

	@Override
	public List<String> getAllUserIdsByRole(String role) {
		// TODO Auto-generated method stub
		List<String> userIdList = new ArrayList<>();
	    String sql = "SELECT userId FROM [dbo].[user] WHERE role = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        
	        statement.setString(1, role);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                String userId = resultSet.getString("userId");
	                userIdList.add(userId);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return userIdList;
	}

	@Override
	public List<User> getCustomerByUserId(String shopUserId) {
		
	    List<User> userList = new ArrayList<>();
	    String sql = "SELECT u.userId, u.fullName, u.email, u.phone, u.address " +
	                 "FROM shop s " +
	                 "INNER JOIN product p ON s.shopId = p.shopId " +
	                 "INNER JOIN cartDetail cd ON p.productId = cd.productId " +
	                 "INNER JOIN [dbo].[order] o ON cd.cartId = o.cartId " +
	                 "INNER JOIN [dbo].[user] u ON o.userId = u.userId " +
	                 "WHERE s.userId = ?";

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        
	        statement.setString(1, shopUserId);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	            	User user = new User(
	            		    resultSet.getString("userId"),
	            		    null,
	            		    resultSet.getString("email"),
	            		    null,  
	            		    resultSet.getString("fullName"),  
	            		    resultSet.getString("phone"),
	            		    resultSet.getString("address"),
	            		    null,  
	            		    null  
	            		);

	                userList.add(user);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return userList;
	}

	@Override
	public List<User> searchUser(String keyword, String role) {
	    String sql = "SELECT * FROM [dbo].[user] WHERE (userId LIKE ? OR fullName LIKE ?) AND role = ?";
	    List<User> userList = new ArrayList<>();

	    try (Connection connection = ConnectDB.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, "%" + keyword + "%");
	        statement.setString(2, "%" + keyword + "%");
	        statement.setString(3, role); 

	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                String userID = resultSet.getString("userId");
	                String fullName = resultSet.getString("fullName");
	                String email = resultSet.getString("email");
	                String phone = resultSet.getString("phone");
	                String address = resultSet.getString("address");
	                String userName = resultSet.getString("username");
	                String password = resultSet.getString("password");
	                String userRole = resultSet.getString("role");
	                Date createdDate = resultSet.getDate("createdDate");

	                User user = new User(userID, userName, email, password, fullName, phone, address, userRole, createdDate);
	                userList.add(user);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return userList;
	}
}
