package dao;

import java.util.List;

import models.Category;

public interface CategoryDAO {
	
	void addCategory(Category category);
	void updateCategory(Category category);
	void deleteCategory(String categoryId);
	int countCategories();
	Category getCategoryById(String categoryId);
	List<Category> getAllCategories();
	List<String> getAllCategoryIds();
}
