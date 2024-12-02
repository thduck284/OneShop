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
	List<Product> getAllProductsByCategoryId(String categoryId);
	List<Product> getAllProducts();
	List<Product> getAllProductsByShopId(String shopId);
	List<Product> getAllProductsByUserId(String userId);
	List<Product> searchProductsByKeyword(String keyword);
}
