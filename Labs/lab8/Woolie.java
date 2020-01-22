import java.util.Random;

/**
 * Made by Nicholas Graca
 */
public class Woolie {

    static int seed;
    private String name;
    private int minAtk;
    private int maxAtk;
    private int hitTime;
    private int maxHP;
    private int currHP;


    /**
     * Constructor for the Woolie
     * @param name
     * @param minAtk
     * @param maxAtk
     * @param hitTime
     * @param maxHP
     */
    public Woolie(String name, int minAtk, int maxAtk, int hitTime, int maxHP) {
        this.name= name;
        this.minAtk = minAtk;
        this.maxAtk = maxAtk;
        this.hitTime = hitTime;
        this.maxHP = maxHP;
        this.currHP = maxHP;
    }

    /**
     * Getter for the name of the Woolie
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Getter fot the amount of attack which is a random number between the min and max attack value for that Woolie
     * @return
     */
    public int getAttackAmount(){
        Random random = new Random();
        return random.nextInt((maxAtk-minAtk)+1)+minAtk;
    }

    /**
     * Getter for the Hit Time
     * @return
     */
    public int getHitTime() {
        return hitTime;
    }

    /**
     * getter for the Max HP
     * @return
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * Getter for the current amount of HP the Woolie has
     * @return
     */
    public int getCurrHP(){
        return currHP;
    }

    /**
     * decreases the current HP of the Woolie by the amount specified
     * @param damage
     */
    public void takeDamage(int damage){
        this.currHP -= damage;
    }

    /**
     * Checks to see if the Woolie is alive
     * @return
     */
    public boolean isOK (){
        if(currHP>0){
            return true;
        }
        return false;
    }

    /**
     * Resets the current HP to be the Max HP
     */
    public void reset(){
        currHP = maxHP;
    }

    /**
     * The toString method for the Woolie
     * @return
     */
    @Override
    public String toString() {
        return
                name + ": Max HP=" + maxHP +
                "min Attack=" + minAtk +
                ", max Attack=" + maxAtk +
                ", Hit Time=" + hitTime +
                ", Max HP=" + maxHP;
    }
}
