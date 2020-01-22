/**
 * PizzaRun is a simple program that calculates the number of 
 * pizzas to buy based on the sum of the slices entered on 
 * the command line arguments after the first argument, the price per pizza.
 * <P>
 * The example below shows an execution of the program on the command line
 * (the dollar sign is the terminal window's prompt, and
 * the # starts a terminal 'shell comment' to the end of the line).
 * <P>
 * In your code. set SLICE_PER_PIE to 8.
 * Example: A pizza costs $9.75 and four people want 1, 3, 4 and 2 slices.
 * <PRE>
 * $ java PizzaRun 9.75 1 3 4 2
 * # produces this output:
 * Buy 2 pizzas for $19.5
 * There will be 6 extra slices.
 * </PRE>
 * @author bks: ben k steele 
 */
public class PizzaRun {

    /** make constructor to hide from students in javadocs */
    private PizzaRun() {}

    /** number of slices per whole pizza */
    public final static int SLICE_PER_PIE = 8;

    /**
     * calcWholePies returns the number of whole pizza pies are needed
     * to fill the order for a given number of slices.
     * @param    nSlices      number of slices desired
     * @return   number of whole pizza pies
     */
    public static int calcWholePies( int nSlices ) {

        int nPies = nSlices / SLICE_PER_PIE ;
        if ( nSlices % SLICE_PER_PIE != 0 ) {
            nPies++ ;
        }
        return nPies;
    }

    /**
     * main method executes the pizza run.
     * @param    args      price sliceCount1 [sliceCount2 ...]      
     */
    public static void main( String args[] ) {

        if ( args.length < 2 ) {
            System.out.println( 
                    "usage: java PizzaRun price numSlice [slice2...]" );
            return;
        }
        double price = Double.parseDouble( args[0] );
        int slices = 0;
        for ( int j = 1; j < args.length; j++ ) {
            slices += Integer.parseInt( args[j] );
        }
        int pies = calcWholePies( slices );
        System.out.println( "Buy " + pies + " pizzas for $" 
                          + price * pies );
        int boughtSlices = SLICE_PER_PIE * pies ;
        System.out.println( "There will be " 
                          + ( boughtSlices - slices ) % boughtSlices 
                          + " extra slices." );
    }

} // class PizzaRun

