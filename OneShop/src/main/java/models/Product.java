package models;

import java.sql.Date;

public class Product {
	private String productId;
	private String categoryId;
	private String shopId;
	private String productName;
	private String description;
	private int price;
	private int quantity;
	private byte[] image;
	private Date createdDate;
	private String formattedCreatedDate;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public Product(String productId, String categoryId, String shopId,
				String productName, String description, int price, 
				int quantity, byte[] image, Date createdDate) {
		
		// TODO Auto-generated constructor stub
		this.productId = productId;
		this.categoryId = categoryId;
		this.shopId = shopId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
		this.createdDate = createdDate;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	
	public String getFormattedCreatedDate() {
        return formattedCreatedDate;
    }

    public void setFormattedCreatedDate(String formattedCreatedDate) {
        this.formattedCreatedDate = formattedCreatedDate;
    }
}
