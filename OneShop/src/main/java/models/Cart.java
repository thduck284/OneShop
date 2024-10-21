package models;

import java.time.LocalDateTime;

public class Cart {
	private String cartId;
	private String userId;
	private String userName;
	private LocalDateTime createdDate;
	
	public Cart(String cartId, String userId, String userName, LocalDateTime createdDate) {
		
		this.cartId = cartId;
		this.userId = userId;
		this.userName = userName;
		this.createdDate = createdDate;
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
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
