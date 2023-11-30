public class Bookings {
    //Variables
    String UserID;
    String FlightID;
    String SeatID;
    String BookingID;
    

    //Member Functions
    public Bookings(String u, String f, String s, String b){
        this.UserID = u;
        this.FlightID = f;
        this.SeatID = s;
        this.BookingID = b;
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
    public String getBookingID(){
        return BookingID;
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
    public void setBookingID(String Booking){
        this.BookingID = Booking;
    }
    
}