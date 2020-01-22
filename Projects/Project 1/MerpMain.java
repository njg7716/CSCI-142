import Processors.*;
import Util.Errors;

import java.util.Scanner;

import java.util.ArrayList;

/**
 * Ierp Main class
 * Handles interaction with the user
 */
public class MerpMain {

    /**
     * Main function
     * @param args name of symbol table file
     */
    public static void main( String args[] ){

        // Initialize reader for usage
        Scanner reader = new Scanner(System.in);

        ArrayList<String> equations = new ArrayList<>();
        System.out.println("Enter type of expression: ");
        String type = reader.nextLine();

        MerpProcessor processor;
        if(type.equals("infix")){
            processor = new MerpInfixProcessor();
        }
        else if(type.equals("prefix")){
            processor = new MerpPrefixProcessor();
        }
        else if(type.equals("postfix")){
            processor = new MerpPostfixProcessor();
        }
        else{
            Errors.error("Invalid type entered.", null);
            return;
        }
        System.out.println("Enter the equations (nothing when done entering): ");
        while(true) {
            String equation = reader.nextLine();

            if (equation.equals("")) {
                break;
            } else {
                equations.add(equation);
            }
        }

        System.out.println("\n\nProgram Output:");
        EquationProcessor eqp = new EquationProcessor(equations, processor);
        eqp.processEquations();
    }
}
