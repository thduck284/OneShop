package dao;

import java.util.List;

import models.User;

public interface UserDAO {
	
	void addUser(User user);
	void updateUser(User user);
	void deleteUser(String userId);
	void resetPassword(String email, String password);
	User getUserById(String userId);
	List<User> getAllUserByRole(String role);
	int countUsersByRole(String role);
	boolean checkDuplicate(String column, String value);
	User getInforUser(String username);
}
