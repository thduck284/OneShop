package models;

import java.sql.Date;

public class Product {
	private String productId;
	private String productName;
	private String description;
	private double price;
	private int quantity;
	private String categoryId;
	private byte[] image;
	private Date createdDate;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product(String productId, String productName, String description,
				double price, int quantity, String categoryId,
				byte[] image, Date createdDate) {
		
		// TODO Auto-generated constructor stub
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.categoryId = categoryId;
		this.image = image;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}	
}
