import java.lang.Object;
import java.util.Date;

public class Order {
    private int orderNo;
    private Date orderDate;
    private double price;
    private Date estimatedDelivery;
    public enum Status{
        PROCESSED, SHIPPED, DELIVERED, RECEIVED
    };
    private Status status;

    public Order(int orderNo, Date orderDate, double price, Date estimatedDelivery, Status status) {
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.price = price;
        this.estimatedDelivery = estimatedDelivery;
        this.status = status;
    }
}
