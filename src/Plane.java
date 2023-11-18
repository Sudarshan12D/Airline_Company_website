package src;

import java.util.ArrayList;

public class Plane {
    //Variables
    ArrayList<Seat> listOfSeats;

    //Member Functions
    public Plane(){
        listOfSeats = new ArrayList<Seat>();
    }

    void addSeat(String r, int s, int id){
        listOfSeats.add(new Seat(r, s, id));
    }

    void addFlight(Seat s){
        listOfSeats.add(s);
    }

    //Getters


    //Setters
    
}
