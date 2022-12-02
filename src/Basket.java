import java.util.ArrayList;

public class Basket {
    private int basket_id;
    private ArrayList<Book> books;
    private double subTotal;

    public Basket(int basket_id, ArrayList<Book> books, double subTotal) {
        this.basket_id = basket_id;
        this.books = books;
        this.subTotal = subTotal;
    }

    public Basket(){
        basket_id = 0;
        books = new ArrayList<Book>();
        subTotal = 0;
    }

    public int getBasket_id() {
        return basket_id;
    }

    public void setBasket_id(int basket_id) {
        this.basket_id = basket_id;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public String printBasket(){
        String output = "";
        for ( Book b: this.getBooks()){
            output += "\""+b.getTitle() +"\" by "+ b.getAuthor().getFirstName()+ " " + b.getAuthor().getLastName()+ "   Quantity:" + b.getNumCopies()+ "\n";
        }
        return output;
    }

    public Book getBook(int ISBN){
        for(Book b: this.getBooks()){
            if (b.getISBN() == ISBN){
                return b;
            }
        }
        return null;
    }

    public void removeBook(int ISBN){
        for(int i = 0; i<this.getBooks().size();i++){
            if (this.getBooks().get(i).getISBN() == ISBN){
                this.getBooks().remove(i);
            }
        }
    }

    public boolean hasBook(String title){
        for(Book b: this.getBooks()){
            if (b.getTitle().equals(title)){
                return true;
            }
        }return false;
    }

    public double getTotal(){
        int output = 0;
        for ( Book b:this.getBooks()){
            output += b.getNumCopies() * b.getPrice();
        }
        return output;
    }
}
