
public class CrewMember {
    //Variables
    String name;
    String position;
    int id;
    

    //Member Functions
    public CrewMember(int id, String n, String p){
        this.name = n;
        this.position = p;
        this.id = id;
    }

    //Getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPosition() {
        return position;
    }


    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    
}