
public class Passenger {
    //Variables
    String fname;
    String lname;
    String email;
    String address;
    

    //Member Functions
    public Passenger(String f, String l, String e, String a){
        this.fname = f;
        this.lname = l;
        this.email = e;
        this.address = a;
    }

    //Getters
    public String getEmail() {
        return email;
    }
    public String getFname() {
        return fname;
    }
    public String getLname() {
        return lname;
    }
    public String getAddress() {
        return address;
    }


    //Setters
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public void serAddress(String address){
        this.address = address;
    }
    
}
