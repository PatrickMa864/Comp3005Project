import java.util.ArrayList;

public class Basket {
    private int basket_id;
    private ArrayList<Book> books;
    private long subTotal;

    public Basket(int basket_id, ArrayList<Book> books, long subTotal) {
        this.basket_id = basket_id;
        this.books = books;
        this.subTotal = subTotal;
    }
}
