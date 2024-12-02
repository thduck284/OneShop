package controllers.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Product;
import service.ProductService;
import serviceImpl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/user/product-by-category"})
public class ProductByCategoryController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductServiceImpl();
	private static final Map<String, List<String>> CATEGORY_MAP = new HashMap<>();
	
	static {
        CATEGORY_MAP.put("group1", Arrays.asList("CATE1", "CATE2", "CATE3", "CATE4", "CATE5"));
        CATEGORY_MAP.put("group2", Arrays.asList("CATE6", "CATE7", "CATE8", "CATE9", "CATE10", "CATE11"));
        CATEGORY_MAP.put("group3", Arrays.asList("CATE12", "CATE13", "CATE14", "CATE15", "CATE16", "CATE17"));
        CATEGORY_MAP.put("group4", Arrays.asList("CATE18", "CATE19", "CATE20", "CATE21", "CATE22", "CATE23"));
        CATEGORY_MAP.put("group5", Arrays.asList("CATE24", "CATE25", "CATE26", "CATE27", "CATE28", "CATE29"));
        CATEGORY_MAP.put("group6", Arrays.asList("CATE30", "CATE31", "CATE32", "CATE33", "CATE34", "CATE35"));
    }
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		String categoryId = request.getParameter("category");
		List<String> lstCategory = CATEGORY_MAP.get(categoryId);
		List<Product> lstProduct = new ArrayList<>();
		
		if (lstCategory != null) {
			for (String id : lstCategory) {
	            List<Product> productsByCategory = productService.getAllProductsByCategoryId(id);
	            lstProduct.addAll(productsByCategory);
	        }
        } else {
        	List<Product> productsByCategory = productService.getAllProductsByCategoryId(categoryId);
            lstProduct.addAll(productsByCategory);
        }
		
		request.setAttribute("lstProduct", lstProduct);
        request.getRequestDispatcher("/views/user/productByCategory.jsp").forward(request, response);
	}
}
