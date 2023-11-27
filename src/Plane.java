
import java.util.ArrayList;

public class Plane {
    //Variables
    ArrayList<Seat> listOfSeats;
    String callSign;
    int id;

    //Member Functions
    public Plane(String callSign){
        this.callSign = callSign;
        listOfSeats = new ArrayList<Seat>();
    }

    void addSeat(String r, int s, int id){
        listOfSeats.add(new Seat(r, s, id));
    }

    void addFlight(Seat s){
        listOfSeats.add(s);
    }

    //Getters
    public String getCallSign() {
        return callSign;
    }
    public int getId() {
        return id;
    }
    public ArrayList<Seat> getListOfSeats() {
        return listOfSeats;
    }


    //Setters
    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setListOfSeats(ArrayList<Seat> listOfSeats) {
        this.listOfSeats = listOfSeats;
    }
    
}
