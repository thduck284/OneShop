package serviceImpl;

import java.util.List;

import dao.ProductDAO;
import daoImpl.ProductDAOImpl;
import models.Product;
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

}
