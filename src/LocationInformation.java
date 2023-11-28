
public class LocationInformation {
    //Variables
    String arrTime;
    String arrLocation;

    String depTime;
    String depLocation;

    
    //Member Functions
    public LocationInformation(String arrTime, String arrLocation, String depTime, String depLocation){
        this.arrTime = arrTime;
        this.arrLocation = arrLocation;

        this.depTime = depTime;
        this.depLocation = depLocation;
    }

    //Getters
    public String getArrLocation() {
        return arrLocation;
    }
    public String getArrTime() {
        return arrTime;
    }
    public String getDepLocation() {
        return depLocation;
    }
    public String getDepTime() {
        return depTime;
    }



    //Setters
    public void setArrLocation(String arrLocation) {
        this.arrLocation = arrLocation;
    }
    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }
    public void setDepLocation(String depLocation) {
        this.depLocation = depLocation;
    }
    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }
    
}
