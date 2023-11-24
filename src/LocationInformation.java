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
    public String getArrAirport() {
        return arrAirport;
    }
    public String getArrGate() {
        return arrGate;
    }
    public String getArrLocation() {
        return arrLocation;
    }
    public LocalDateTime getArrTime() {
        return arrTime;
    }
    public String getDepAirport() {
        return depAirport;
    }
    public String getDepGate() {
        return depGate;
    }
    public String getDepLocation() {
        return depLocation;
    }
    public LocalDateTime getDepTime() {
        return depTime;
    }



    //Setters
    public void setArrAirport(String arrAirport) {
        this.arrAirport = arrAirport;
    }
    public void setArrGate(String arrGate) {
        this.arrGate = arrGate;
    }
    public void setArrLocation(String arrLocation) {
        this.arrLocation = arrLocation;
    }
    public void setArrTime(LocalDateTime arrTime) {
        this.arrTime = arrTime;
    }
    public void setDepAirport(String depAirport) {
        this.depAirport = depAirport;
    }
    public void setDepGate(String depGate) {
        this.depGate = depGate;
    }
    public void setDepLocation(String depLocation) {
        this.depLocation = depLocation;
    }
    public void setDepTime(LocalDateTime depTime) {
        this.depTime = depTime;
    }
    
}
