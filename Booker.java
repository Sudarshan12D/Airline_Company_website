import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Booker {
    public static FlightList handleBooking(int price, String creditCard, String email, String flightID, ArrayList<String> seatID, boolean insurance, Plane thisPlane) {
        int parsedFlightID = Integer.parseInt(flightID);

        for (int i = 0; i < seatID.size(); i++) {
            String thisSeat = seatID.get(i);
            int parsedSeatID = Integer.parseInt(thisSeat);
            long bookingID = addBooking(email, parsedFlightID, parsedSeatID, insurance);
            addPayment(bookingID, price, creditCard);
            updateSeatBDBooked(flightID, thisSeat);
            setSeatBooked(thisPlane, thisSeat);
        }
        return FlightDataRetriever.loadAllData();
    }

    public static long addBooking(String email, int flightID, int seatID, Boolean insurance) {
        String SQLSelectUserID = "SELECT UserID FROM Users WHERE Email = ?";
        String SQLInsertBooking = "INSERT INTO Bookings(UserID, FlightID, SeatID, CancellationInsurance) VALUES (?, ?, ?, ?)";
    
        long id = 0;
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmtSelectUserID = conn.prepareStatement(SQLSelectUserID);
             PreparedStatement pstmtInsertBooking = conn.prepareStatement(SQLInsertBooking, Statement.RETURN_GENERATED_KEYS)) {
    
            // Set the email parameter for the first query
            pstmtSelectUserID.setString(1, email);
    
            // Execute the query to get the UserID
            try (ResultSet rs = pstmtSelectUserID.executeQuery()) {
                if (rs.next()) {
                    // Retrieve the UserID
                    int userID = rs.getInt("UserID");
    
                    // Set parameters for the second query (insert into Bookings)
                    pstmtInsertBooking.setInt(1, userID);
                    pstmtInsertBooking.setInt(2, flightID);
                    pstmtInsertBooking.setInt(3, seatID);
                    pstmtInsertBooking.setBoolean(4, insurance);
    
                    // Execute the second query
                    int affectedRows = pstmtInsertBooking.executeUpdate();
    
                    // Check the affected rows
                    if (affectedRows > 0) {
                        // Get the ID back
                        try (ResultSet generatedKeys = pstmtInsertBooking.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                id = generatedKeys.getLong(1);
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return id;
    }

    public static long addPayment(long bookingID, int price, String creditCard) {
        String SQL = "INSERT INTO Payments(BookingID, Amount, CreditCardUsed) VALUES (?, ?, ?)";
        int parsedBookingID = (int) bookingID;
        long id = 0;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, parsedBookingID);
            pstmt.setInt(2, price);
            pstmt.setString(3, creditCard);

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

    public static void updateSeatBDBooked(String flightID, String seatID) {
        int parsedFlightID = Integer.parseInt(flightID);
        int parsedSeatID = Integer.parseInt(seatID);

        String SQL = "UPDATE Seats SET IsBooked = TRUE WHERE SeatID = ?";
        int updatedSeatID = parsedSeatID + (parsedFlightID - 1) * 36;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, updatedSeatID);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void setSeatBooked(Plane thisPlane, String thisSeat ) {
        int parsedSeatID = Integer.parseInt(thisSeat);
        int modifiedSeatID = parsedSeatID + (36 * (thisPlane.getId() -1));
        Seat seatObject = thisPlane.getSeat(modifiedSeatID);
        seatObject.setIsBooked(true);
    }

    public static void cancelBooking(Plane thisPlane, String SeatID, Bookings thisBooking) {
        removeBooking(thisBooking);
        updateSeatDBNot(thisPlane, SeatID);
        setSeatNotBooked(thisPlane, SeatID);
    }

    public static void removeBooking(Bookings thisBooking) {
        String SQL = "DELETE FROM Bookings WHERE BookingID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    
            pstmt.setInt(1, Integer.parseInt(thisBooking.getBookingID()));
    
            int affectedRows = pstmt.executeUpdate();
    
            if (affectedRows > 0) {
                System.out.println("Booking removed successfully.");
            } else {
                System.out.println("Failed to remove booking.");
            }
    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void updateSeatDBNot(Plane thisPlane, String seatID) {
        int parsedSeatID = Integer.parseInt(seatID);
        int updatedSeatID = parsedSeatID + (36 * (thisPlane.getId() - 1));
    
        String SQL = "UPDATE Seats SET IsBooked = FALSE WHERE SeatID = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    
            pstmt.setInt(1, updatedSeatID);
    
            pstmt.executeUpdate();
    
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void setSeatNotBooked(Plane thisPlane, String seatID) {
        int parsedSeatID = Integer.parseInt(seatID);
        int modifiedSeatID = parsedSeatID + (36 * (thisPlane.getId() - 1));
        Seat seatObject = thisPlane.getSeat(modifiedSeatID);
        seatObject.setIsBooked(false);
    }
}