public class Book {
    private String title;
    private Author author;
    private int ISBN;
    private Publisher publisher;
    private double price;
    private int numPages;
    public static String[] Genres = {"-", "FICTION", "NONFICTION", "SCIFI", "FANTASY", "MYSTERY", "ROMANCE", "THRILLER",
            "HISTORY", "HORROR", "ADVENTURE", "GRAPHIC", "MANGA", "POETRY", "COOKBOOK", "CHILDREN", "GUIDEBOOK",
            "RESEARCH", "BIOGRAPHIC", "INFORMATIONAL"};
    private String genre;
    private int numCopies;
    private int version;
    private double publisherRoyalty;

    private int publishedYear;

    public Book(String title, Author author, int ISBN, Publisher publisher, double price, int numPages, String genre, int numCopies, int version, double publisherRoyalty, int publishedYear) {
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
        this.publishedYear = publishedYear;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
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

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
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
