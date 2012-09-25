import java.util.ArrayList ;
import javax.swing.* ;
import java.util.Date ;


public class TestClothCutter {
	  public static int SYNC = 500 ;
	  public static int SLEEP = 100 ;


	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    int width = 42;
	    int height = 30;
	    //int width = 42;
	    //int height = 28;
	    int pixels = 30;
        /*
	    int width = 22 ;
	    int height = 15 ;
        */

	    // Algorithmic stuff.
        // DEBUG
        System.out.println("Creating a new ClothCutter() [width=" + width + " height=" + height + "]");
        
	    //ClothCutter cutter = new ClothCutter(width,height,patterns) ;
	    ClothCutter cutter = new ClothCutter(width,height) ;
        
	    cutter.addPattern(new Pattern(2,2,1,"A")) ;
	    cutter.addPattern(new Pattern(2,6,4,"B")) ;
	    cutter.addPattern(new Pattern(4,2,3,"C")) ;
	    cutter.addPattern(new Pattern(5,3,5,"D")) ;
	    //cutter.addPattern(new Pattern(5,5,1,"E")) ;
        
	    cutter.optimize();
	    System.out.println( cutter.value() );
	    System.out.println( cutter.formattedGarments() );

	    // Graphical stuff.
	    Cloth cloth = new Cloth(width,height,pixels) ;
	    JFrame frame = new JFrame("A Bolt of Fabulously Expensive Silk") ;
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().add(cloth);
	    frame.pack();
	    frame.setVisible(true);
	    sleep(SYNC);
	    
	    for (DrawableCut c : cutter.cuts()) {
	    	sleep(SLEEP);
            
	    	// DEBUG
            //System.out.println("Displaying cut.");
	    	cloth.drawCut(c); 
	    }
	    
	    sleep(SYNC) ;
	    
	    for (Garment g : cutter.garments()) {
	    	sleep(SLEEP); 
            
	    	// DEBUG
            //System.out.println("Displaying garment.");
	    	cloth.drawGarment(g); 
	    }
	}

    
	/**
	 * Controls the amount of time that the program sleeps.
     * 
	 * @param milliseconds
     * 		Number of milliseconds that the program should sleep.
	 */
	public static void sleep(long milliseconds) {
		Date d ;
		long start, now;
		d = new Date();
		start = d.getTime();
        
		do { 
			d = new Date(); 
			now = d.getTime(); 
		} while ( (now - start) < milliseconds );
	}

}
