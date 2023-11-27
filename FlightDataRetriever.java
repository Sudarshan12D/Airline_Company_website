import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FlightDataRetriever {
    public static ArrayList<Object[]> getAvailableFlights() {
        ArrayList<Object[]> flights = new ArrayList<>();
        String sql = "SELECT FlightID, Origin, Destination, DepartureDateTime, ArrivalDateTime FROM Flights";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Object[] row = new Object[5]; // 5 columns in the flight table
                row[0] = rs.getInt("FlightID");
                row[1] = rs.getString("Origin");
                row[2] = rs.getString("Destination");
                row[3] = rs.getTimestamp("DepartureDateTime");
                row[4] = rs.getTimestamp("ArrivalDateTime");
                flights.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flights;
    }
}
