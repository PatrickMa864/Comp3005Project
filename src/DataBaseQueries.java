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
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DATABASE, USER, "Aks2392002");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws SQLException {
        ArrayList<Integer> test = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery("SELECT price FROM BOOK");
            while(result.next()){
                test.add(result.getInt("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(test);
    }
}
