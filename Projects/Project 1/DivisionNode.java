package Nodes;

import Util.SymbolTable;

/**
 * Created by njg77 on 3/5/2017.
 */
public class DivisionNode extends BinaryOperatorNode {

    /**Constructor that sets the left/right children and sets the operator to the string // The precedence is set to MULT_DIVIDE
     *
     * @param leftChild- the MerpNode representing the left child
     * @param rightChild- the MerpNode representing the right child
     */
    public DivisionNode(MerpNode leftChild, MerpNode rightChild) {
        super(leftChild, rightChild, Precedence.MULT_DIVIDE, "/");
    }

    /**Evaluates the node to determine its integer value Errors if the right child evaluates to zero
     *
     * @param symbolTable- the symbol table to use for variable processing
     * @return the integer value of this node
     */
    public int evaluate(SymbolTable symbolTable){
        return leftChild.evaluate(symbolTable)/rightChild.evaluate(symbolTable);
    }
}
