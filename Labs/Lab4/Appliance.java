/**
 * Created by njg77 on 2/20/2017.
 */
public class Appliance extends Component {
    private boolean inUse;
    private int reqCurrent;

    //Constructor for Appliance
    public Appliance(String name, Component source, int reqCurr){
        super(name,source);
        this.reqCurrent = reqCurr;

    }

    @Override
    //you cant add anything to an appliance so it always returns false
    public boolean add(Component newElem) {
        return false;
    }

    @Override
    //makes the current current 0 and turns the appliance off
    public void reset() {
        currCurrent = 0;
        inUse = false;

    }

    @Override
    //displays the appliance
    protected void display(String offset) {
        System.out.println(offset+"Appliance: "+name+" using "+ currCurrent+" amps");
    }

    //turns the appliance off or on and updates the current
    public String toggleUsage(){
        if (inUse){
            inUse = false;
            return updateCurrent(-reqCurrent);
        }
        else{
            inUse = true;
            return updateCurrent(reqCurrent);
        }
    }

    //checks to see if the appliance is in use or not
    public boolean getInUse(){
        return inUse;
    }
}
