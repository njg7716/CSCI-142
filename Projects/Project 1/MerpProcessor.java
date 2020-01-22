package Processors;

import Nodes.*;
import Util.SymbolTable;

import java.util.ArrayList;

/**
 * Abstract class for MerpProcessors
 */
public abstract class MerpProcessor {

    protected MerpNode tree;

    /**
     * determines if a string is numeric
     * @param s the string to determine if it is numeric
     * @return true if it is numeric, false otherwise
     */
    public static Boolean isNumeric( String s ){
        return s.matches( "[-+]?\\d+" );
    }

    /**
     * determines if a string is a variable
     * @param s the string to determine if it is a variable
     * @return true if it is a veriable, false otherwise
     */
    public static Boolean isVariable( String s ){
        return s.matches( "^[a-zA-Z].*" );
    }

    /**
     * Getter for the underlying parse tree
     * @return an  MerpNode representing the root of the parse tree
     */
    public MerpNode getTree() {
        return tree;
    }

    /**
     * Creates an MerpNode based on the string passed in
     * @param s the string used to determine the type of MerpNode to return
     * @return the MerpNode represented by the string
     */
    protected MerpNode createMerpNode(String s){
        if(isVariable(s)){
            return new VariableNode(s);
        }
        if(isNumeric(s)){
            return new ConstantNode(Integer.parseInt(s));
        }
        switch (s){
            case "+":
                return new AdditionNode(null, null);
            case "-":
                return new SubtractionNode(null, null);
            case "//":
                return new DivisionNode(null, null);
            case "*":
                return new MultiplicationNode(null, null);
            case "^":
                return new PowerNode(null, null);
            case "|":
                return new AbsValueNode(null);
            case "_":
                return new NegationNode(null);
            case "@":
                return new SquareRootNode(null);
            case ">":
                return new GreaterThanNode(null, null);
            case ">=":
                return new GreaterThanEqualNode(null, null);
            case "<":
                return new LessThanNode(null, null);
            case "<=":
                return new LessThanEqualNode(null, null);
            case "==":
                return new EqualityNode(null, null);
            case "!=":
                return new NotEqualityNode(null, null);
            default:
                return null;
        }
    }

    /**
     * Evaluates the underlying parse tree
     * @param symbolTable the symbol table to use for variable processing
     * @return the integer value of the parse tree
     */
    public int evaluateTree( SymbolTable symbolTable){
        return this.tree.evaluate(symbolTable);
    }

    /**
     * Constructs a parse tree from a list of IerpNodes
     * @param tokens list of IerpNodes used to create the pares tree
     */
    public abstract void constructTree( ArrayList<String> tokens );


}
