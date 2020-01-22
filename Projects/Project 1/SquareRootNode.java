package Nodes;

import Util.SymbolTable;

/**
 * Created by njg77 on 3/5/2017.
 */
public class SquareRootNode extends UnaryOperatorNode implements MerpNode {

    /**Constructor that sets the left child and sets the operator to the string @ The precedence is set to POWER
     *
     * @param child- MerpNode that is the child of this node
     */
    public SquareRootNode(MerpNode child) {
        super(child, Precedence.POWER, "@");
    }

    /**Evaluates the node to determine its integer value Errors if the child evaluates to a negative number
     *
     * @param symbolTable the symbol table to use for variable processing
     * @return the integer value of this node
     */
    @Override
    public int evaluate(SymbolTable symbolTable) {
        return (int)Math.sqrt(child.evaluate(symbolTable));
    }
}
