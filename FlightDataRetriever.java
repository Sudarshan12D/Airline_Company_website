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
        ArrayList<FlightItinerary> flightItinerarieList = new ArrayList<FlightItinerary>();
        FlightList fl = new FlightList();

        //Get all Location Information
        String sql = "SELECT FlightID, Origin, Destination, DepartureDateTime, ArrivalDateTime FROM Flights";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ArrayList<String> sqls = new ArrayList<String>();
                sqls.set(0, rs.getString("FlightID"));
                sqls.set(1, rs.getString("Origin"));
                sqls.set(2, rs.getString("Destination"));
                sqls.set(3, rs.getString("DepartureDateTime"));
                sqls.set(4, rs.getString("ArrivalDateTime"));
                
                LocationInformation l = new LocationInformation(sqls.get(4), sqls.get(2), sqls.get(3), sqls.get(1));
                locationList.add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Get all Seat information
        sql = "SELECT SeatID, SeatNumber, SeatType, Price, IsBooked FROM Seats";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ArrayList<String> sqls = new ArrayList<String>();
                sqls.set(0, rs.getString("SeatID"));
                sqls.set(1, rs.getString("SeatNumber"));
                sqls.set(2, rs.getString("SeatType"));
                sqls.set(3, rs.getString("Price"));
                
                
                Seat s = new Seat(Integer.valueOf(sqls.get(1)), sqls.get(2), sqls.get(3), Integer.valueOf(sqls.get(4)), rs.getBoolean("IsBooked"));
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
                sqls.set(0, rs.getString("PlaneID"));
                sqls.set(1, rs.getString("Model"));
                
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
                sqls.set(0, rs.getString("CrewID"));
                sqls.set(1, rs.getString("Position"));
                sqls.set(2, rs.getString("Position"));
                
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
        
        //Create Flight Ittinerarys
        for(int j = 0; j < planeList.size(); j++){
            FlightItinerary fi = new FlightItinerary(planeList.get(j), locationList.get(j), crewList.get(j));
            fl.addFlight(fi);
        }

        //return flight list
        return fl;
    }






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
