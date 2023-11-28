
public class Seat {
    //Variables SeatID, SeatNumber, SeatType, Price, IsBooked
    int id;
    String seatNumber;
    String seatType;
    int price;
    Boolean isBooked;

    

    //Member Functions
    public Seat(int i, String num, String type, int p, Boolean bool){
        this.id = i;
        this.seatNumber = num;
        this.seatType = type;
        this.price = p;
        this.isBooked = bool;
    }


    //Getters
    public int getId() {
        return id;
    }
    public Boolean getIsBooked() {
        return isBooked;
    }
    public int getPrice() {
        return price;
    }
    public String getSeatNumber() {
        return seatNumber;
    }
    public String getSeatType() {
        return seatType;
    }
    


    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setIsBooked(Boolean isBooked) {
        this.isBooked = isBooked;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }
    
    
}