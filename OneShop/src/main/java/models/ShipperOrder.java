package models;

import java.util.Date;

public class ShipperOrder {
    private String orderId;
    private String shipperId;
    private String status;
    private Date deliveryDate;

    public ShipperOrder(String orderId, String shipperId, String status, Date deliveryDate) {
        this.orderId = orderId;
        this.shipperId = shipperId;
        this.status = status;
        this.deliveryDate = deliveryDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShipperId() {
        return shipperId;
    }

    public void setShipperId(String shipperId) {
        this.shipperId = shipperId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
