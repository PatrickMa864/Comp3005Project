import java.lang.Object;
import java.util.Date;

public class Order {
    private int orderNo;
    private Date orderDate;
    private long price;
    private Date estimatedDelivery;
    private enum Status{
        PROCESSED, SHIPPED, DELIVERED, RECEIVED
    };
    private Status status;

    public Order(int orderNo, Date orderDate, long price, Date estimatedDelivery, Status status) {
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.price = price;
        this.estimatedDelivery = estimatedDelivery;
        this.status = status;
    }
}
