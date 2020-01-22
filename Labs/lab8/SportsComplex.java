/**
 * Made by Nicholas Graca
 */
class SportsComplex {
    private int maxBattles;
    private int currBattles;

    /**
     * Constructor for the SportsComplex
     */
    public SportsComplex(int maxInUse) {
        this.currBattles = 0;
        this.maxBattles = maxInUse;
    }

    /**
     * If there are already enough battles happening then it will tell the thread to wait
     * otherwise it will start the battle and update the number of battles
     * @param t
     */
    public synchronized void enterArena (WoolieBattleThread t){
        while(currBattles == maxBattles){
            try {
                System.out.println("WOOLIES: "+ t.getFighter1().getName()+" and "+ t.getFighter2().getName()+" enter Arena Line to Battle.");
                wait();
            }catch (InterruptedException e){
            }

        }
        System.out.println("WOOLIES: "+ t.getFighter1().getName()+" and "+ t.getFighter2().getName()+" enter Arena to Battle.");
        currBattles++;
    }

    /**
     * Decrements the number of battles and notifies the other threads
     */
    public synchronized void leaveArena (){
        currBattles--;
        notifyAll();
    }
}
