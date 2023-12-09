import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FlightDataRetriever {




    public static FlightList loadAllData(){
        ArrayList<LocationInformation> locationList = new ArrayList<LocationInformation>();
        ArrayList<Plane> planeList = new ArrayList<Plane>();
        ArrayList<Seat> seatList = new ArrayList<Seat>();
        ArrayList<CrewMember> crewMemberList = new ArrayList<CrewMember>();
        ArrayList<Crew> crewList = new ArrayList<Crew>();
        ArrayList<Integer> flightIDList = new ArrayList<Integer>();
        // ArrayList<FlightItinerary> flightItinerarieList = new ArrayList<FlightItinerary>();
        FlightList fl = new FlightList();
        

        //Get all Location Information
        String sql = "SELECT FlightID, Origin, Destination, DepartureDateTime, ArrivalDateTime FROM Flights";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ArrayList<String> sqls = new ArrayList<String>();
                sqls.add(rs.getString("FlightID"));
                sqls.add(rs.getString("Origin"));
                sqls.add(rs.getString("Destination"));
                sqls.add(rs.getString("DepartureDateTime"));
                sqls.add(rs.getString("ArrivalDateTime"));
                
                LocationInformation l = new LocationInformation(sqls.get(4), sqls.get(2), sqls.get(3), sqls.get(1));
                flightIDList.add(Integer.valueOf(sqls.get(0)));
                locationList.add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Get all Seat information
        sql = "SELECT SeatID, SeatNumber, SeatType, Price, IsBooked, PlaneID FROM Seats";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ArrayList<String> sqls = new ArrayList<String>();
                sqls.add(rs.getString("SeatID"));
                sqls.add(rs.getString("SeatNumber"));
                sqls.add(rs.getString("SeatType"));
                sqls.add(rs.getString("Price"));
                sqls.add(rs.getString("PlaneID"));
                
                
                Seat s = new Seat(Integer.valueOf(sqls.get(0)), sqls.get(1), sqls.get(2), Integer.valueOf(sqls.get(3)), rs.getBoolean("IsBooked"), Integer.valueOf(sqls.get(4)));
                seatList.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Get all Plane information
        sql = "SELECT PlaneID, Model FROM Planes";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ArrayList<String> sqls = new ArrayList<String>();
                sqls.add(rs.getString("PlaneID"));
                sqls.add(rs.getString("Model"));
                
                Plane p = new Plane(sqls.get(1));
                planeList.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Get all CrewMember information
        sql = "SELECT CrewID, Name, Position FROM Crews";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ArrayList<String> sqls = new ArrayList<String>();
                sqls.add(rs.getString("CrewID"));
                sqls.add(rs.getString("Position"));
                sqls.add(rs.getString("Position"));
                
                CrewMember cm = new CrewMember(Integer.valueOf(sqls.get(0)), sqls.get(1), sqls.get(2));
                crewMemberList.add(cm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Create crews from crewmembers
        for(int i = 0; i < crewMemberList.size() / 3; i++){
            Crew c = new Crew();
            c.addCrew(crewMemberList.get(i));
            c.addCrew(crewMemberList.get(i + 1));
            c.addCrew(crewMemberList.get(i + 2));
            crewList.add(c);
        }
        
        //Add seats to plane
        int seatsPerPlane = 36;

        for (int i = 0; i < planeList.size(); i++) {
            planeList.get(i).setId(i + 1); // Set unique id for each plane
            for (int k = i * seatsPerPlane; k < (i + 1) * seatsPerPlane; k++) {
                planeList.get(i).addSeat(seatList.get(k));
            }
        }

        //Create Flight Ittinerarys
        for(int j = 0; j < planeList.size(); j++){
            FlightItinerary fi = new FlightItinerary(flightIDList.get(j), planeList.get(j), locationList.get(j), crewList.get(j));
            fl.addFlight(fi);
        }

        

        //return flight list
        return fl;
    }
    public static ArrayList<Bookings> loadBookingData() {
        ArrayList<Bookings> bookingList = new ArrayList<Bookings>();

        // Get all Booking information
        String sql = "SELECT UserID, FlightID, SeatID, BookingID FROM Bookings";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String userID = rs.getString("UserID");
                String flightID = rs.getString("FlightID");
                String seatID = rs.getString("SeatID");
                String bookingID = rs.getString("BookingId");

                Bookings booking = new Bookings(userID, flightID, seatID, bookingID);
                bookingList.add(booking);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookingList;
    }
}

