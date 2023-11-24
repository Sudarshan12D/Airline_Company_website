import java.util.ArrayList;

public class FlightList {
    //Variables
    ArrayList<FlightItinerary> listOfFlights;

    //Member Functions
    public FlightList(){
        listOfFlights = new ArrayList<FlightItinerary>();

    }

    void addFlight(Plane p, Airline a, LocationInformation l, Crew c){
        listOfFlights.add(new FlightItinerary(p, a, l, c));
    }

    void addFlight(FlightItinerary f){
        listOfFlights.add(f);
    }

    void removeFlight(){


    }

    //Getters
    public ArrayList<FlightItinerary> getListOfFlights() {
        return listOfFlights;
    }


    //Setters
    public void setListOfFlights(ArrayList<FlightItinerary> listOfFlights) {
        this.listOfFlights = listOfFlights;
    }
    
}

