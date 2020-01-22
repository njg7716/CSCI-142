package Nodes;

import Util.SymbolTable;

import java.util.ArrayList;

/**
 * Created by njg77 on 3/5/2017.
 */
public abstract class UnaryOperatorNode implements MerpNode {
    protected MerpNode child;
    protected String operator;
    protected Precedence precedence;

    /**
     *
     * @param child
     * @param precedence
     * @param operator
     */
    public UnaryOperatorNode(MerpNode child, Precedence precedence, String operator){
        this.child = child;
        this.precedence = precedence;
        this.operator=operator;
    }

    /**Displays this node as prefix notation expression string
     *
     * @return string representing the node as prefix notation
     */
    @Override
    public String toPrefixString() {
        String list= "";
        list+= operator;
        list+=" ";
        list+= child.toPrefixString();
        return list;
    }

    /**Displays this node as infix notation expression string
     *
     * @return string representing the node as infix notation
     */
    @Override
    public String toInfixString() {
        String list= "(";
        list+= operator;
        list+=" ";
        list+= child.toInfixString();
        list+=")";
        return list;
    }

    /**Displays this node as postfix notation expression string
     *
     * @return string representing the node as postfix notation
     */
    @Override
    public String toPostfixString() {
        String list= "";
        list+= child.toPostfixString();
        list+=" ";
        list+= operator;
        return list;
    }

    /**Returns the precedence of this node
     *
     * @return returns the precedence as an int value
     */
    @Override
    public int getPrecedence() {
        return precedence.getPrecedence();
    }

    /**determines if the node is an operation node
     *
     * @return- true if an operation node, false otherwise
     */
    @Override
    public boolean isOperation() {
        return true;
    }

    /**Determines the node type
     *
     * @return the type of this node
     */
    @Override
    public NodeType getNodeType() {
        return NodeType.UnaryOperation;
    }

    /**Sets the child of this node
     *
     * @param child- the MerpNode representing the child
     */
    public void setChild(MerpNode child){
        this.child = child;
    }
}
