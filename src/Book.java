public class Book {
    private String title;
    private Author author;
    private int ISBN;
    private Publisher publisher;
    private double price;
    private int numPages;
    private Genre genre;
    private int numCopies;
    private int version;
    private double publisherRoyalty;

    public Book(String title, Author author, int ISBN, Publisher publisher, double price, int numPages, Genre genre, int numCopies, int version, double publisherRoyalty) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.price = price;
        this.numPages = numPages;
        this.genre = genre;
        this.numCopies = numCopies;
        this.version = version;
        this.publisherRoyalty = publisherRoyalty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public double getPublisherRoyalty() {
        return publisherRoyalty;
    }

    public void setPublisherRoyalty(double publisherRoyalty) {
        this.publisherRoyalty = publisherRoyalty;
    }

    public String getInfo(){
        return "Title: " + title +
        "\nAuthor: " + author.getFirstName() + " " + author.getLastName() +
        "\nISBN: " + ISBN +
        "\nPublisher: " + publisher.getName() +
        "\nPrice: " + price +
        "\nNo. Pages: " + numPages +
        "\nGenre: " + genre +
        "\nAmount in Stock: " + numCopies +
        "\nBook Version: " + version;
    }

    public String getSimpleInfo(){
        return  title + " by: " + author.getFirstName() + " " + author.getLastName() +
                "\nISBN: " + ISBN + "\nQuantity: " + numCopies + "\nPrice: " + (numCopies * price);
    }
}
