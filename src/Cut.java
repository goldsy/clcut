/**
 * This class stores cut information including what cut comes after this one.
 * 
 * @author Jeff Goldsworthy
 *
 */
public class Cut {
	public static final int VERTICAL_CUT = 0;
	public static final int HORIZONTAL_CUT = 1;
	
	// Orientation is either 0 for vertical, or 1 for horizontal.
	private int orientation = -1;
	
	// Valid cut locations are 1 to (width-1).
	private int location = 0;
	
	// Length of cut.
	private int length = 0;


	/**
	 * Class ctor.
	 * 
	 * @param _orientation
     * The orientation of this cut.
     * 
	 * @param _location
     * The location of the cut which is either along the width or height
     * depending on whether this is a vertical of horizontal cut.
     * 
	 * @param _length
     * The length of the cut.
	 */
	public Cut(int _orientation, int _location, int _length) {
		orientation = _orientation;
		location = _location;
		length = _length;
	}
    
	
    /**
     * This method returns whether the cut is vertical or not.
     * 
     * @return
     *	This method returns true when the cut is vertical and false otherwise.
     */
	public boolean isVertical() {
		return (orientation == VERTICAL_CUT);
	}
    
	
    /**
     * This method returns whether the cut is horizontal or not.
     * 
     * @return
     * 	This method returns true when the cut is horizontal and false otherwise.
     */
	public boolean isHorizontal() {
		return (orientation == HORIZONTAL_CUT);
	}
    
	
    /**
     * Gets the orientation for this cut.
     * 
     * @return
     * This method returns the orientation of the cut.
     */
	public int getOrientation() {
		return orientation;
	}
    
	
    /**
     * This method gets the location of the cut.
     * 
     * @return
     * This method returns the integer location of the cut. The location is 
     * relative to the orientation.
     */
	public int getLocation() {
		return location;
	}
    
    
	/**
	 * Gets the length of the cut.
     * 
	 * @return
     * This method returns the length of the cut.
	 */
	public int getLenth() {
		return length;
	}
    
	
    /**
     * Returns the X Start coordinate for the left or top rectangle when applying
     * this cut. This provides some consistency in calls since it always returns
     * the same value.
     * 
     * @param sourceXStart
     * 
     * @return
     * Returns the start X coordinate.
     */
	public int getLeftTopXStart(int sourceXStart) {
        // Whether a vertical or horizontal cut the start will always be the
		// as the original rectangle.
		return sourceXStart;
	}
	
    
    /**
     * 
     * @param sourceYStart
     * @return
     */
	public int getLeftTopYStart(int sourceYStart) {
        // Whether a vertical or horizontal cut the start will always be the
		// as the original rectangle.
		return sourceYStart;
	}
    
	
    /**
     * 
     * @param sourceXStart
     * @return
     */
	public int getRightBottomXStart(int sourceXStart) {
		if (isVertical()) {
			return sourceXStart + location;
		}
		else {
			// Otherwise this is a horizontal cut. X start is same for top and
			// bottom rectangle and the original rectangle.
            return sourceXStart;
		}
	}
    
    
    /**
     * 
     * @param sourceYStart
     * @return
     */
	public int getRightBottomYStart(int sourceYStart) {
		if (isVertical()) {
			return sourceYStart;
		}
		else {
			return sourceYStart + location;
		}
	}
}
