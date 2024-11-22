
package controllers.vendor;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import models.Product;
import service.CategoryService;
import service.ProductService;
import service.ShopService;
import serviceImpl.CategoryServiceImpl;
import serviceImpl.ProductServiceImpl;
import serviceImpl.ShopServiceImpl;

@WebServlet(urlPatterns = { "/vendor/edit-product" })

@MultipartConfig
public class EditProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ShopService shopService = new ShopServiceImpl();
	private CategoryService categoryService = new CategoryServiceImpl();
	private ProductService productService = new ProductServiceImpl();
	private Product product = new Product();
	private String productId;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		productId = request.getParameter("productId");
		
		List<String> listCategoryId = categoryService.getAllCategoryIds();
		List<String> listShopId = shopService.getAllShopIdByUserId(userId);
		product = productService.getProductById(productId);

		request.setAttribute("listCategoryId", listCategoryId);
		request.setAttribute("listShopId", listShopId);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/views/vendor/editProduct.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		product.setShopId((request.getParameter("shopId") == null) ? product.getShopId() : request.getParameter("shopId")) ;
		product.setProductName((request.getParameter("productName") == null) ? product.getProductName() : request.getParameter("productName")) ;
		product.setPrice((int) ((request.getParameter("price") == null) ? product.getPrice() : Integer.parseInt(request.getParameter("price")))); 	
		product.setQuantity((int) ((request.getParameter("quantity") == null) ? product.getQuantity() : Integer.parseInt(request.getParameter("quantity")))); 
		product.setCategoryId((request.getParameter("categoryId") == null) ? product.getCategoryId() : request.getParameter("categoryId")); 
		product.setDescription((request.getParameter("description") == null) ? product.getDescription() : request.getParameter("description")); 
		product.setCreatedDate((request.getParameter("createdDate") == null) ? product.getCreatedDate() : Date.valueOf(request.getParameter("createdDate")));
		  
		Part imagePart = request.getPart("image"); 
		byte[] imageData = null; 
		
		if (imagePart != null && imagePart.getSize() > 0) { 
			try (InputStream inputStream = imagePart.getInputStream()) { 
				imageData = new byte[(int) imagePart.getSize()]; 
		        inputStream.read(imageData); 
		    } catch (IOException e) {
		        e.printStackTrace(); 
		        return;
		    }
		 } else {
			 
			 imageData = product.getImage();;
		 }
		 
		product.setImage(imageData);  
		productService.updateProduct(product);
		
		request.setAttribute("message", "Cập nhật thông tin cửa hàng thành công!");
		request.getRequestDispatcher("/views/vendor/editProduct.jsp").forward(request, response);
	}
}
