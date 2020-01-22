package Nodes;

import Util.SymbolTable;

/**
 * Created by njg77 on 3/5/2017.
 */
public class NegationNode extends UnaryOperatorNode implements MerpNode {

    /**Constructor that sets the left child and sets the operator to the string _ The precedence is set to MULT_DIVIDE
     *
     * @param child- MerpNode that is the child of this node
     */
    public NegationNode(MerpNode child) {
        super(child, Precedence.MULT_DIVIDE, "_");
    }

    /**Evaluates the node to determine its integer value
     *
     * @param symbolTable the symbol table to use for variable processing
     * @return the integer value of this node
     */
    @Override
    public int evaluate(SymbolTable symbolTable) {
        return 0-child.evaluate(symbolTable);
    }
}
