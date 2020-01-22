package rit.stu.act2;

/**
 * Created by njg77 on 2/7/2017.
 */
public class Guerilla implements Player {
    public static  final int CHANCE_TO_BEAT_SOLDIER = 20;
    private int id;

    public Guerilla(int id){
        this.id = id;
    }

    @Override
    //Prints info about the guerrilla being defeated
    public void defeat(Player player) {
        System.out.println(toString()+ " cries, 'Derrotado por " + player.toString());
    }

    @Override
    //Prints info about the guerrilla winning
    public void vicory(Player player) {
        System.out.println(toString()+ " yells, 'Victoria sobre " + player.toString());

    }

    @Override
    //prints the Guerrillas and its information
    public String toString() {
        return "Guerilla #" + id;
    }
}
