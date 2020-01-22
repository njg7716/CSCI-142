package Nodes;

import Util.SymbolTable;

/**
 * Created by njg77 on 3/5/2017.
 */
public class PowerNode extends BinaryOperatorNode {

    /**Constructor that sets the left/right children and sets the operator to the string ^ The precedence is set to POWER
     *
     * @param leftChild- the MerpNode representing the base
     * @param rightChild- the MerpNode representing the power
     */
    public PowerNode(MerpNode leftChild, MerpNode rightChild) {
        super(leftChild, rightChild, Precedence.POWER, "^");
    }

    /**Evaluates the node to determine its integer value
     *
     * @param symbolTable- the symbol table to use for variable processing
     * @return the integer value of this node
     */
    public int evaluate(SymbolTable symbolTable){
        return (int)Math.pow(leftChild.evaluate(symbolTable), rightChild.evaluate(symbolTable));
    }
}
