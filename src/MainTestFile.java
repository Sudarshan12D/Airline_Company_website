import java.time.LocalDateTime;
import java.time.Month;

public class MainTestFile {
    public static void main(String[] args) {
        FlightList FL = new FlightList();

        //FLight 1 Information
        Plane p1 = new Plane("AC150");
        Airline a1 = new Airline("Air Canada", 44);
        LocalDateTime arr1 = LocalDateTime.of(
            2024, Month.JUNE, 24, 14, 33, 48);
        LocalDateTime dep1 = LocalDateTime.of(
            2024, Month.JUNE, 23, 10, 40, 12);

        CrewMember c1 = new CrewMember("Jeff", "Captain 1", 12);
        CrewMember c2 = new CrewMember("Garry", "Flight Attendant", 40);
        CrewMember c3 = new CrewMember("Fred", "Captain 2", 82);
        Crew crew1 = new Crew();
        crew1.addCrew(c1);
        crew1.addCrew(c2);
        crew1.addCrew(c3);
        LocationInformation l1 = new LocationInformation(
            arr1, "Toronto Canada", "Toronto International", "A43", 
            dep1, "Calgary Canada", "Calgary International", "C32");
        FlightItinerary f1 = new FlightItinerary(p1, a1, l1, crew1);


        //Flight 2 Information
        Plane p2 = new Plane("WJ664");
        Airline a2 = new Airline("West Jet", 55);
        LocalDateTime arr2 = LocalDateTime.of(
            2024, Month.AUGUST, 12, 5, 00, 00);
        LocalDateTime dep2 = LocalDateTime.of(
            2024, Month.AUGUST, 11, 23, 45, 00);

        CrewMember c4 = new CrewMember("Rachel", "Captain 1", 77);
        CrewMember c5 = new CrewMember("Sandra", "Flight Attendant", 88);
        CrewMember c6 = new CrewMember("Paul", "Captain 2", 99);
        Crew crew2 = new Crew();
        crew1.addCrew(c4);
        crew1.addCrew(c5);
        crew1.addCrew(c6);
        LocationInformation l2 = new LocationInformation(
            arr2, "Montreal", "Montreal International", "B66",
            dep2, "Calgary Canada", "Calgary International", "C99");
        FlightItinerary f2 = new FlightItinerary(p2, a2, l2, crew2);


        FL.addFlight(f1);
        FL.addFlight(f2);

        System.out.println("Made it through");
    }
}
