
import java.util.ArrayList;

public class Crew {
    //Variables
    ArrayList<CrewMember> listOfCrew;

    //Member Functions
    public Crew(){
        listOfCrew = new ArrayList<CrewMember>();
    }

    void addCrew(String n, String p, int id){
        listOfCrew.add(new CrewMember(id, n, p));
    }

    void addCrew(CrewMember c){
        listOfCrew.add(c);
    }

    void removeFlight(){


    }

    //Getters
    public ArrayList<CrewMember> getListOfCrew() {
        return listOfCrew;
    }
    

    //Setters
    public void setListOfCrew(ArrayList<CrewMember> listOfCrew) {
        this.listOfCrew = listOfCrew;
    }
}