package controllers.vendor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Order;
import models.OrderDerivative;
import models.Product;
import models.Promotion;
import models.Review;
import models.Shop;
import models.User;
import service.OrderService;
import service.ProductService;
import service.PromotionService;
import service.ReviewService;
import service.ShopService;
import service.UserService;
import serviceImpl.OrderServiceImpl;
import serviceImpl.ProductServiceImpl;
import serviceImpl.PromotionServiceImpl;
import serviceImpl.ReviewServiceImpl;
import serviceImpl.ShopServiceImpl;
import serviceImpl.UserServiceImpl;

@WebServlet(urlPatterns = {"/vendor/search-shop", "/vendor/search-product", "/vendor/search-order"
		, "/vendor/search-detail-order", "/vendor/search-customer", "/vendor/search-promotion"
		, "/vendor/search-review"})
public class SearchController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private ShopService shopService = new ShopServiceImpl();
	private ProductService productService = new ProductServiceImpl();
	private UserService userService = new UserServiceImpl();
	private OrderService orderService = new OrderServiceImpl();
	private ReviewService reviewService = new ReviewServiceImpl();
	private PromotionService promotionService = new PromotionServiceImpl();
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String searchQuery = request.getParameter("searchQuery");
        String servletPath = request.getServletPath();
        
        User user = (User) httpRequest.getSession().getAttribute("vendorInfor");
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        List<?> results = null;

        if ("/vendor/search-shop".equals(servletPath)) {
        	List<Shop> listShop = shopService.searchShop(searchQuery);
        	List<Map<String, Object>> shopDataList = new ArrayList<>();
        	
        	for (Shop shop : listShop) {
                Map<String, Object> shopDataMap = new HashMap<>();
                shopDataMap.put("shopId", shop.getShopId());
                shopDataMap.put("userId", shop.getUserId());
                shopDataMap.put("shopName", shop.getShopName());
                shopDataMap.put("description", shop.getDescription());
                shopDataMap.put("status", shop.getStatus());
                shopDataMap.put("createdDate", shop.getFormattedCreatedDate());
                
                if(shop.getUserId().equals(user.getUserId())) {
                	shopDataList.add(shopDataMap);
                }
            }
        	
            results = shopDataList;
        } else if ("/vendor/search-product".equals(servletPath)) {
        	
        	List<Product> listProduct = productService.getAllProductsByUserId(user.getUserId());
        	List<Product> productSearch = new ArrayList<>();
        	String lowerCaseKeyword = searchQuery.toLowerCase();
        	
        	 productSearch = listProduct.stream()
                     .filter(product -> 
                         product.getProductName().toLowerCase().contains(lowerCaseKeyword) || 
                         product.getDescription().toLowerCase().contains(lowerCaseKeyword))
                     .collect(Collectors.toList());
        	 
        	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
             for (Product product : productSearch) {
                 if (product.getCreatedDate() != null) {
                	 String formattedDate = sdf.format(product.getCreatedDate());
                     product.setFormattedCreatedDate(formattedDate);
                 }
             }
        	 
        	 results = productSearch;
        } else if ("/vendor/search-order".equals(servletPath)) {
        	
        	List<Order> listOrder = orderService.getAllOrdersByUserId(user.getUserId());
        	List<Order> orderSearch = new ArrayList<>();
        	String lowerCaseKeyword = searchQuery.toLowerCase();
        	
        	orderSearch = listOrder.stream()
                    .filter(order -> 
                    	order.getOrderId().toLowerCase().contains(lowerCaseKeyword) ||
                    	order.getCartId().toLowerCase().contains(lowerCaseKeyword) ||
                    	order.getUserId().toLowerCase().contains(lowerCaseKeyword))
                    .collect(Collectors.toList());
        	
        	results = orderSearch;
        } else if ("/vendor/search-detail-order".equals(servletPath)) {
        	
        	DefaultTableModel tableOrder = orderService.getAllDetailOrdersByUserId(user.getUserId());
        	List<OrderDerivative> orderDerivativeSearch = new ArrayList<>(); 
        	String lowerCaseKeyword = searchQuery.toLowerCase();
        	
        	for (int i = 0; i < tableOrder.getRowCount(); i++) {
                String orderId = (String) tableOrder.getValueAt(i, 0);
                String fullName = (String) tableOrder.getValueAt(i, 1);
                String productName = (String) tableOrder.getValueAt(i, 2);
                String shopName = (String) tableOrder.getValueAt(i, 3);
                Double price = (Double) tableOrder.getValueAt(i, 4);
                Integer quantity = (Integer) tableOrder.getValueAt(i, 5);
                Object paymentDateObj = tableOrder.getValueAt(i, 6);
                String paymentDate = null;
                if (paymentDateObj instanceof java.sql.Timestamp) {
                    java.sql.Timestamp timestamp = (java.sql.Timestamp) paymentDateObj;
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm"); 
                    paymentDate = sdf.format(timestamp);  
                } else {
                    paymentDate = "Không có ngày";  
                }
                
                OrderDerivative orderDerivative = new OrderDerivative();
                orderDerivative.setOrderId(orderId);
                orderDerivative.setFullName(fullName);
                orderDerivative.setProductName(productName);
                orderDerivative.setShopName(shopName);
                orderDerivative.setPrice(price);
                orderDerivative.setQuantity(quantity);
                orderDerivative.setPaymentString(paymentDate);
                
                orderDerivativeSearch.add(orderDerivative);
            }
        	
        	List<OrderDerivative> orderSearch = new ArrayList<>(); 
        	
        	for (OrderDerivative order : orderDerivativeSearch) {
                if (order.getFullName().toLowerCase().contains(lowerCaseKeyword) ||
                    order.getProductName().toLowerCase().contains(lowerCaseKeyword) ||
                    order.getShopName().toLowerCase().contains(lowerCaseKeyword)) {
                    
                    orderSearch.add(order);
                }
            }
        	
        	results = orderSearch;
        } else if ("/vendor/search-customer".equals(servletPath)) {
        	
        	List<User> listCustomer = userService.getCustomerByUserId(user.getUserId());
        	Set<String> seenUserIds = new HashSet<>();
            List<User> uniqueCustomers = listCustomer.stream()
                .filter(customer -> seenUserIds.add(customer.getUserId()))  
                .collect(Collectors.toList());
            
        	List<User> customerSearch = new ArrayList<>();
        	String lowerCaseKeyword = searchQuery.toLowerCase();
        	
        	for (User customer : uniqueCustomers) {
                if (customer.getUserId().toLowerCase().contains(lowerCaseKeyword) ||
                    customer.getFullName().toLowerCase().contains(lowerCaseKeyword) ||
                    customer.getAddress().toLowerCase().contains(lowerCaseKeyword)) {
                    
                    customerSearch.add(customer);
                }
            }
        	
        	results = customerSearch;
        } else if ("/vendor/search-review".equals(servletPath)) {
        	
        	List<Review> listReview = reviewService.getReviewByUserId(user.getUserId());
        	List<Review> reviewSearch = new ArrayList<>();
        	String lowerCaseKeyword = searchQuery.toLowerCase();
        	
        	reviewSearch = listReview.stream()
                    .filter(review -> 
                    	review.getReviewId().toLowerCase().contains(lowerCaseKeyword) ||
                    	review.getProductId().toLowerCase().contains(lowerCaseKeyword) ||
                    	review.getUserId().toLowerCase().contains(lowerCaseKeyword))
                    .collect(Collectors.toList());
        	
        	results = reviewSearch;
        	
        } else {
        	List<Promotion> listPromotion = promotionService.getAllPromotion();
        	List<Promotion> promotionSearch = new ArrayList<>();
        	String lowerCaseKeyword = searchQuery.toLowerCase();
        	
        	for (Promotion promotion : listPromotion) {
                if (promotion.getUserId().toLowerCase().contains(lowerCaseKeyword) ||
                    promotion.getPromotionId().toLowerCase().contains(lowerCaseKeyword)) {
                	
                	String expirationString = new SimpleDateFormat("yyyy-MM-dd").format(promotion.getExpirationDate());
                    promotion.setExpirationString(expirationString);
                    
                    promotionSearch.add(promotion);
                }
            }
        	
        	results = promotionSearch;
        }
        
        if (results != null) {
            String jsonResponse = new ObjectMapper().writeValueAsString(results);
            response.getWriter().write(jsonResponse);
        } else {
            response.getWriter().write("[]"); 
        }
	}
}
