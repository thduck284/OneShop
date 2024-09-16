package models;

public class OrderDetail {
	private String orderDetailId;
	private String orderId;
	private String productId;
	private int quantity;
	private double price;
	
	public OrderDetail(String orderDetailId, String orderId, String productId,
			int quantity, double price) {
		
		// TODO Auto-generated constructor stub
		this.orderDetailId = orderDetailId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	public String getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}	
}
