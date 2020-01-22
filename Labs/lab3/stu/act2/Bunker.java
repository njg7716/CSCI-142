package rit.stu.act2;
import rit.cs.QueueList;

/**
 * Created by njg77 on 2/7/2017.
 */
public class Bunker {
    private QueueList<Soldier> bunker;
    private int numSoldiers;

    //Constucts bunker clas
    public Bunker(int numSoldiers){
        this.numSoldiers = numSoldiers;
        bunker = new QueueList<>();
        for (int i=1; i<=numSoldiers; i++){
            Soldier s = new Soldier(i);
            bunker.enqueue(s);
        }
    }
    //Checks to see if there are soldiers in the bunker
    public boolean hasSoldiers(){
        return bunker.empty();
    }

    //return the number of soldiers
    public int getNumSoldiers(){
        return numSoldiers;
    }

    //removes a soldier from the bunker to get a hostage
    public Soldier deployNextSoldier(){
        numSoldiers--;
        return bunker.dequeue();

    }

    //adds the soldier back into the bunker
    public void fortifySoldiers(Soldier soldier){
        bunker.enqueue(soldier);
        numSoldiers++;
    }
}
