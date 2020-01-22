/**
 * Created by njg77 on 2/20/2017.
 */
public class Circuit extends MultiComponent {
    private int maxCapacity;

    //Constructs the circuit
    public Circuit(String name, Component source, int maxCapacity){
        super(name, source);
        this.maxCapacity = maxCapacity;
    }

    @Override
    //Adds a receptacle to as a child of the current circuit and checks to see if it is a receptacle or not
    public boolean add(Component el) {
        if (el instanceof Appliance){
            return false;
        }
        children.add(el);
        return true;
    }

    @Override
    //displays the circuit and calls the display function for the children
    protected void display(String offset) {
        System.out.println("Circuit: " + name + " using " + currCurrent + " out of " + maxCapacity + " amps");
        for (Component c: children) {
            c.display("    ");
        }
        }

    @Override
    //updates the amount of current going through the circuit and sees if it is overloaded
    protected String updateCurrent(int deltaCurrent){
        if(currCurrent+deltaCurrent>maxCapacity){
            reset();
            return name;
        }
        else{
            currCurrent+=deltaCurrent;
        }
        return null;

    }


}
