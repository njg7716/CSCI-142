import Processors.MerpInfixProcessor;
import Processors.MerpPostfixProcessor;
import Processors.MerpPrefixProcessor;
import Processors.MerpProcessor;
import Util.Errors;
import Util.SymbolTable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Merp Unit Testing Class
 */
public class MerpTest {
    public static void main(String[] args) {

        // Tests just a constant
        testEquation("5", "5", "5",
                "5", 5, new SymbolTable());

        // Tests a variable
        SymbolTable x = new SymbolTable();
        x.put("x", 5);
        testEquation("x", "x", "x",
                "x", 5, x);

        // Test single binary operation,. addition, no variables
        testTree("5 + 2", "(5 + 2)", "+ 5 2",
                "5 2 +", 7, "infix", new SymbolTable());

        testTree("+ 5 2", "(5 + 2)", "+ 5 2",
                "5 2 +", 7, "prefix", new SymbolTable());

        testTree("5 2 +", "(5 + 2)", "+ 5 2",
                "5 2 +", 7, "postfix", new SymbolTable());

        // Test single binary operation, addition, with variable
        testTree("x + 2", "(x + 2)", "+ x 2",
                "x 2 +", 7, "infix", x);

        testTree("+ x 2", "(x + 2)", "+ x 2",
                "x 2 +", 7, "prefix", x);

        testTree("x 2 +", "(x + 2)", "+ x 2",
                "x 2 +", 7, "postfix", x);

        // Test single binary operation, multiplication, with variable
        testTree("x * 2", "(x * 2)", "* x 2",
                "x 2 *", 10, "infix", x);

        testTree("* x 2", "(x * 2)", "* x 2",
                "x 2 *", 10, "prefix", x);

        testTree("x 2 *", "(x * 2)", "* x 2",
                "x 2 *", 10, "postfix", x);

        // Test single binary operation, multiplication, with 2 variables
        x.put("y", 2);
        testTree("x * y", "(x * y)", "* x y",
                "x y *", 10, "infix", x);

        testTree("* x y", "(x * y)", "* x y",
                "x y *", 10, "prefix", x);

        testTree("x y *", "(x * y)", "* x y",
                "x y *", 10, "postfix", x);

        // Test compound statements for order of operations, with 2 variables
        testTree("y + x * 3", "(y + (x * 3))", "+ y * x 3",
                "y x 3 * +", 17, "infix", x);

        testTree("+ y * x 3", "(y + (x * 3))", "+ y * x 3",
                "y x 3 * +", 17, "prefix", x);

        testTree("y x 3 * +", "(y + (x * 3))", "+ y * x 3",
                "y x 3 * +", 17, "postfix", x);

        // Test Boolean Operations, true
        testTree("x > 2", "(x > 2)", "> x 2",
                "x 2 >", 1, "infix", x);

        testTree("> x 2", "(x > 2)", "> x 2",
                "x 2 >", 1, "prefix", x);

        testTree("x 2 >", "(x > 2)", "> x 2",
                "x 2 >", 1, "postfix", x);

        // Tests Boolean operations, false
        x.put("x", 2);
        testTree("x > 2", "(x > 2)", "> x 2",
                "x 2 >", 0, "infix", x);

        testTree("> x 2", "(x > 2)", "> x 2",
                "x 2 >", 0, "prefix", x);

        testTree("x 2 >", "(x > 2)", "> x 2",
                "x 2 >", 0, "postfix", x);

        // Test unary operation, no variable
        testTree("_ 2", "(_ 2)", "_ 2",
                "2 _", -2, "infix", x);

        testTree("_ 2", "(_ 2)", "_ 2",
                "2 _", -2, "prefix", x);

        testTree("2 _", "(_ 2)", "_ 2",
                "2 _", -2, "postfix", x);

        // Test unary operation, variable
        testTree("_ y", "(_ y)", "_ y",
                "y _", -2, "infix", x);

        testTree("_ y", "(_ y)", "_ y",
                "y _", -2, "prefix", x);

        testTree("y _", "(_ y)", "_ y",
                "y _", -2, "postfix", x);

        //Add more tests as you see fit.
    }

    public static void testEquation(String equation, String expectedInfix, String expectedPrefix,
                                    String expectedPostfix, int expectedValue, SymbolTable symbolTable) {
        testTree(equation, expectedInfix, expectedPrefix,
                expectedPostfix, expectedValue, "infix",
                symbolTable);

        testTree(equation, expectedInfix, expectedPrefix,
                expectedPostfix, expectedValue, "prefix",
                symbolTable);

        testTree(equation, expectedInfix, expectedPrefix,
                expectedPostfix, expectedValue, "postfix",
                symbolTable);
    }

    private static void testTree(String equation, String expectedInfix, String expectedPrefix,
                                 String expectedPostfix, int expectedValue, String type,
                                 SymbolTable symbolTable) {

        System.out.println("Testing " + equation + " using " + type);
        MerpProcessor processor;

        if (type.equals("infix")) {
               processor = new MerpInfixProcessor();
        } else if (type.equals("prefix")) {
            processor = new MerpPrefixProcessor();
        } else if (type.equals("postfix")) {
               processor = new MerpPostfixProcessor();
        } else {
            Errors.error("Invalid type", type);
            return;
        }


        processor.constructTree(new ArrayList<String>(Arrays.asList(equation.split(" "))));

        if(processor.getTree().toInfixString().equals(expectedInfix)){
            System.out.println("\t" + type + " toInfixString passed");
        }
        else{
            System.out.println("\t" + equation + " toInfixString failed. Got " +
                    processor.getTree().toInfixString() +
                    " expected " + expectedInfix);
        }

        if(processor.getTree().toPrefixString().equals(expectedPrefix)){
            System.out.println("\t" + type + " toPrefixString passed");
        }
        else{
            System.out.println("\t" + equation + " toInfixString failed. Got " +
                    processor.getTree().toPrefixString() +
                    " expected " + expectedPrefix);
        }

        if(processor.getTree().toPostfixString().equals(expectedPostfix)){
            System.out.println("\t" + type + " toPostfixString passed");
        }
        else{
            System.out.println("\t" + equation + " toPostString failed. Got " +
                    processor.getTree().toPostfixString() +
                    " expected " + expectedPostfix);
        }

        if(processor.getTree().evaluate(symbolTable) == expectedValue){
            System.out.println("\t" + type + " value passed");
        }
        else{
            System.out.println("\t" + equation + " value failed. Got " +
                    processor.getTree().evaluate(symbolTable) +
                    " expected " + expectedValue);
        }
    }
}
