package models;

public class OrderDerivative {
	
	private String orderId;
    private String fullName;
    private String productName;
    private String shopName;
    private Double price;
    private Integer quantity;
    private String paymentString;
    
    public OrderDerivative() {
		// TODO Auto-generated constructor stub
	}

    public OrderDerivative(String orderId, String fullName, String productName, String shopName, Double price, Integer quantity, String paymentString) {
        this.orderId = orderId;
        this.fullName = fullName;
        this.productName = productName;
        this.shopName = shopName;
        this.price = price;
        this.quantity = quantity;
        this.paymentString = paymentString;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPaymentString() {
        return paymentString;
    }

    public void setPaymentString(String paymentString) {
        this.paymentString = paymentString;
    }
}
