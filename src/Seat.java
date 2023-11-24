
public class Seat {
    //Variables
    String row;
    int id;
    int seatNumber;
    

    //Member Functions
    public Seat(String r, int s, int id){
        this.row = r;
        this.seatNumber = s;
        this.id = id;
    }


    //Getters
    public int getId() {
        return id;
    }
    public String getRow() {
        return row;
    }
    public int getSeatNumber() {
        return seatNumber;
    }


    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setRow(String row) {
        this.row = row;
    }
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    
}
