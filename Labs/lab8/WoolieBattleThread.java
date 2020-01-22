

/**
 * Made by Nicholas Graca
* This is where the woolies will actually fight
*/ 
class WoolieBattleThread extends Thread implements Runnable {
    private Woolie fighter1;
    private Woolie fighter2;
    private SportsComplex arena;

    /**
     * Constructor for the WoolieBattleThread
     * @param fighter1- A woolie that will fight fighter2
     * @param fighter2 A woolie that will fight fighter1
     * @param arena-The sportsComplex where the two fighters will fight
     */
    public WoolieBattleThread(Woolie fighter1, Woolie fighter2, SportsComplex arena) {
       this.fighter1 = fighter1;
       this.fighter2 = fighter2;
       this.arena = arena;
    }

    /**
     * Gets the woolie that is fighter1
     * @return a Woolie
     */
    public Woolie getFighter1() {
        return fighter1;
    }

    /**
     * Gets the woolie that is fighter2
     * @return a Woolie
     */
    public Woolie getFighter2() {
        return fighter2;
    }

    /**
     * Gets the winner of the fight
     * @return a Woolie
     */
    public Woolie getWinner(){
        if(fighter1.isOK()){
            return fighter1;
        }
        return  fighter2;
    }
    /**
     *Enters the area and will loop through until there is only one Woolie left. You can follow the print statements to
     * see what happens
     */ 
    public void run() {
        enterArena();
        int time = 0;
        while(getFighter1().isOK() && getFighter2().isOK()){
            if(time%getFighter1().getHitTime()==0){
                int damage = getFighter1().getAttackAmount();
                System.out.println(getFighter1().getName()+" does "+ damage+" damage to "+ getFighter2().getName());
                getFighter2().takeDamage(damage);
                System.out.println(getFighter2().getName()+" has "+fighter2.getCurrHP()+" HP left.");
                System.out.println();
            }
            if(time%getFighter2().getHitTime()==0){
                int damage = getFighter2().getAttackAmount();
                System.out.println(getFighter2().getName()+" does "+ damage+" damage to "+ getFighter1().getName());
                getFighter1().takeDamage(damage);
                System.out.println(getFighter1().getName()+" has "+fighter1.getCurrHP()+" HP left.");
                System.out.println();
            }
            time++;
        }
        try {
            sleep(1000);
        } catch (InterruptedException e) {
        }
        exitArena();
    }

    /**
     * calls the enterArena in SportsComplex
     */
    public void enterArena(){
        arena.enterArena(this);
    }

    /**
     * calls the leaveArena in SportsComplex
     */
    public void exitArena(){
        arena.leaveArena();
    }

}
