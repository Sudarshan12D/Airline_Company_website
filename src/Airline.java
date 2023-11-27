

public class Airline {
    //Variables
    String airlineName;
    int idNum;
    

    //Member Functions
    public Airline(String name, int id){
        this.airlineName = name;
        this.idNum = id;

    }

    //Getters
    public String getAirlineName() {
        return airlineName;
    }

    public int getIdNum() {
        return idNum;
    }

    //Setters
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }


}
