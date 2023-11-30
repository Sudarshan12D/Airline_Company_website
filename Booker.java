import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Booker {
    //public static long handleBooking(int price, String creditCard, int memberID, int flightID, int seatID, Boolean insurance)
    public static long handleBooking(int price, String creditCard, String email, String flightID,  ArrayList<String> seatID, boolean insurance) {

        for (int i =0; i < seatID.size(); i++) {
            String thisSeat = seatID.get(i);
            addBooking(email, flightID, thisSeat);
        }

        addPayment();

    }

    //public static long addBooking(String email, int flightID, int seatID, Boolean insurance)
    public static long addBooking(String email, int flightID, String seatID) {
        //String SQL = "INSERT INTO Bookings(UserID, FlightID, SeatID, CancelationInsurance) VALUES (?, ?, ?, ?)";
        String SQL = "INSERT INTO Bookings(UserID, FlightID, SeatID) VALUES (?, ?, ?)";

        long id = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, memberID);
            pstmt.setInt(2, flightID);
            pstmt.setString(3, seatID);
            //pstmt.setBoolean(4, insurance);

            int affectedRows = pstmt.executeUpdate();

            // Check the affected rows
            if (affectedRows > 0) {
                // Get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }
    public static long addPayment(int memberID, int flightID, int seatID, Boolean insurance) {
        String SQL = "INSERT INTO Bookings(UserID, FlightID, SeatID, CancelationInsurance) VALUES (?, ?, ?, ?)";

        long id = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, memberID);
            pstmt.setInt(2, flightID);
            pstmt.setInt(3, seatID);
            pstmt.setBoolean(4, insurance);

            int affectedRows = pstmt.executeUpdate();

            // Check the affected rows
            if (affectedRows > 0) {
                // Get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }
    

}