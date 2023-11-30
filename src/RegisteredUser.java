public class RegisteredUser {
    String email;
    String fname;
    String lname;
    String creditCardNumber;
    Boolean isMember;
    String address;
    int idNumber;


    public RegisteredUser(String e, String f, String l, String c, String a, Boolean m, int i){
        this.email = e;
        this.fname = f;
        this.lname = l;
        this.creditCardNumber = c;
        this.address = a;
        this.isMember = m;
        this.idNumber = i;
    }

    //Get
    public String getAddress() {
        return address;
    }
    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    public String getEmail() {
        return email;
    }
    public String getFname() {
        return fname;
    }
    public Boolean getIsMember() {
        return isMember;
    }
    public String getLname() {
        return lname;
    }

    //Set
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public void setIsMember(Boolean isMember) {
        this.isMember = isMember;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }



}
