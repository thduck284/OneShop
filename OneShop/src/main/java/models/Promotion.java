package models;

import java.util.Date;

public class Promotion {
    private String promotionId;
    private String userId;
    private int price;
    private Date expirationDate;
    private boolean status;
    private String expirationString;

    public String getExpirationString() {
		return expirationString;
	}

	public Promotion() {
		// TODO Auto-generated constructor stub
	}
    
    public Promotion(String promotionId, String userId, int price, Date expirationDate, boolean status) {
        this.promotionId = promotionId;
        this.userId = userId;
        this.price = price;
        this.expirationDate = expirationDate;
        this.status = status;
    }

	public String getPromotionId() {
        return promotionId;
    }

    public void setPromoId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void setExpirationString(String expirationString) {
		this.expirationString = expirationString;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}
}
