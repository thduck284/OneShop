package models;

import java.util.Date;

public class Order {
    private String orderId;
    private String userId;
    private String cartId;
    private String promoId;
    private String shippingId;
    private Boolean status;
    private String paymentMethod;
    private Boolean paymentStatus;
    private Date paymentDate;
    private int totalCost;

    public Order() {
		// TODO Auto-generated constructor stub
	}
    
    public Order(String orderId, String userId, String cartId, String promoId, String shippingId, 
                 Boolean status, String paymentMethod, Boolean paymentStatus, Date paymentDate, int totalCost) {
        this.orderId = orderId;
        this.userId = userId;
        this.cartId = cartId;
        this.promoId = promoId;
        this.shippingId = shippingId;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.totalCost = totalCost;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public String getShippingId() {
        return shippingId;
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
