package models;

import java.util.Arrays;

public class ProductReview {
    private String productId;
    private String productName;
    private int price;
    private byte[] image;
    private String shopId;
    private int rateCount;

    public ProductReview(byte[] image, int price, String productId, int rateCount, String productName, String shopId) {
        this.image = image;
        this.price = price;
        this.productId = productId;
        this.rateCount = rateCount;
        this.productName = productName;
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "ProductReview{" +
                "image=" + Arrays.toString(image) +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", shopId='" + shopId + '\'' +
                ", rateCount=" + rateCount +
                '}';
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public int getRateCount() {
        return rateCount;
    }

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}