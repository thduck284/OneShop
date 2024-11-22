package models;

import java.util.Date;

public class Promotion {
    private String promoId;
    private int price;
    private Date expirationDate;

    public Promotion(String promoId, int price, Date expirationDate) {
        this.promoId = promoId;
        this.price = price;
        this.expirationDate = expirationDate;
    }

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
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
