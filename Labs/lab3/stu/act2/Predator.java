package rit.stu.act2;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by njg77 on 2/7/2017.
 */
public class Predator implements Player {
    public final int CHANCE_TO_BEAT_HOSTAGE = 75;
    public final int CHANCE_TO_BEAT_SOLDIER = 20;

    public Predator(){

    }

    @Override
    //Prints info about the predator being defeated
    public void defeat(Player player) {
        System.out.println("The predator cries out in glorious defeat to "+ player);
    }

    @Override
    //Prints info about the predator winning
    public void vicory(Player player) {
        System.out.println("The predator yells out in triumphant victory over "+ player);

    }

    @Override
    //prints predator
    public String toString() {
        return "Predator";
    }
}
