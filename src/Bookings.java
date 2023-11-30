public class Bookings {
    //Variables
    String UserID;
    String FlightID;
    String SeatID;
    

    //Member Functions
    public Bookings(String u, String f, String s){
        this.UserID = u;
        this.FlightID = f;
        this.SeatID = s;
    }

    //Getters
    public String getUserID() {
        return UserID;
    }
    public String getFlightID() {
        return FlightID;
    }
    public String getSeatID() {
        return SeatID;
    }


    //Setters
    public void setUserID(String User) {
        this.UserID = User;
    }
    public void setFlightID(String Flight) {
        this.FlightID = Flight;
    }
    public void setSeatID(String Seat) {
        this.SeatID = Seat;
    }
    
}