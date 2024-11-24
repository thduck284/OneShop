package models;

import java.time.LocalDateTime;

public class Shop {
    private String shopId;                
    private String userId;                 
    private String shopName;          
    private String description;        
    private String status;             
    private LocalDateTime createdDate; 

    public Shop() {
    }

    public Shop(String shopId, String userId, String shopName, String description, String status, LocalDateTime createdDate) {
        this.shopId = shopId;
        this.userId = userId;
        this.shopName = shopName;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
