public class AddToOrder {

    private long isbn;
    private int orderNo;

    public AddToOrder(long isbn, int orderNo) {
        this.isbn = isbn;
        this.orderNo = orderNo;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }
}
