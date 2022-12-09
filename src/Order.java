import java.util.Date;

public class Order {
    private int orderNo;
    private Date initialDate;
    private double price;
    private Date estimatedDelivery;
    public enum Status{
        PROCESSED, SHIPPED, DELIVERED, RECEIVED
    };
    private Status status;
    private int shippingAddressID;
    private int BillingAddressID;
    private String user_name;

    public Order(int orderNo, Date initialDate, double price, Date estimatedDelivery, Status status, int shippingAddressID, int billingAddressID, String user_name) {
        this.orderNo = orderNo;
        this.initialDate = initialDate;
        this.price = price;
        this.estimatedDelivery = estimatedDelivery;
        this.status = status;
        this.shippingAddressID = shippingAddressID;
        BillingAddressID = billingAddressID;
        this.user_name = user_name;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEstimatedDelivery(Date estimatedDelivery) {
        this.estimatedDelivery = estimatedDelivery;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setShippingAddressID(int shippingAddressID) {
        this.shippingAddressID = shippingAddressID;
    }

    public void setBillingAddressID(int billingAddressID) {
        BillingAddressID = billingAddressID;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public double getPrice() {
        return price;
    }

    public Date getEstimatedDelivery() {
        return estimatedDelivery;
    }

    public Status getStatus() {
        return status;
    }

    public int getShippingAddressID() {
        return shippingAddressID;
    }

    public int getBillingAddressID() {
        return BillingAddressID;
    }

    public String getUser_name() {
        return user_name;
    }
}
