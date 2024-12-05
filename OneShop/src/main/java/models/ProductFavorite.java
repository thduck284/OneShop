package models;


import java.util.Arrays;

public class ProductFavorite {
    private String productId;
    private String productName;
    private int price;
    private byte[] image;
    private String shopId;
    private int favoriteCount;

    //Getter and Setter

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
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



    public ProductFavorite(int favoriteCount, byte[] image, int price, String productId, String productName, String shopId) {
        this.favoriteCount = favoriteCount;
        this.image = image;
        this.price = price;
        this.productId = productId;
        this.productName = productName;
        this.shopId = shopId;

    }

    @Override
    public String toString() {
        return "ProductFavorite{" +
                "favoriteCount=" + favoriteCount +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", image=" + Arrays.toString(image) +
                ", shopId='" + shopId + '\'' +
                '}';
    }
}
