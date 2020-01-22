import java.util.ArrayList;

/**
 * Represents a vehicle in the parking simulator.
 *
 * @author Sean Strout @ RIT CS
 * @author Nicholas Graca
 */
public class Vehicle {
    private int plate;
    private boolean parked;
    private Permit permit;
    private ArrayList<Ticket> tickets = new ArrayList<>();
    /**
     * The main method runs a suite of test cases for the Vehicle class.
     *      <br>
     *      Step 1.  Stub out all the methods of the Vehicle class.  Use the
     *          javadocs to get method signatures.  For methods that return
     *          objects, return null.  For methods that return a boolean,
     *          return false.  For methods that return an integer, return 0.<br>
     *      <br><br>
     *      Step 2.  Run the program and it should fail every test.
     *      <br><br>
     *      Step 3.  Implement the Vehicle methods in the following order.  Use the
     *          UML diagram and javadocs to assist you.  After writing each method you
     *          should rerun the test method to make sure it produces the correct output.<br>
     *      <br><br>
     *              1. Add the private state and implement the constructor.<br>
     *              2. Implement getPlate()<br>
     *              3. Implement isParked()<br>
     *              4. Implement hasPermit()<br>
     *              5. Implement getPermit()<br>
     *              6. Implement getTickets()<br>
     *              7. Implement toString()<br>
     *              8. Implement setParked()<br>
     *              9. Implement equals()<br>
     *              10. Implement setPermit()<br>
     *              11. Implement giveTicket()<br>
     *      <br><br>
     *           It is expected that each test should produce "OK" for output.
     * @param args command line arguments (unused)
     */

    //The main method runs a suite of test cases for the Vehicle class.
    public static void main(String[] args) {
        // create a Vehicle, v1, whose plate number is 10, is unparked, with no
        // permit and no tickets (an empty list).
        Vehicle v1 = new Vehicle(10);

        // call the following operations on v1 and print each result:
        //      getPlate() -> 10
        //      isParked() -> false
        //      hasPermit() -> false
        //      getPermit() -> null
        //      getTickets() -> []
        System.out.println("v1 plate is 10? " + (10 == v1.getPlate() ? "OK" :
                "FAIL, got: " + v1.getPlate()));
        System.out.println("v1 is not parked? " + (!v1.isParked() ? "OK" : "FAIL"));
        System.out.println("v1 has no permit? " + (!v1.hasPermit() ? "OK" : "FAIL"));
        System.out.println("v1 permit is null? " + (v1.getPermit() == null ? "OK" :
                "FAIL, got: " + v1.getPermit()));
        System.out.println("v1 has no tickets? " + (v1.getTickets() != null && v1.getTickets().size() == 0 ?
                "OK" : "FAIL, got: " + v1.getTickets()));

        // check's Vehicle's toString(), you should get:
        //      Vehicle{plate=10, permit=null, parked=false, tickets=[]}
        System.out.println("v1 toString?: " +
            (v1.toString() != null && v1.toString().equals("Vehicle{plate=10, permit=null, parked=false, tickets=[]}") ?
                    "OK" : "FAIL, got: " + v1.toString()));

        // create a second Vehicle v2, whose plate number is 20, park it, test
        // it is parked and then its toString(), you should get:
        //      v2: Vehicle{plate=20, permit=null, parked=true, tickets=[]}
        Vehicle v2 = new Vehicle(20);
        v2.setParked(true);
        System.out.println("v2 toString?: " +
                (v2.toString() != null && v2.toString().equals("Vehicle{plate=20, permit=null, parked=true, tickets=[]}") ?
                        "OK" : "FAIL, got: " + v2.toString()));

        // create a third Vehicle v3, whose plate number is also 20 test it's
        // toString() is:
        //      v3: Vehicle{plate=20, permit=null, parked=false, tickets=[]}
        Vehicle v3 = new Vehicle(20);
        System.out.println("v3 toString?: " +
                (v3.toString() != null && v3.toString().equals("Vehicle{plate=20, permit=null, parked=false, tickets=[]}") ?
                        "OK" : "FAIL, got: " + v3.toString()));

        // verify that v2 and v3 are equal because they have the same plate number:
        System.out.println("v2 equals v3? " + (v2.equals(v3) ? "OK" : "FAIL"));

        // verify that v1 and v2 are not equal:
        System.out.println("v1 does not equal v3? " + (!v1.equals(v3) ? "OK" : "FAIL"));

        // verify that v1 is not equal to the string, "v1":
        System.out.println("v1 does not equal \"v1\"? " + (!v1.equals("v1") ? "OK" : "FAIL"));

        // issue a handicapped permit, p1, to v1 and make sure v1 has the permit
        Permit p1 = new Permit(1, Permit.Type.HANDICAPPED);
        v1.setPermit(p1);
        System.out.println("v1 with permit p1? " + (v1.getPermit() != null && v1.getPermit().equals(p1) ? "OK" : "FAIL"));

        // give a ticket, t2, to v2 for parking without a permit and verify it has it
        Ticket t1 = new Ticket(v2.getPlate(), Fine.NO_PERMIT);
        v2.giveTicket(t1);
        System.out.println("v2 with ticket t1? " + (v2.getTickets() != null &&
                v2.getTickets().get(0).equals(t1) ? "OK" : "FAIL"));

        // give another ticket, t3, to v2 and verify it has it as well
        Ticket t2 = new Ticket(v2.getPlate(), Fine.PARKING_HANDICAPPED);
        v2.giveTicket(t2);
        System.out.println("v2 with another ticket t2? " + (v2.getTickets() != null && v2.getTickets().get(1).equals(t2) ?
                "OK" : "FAIL"));
    }
    // creates a new Vehicle
    public Vehicle(int plate){
        this.plate = plate;
        this.parked = parked;
        this.permit = permit;
        this.tickets = tickets;
    }
    // gets the plate of the vehicle
    public int getPlate(){
        return plate;
    }

    //tells you whether the vehicle is parked or not
    public boolean isParked(){
        return parked;
    }

    //tells you if the vehicle has a permit or not
    public boolean hasPermit(){
        return permit instanceof Permit;
    }

    //gets the permit for the vehicle
    public Permit getPermit(){
        return permit;
    }

    //gets the tickets for the vehicle
    public ArrayList<Ticket> getTickets(){
        return tickets;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "plate=" + plate +
                ", permit=" + permit +
                ", parked=" + parked +
                ", tickets=" + tickets +
                '}';
    }

    //sets the situation of the vehicle
    public void setParked(boolean parked){
        this.parked = parked;
    }
    //compares two vehicles to see if they are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        return plate == vehicle.plate;
    }

    //sets the permit
    public void setPermit(Permit permit){
        this.permit = permit;
    }

    //gives the vehicle a ticket
    public void giveTicket(Ticket ticket){
        tickets.add(ticket);
    }
}
