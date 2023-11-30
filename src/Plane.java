
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

    void addSeat(int i, String num, String type, int p, Boolean bool){
        listOfSeats.add(new Seat(i, num, type, p, bool, id));
    }

    void addSeat(Seat s){
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

    public Seat getSeat(int n){
        for (int i = 0; i < listOfSeats.size(); i++){
            if (listOfSeats.get(i).id == n){
                return listOfSeats.get(i);
            }
        }
        return null;
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