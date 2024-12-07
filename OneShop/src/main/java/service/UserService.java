package service;

import java.util.List;

import models.User;

public interface UserService {
	
	void addUser(User user);
	void updateUser(User user);
	void deleteUser(String userId);
	void resetPassword(String email, String password);
	int countUsersByRole(String role);
	boolean checkDuplicate(String column, String value);
	boolean validUser(String userName, String password);
	User getInforUser(String username);
	User getUserById(String userId);
	List<User> getAllUserByRole(String role);
	List<String> getAllUserIdsByRole(String role);
	List<User> getCustomerByUserId(String userId);
	List<User> searchUser(String searchQuery, String role);
}
