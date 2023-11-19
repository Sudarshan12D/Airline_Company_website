


public class FlightItinerary {
    //Variables
    static int idSetter = 0;
    int id;
    Plane plane;
    Airline airline;
    LocationInformation locationInformation; 
    Crew crew;


    //Member Functions
    public FlightItinerary(Plane p, Airline a, LocationInformation l, Crew c){
        this.id = idSetter++;
        this.plane = p;
        this.airline = a;
        this.locationInformation = l;
        this.crew = c;
    }

    //Getters


    //Setters

}
