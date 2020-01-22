public class ParkingSpot {
    final static String OCCUPIED_STR = "*";
    private int spot;
    private Permit.Type type;
    private Vehicle vehicle;

    //Create a new parking spot.
    public ParkingSpot(int spot, Permit.Type type){
        this.spot = spot;
        this.type = type;
    }

    //Get the spot number.
    public int getSpot() {
        return spot;
    }

    //Get the spot type.
    public Permit.Type getType() {
        return type;
    }

    //Get the vehicle in the spot.
    public Vehicle getVehicle() {
        return vehicle;
    }

    //The main test function for the ParkingSpot class.
    public static void main(String[] args) {
        Vehicle v1 = new Vehicle(10);
        ParkingSpot p1 = new ParkingSpot(1, Permit.Type.HANDICAPPED);
        System.out.println("Test #1:");
        verifySpot("p1", p1, 1, Permit.Type.HANDICAPPED, null);
        p1.occupySpot(v1);
        System.out.println();
        System.out.println("Test #2");
        verifySpot("p1", p1, 1, Permit.Type.HANDICAPPED, v1);
        p1.vacateSpot();
        System.out.println();
        System.out.println("Test #3");
        verifySpot("p1", p1, 1, Permit.Type.HANDICAPPED, null);



    }

    //Have a vehicle take this spot and become parked.
    public void occupySpot(Vehicle vehicle){
        this.vehicle = vehicle;
        vehicle.setParked(true);
    }

    @Override
    //The string representation of a parking spot is in the format:  ##:?  Where ## is the spot number, and ? is the spot type (* if occupied, otherwise if unoccupied it is either H for handicapped, R for reserved or G for general.
    public String toString() {
        if(type == Permit.Type.HANDICAPPED){
            return spot + ":" + "H";
        }
        if(type == Permit.Type.GENERAL){
            return spot + ":" + "G";
        }
        if(type == Permit.Type.RESERVED){
            return spot + ":" + "R";
        }
        else{
            return spot + ":" + OCCUPIED_STR;
        }
    }

    //Have a vehicle leave a spot and become unparked.
    public void vacateSpot(){
        vehicle = null;
    }

    //Verify a parking spot has the correct spot id, type and vehicle.
    public static void verifySpot(String spotVar, ParkingSpot s, int spot,
                                  Permit.Type type, Vehicle vehicle){
        if(s.getSpot() == spot){
            System.out.println("The spots are the same.");
        }
        else{
            System.out.println("The spots are different. Should have gotten" + s.getSpot() +
                                "Instead got: " + spot);
        }
        if(s.getType() == type){
            System.out.println("The types are the same.");
        }
        else{
            System.out.println("The types are different. Should have gotten" + s.getType() +
                    "Instead got: " + type);
        }
        if(s.getVehicle() == null  && vehicle == null) {
            System.out.println("Both Vehicles are null.");
        }
        else{
            if(s.getVehicle().equals(vehicle) == true){
                System.out.println("The Vehicles are the same.");
            }
            else{
            System.out.println("The plates are different. Should have gotten" + s.getVehicle().getPlate() +
                    "Instead got: " + vehicle.getPlate());
            }
            if(s.getVehicle().isParked() == true){
                System.out.println("The Vehicle is currently parked.");
            }
            else{
                System.out.println("The Vehicle is not currently parked.");
            }
        }


    }
}
