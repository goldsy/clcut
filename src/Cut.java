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
}
