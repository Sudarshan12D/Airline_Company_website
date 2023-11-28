import java.util.ArrayList;

public class FlightList {
    //Variables
    ArrayList<FlightItinerary> listOfFlights;

    //Member Functions
    public FlightList(){
        listOfFlights = new ArrayList<FlightItinerary>();

    }

    void addFlight(int id, Plane p, LocationInformation l, Crew c){
        listOfFlights.add(new FlightItinerary(id, p, l, c));
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

    public FlightItinerary getFlightItinerary(int num){
        return listOfFlights.get(num);
    }

    public ArrayList<Object[]> getAllFlightInfo(){
        ArrayList<Object[]> flights = new ArrayList<>();

        for(int i = 0; i < listOfFlights.size(); i++){
            Object[] row = new Object[5]; // 5 columns in the flight table
            row[0] = listOfFlights.get(i).getId();
            row[1] = listOfFlights.get(i).getLocationInformation().getDepLocation();
            row[2] = listOfFlights.get(i).getLocationInformation().getArrLocation();
            row[3] = listOfFlights.get(i).getLocationInformation().getDepTime();
            row[4] = listOfFlights.get(i).getLocationInformation().getArrTime();
            flights.add(row);
        }

        return flights;
    }


    //Setters
    public void setListOfFlights(ArrayList<FlightItinerary> listOfFlights) {
        this.listOfFlights = listOfFlights;
    }

}