package dao;

import java.util.List;

import models.Category;

public interface CategoryDAO {
	
	void addCategory(Category category);
	void updateCategory(Category category);
	void deleteCategory(String categoryId);
	Category getCategoryById(String categoryId);
	int countCategories();
	List<Category> getAllCategories();
}
