import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


public class DataBaseQueries {

    private static final String USER = "postgres";
    private static final String DATABASE = "COMP3005_Project";
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


    public static void main(String[] args) throws SQLException {


        ArrayList<Author> authors = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM Author");
            while(result.next()){
                authors.add(new Author(result.getString("firstname"), result.getString("lastname")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Basket b1 = new Basket();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM BOOK");
            while(result.next()){

                //b1.addBook(new Book(result.getString("title"), get"Author")),
                  //      result.getString("title"),result.getString("title"),result.getString("title"),
                   //     result.getString("title"),result.getString("title"),result.getString("title"),);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        ArrayList<Integer> test = new ArrayList<>();
//        try {
//            ResultSet result = statement.executeQuery("SELECT price FROM BOOK");
//            while(result.next()){
//                test.add(result.getInt("price"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        newAddress();

        System.out.println(b1.printBasket());
    }

    public static ArrayList<User> makeUserList(){
        ArrayList<User> users = new ArrayList<>();

        try {
            ResultSet result = statement.executeQuery("SELECT * FROM users");

            while(result.next()){
                users.add(new User(result.getString("user_name"), result.getString("password"),
                        result.getString("first_name"), result.getString("last_name"),
                        result.getString("email"), getAddress(result.getInt("address_id"))));
                System.out.println(users.get(0).getUserName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }return users;
    }

    public static Address getAddress(int address_id){
        Address address = null;
        try {
            ResultSet result = statement.executeQuery(String.format("SELECT * FROM address WHERE address_id='%s'", address_id));
            while(result.next()) {
                address = new Address(result.getInt("address_id"), result.getInt("apartment_num"),
                        result.getString("street_name"), result.getInt("street_num"), result.getString("city"),
                        result.getString("province"), result.getString("country"), result.getString("postal_code"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return address;
    }

    public static void addBookToBasket(User user, Book book){
        boolean found = false;
        int quantity = 0;
        try {
            ResultSet result = statement.executeQuery("SELECT user_name, isbn FROM addtobasket");
            while(result.next()){
                if (result.getString("user_name").equals(user.getUserName()) && result.getLong("isbn")==book.getISBN()){
                    found=true;
                    quantity = result.getInt("quantity");
                    break;
                }
            }
            if(found){
                statement.executeUpdate(String.format("UPDATE addtobasket SET quantity='%d' WHERE user_name='%s', isbn='%d%n'", quantity+1, user.getUserName(), book.getISBN()));
            } else {
                statement.executeUpdate(String.format("INSERT into addtobasket values ('%s','%d%n','%d')", user.getUserName(), book.getISBN(), 1));
            }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addToQueries(String command, Object object){
        try {
            statement.executeUpdate(String.format("'%s'", command));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
