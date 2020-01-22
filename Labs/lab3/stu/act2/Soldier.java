package rit.stu.act2;

/**
 * Created by njg77 on 2/7/2017.
 */
public class Soldier implements Player{
    private int id;

    public Soldier(int id){
        this.id = id;
    }

    @Override
    public void defeat(Player player) {
        String soldier = toString();
        System.out.println( soldier + " cries, 'Besiegt von " + player + "!");
    }

    @Override
    public void vicory(Player player) {
        String soldier = toString();
        System.out.println( soldier + " yells, 'Sieg \u00FCber " + player + "!");

    }

    @Override
    public String toString() {
        return "Soldier #" + id;
    }
}
