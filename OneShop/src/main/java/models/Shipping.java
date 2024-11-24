package models;

public class Shipping {
    private String shippingId;
    private String distance;
    private int cost;

    public Shipping(String shippingId, String distance, int cost) {
        this.shippingId = shippingId;
        this.distance = distance;
        this.cost = cost;
    }

    public String getShippingId() {
        return shippingId;
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
