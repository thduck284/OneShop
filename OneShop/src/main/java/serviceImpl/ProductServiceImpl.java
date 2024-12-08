package serviceImpl;

import java.util.List;

import dao.ProductDAO;
import daoImpl.ProductDAOImpl;
import models.Product;
import models.ProductFavorite;
import models.ProductReview;
import service.ProductService;

public class ProductServiceImpl implements ProductService{
	
	ProductDAO productDAO = new ProductDAOImpl();

	@Override
	public void addProduct(Product product) {
		productDAO.addProduct(product);
	}

	@Override
	public void updateProduct(Product product) {
		productDAO.updateProduct(product);
	}

	@Override
	public void deleteProduct(String productId) {
		productDAO.deleteProduct(productId);
	}

	@Override
	public Product getProductById(String productId) {
		return productDAO.getProductById(productId);
	}

	@Override
	public int countProducts() {
		return productDAO.countProducts();
	}

	@Override
	public byte[] getImageById(String productId) {
		return productDAO.getImageById(productId);
	}

	@Override
	public List<Product> getAllProducts() {
		return productDAO.getAllProducts();
	}

	@Override
	public List<Product> getAllProductsByShopId(String shopId) {
		// TODO Auto-generated method stub
		return productDAO.getAllProductsByShopId(shopId);
	}

	@Override
	public List<Product> getAllProductsByUserId(String userId) {
		// TODO Auto-generated method stub
		return productDAO.getAllProductsByUserId(userId);
	}

	@Override
	public List<Product> getAllProductsByCategoryId(String categoryId) {
		// TODO Auto-generated method stub
		return productDAO.getAllProductsByCategoryId(categoryId);
	}

	@Override
	public List<Product> searchProductsByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return productDAO.searchProductsByKeyword(keyword);
	}

	@Override
	public List<Product> getNewProducts(int page, int pageSize) {
		// TODO Auto-generated method stub
		return productDAO.getNewProducts(page, pageSize);
	}

	@Override
	public List<Product> getBestSellingProducts() {
		// TODO Auto-generated method stub
		return productDAO.getBestSellingProducts();
	}

	@Override
	public List<Product> getTopRatedProducts() {
		// TODO Auto-generated method stub
		return productDAO.getTopRatedProducts();
	}

	@Override
	public List<ProductFavorite> getProductsByCriteria(String criteria, int page, int pageSize) {
		// TODO Auto-generated method stub
		return productDAO.getProductsByCriteria(criteria, page, pageSize);
	}

	@Override
	public List<ProductFavorite> getProductFavorites() {
		// TODO Auto-generated method stub
		return productDAO.getProductFavorites();
	}

	@Override
	public List<ProductFavorite> getPagedFavoriteProducts(int page, int pageSize) {
		// TODO Auto-generated method stub
		return productDAO.getPagedFavoriteProducts(page, pageSize);
	}

	@Override
	public int countFavoriteProducts() {
		// TODO Auto-generated method stub
		return productDAO.countFavoriteProducts();
	}

	@Override
	public int countNewProducts() {
		// TODO Auto-generated method stub
		return productDAO.countNewProducts();
	}

	@Override
	public List<ProductReview> getProductsManyRated(int page, int pageSize) {
		// TODO Auto-generated method stub
		return productDAO.getProductsManyRated(page, pageSize);
	}

	@Override
	public int countProductsManyRated() {
		// TODO Auto-generated method stub
		return productDAO.countProductsManyRated();
	}

	@Override
	public List<Product> getProductManyBuy(int page, int pageSize) {
		// TODO Auto-generated method stub
		return productDAO.getProductManyBuy(page, pageSize);
	}

	@Override
	public int countProductsManyBuy() {
		// TODO Auto-generated method stub
		return productDAO.countProductsManyBuy();
	}

}
