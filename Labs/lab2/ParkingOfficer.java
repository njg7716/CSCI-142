import java.util.ArrayList;

public class ParkingOfficer extends Object{
    private ParkingLot lot;
    private static final int PAUSE_TIME = 1000;
    private ArrayList<Ticket> tickets;

    //Create the parking officer.
    public ParkingOfficer() {
        this.tickets = tickets;
    }

    //Determine the type of fine a vehicle *would* get if they parked in a spot. If a vehicle is in a handicapped spot and either doesn't have a permit, or a handicapped permit, the fine is Fine.PARKING_HANDICAPPED. If a vehicle is in a reserved spot and either doesn't have a permit, or a handicapped or reserved permit, the fine is Fine.PARKING_RESERVED. If a vehicle is parked in a general spot and does not have a permit, the fine is NO_PERMIT. Otherwise there is no fine, Fine.NO_FINE.
    public static Fine getFineVehicleSpot(Vehicle vehicle, ParkingSpot spot){
        Fine fine;
        if(vehicle.getPermit() == null) {
            fine = Fine.NO_PERMIT;
        }
        else if(vehicle.getPermit().getType().equals(Permit.Type.HANDICAPPED) == false && spot.getType() == Permit.Type.HANDICAPPED){
            fine = Fine.PARKING_HANDICAPPED;
        }
        else if(vehicle.getPermit().getType().equals(Permit.Type.HANDICAPPED) == false && spot.getType() == Permit.Type.RESERVED && vehicle.getPermit().equals(Permit.Type.RESERVED) == false) {
            fine = Fine.PARKING_RESERVED;
        }
        else{
            fine = Fine.NO_FINE;
        }
        return fine;
    }

    //Connect the parking officer to the parking lot they will be responsible for patrolling.
    public void setParkingLot(ParkingLot lot){
        this.lot = lot;
    }

    //Get all the tickets the officer has issued.
    public ArrayList<Ticket> getTickets(){
        return tickets;
    }

    //Issue a ticket to a vehicle with a fine. If a ticket is issued it should print:  Issuing ticket to: {vehicle} in spot {spot} for {fine} Where {vehicle} is the Vehicle, {spot} is the spot number and {fine} is the Fine type.
    private void issueTicket(Vehicle vehicle, int spot, Fine fine){
        System.out.println("Issuing ticket to: " + vehicle + "in spot" + spot + "for" + fine);
    }

    //When the officer patrols the lot, they go to each spot (starting at the first until last). If there is a vehicle in the spot, they check whether a fine should be assessed (hint, see the helper method getFineVehicleSpot()). If there is a fine (not Fine.NO_FINE), then a ticket should be issued (see issueTicket()), and then the officer should pause for a second (see RITParking.pause()).
    public void patrolLot(){
        int i = 0;
        Fine fine;
        while(i< lot.getCapacity()){
            if(lot.isSpotVacant(i) == false){
                fine = getFineVehicleSpot(lot.getSpot(i).getVehicle(),lot.getSpot(i));
                if(fine != Fine.NO_FINE){
                    issueTicket(lot.getSpot(i).getVehicle(), i , fine);
                    RITParking.pause(PAUSE_TIME);
                }
            }
        }
    }

    //A test method for the parking officer.
    public static void main(String[] args) {
        ParkingOfficer po = new ParkingOfficer();
        ParkingLot pl = new ParkingLot(5,5,20);
        po.setParkingLot(pl);
        Vehicle v1 = new Vehicle(1);
        Permit p1 = new Permit(1, Permit.Type.GENERAL);
        v1.setPermit(p1);
    }
}
