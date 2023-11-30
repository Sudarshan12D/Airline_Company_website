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
            String url = "jdbc:mysql://localhost:3306/FLIGHT_BOOKINGS";
            String username = "root";
<<<<<<< HEAD
            String password = "Barcelona15";
=======
            String password = "hgrt%48K";
>>>>>>> ceedbd0fb04d51c9e6e512f8567d58ec0d210c8f

            // Load the JDBC driver
           Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}