import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Made by Nicholas Graca
 * This is the class that you run and this is my last CS2 Lab FOREVER!!!!! WOO HOO!!!!
 */
public class WoolieBattleRoyale {

    /**
     * Creates Woolies from a file and creates the SportsComplex as well as the troll.
     * Once everything has been created it will start everything else.
     * @param args
     * @throws FileNotFoundException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Scanner in = new Scanner(new File(args[0]));
        ArrayList<Woolie> woolies = new ArrayList();
        int maxBattles = Integer.parseInt(in.nextLine());
        SportsComplex sportsComplex = new SportsComplex(maxBattles);
        while(in.hasNext()){
            String[] line = in.nextLine().split(",");
            String name = line[0];
            int minAtk = Integer.parseInt(line[1]);
            int maxAtk = Integer.parseInt(line[2]);
            int hitTime = Integer.parseInt(line[3]);
            int maxHP = Integer.parseInt(line[4]);
            Woolie woolie = new Woolie(name, minAtk,maxAtk, hitTime, maxHP);
            woolies.add(woolie);
        }
        Troll troll = new Troll(woolies, sportsComplex);
        troll.beginBattleRoyale();
    }
}
