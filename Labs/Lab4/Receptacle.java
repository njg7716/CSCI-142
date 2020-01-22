/**
 * Created by njg77 on 2/20/2017.
 */
public class Receptacle extends MultiComponent {

    private int maxChildren;
    private int curChildren;

    //Constructs a receptacle
    public Receptacle(String name, Component source, int maxEl){
        super(name, source);
        maxChildren = maxEl;
        curChildren = 0;
    }

    @Override
    //adds a receptacle to a circuit or another receptacle and updates the amount of current children and checks to see
    //if the current amount of children is greater than the max amount of children
    public boolean add(Component el) {
        if(curChildren<maxChildren){
            if(el instanceof Receptacle || el instanceof Appliance){
                children.add(el);
                curChildren++;
                return true;
            }
            else if(el instanceof Circuit){
                return false;
            }
        }
        System.out.println("The Receptacle is already at its max number of children.");
        return false;
    }

    @Override
    //Displays the receptacle and calls the displat function on its children
    protected void display(String offset) {
        System.out.println(offset+"Receptacle: "+name+" using "+ currCurrent+" amps");
        for (int x=0; x<this.children.size();x++) {
            this.children.get(x).display("    ");
        }
    }
}
