package dao;

import java.util.List;

import models.Product;
import models.ProductFavorite;
import models.ProductReview;

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

	List<Product> getNewProducts(int page, int pageSize);
	List<Product> getBestSellingProducts();
	List<Product> getTopRatedProducts();
	List<ProductFavorite> getProductsByCriteria(String criteria, int page, int pageSize);


	List<ProductFavorite> getProductFavorites();
	List<ProductFavorite> getPagedFavoriteProducts(int page, int pageSize);
	int countFavoriteProducts();
	int countNewProducts();

	List<ProductReview> getProductsManyRated(int page, int pageSize);
	int countProductsManyRated();
	List<Product> getProductManyBuy(int page, int pageSize);
	int countProductsManyBuy();

}
