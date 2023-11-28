import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FlightDataRetriever {
    public static ArrayList<String> getAvailableFlights() {
        ArrayList<String> flights = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String sql = "SELECT * FROM Flights";
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    String flightInfo = "FlightID: " + rs.getInt("FlightID") +
                            ", Origin: " + rs.getString("Origin") +
                            ", Destination: " + rs.getString("Destination") +
                            ", Departure: " + rs.getTimestamp("DepartureDateTime") +
                            ", Arrival: " + rs.getTimestamp("ArrivalDateTime");
                    flights.add(flightInfo);
                    System.out.println("Retrieved: " + flightInfo); // Debug
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flights;
    }
}
