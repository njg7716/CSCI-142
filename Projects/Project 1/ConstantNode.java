package Nodes;

import Util.SymbolTable;

/**
 * Created by njg77 on 3/5/2017.
 */
public class ConstantNode extends java.lang.Object implements MerpNode {

    private int value;

    /**Constructor that sets the value of this node
     *
     * @param value
     */
    public ConstantNode(int value){
        this.value = value;
    }

    /** Returns the value of this node
     *
     * @param symbolTable the symbol table to use for variable processing
     * @return- integer value of this node
     */
    @Override
    public int evaluate(SymbolTable symbolTable) {
        return value;
    }

    /**Returns the value of this node in prefix notation
     *
     * @return the integer value of this node
     */
    @Override
    public String toPrefixString() {
        return String.valueOf(value);
    }

    /**Returns the value of this node in infix notation
     *
     * @return the integer value of this node
     */
    @Override
    public String toInfixString() {
        return String.valueOf(value);
    }

    /**Returns the value of this node in postfix notation
     *
     * @return the integer value of this node
     */
    @Override
    public String toPostfixString() {
        return String.valueOf(value);
    }

    /**Returns the precedence of this node
     *
     * @return returns the precedence of CONSTANT
     */
    @Override
    public int getPrecedence() {
        return 3;
    }

    /**determines if the node is an operation node
     *
     * @return- true if an operation node, false otherwise
     */
    @Override
    public boolean isOperation() {
        return false;
    }

    /**Determines the node type
     *
     * @return- the type of this node
     */
    @Override
    public NodeType getNodeType() {
        return NodeType.Constant;
    }
}
