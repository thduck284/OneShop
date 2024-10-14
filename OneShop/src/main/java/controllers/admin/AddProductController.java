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

@WebServlet(urlPatterns = {"/admin/add-product"})
@MultipartConfig
public class AddProductController extends HttpServlet{
		
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl();
	Product product = new Product();
	
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		request.getRequestDispatcher("/views/admin/addProduct.jsp").forward(request, response);	 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		product.setProductName(request.getParameter("productName")) ;
		product.setPrice(Float.parseFloat(request.getParameter("price"))); 	
		product.setQuantity(Integer.parseInt(request.getParameter("quantity"))); 
		product.setCategoryId(request.getParameter("categoryId")); 
		product.setDescription(request.getParameter("description")); 
		product.setCreatedDate(Date.valueOf(request.getParameter("createdDate")));
		  
		Part imagePart = request.getPart("image"); 
		byte[] imageData = null; 
		if(imagePart != null && imagePart.getSize() > 0) { 
			try (InputStream inputStream = imagePart.getInputStream()) { 
				
				imageData = new byte[(int)
				imagePart.getSize()]; 
				inputStream.read(imageData); 
			} 
			catch (IOException e) {
			    e.printStackTrace(); 
			    return;
			} 				
		}
		
		product.setImage(imageData);
		
		productService.addProduct(product); 
		request.setAttribute("message", "Thêm sản phẩm thành công!");
		
		request.getRequestDispatcher("/views/admin/addProduct.jsp").forward(request, response);
    }
}
