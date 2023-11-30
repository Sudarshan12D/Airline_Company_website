
import java.util.ArrayList;

public class PassengerList {
    //Variables
    ArrayList<Passenger> listOfPassengers;

    //Member Functions
    public PassengerList(){
        listOfPassengers = new ArrayList<Passenger>();
    }

    void addPassenger(String f, String l, String e, String a){
        listOfPassengers.add(new Passenger(f, l, e, a));
    }

    void addPassenger(Passenger p){
        listOfPassengers.add(p);
    }


    //Getters
    public ArrayList<Passenger> getListOfPassengers() {
        return listOfPassengers;
    }


    //Setters
    public void setListOfPassengers(ArrayList<Passenger> listOfPassengers) {
        this.listOfPassengers = listOfPassengers;
    }
    
}