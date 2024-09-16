package models;

public class Cart {
	private String cartId;
	private String userId;
	private String createdDate;
	
	public Cart(String cartId, String userId, String createdDate) {
		
		// TODO Auto-generated constructor stub
		this.cartId = cartId;
		this.userId = userId;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
