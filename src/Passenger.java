
public class Passenger {
    //Variables
    String fname;
    String lname;
    String email;
    

    //Member Functions
    public Passenger(String f, String l, String e){
        this.fname = f;
        this.lname = l;
        this.email = e;
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
    
}
