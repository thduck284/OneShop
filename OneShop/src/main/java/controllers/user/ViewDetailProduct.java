package controllers.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Category;
import models.Product;
import models.Shop;
import service.CategoryService;
import service.ProductService;
import service.ShopService;
import service.WishListService;
import serviceImpl.CategoryServiceImpl;
import serviceImpl.ProductServiceImpl;
import serviceImpl.ShopServiceImpl;
import serviceImpl.WishListServiceImpl;

@WebServlet(urlPatterns = {"/user/view-detail-product"})
public class ViewDetailProduct extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductServiceImpl();
	private CategoryService categoryService = new CategoryServiceImpl();
	private ShopService shopService = new ShopServiceImpl();
	private WishListService wishListService = new WishListServiceImpl();
	private Product product = new Product();
	private Category category = new Category();
	private Shop shop = new Shop();
	private String productId;
	private String userId;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		userId = request.getParameter("userId");
		productId = request.getParameter("productId");
		
		String status = (wishListService.checkWishList(userId, productId) ? "true" : "false");
		product = productService.getProductById(productId);
		category = categoryService.getCategoryById(product.getCategoryId());
		shop = shopService.getShopById(product.getShopId());
	    
		request.setAttribute("status", status);
		request.setAttribute("product", product);
		request.setAttribute("category", category);
		request.setAttribute("shop", shop);
		request.getRequestDispatcher("/views/user/viewDetailProduct.jsp").forward(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		
    }
}
