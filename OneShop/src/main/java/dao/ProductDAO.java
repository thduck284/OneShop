package dao;

import java.util.List;

import models.Product;

public interface ProductDAO {
	
	void addProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(String productId);
	Product getProductById(String productId);
	int countProducts();
	byte[] getImageById(String productId);
	List<Product> getAllProducts();
}
