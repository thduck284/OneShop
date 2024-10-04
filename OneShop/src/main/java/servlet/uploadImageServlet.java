package servlet;

import java.io.IOException;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image")
public class uploadImageServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO = new ProductDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("id");;  

        try {
            
            byte[] imageData = productDAO.getImageById(productId);

            if (imageData != null) {
               
                response.setContentType("image/jpeg");
                response.getOutputStream().write(imageData);
            } else {
                
                response.sendError(HttpServletResponse.SC_NOT_FOUND);  
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);  
        }
    }

}
