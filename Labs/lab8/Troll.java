import java.util.ArrayList;

/**
 * Made by Nicholas Graca
 */
public class Troll {
    public static int seed;
    public ArrayList<Woolie> woolies;
    public SportsComplex sportsComplex;

    /**
     * Constructor for the Troll
     * @param woolies
     * @param sportsComplex
     */
    public Troll(ArrayList<Woolie> woolies, SportsComplex sportsComplex) {
        this.woolies = woolies;
        this.sportsComplex = sportsComplex;
    }

    /**
     * Creates all the threads and puts the Woolies in them. It will start the threads and join them all.
     * Also it prints the last Woolie alive!
     * @throws InterruptedException
     */
    public void beginBattleRoyale() throws InterruptedException {
        ArrayList<WoolieBattleThread> threads = new ArrayList<>();
        int round = 1;
        while(woolies.size()>1) {
            System.out.println("Round "+ round+ " is about to begin!");
            System.out.println("The contestants are:");
            for(Woolie w:woolies){
                System.out.println(w);
            }
            System.out.println();
            while (woolies.size()>1) {
                Woolie temp1 = woolies.remove(0);
                Woolie temp2 = woolies.remove(0);
                WoolieBattleThread t = new WoolieBattleThread(temp1, temp2, sportsComplex);
                threads.add(t);
                t.start();
            }

            for (WoolieBattleThread t: threads) {
                t.join();
                Woolie winner = t.getWinner();
                winner.reset();
                if(woolies.contains(winner)){
                    break;
                }
                woolies.add(winner);
            }
            System.out.println("The round is over!");
            System.out.println();
            round++;
        }
        System.out.println(woolies.get(0).getName()+" is the last one standing!");
    }
}
