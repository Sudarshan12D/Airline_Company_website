import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private DatabaseConnection() {
        // private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        if (connection == null || isConnectionClosed()) {
            initializeConnection();
        }
        return connection;
    }

    private static boolean isConnectionClosed() {
        try {
            return connection == null || connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    private static void initializeConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/FLIGHTBOOKINGS";
            String username = "root";
<<<<<<< HEAD
            String password = "#Miata2022";
=======
            String password = "Sudu12june@#";
>>>>>>> 57cc3964fed5e822bcb93a900331e4248601e8fb

            // Load the JDBC driver
           Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}