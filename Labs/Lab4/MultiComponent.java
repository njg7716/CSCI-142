import java.util.ArrayList;

/**
 * Created by njg77 on 2/20/2017.
 */
public abstract class MultiComponent extends Component {

    protected ArrayList<Component> children;

    //Constructs multicomponent
    protected MultiComponent(String name, Component source) {
        super(name, source);
        children = new ArrayList<>();
    }
//resets the component and its children
    public void reset(){
        currCurrent = 0;
        for (Component child:children){
            child.reset();
        }
    }
}
