
import java.util.ArrayList;

public class PassengerList {
    //Variables
    ArrayList<Passenger> listOfPassengers;

    //Member Functions
    public PassengerList(){
        listOfPassengers = new ArrayList<Passenger>();
    }

    void addFlight(String f, String l, String e){
        listOfPassengers.add(new Passenger(f, l, e));
    }

    void addFlight(Passenger p){
        listOfPassengers.add(p);
    }


    //Getters


    //Setters
    
}
