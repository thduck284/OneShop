package models;

import java.time.LocalDateTime;

public class Product {
	private String productId;
	private String productName;
	private String description;
	private double price;
	private int quantity;
	private String categoryId;
	private String imageUrl;
	private LocalDateTime createdDate;
	
	public Product(String productId, String productName, String description,
				double price, int quantity, String categoryId,
				String imageUrl, LocalDateTime createdDate) {
		
		// TODO Auto-generated constructor stub
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.categoryId = categoryId;
		this.imageUrl = imageUrl;
		this.createdDate = createdDate;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}	
}
