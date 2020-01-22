package rit.stu.act2;

import rit.cs.QueueList;
import rit.cs.StackList;
import rit.cs.Stack;
import rit.cs.Queue;

import java.util.Random;

/**
 * Created by njg77 on 2/7/2017.
 */
public class EnemyBase {
    private Queue<Guerilla> guerillas;
    private Stack<Hostage> hostages;
    private int numGuerillas;
    private int numHostages;

    //Constructs the enemy base with the given numbers of hostages and gorrillas
    public EnemyBase(int numHostages, int numGuerillas){
        this.numHostages = numHostages;
        this.numGuerillas = numGuerillas;
        hostages = new StackList<>();
        for (int i=1; i<=numHostages; i++){
            Hostage h = new Hostage(i);
            hostages.push(h);
        }
        guerillas = new QueueList<>();
        for (int i=1; i<=numGuerillas; i++){
            Guerilla g = new Guerilla(i);
            guerillas.enqueue(g);
        }
    }

    //adds a guerrilla to the queue
    private void addGuerilla(Guerilla guerilla){
        guerillas.enqueue(guerilla);
        numGuerillas++;
    }

    //adds a hostage to the stack
    private void addHostage(Hostage hostage){
        hostages.push(hostage);
        numHostages++;
    }

    //returns and removes a guerrilla from the guerrilla queue
    private Guerilla getGuerilla(){
        numGuerillas--;
        return guerillas.dequeue();
    }

    //returns and removes a hostage from the stack
    private Hostage getHostage(){
        numHostages--;
        return hostages.pop();
    }

    //returns the number of guerrillas
    public int getNumGuerillas() {
        return numGuerillas;
    }

    //returns the number of hostages
    public int getNumHostages() {
        return numHostages;
    }

    //causes the soldier and guerrilla to fight and see if the hostage leaves the base safely
    public Hostage rescueHostage(Soldier soldier){
        System.out.println(soldier + " enters enemy base...");
        Hostage h = getHostage();
        if(guerillas.empty()){
            numHostages--;
            return h;

        }
        else{
            Guerilla g = getGuerilla();
            Random di = new Random();
            int result = di.nextInt(100)+1;
            System.out.println(soldier+ "battles "+ g + " who rolls a #" + result);
            if(result>Guerilla.CHANCE_TO_BEAT_SOLDIER ){
                soldier.vicory(g);
                g.defeat(soldier);
                numHostages--;
                return h;
            }
            else{
                g.vicory(soldier);
                soldier.defeat(g);
                hostages.push(h);
                numGuerillas++;
            }
        }
        return null;
    }
}
