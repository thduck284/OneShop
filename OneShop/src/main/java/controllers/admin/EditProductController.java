package controllers.admin;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import models.Product;
import service.ProductService;
import serviceImpl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/admin/edit-product"})
@MultipartConfig
public class EditProductController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl();
	Product product = new Product();
	String productId;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		productId = request.getParameter("productId");
		product = productService.getProductById(productId);
		request.setAttribute("product", product);
		
		request.getRequestDispatcher("/views/admin/editProduct.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		product.setProductName((request.getParameter("productName") == null) ? product.getProductName() : request.getParameter("productName")) ;
		product.setPrice((float) ((request.getParameter("price") == null) ? product.getPrice() : Float.parseFloat(request.getParameter("price")))); 	
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
			 
		        Product existingProduct = productService.getProductById(productId); 
		        if (existingProduct != null) {
		            imageData = existingProduct.getImage(); 
		        }
		 }
		 
		 product.setImage(imageData);
		  
		productService.updateProduct(product); 
		response.sendRedirect(request.getContextPath() + "/admin/product");
    }
}
