package serviceImpl;

import java.util.List;

import dao.UserDAO;
import daoImpl.UserDAOImpl;
import models.User;
import service.UserService;

public class UserServiceImpl implements UserService{
	
	UserDAO userDAO = new UserDAOImpl();
	
	@Override
	public void addUser(User user) {
		
		userDAO.addUser(user);
	}
	
	@Override
	public void updateUser(User user) {
		
		userDAO.updateUser(user);
	}
	
	@Override
	public void deleteUser(String userId) {
		
		userDAO.deleteUser(userId);
	}
	
	@Override
	public void resetPassword(String email, String password) {
		
		userDAO.resetPassword(email, password);
	}
	
	@Override
	public int countUsersByRole(String role) {
		return userDAO.countUsersByRole(role);
	}
	
	@Override
	public boolean checkDuplicate(String column, String value) {
		
		return userDAO.checkDuplicate(column, value);
	}
	
	@Override
	public boolean validUser(String userName, String password) {
		// TODO Auto-generated method stub
		return userDAO.validUser(userName, password);
	}
	
	@Override
	public User getInforUser(String username) {
		return userDAO.getInforUser(username);
	}
	
	@Override
	public User getUserById(String userId) {
		
		return userDAO.getUserById(userId);
	}
	
	@Override
	public List<User> getAllUserByRole(String role) {
		return userDAO.getAllUserByRole(role);
	}

	@Override
	public List<String> getAllUserIdsByRole(String role) {
		// TODO Auto-generated method stub
		return userDAO.getAllUserIdsByRole(role);
	}

	@Override
	public List<User> getCustomerByUserId(String userId) {
		// TODO Auto-generated method stub
		return userDAO.getCustomerByUserId(userId);
	}

	@Override
	public List<User> searchUser(String searchQuery, String role) {
		// TODO Auto-generated method stub
		return userDAO.searchUser(searchQuery, role);
	}
}
