package Nodes;

import Util.SymbolTable;

/**
 * Created by njg77 on 3/5/2017.
 */
public abstract class BooleanOperatorNode extends BinaryOperatorNode implements MerpNode {

    /**Constructor for Boolean operation nodes The precedence is set to BOOLEAN
     *
     * @param left- the left child for this operation
     * @param right- the right child for this operation
     * @param operator- the string representing the operation for this node
     */
    public BooleanOperatorNode(MerpNode left, MerpNode right, String operator){
        super(left,right,Precedence.BOOLEAN, operator);
    }

    /**Returns the precedence of this node
     *
     * @return returns the precedence of BOOLEAN
     */
    @Override
    public int getPrecedence() {
        return 4;
    }
}
