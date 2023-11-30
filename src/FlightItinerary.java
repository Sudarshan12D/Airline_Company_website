public class FlightItinerary {
    //Variables
    int id;
    Plane plane;
    
    LocationInformation locationInformation; 
    Crew crew;


    //Member Functions
    public FlightItinerary(int id, Plane p, LocationInformation l, Crew c){
        this.id = id;
        this.plane = p;
        this.locationInformation = l;
        this.crew = c;
    }

    //Getters

    public Crew getCrew() {
        return crew;
    }
    public int getId() {
        return id;
    }
    public LocationInformation getLocationInformation() {
        return locationInformation;
    }
    public Plane getPlane() {
        return plane;
    }


    //Setters

    public void setCrew(Crew crew) {
        this.crew = crew;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setLocationInformation(LocationInformation locationInformation) {
        this.locationInformation = locationInformation;
    }
    public void setPlane(Plane plane) {
        this.plane = plane;
    }



    

    

}