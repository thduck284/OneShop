package service;

import java.util.List;

import models.Product;
import models.ProductFavorite;

public interface ProductService {
	
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
	List<ProductFavorite> getProductFavorites();
	List<ProductFavorite> getPagedFavoriteProducts(int page, int pageSize);
	List<Product> getNewProducts(int page, int pageSize);
	List<Product> getProductsByCriteria(String criteria, int page, int pageSize);
	int countFavoriteProducts();
	int countNewProducts();
}
