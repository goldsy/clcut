/**
 * This class stores cut information including what cut comes after this one.
 * @author Jeff
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
	 * @param _location
	 * @param _length
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
     * This method gets the location of the cut.
     * 
     * @return
     * 	This method returns the integer location of the cut. The location is 
     * relative to the orientation.
     */
	public int getLocation() {
		return location;
	}
    
	
    /**
     * Returns the Y location for the cut.  This is the start point.
     * 
     * @return
     * This method returns the Y location of the start of the line.
     */
	public int getXStart() {
		if (isVertical()) {
			return location;
		}
		else {
			return 0;
		}
	}
    
	
    /**
     * Returns the Y location for the cut.  This is the start point.
     * 
     * @return
     * This method returns the Y location of the start of the line.
     */
	public int getYStart() {
		if (isHorizontal()) {
			return location;
		}
		else {
			return 0;
		}
	}
    
	
    /**
     * Returns the Y location for the cut.  This is the start point.
     * 
     * @return
     * This method returns the Y location of the start of the line.
     */
	public int getXEnd() {
		if (isVertical()) {
			return location;
		}
		else {
			return getXStart() + length;
		}
	}
    
	
    /**
     * Returns the Y location for the cut.  This is the start point.
     * 
     * @return
     * This method returns the Y location of the start of the line.
     */
	public int getYEnd() {
		if (isHorizontal()) {
			return location;
		}
		else {
			return getYStart() + length;
		}
	}
}
