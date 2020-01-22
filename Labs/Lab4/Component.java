/**
 * Created by njg77 on 2/20/2017.
 */
public abstract class Component {
    protected int currCurrent;
    protected String name;
    protected Component source;

    //Constructs a component
    protected Component(String name, Component source){
        this.name = name;
        this.source = source;
    }

    public abstract boolean add(Component newElem);

    public abstract void reset();

    protected abstract void display(String offset);

    //calls the display in circuit
    public void display(){
        this.display("    ");
    }

    //Updates the current and calls the update current for the sources
    protected String updateCurrent(int deltaCurrent) {
        currCurrent += deltaCurrent;
        return source.updateCurrent(deltaCurrent);

    }
}
