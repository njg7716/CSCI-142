package rit.stu.act2;

/**
 * Created by njg77 on 2/7/2017.
 */
public class Hostage implements Player {
    private int id;

    public Hostage(int id){
        this.id = id;
    }


    @Override
    //Prints info about the hostage being defeated
    public void defeat(Player player) {
        System.out.println(toString()+ " cries, 'Defeated by "+ player+"'");
    }

    @Override
    //Prints info about the hostage winning
    public void vicory(Player player) {
        System.out.println(toString() + " yells, 'Victory over "+ player+"'");
    }

    @Override
    //prints the hostage and its id
    public String toString() {
        return "Hostage #" + id;
    }
}
