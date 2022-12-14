import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


public class DataBaseQueries {

    private static final String USER = "postgres";
    private static final String DATABASE = "COMP3005_Project";

    public static int noOfAddressID = 0;
    public static int noOfOrderID = 0;
    public static int noOfUsers = 0;
    public static Connection connection;
    public static Statement statement;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DATABASE, USER, "password");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * @return an ArrayList of all users currently registers on the system
     */
    public static ArrayList<User> makeUserList() {
        ArrayList<User> users = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM users");

            while (result.next()) {
                users.add(new User(result.getString("user_name"), result.getString("password"),
                        result.getString("first_name"), result.getString("last_name"),
                        result.getString("email"), result.getInt("address_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }


    /**
     * The Method takes the address_id as a parameter and returns the corresponding address from the database
     * @param address_id representing the primary key in the database
     * @return the address for the corresponding address id given
     */
    public static Address getAddress(int address_id) {
        Address address = null;
        try {
            ResultSet result = statement.executeQuery(String.format("SELECT * FROM address WHERE address_id='%s'", address_id));
            while (result.next()) {
                address = new Address(result.getInt("address_id"), result.getInt("apartment_num"),
                        result.getString("street_name"), result.getInt("street_num"), result.getString("city"),
                        result.getString("province"), result.getString("country"), result.getString("postal_code"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return address;
    }


    /**
     *
     * @return an ArrayList of all the available books
     */
    public static ArrayList<Book> getAvailableBooks(){
        ArrayList<Book> books = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM BOOK");
            while (result.next()) {
                books.add(new Book(result.getLong("ISBN"), result.getString("name"), result.getString("genre"),
                        result.getInt("no_of_copies"), result.getDouble("price"), result.getInt("no_of_pages"),
                        result.getInt("version"), result.getDouble("royalty"), result.getDate("published_year"),
                        result.getString("publisher_name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    /**
     *
     * @return an ArrayList of all the orders books
     */
    public static ArrayList<Order> getOrders(){
        ArrayList<Order> orders = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM ORDERS");
            while (result.next()) {
                orders.add(new Order(result.getInt("order_no"), result.getDate("initial_date"), result.getDouble("price"), result.getDate("estimate_delivery"),
                        result.getString("status"), result.getInt("shipping_address_id"), result.getInt("billing_address_id"), result.getString("user_name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    /**
     *
     * @return an ArrayList of all the orders tied to books
     */
    public static ArrayList<AddToOrder> getOrdersTiedBooks(){
        ArrayList<AddToOrder> orders = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM ADDTOORDER");
            while (result.next()) {
                orders.add(new AddToOrder(result.getLong("isbn"), result.getInt("order_no")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    /**
     * This method returns an author for a corresponding book through passing the ISBN of that book
     * @param ISBN represents the ISBN for the book
     * @return an author represnting the author of the book, otherwise it returns null
     */
    public static Author getAuthorByISBN(long ISBN){
        try {
            ResultSet result = statement.executeQuery(String.format("SELECT first_name, last_name from writes WHERE ISBN = '%d%n'", ISBN));
            while (result.next()) {
                Author author = new Author(result.getString("first_name"), result.getString("last_name"));
                return author;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * This method returns an author for a corresponding book through passing the ISBN of that book
     * @param ISBN represents the ISBN for the book
     * @return a book represnting the author of the book, otherwise it returns null
     */
    public static Book getBookByISBN(long ISBN){
        try {
            ResultSet result = statement.executeQuery(String.format("SELECT * from book WHERE ISBN = '%d%n'", ISBN));
            while (result.next()) {
                Book book = new Book(result.getLong("ISBN"), result.getString("name"), result.getString("genre"),
                        result.getInt("no_of_copies"), result.getDouble("price"), result.getInt("no_of_pages"),
                        result.getInt("version"), result.getDouble("royalty"), result.getDate("published_year"),
                        result.getString("publisher_name"));
                return book;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * This method returns a publisher with the passed name
     * @param name represents the name of the publisher
     * @return an author represnting the author of the book, otherwise it returns null
     */
    public static Publisher getPublisherByName(String name){
        try {
            ResultSet result = statement.executeQuery(String.format("SELECT * from publisher WHERE  publisher_name = '%s'", name));
            while (result.next()) {
                Publisher publisher = new Publisher(result.getString("publisher_name"), result.getLong("phone_number"), result.getString("email"), result.getLong("banking_account"), result.getInt("address_id"));
                return publisher;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    /**
     * @param user represents the current user adding to the basket
     * @param book represent the book to be added to the basket
     */
    public static void addBookToBasket(User user, Book book) {
        boolean found = false;
        int quantity = 0;
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM addtobasket");
            while (result.next()) {
                if (result.getString("user_name").equals(user.getUserName()) && result.getLong("isbn") == book.getISBN()) {
                    found = true;
                    quantity = result.getInt("quantity");
                    break;
                }
            }

            if (found) {
                statement.executeUpdate(String.format("UPDATE addtobasket SET quantity='%d' WHERE user_name='%s' and isbn='%d%n'", quantity + 1, user.getUserName(), book.getISBN()));
            } else {
                statement.executeUpdate(String.format("INSERT into addtobasket values ('%s','%d%n','%d')", user.getUserName(), book.getISBN(), 1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param user represents the current user adding to the basket
     * @param book represent the book to be deleted to the basket
     */
    public static void deleteBookFromBasket(User user, Book book) {
        boolean found = false;
        int quantity = 0;
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM addtobasket");
            while (result.next()) {
                if (result.getString("user_name").equals(user.getUserName()) && result.getLong("isbn") == book.getISBN()) {
                    found = true;
                    quantity = result.getInt("quantity");
                    break;
                }
            }
            if (found && (quantity > 1)) {
                statement.executeUpdate(String.format("UPDATE addtobasket SET quantity='%d' WHERE user_name='%s' and isbn='%d%n'", quantity - 1, user.getUserName(), book.getISBN()));
            }
            else if(found){
                statement.executeUpdate(String.format("DELETE FROM addtobasket WHERE user_name = '%s' AND ISBN = '%d%n'", user.getUserName(), book.getISBN()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * set the noOfAddressID to the current number of addresses on the database
     */
    public static void updateAddressCount(){
        try {
            ResultSet result = statement.executeQuery("SELECT count(address_id) From (SELECT * from address)counts;");
            while (result.next()) {
                noOfAddressID = result.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * set the noOfOrderID to the current number of orders on the database
     */
    public static void updateOrderCount(){
        try {
            ResultSet result = statement.executeQuery("SELECT count(order_no) From (SELECT * from orders)counts;");
            while (result.next()) {
                noOfOrderID = result.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * set the noOfUsers to the current number of users on the database
     */
    public static void updateUserCount(){
        try {
            ResultSet result = statement.executeQuery("SELECT count(user_name) From (SELECT * from users)counts;");
            while (result.next()) {
                noOfUsers = result.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * @param order add the passsed address to the dataBase
     */
    public static void addNewOrder(Order order){
        try {
            statement.executeUpdate(String.format("INSERT into orders values ('%d','%tF','%f', '%tF', '%s', '%d', '%d', '%s')",
                    order.getOrderNo(), order.getInitialDate(), order.getPrice(), order.getEstimatedDelivery(), order.getStatus()
                    , order.getShippingAddressID(), order.getBillingAddressID(), order.getUser_name()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * @param address add the address to the list of addresses in the DataBase
     */
    public static void addNewAddress(Address address){
        try {
            statement.executeUpdate(String.format("INSERT into address values ('%d','%d','%s', '%d', '%s', '%s', '%s', '%s')", address.getAddress_id(), address.getApartmentNo(), address.getStreetName()
                    , address.getStreetNum(), address.getCity(), address.getProvince(), address.getCountry(), address.getPostalCode()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method add a given user to the lis tof user in the dataBase
     * @param user a User to be added to the dataBase
     */
    public static void addNewUser(User user){
        try {
            statement.executeUpdate(String.format("INSERT into users values ('%s','%s','%s','%s','%s', '%d')",
                    user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This method adds a new book to the list of books in the database
     * @param book the book to be added to the list of books in the store
     */
    public static void addNewBook(Book book){
        try {
            statement.executeUpdate(String.format("INSERT into book values ('%d%n','%s','%s', '%d', '%f', '%d', '%d', '%f', '%tF', '%s')",
                    book.getISBN(), book.getName(), book.getGenre(), book.getNumCopies(), book.getPrice(), book.getNumPages(),
                    book.getVersion(), book.getPublisherRoyalty(), book.getPublishedYear(), book.getPublisherName()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method updates the quantity of a corresponding book in the store,
     * @param book the book to update its quantity
     * @param addRemove boolean representing if book quantity to be incremented or decremented. If true the quantity for
     *the passed book is incremented by one. If false it gets decremented by one
     */

    public static void updateBookAmount(Book book, boolean addRemove){
        int quantity = 0;

        try {
            ResultSet result = statement.executeQuery("SELECT * FROM book");
            while (result.next()) {
                if (result.getLong("isbn") == book.getISBN()) {
                    quantity = result.getInt("no_of_copies");
                    break;
                }
            }
            if(addRemove) {
                statement.executeUpdate(String.format("UPDATE book SET no_of_copies='%d' WHERE isbn='%d%n'", quantity - 1, book.getISBN()));
            } else {
                statement.executeUpdate(String.format("UPDATE book SET no_of_copies='%d' WHERE isbn='%d%n'", quantity + 1, book.getISBN()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param publisher add the publisher to list of publishers in the dataBase
     */
    public static void addNewPublisher(Publisher publisher){
        try {
            statement.executeUpdate(String.format("INSERT into publisher values ('%s','%s','%d%n', '%d%n', '%d')",
                    publisher.getName(), publisher.getEmail(), publisher.getPhone(), publisher.getBankAccount(), publisher.getAddressID()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param author add the author to list of authors in the dataBase
     */
    public static void addNewAuthor(Author author){
        System.out.println(author.getFirstName() + author.getLastName());
        try {
            statement.executeUpdate(String.format("INSERT into AUTHOR values ('%s','%s')",
                    author.getFirstName(), author.getLastName()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This method updates the writes table in the dataBase. it sets the author parameter given as thw writer for the passed book
     * @param fullName the name of the author to be set as the book writer
     * @param book the book been written by the passed author
     */
    public static void updateWrites(String fullName, Book book){
        String[] splited = fullName.split("\s+");
        try {
            statement.executeUpdate(String.format("INSERT into WRITES values ('%s','%s','%d%n')",
                    splited[0], splited[1], book.getISBN()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This method returns an arrayList of all the authors in the database
     * @return an ArrayList of Author
     */

    public static String[] makeAuthorsList() {
        ArrayList<Author> authors = new ArrayList<>();
        String[] authorsS;
        int counter = 0;
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM AUTHOR");

            while (result.next()) {
                authors.add(new Author(result.getString("first_name"), result.getString("last_name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        authorsS = new String[authors.size()];
        for(Author a: authors){
            authorsS[counter++] = a.getFirstName() + " "+ a.getLastName();
        }

        return authorsS;
    }


    /**
     * This method returns an arrayList of all the publishers in the database
     * @return an ArrayList of Publisher
     */
    public static String[] makePublishersList() {
        ArrayList<Publisher> publishers = new ArrayList<>();
        String[] publishersS;
        int counter = 0;
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM PUBLISHER");

            while (result.next()) {
                publishers.add(new Publisher(result.getString("publisher_name"), result.getLong("phone_number"),
                        result.getString("email"), result.getLong("banking_account"), result.getInt("address_id")));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        publishersS = new String[publishers.size()];
        for(Publisher p: publishers){
            publishersS[counter++] = p.getName();
        }

        return publishersS;
    }

    public static void addBookToOrder(Book book, Order order) {
        try {
            statement.executeUpdate(String.format("INSERT into addtoorder values('%d', '%d')", book.getISBN(), order.getOrderNo()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}