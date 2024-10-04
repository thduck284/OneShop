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

@WebServlet(urlPatterns = {"/admin/add-product"})
@MultipartConfig
public class AddProductController extends HttpServlet{
		
	private static final long serialVersionUID = 1L;
	ProductDAO productDAO = new ProductDAO();
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
		request.getRequestDispatcher("/views/admin/addProduct.jsp").forward(request, response);	 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String productName = request.getParameter("productName");
		Float price = Float.parseFloat(request.getParameter("price")); 	
		int quantity = Integer.parseInt(request.getParameter("quantity")); 
		String categoryId = request.getParameter("categoryId"); 
		String description = request.getParameter("description"); 
		Date createdDate = Date.valueOf(request.getParameter("createdDate"));
		  
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
		  
		try { 
			
			productDAO.addProduct(productName, description, price, quantity, categoryId, imageData, createdDate); 
			request.setAttribute("message", "Thêm sản phẩm thành công!");
		} 
		catch (SQLException e) {
			e.printStackTrace(); 
			request.setAttribute("message", "Lỗi khi thêm sản phẩm!");
			return;
		}
		
		request.getRequestDispatcher("/views/admin/addProduct.jsp").forward(request, response);
    }
}
