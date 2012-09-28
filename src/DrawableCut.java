/**
 * This class stores drawing information for a cut.
 * 
 * @author Jeff Goldsworthy
 *
 */
public class DrawableCut {
    // Start and end coordinates for this cut.
    private int startX = 0;
    private int startY = 0;
    private int endX = 0;
    private int endY = 0;

    
    /**
     * Class ctor.
     * 
     * @param sourceCut
     * The cut information to be used to determine the drawable cut coordinates.
     * 
     * @param absoluteXStart
     * The X start coordinate of the rectangle to be cut.
     * 
     * @param absoluteYStart
     * The Y start coordinate of the rectangle to be cut.
     */
    public DrawableCut(Cut sourceCut, int absoluteXStart, int absoluteYStart) {
    	// Use the start x and y positions and the cut information to determine
    	// the coordinates of the start and end of the cut to be drawn.
        if (sourceCut.isVertical()) {
            // Take the x start of the rectangle and add the cut location.
        	startX = absoluteXStart + sourceCut.getLocation();
            startY = absoluteYStart;
            
            // End X is same as start because vertical.
            endX = startX;
            endY = absoluteYStart + sourceCut.getLenth();
        }
        else {
        	// The line is horizontal.
            startX = absoluteXStart;
            startY = absoluteYStart + sourceCut.getLocation();
            
            endX = absoluteXStart + sourceCut.getLenth();
            endY = startY;
        }
    }

    
    /**
     * Returns the X start coordinate for the cut.
     * 
     * @return
     * This method returns the X start coordinate of the start of the line.
     */
    public int getXStart() {
    	return startX;
    }


    /**
     * Returns the Y start coordinate for the cut.
     * 
     * @return
     * This method returns the Y start coordinate of the start of the line.
     */
    public int getYStart() {
        return startY;
    }

    
    /**
     * Returns the X end coordinate for the cut.
     * 
     * @return
     * This method returns the X end coordinate of the end of the line.
     */
    public int getXEnd() {
    	return endX;
    }


    /**
     * Returns the Y end coordinate for the cut.
     * 
     * @return
     * This method returns the Y end coordinate of the end of the line.
     */
    public int getYEnd() {
        return endY;
    }
}
