


public class FlightItinerary {
    //Variables
    static int idSetter = 0;
    int id;
    Plane plane;
    
    LocationInformation locationInformation; 
    Crew crew;


    //Member Functions
    public FlightItinerary(Plane p, LocationInformation l, Crew c){
        this.id = idSetter++;
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
    public static int getIdSetter() {
        return idSetter;
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
    public static void setIdSetter(int idSetter) {
        FlightItinerary.idSetter = idSetter;
    }
    public void setLocationInformation(LocationInformation locationInformation) {
        this.locationInformation = locationInformation;
    }
    public void setPlane(Plane plane) {
        this.plane = plane;
    }

}
