package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cart {
	private String cartId;
	private String userId;
	private String fullName;
	private int totalPrice;
	private LocalDateTime createdDate;
	private boolean status;
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}
	
	public Cart(String cartId, String userId, String fullName, int totalPrice, LocalDateTime createdDate, boolean status) {
		
		this.cartId = cartId;
		this.userId = userId;
		this.fullName = fullName;
		this.totalPrice = totalPrice;
		this.createdDate = createdDate;
		this.status = status;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getFormattedCreatedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return createdDate.format(formatter);
    }
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
