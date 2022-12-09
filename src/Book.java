import java.util.Date;

public class Book {
    private long ISBN;
    private String name;
    private String genre;
    private int numCopies;
    private double price;
    private int numPages;
    private int version;
    private double publisherRoyalty;
    private String publisherName;

    private Date publishedYear;

    public static String[] Genres = {"-", "FICTION", "NONFICTION", "SCIFI", "FANTASY", "MYSTERY", "ROMANCE", "THRILLER",
            "HISTORY", "HORROR", "ADVENTURE", "GRAPHIC", "MANGA", "POETRY", "COOKBOOK", "CHILDREN", "GUIDEBOOK",
            "RESEARCH", "BIOGRAPHIC", "INFORMATIONAL"};

    public Book(long ISBN, String name, String genre, int numCopies, double price, int numPages, int version, double publisherRoyalty, Date publishedYear, String publisherName) {
        this.ISBN = ISBN;
        this.name = name;
        this.genre = genre;
        this.numCopies = numCopies;
        this.price = price;
        this.numPages = numPages;
        this.version = version;
        this.publisherRoyalty = publisherRoyalty;
        this.publisherName = publisherName;
        this.publishedYear = publishedYear;
    }

    public long getISBN() {
        return ISBN;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getNumCopies() {
        return numCopies;
    }

    public double getPrice() {
        return price;
    }

    public int getNumPages() {
        return numPages;
    }

    public int getVersion() {
        return version;
    }

    public double getPublisherRoyalty() {
        return publisherRoyalty;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public Date getPublishedYear() {
        return publishedYear;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setPublisherRoyalty(double publisherRoyalty) {
        this.publisherRoyalty = publisherRoyalty;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setPublishedYear(Date publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getInfo(){
        return "Title: " + name +
        "\nISBN: " + ISBN +
        "\nPublisher name: " + publisherName +
        "\nPrice: " + price +
        "\nNo. Pages: " + numPages +
        "\nGenre: " + genre +
        "\nAmount in Stock: " + numCopies +
        "\nBook Version: " + version;
    }


    public String getSimpleInfo(){
        return  name + " by: " + DataBaseQueries.getAuthorByISBN(this.getISBN()).getFullName() +
                "\nISBN: " + ISBN + "\nQuantity: " + numCopies + "\nPrice: " + (numCopies * price);
    }
}
