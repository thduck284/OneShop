package controllers.admin;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import models.Product;

@WebServlet(urlPatterns = {"/admin/edit-product"})
@MultipartConfig
public class EditProductController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	ProductDAO productDAO = new ProductDAO();
	Product product = new Product();
	String productId;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		productId = request.getParameter("productId");
		product = productDAO.getProductById(productId);
		request.setAttribute("product", product);
		
		request.getRequestDispatcher("/views/admin/editProduct.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String productName = (request.getParameter("productName") == null) ? product.getProductName() : request.getParameter("productName");
		Float price = (float) ((request.getParameter("price") == null) ? product.getPrice() : Float.parseFloat(request.getParameter("price"))); 	
		int quantity = (int) ((request.getParameter("quantity") == null) ? product.getQuantity() : Integer.parseInt(request.getParameter("quantity"))); 
		String categoryId = (request.getParameter("categoryId") == null) ? product.getCategoryId() : request.getParameter("categoryId"); 
		String description = (request.getParameter("description") == null) ? product.getDescription() : request.getParameter("description"); 
		Date createdDate = (request.getParameter("createdDate") == null) ? product.getCreatedDate() : Date.valueOf(request.getParameter("createdDate"));

		  
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
			 
		        Product existingProduct = productDAO.getProductById(productId); 
		        if (existingProduct != null) {
		            imageData = existingProduct.getImage(); 
		        }
		 }
		  
		try { 
			
			productDAO.updateProduct(productId,productName, description, price, quantity, categoryId, imageData, createdDate); 
			response.sendRedirect(request.getContextPath() + "/admin/product");
		} 
		catch (SQLException e) {
			e.printStackTrace(); 
			return;
		}
    }
}
