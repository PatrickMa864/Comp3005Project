import java.util.ArrayList;

public class Basket {

    private ArrayList<Book> books;

    public Basket(){
        books = new ArrayList<Book>();
    }

    public Basket(ArrayList<Book> books){
        this.books = new ArrayList<Book>();
        for (Book b: books){
            this.books.add(b);
        }
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }



    public String printBasket(){
        String output = "";
        for ( Book b: this.getBooks()){
            output += "\""+b.getName() +"\" by "+ DataBaseQueries.getAuthorByISBN(b.getISBN()).getFullName() + "   Quantity:" + b.getNumCopies()+ "\n";
        }
        return output;
    }

    public Book getBook(long ISBN){
        for(Book b: this.getBooks()){
            if (b.getISBN() == ISBN){
                return b;
            }
        }
        return null;
    }

    public void removeBook(long ISBN){
        for(int i = 0; i<this.getBooks().size();i++){
            if (this.getBooks().get(i).getISBN() == ISBN){
                this.getBooks().remove(i);
            }
        }
    }

    public void addBook(Book book){
        books.add(book);
    }

    public boolean hasBook(String title){
        for(Book b: this.getBooks()){
            if (b.getName().equals(title)){
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
