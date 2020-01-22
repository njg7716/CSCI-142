package Nodes;

import Util.SymbolTable;

/**
 * Created by njg77 on 3/5/2017.
 */
public abstract class BinaryOperatorNode implements MerpNode {

    protected MerpNode leftChild;
    protected java.lang.String operator;
    protected Precedence precedence;
    protected MerpNode rightChild;

    /**Binary Node Constructor
     *
     * @param leftChild- MerpNode representing the left child
     * @param rightChild- MerpNode representing the right child
     * @param precedence- Precedence of the operator
     * @param operator- String representing the operator
     */
    public BinaryOperatorNode(MerpNode leftChild, MerpNode rightChild, Precedence precedence, String operator){
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.precedence = precedence;
        this.operator = operator;
    }

    /**Setter for left child
     *
     * @param leftChild- The MerpNode to be set at this node's left child
     */
    public void setLeftChild(MerpNode leftChild){
        this.leftChild = leftChild;
    }

    /**Setter for right child
     *
     * @param rightChild- The MerpNode to be set at this node's left child
     */
    public void setRightChild(MerpNode rightChild){
        this.rightChild = rightChild;
    }

    @Override
    public int evaluate(SymbolTable symbolTable) {
        return 0;
    }

    /**Displays this node as prefix notation expression string
     *
     * @return string representing the node as prefix notation
     */
    @Override
    public String toPrefixString() {
        String list= "";
        list+= operator;
        list+= " ";
        list+= leftChild.toPrefixString();
        list+= " ";
        list+= rightChild.toPrefixString();
        return list;
    }

    /**Displays this node as infix notation expression string
     *
     * @return
    string representing the node as infix notation
     */
    @Override
    public String toInfixString() {
        String list= "(";
        list+= leftChild.toInfixString();
        list+= " ";
        list+= operator;
        list+= " ";
        list+= rightChild.toInfixString();
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
        list+= leftChild.toPostfixString();
        list+= " ";
        list+= rightChild.toPostfixString();
        list+= " ";
        list+= operator;
        return list;
    }

    /**Returns the precedence of this node
     *
     * @return returns the precedence of this node as an int value
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
        return NodeType.BinaryOperation;
    }
}
