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
<<<<<<< HEAD
            String url = "jdbc:mysql://localhost:3306/FLIGHT_BOOKINGS";
            String username = "root";
            String password = "#Miata2022";

            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
=======
            return connection == null || connection.isClosed();
        } catch (SQLException e) {
>>>>>>> 47beea0f77f262b28a3b647c715da45efe983dd2
            e.printStackTrace();
            return true;
        }
    }

    private static void initializeConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/FLIGHT_BOOKINGS";
            String username = "root";
            String password = "hgrt%48K";

            // Load the JDBC driver
           Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}