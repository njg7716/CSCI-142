package rit.stu.act2;
import rit.cs.Stack;
import rit.cs.StackList;

/**
 * Created by njg77 on 2/7/2017.
 */
public class Chopper {
    public static final int MAX_OCCUPANCY = 6;
    private Stack<Player> chopper;
    private int numPassengers;
    private int numRescued;

    //Constructs chopper
    public Chopper(){
        chopper = new StackList<>();
    }

    //checks to see if the chopper is empty
    public boolean isEmpty(){
        return chopper.empty();
    }

    //returns the number of passengers in the choppa
    public boolean isFull(){
        return numPassengers==6;
    }

    //returns the number of people rescued
    public int getNumRescued(){
        return numRescued;
    }

    //Empties the chopper and adds 6 to the number of people rescued
    public void rescuePassengers(){
        if(isFull()){
            for(int i=1;i<=6;i++){
                Player p = chopper.pop();
                System.out.println("Chopper transported "+p+" to safety!");
                numRescued++;
            }
            numPassengers = 0;
        }
    }

    //adds a passenger to the choppa
    public void boardPassenger(Player player) {
        if (isFull()) {
            rescuePassengers();

        } else {
            chopper.push(player);
            numPassengers++;
            System.out.println(player + " boards the chopper!");

        }

    }
}
