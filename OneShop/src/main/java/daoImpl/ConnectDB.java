package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	
	private static final String URL = "jdbc:sqlserver://NGHIATRAN:1433;databaseName=OneShop;encrypt=false;trustServerCertificate=true;";
    
    public static Connection getConnection() throws SQLException {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, "sa", "16092004");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("SQL Server Driver not found");
        } catch (SQLException e) {
            System.out.println("Connection failed to database: " + URL);
            System.out.println("Error message: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
	public static void testConnection() {
		try (Connection connection = getConnection()) {
			if (connection != null) {
				System.out.println("Database connection test successful!");
			} else {
				System.err.println("Database connection test failed.");
			}
		} catch (SQLException e) {
			System.err.println("Error during connection test: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		testConnection();
	}
    
	/*
	 * public void addUser() throws SQLException { String sql =
	 * "INSERT INTO abcd(userId, fullName, email, phoneNumber, address, accountName, password, role, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
	 * ;
	 * 
	 * try (Connection connection = ConnectDB.getConnection();
	 * 
	 * PreparedStatement statement = connection.prepareStatement(sql)) {
	 * 
	 * statement.setString(1, "KH01"); statement.setString(2, "admin");
	 * statement.setString(3, "admin"); statement.setString(4, "admin");
	 * statement.setString(5, "admin"); statement.setString(6, "admin");
	 * statement.setString(7, "admin"); statement.setString(8, "admin");
	 * statement.setTimestamp(9, null);
	 * 
	 * statement.executeUpdate(); } catch (SQLException e) {
	 * 
	 * e.printStackTrace(); throw new SQLException("Error! Cant add user");
	 * 
	 * }
	 * 
	 * }
	 */
}
