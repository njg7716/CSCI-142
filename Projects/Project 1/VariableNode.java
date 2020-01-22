package Nodes;

import Util.SymbolTable;

/**
 * Created by njg77 on 3/5/2017.
 */
public class VariableNode extends java.lang.Object implements MerpNode {

    private java.lang.String name;

    /**Constructor
     *
     * @param name- variable name
     */
    public VariableNode(java.lang.String name){
        this.name = name;
    }

    /**Evaluates the node to determine its integer value
     *
     * @param symbolTable the symbol table to use for variable processing
     * @return the integer value of this node
     */
    @Override
    public int evaluate(SymbolTable symbolTable) {
        return symbolTable.get(name);
    }

    /**Displays this node as prefix notation expression string
     *
     * @return string representing the node as prefix notation
     */
    @Override
    public String toPrefixString() {
        return name;
    }

    /**Displays this node as infix notation expression string
     *
     * @return string representing the node as infix notation
     */
    @Override
    public String toInfixString() {
        return name;
    }

    /**Displays this node as postfix notation expression string
     *
     * @return string representing the node as postfix notation
     */
    @Override
    public String toPostfixString() {
        return name;
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
     * @return the type of this node
     */
    @Override
    public NodeType getNodeType() {
        return NodeType.Constant;
    }
}
