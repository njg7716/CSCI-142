import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;

public class ParkingLot {
    private int capacity;
    private int generalSpots;
    private int handicappedSpots;
    public static final int ILLEGAL_SPOT = -1;
    private static final int SPOTS_PER_LINE = 10;
    private ArrayList<ParkingSpot> lot;
    private int parkedVehicles;
    private int reservedSpots;

    //Create a new parking lot.
    public ParkingLot(int handicappedSpots, int reservedSpots, int generalSpots){
        this.handicappedSpots = handicappedSpots;
        this.reservedSpots = reservedSpots;
        this.generalSpots = generalSpots;
        capacity = reservedSpots + handicappedSpots + generalSpots;
        lot = new ArrayList<>();
        initializeSpots();
    }

    //Get the total number of spots in the lot.
    public int getCapacity() {
        return capacity;
    }

    //Get a parking spot.
    public ParkingSpot getSpot(int spot) {
       return lot.get(spot);
    }

    //Get the number of spots that are occupied by vehicles.
    public int getNumParkedVehicles() {
        return parkedVehicles;
    }

    //Create the parking spots for the lot. This is a helper method that is called by the constructor, after the lot has been created, to initialize and add each new spot to the lot.
    private void initializeSpots(){
        int i = 0;
        while(i<handicappedSpots){
            lot.add(new ParkingSpot(i, Permit.Type.HANDICAPPED));
            ++i;
        }
        while(i<reservedSpots + handicappedSpots){
            lot.add(new ParkingSpot(i, Permit.Type.RESERVED));
            ++i;
        }
        while(i<handicappedSpots + generalSpots + reservedSpots){
            lot.add(new ParkingSpot(i, Permit.Type.GENERAL));
            ++i;
        }
    }

    //Tells whether a spot is in a valid range within the parking lot or not.
    public boolean isSpotValid(int spot){
        if(spot>capacity){
            return false;
        }
        else{
            return true;
        }
    }

    //Is a parking spot open at the moment (not occupied by a vehicle)?
    public boolean isSpotVacant(int spot){
        if (getSpot(spot).getVehicle() == null){
            return true;
        }
        else{
            return false;
        }
    }

    //Park a vehicle in a spot. If the spot is already occupied by another vehicle, it cannot be parked in this spot.
    public boolean parkVehicle(Vehicle vehicle, int spot){
        if(isSpotVacant(spot)==false){
            return false;
        }
        else {
            getSpot(spot).occupySpot(vehicle);
            parkedVehicles++;
            return true;
        }
    }

    //Remove a vehicle from a parked spot. If the vehicle is found in a parked spot in the lot, it is removed from the spot.
    public int removeVehicle(Vehicle vehicle){
        int i = 0;
        while(i<lot.size()){
            if(lot.get(i).getVehicle() == vehicle){
                getSpot(i).vacateSpot();
                parkedVehicles--;
                return i;
            }
            i++;
        }
        return ILLEGAL_SPOT;
    }

    //Return a string representation of the parking lot. The format is 10 spots per line, with a space between each spot (hint, use the ParkingSpot's toString() to get each spot's string). Once all spots are printed, on a new line print the number of vacant spots. For example:
    public String toString(){
        int i = 0;
        String Lot= "";
        while(i< capacity){
            if(i%SPOTS_PER_LINE == 0 && i != 0){
                Lot+="\n";
            }
                Lot += lot.get(i).toString();
                Lot += " ";
                ++i;

        }
        Lot += "\nVacant Spots: "+ (capacity-parkedVehicles);
        return Lot;
    }
    //Tests class
    public static void main(String[] args) {
        System.out.println("Test #1");
        ParkingLot pl = new ParkingLot(5,5,20);
        Vehicle v1 = new Vehicle(1);
        pl.parkVehicle(v1,10);
        System.out.println(pl.toString());

        System.out.println("Test #2");
        pl.removeVehicle(v1);
        System.out.println(pl);
    }

}
