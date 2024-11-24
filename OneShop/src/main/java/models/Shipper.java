package models;

public class Shipper {
    private String shipperId;
    private String shipperName;
    private String phone;
    private String status;

    public Shipper(String shipperId, String shipperName, String phone, String status) {
        this.shipperId = shipperId;
        this.shipperName = shipperName;
        this.phone = phone;
        this.status = status;
    }

    public String getShipperId() {
        return shipperId;
    }

    public void setShipperId(String shipperId) {
        this.shipperId = shipperId;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
