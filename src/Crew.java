package src;

import java.util.ArrayList;

public class Crew {
    //Variables
    ArrayList<CrewMember> listOfCrew;

    //Member Functions
    public Crew(){
        listOfCrew = new ArrayList<CrewMember>();
    }

    void addFlight(String n, String p, int id){
        listOfCrew.add(new CrewMember(n, p, id));
    }

    void addFlight(CrewMember c){
        listOfCrew.add(c);
    }

    void removeFlight(){


    }

    //Getters


    //Setters
}
