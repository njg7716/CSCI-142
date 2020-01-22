package Processors;
import Util.SymbolTable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by njg77 on 3/9/2017.
 */
public class EquationProcessor {

    private ArrayList<String> equations;
    private MerpProcessor processor;
    private SymbolTable symbolTable;

    /**
     *
     * @param equations- ArrayList containing the equations
     * @param processor- the Merp processor to use to process expressions
     */
    public EquationProcessor(ArrayList<String> equations, MerpProcessor processor){
        this.equations = equations;
        this.processor = processor;
        this.symbolTable = new SymbolTable();
    }

    /**Processes the provided list of statements using the provided Merp Processor and Sysmbol Table.
     *
     */
    public void processEquations(){
        for(String x:equations){
            processEquation(x);
        }
    }
    public void processEquation(String eq){
        eq = eq.trim();
        String[] list = eq.split(" ");
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList(list));
        ArrayList<String> commands = new ArrayList<>();
        for(String i:list2){
            if(i.equals(" ")!=true){
                commands.add(i);
            }
        }
    }

}
