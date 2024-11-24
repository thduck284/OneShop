package models;

import java.util.Date;

public class PromoCustomer {
    private String promoId;
    private String userId;
    private int price;
    private Date expirationDate;

    public PromoCustomer(String promoId, String userId, int price, Date expirationDate) {
        this.promoId = promoId;
        this.userId = userId;
        this.price = price;
        this.expirationDate = expirationDate;
    }

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
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
}
