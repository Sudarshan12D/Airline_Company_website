package src;
import java.time.LocalDateTime;

public class LocationInformation {
    //Variables
    LocalDateTime arrTime;
    String arrLocation;
    String arrAirport;
    String arrGate;

    LocalDateTime depTime;
    String depLocation;
    String depAirport;
    String depGate;

    
    //Member Functions
    public LocationInformation(LocalDateTime arrTime, String arrLocation, String arrAirport, String arrGate, LocalDateTime depTime, String depLocation, String depAirport, String depGate){
        this.arrTime = arrTime;
        this.arrLocation = arrLocation;
        this.arrAirport = arrAirport;
        this.arrGate = arrGate;

        this.depTime = depTime;
        this.depLocation = depLocation;
        this.depAirport = depAirport;
        this.depGate = depGate;
    }

    //Getters


    //Setters
    
}
