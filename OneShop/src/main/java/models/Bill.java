package models;

public class Bill {
	private String billId;
	private String userId;
	private double totalPrice;
	private String status;
	private String paymentMethod;
	private String shippingAddress;
	private String createdDate;
	
	public Bill(String billId, String userId, double totalPrice, String status, 
			String paymentMethod, String shippingAddress, String createdDate) {
		
		this.billId = billId;
		this.userId= userId;
		this.totalPrice = totalPrice;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.shippingAddress = shippingAddress;
		this.createdDate = createdDate;
	}	

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
