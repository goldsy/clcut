import java.util.ArrayList ;
import java.util.HashMap;


/**
 * This class stores the dimensions of a piece of cloth cut from the original
 * piece of cloth.
 * 
 * @author Jeff
 *
 */
public class ClothRectangle {
	private int width = 0;
	private int height = 0;
	private ArrayList<Pattern> patterns = null;
    private int minPatternWidth;
    private int minPatternHeight;
    //private static RectangleBST solvedRectangles = new RectangleBST();
    private static HashMap<Integer, ClothRectangle> solvedRectangles = new HashMap<Integer, ClothRectangle>();
	
	private int optimalValue = 0;
	
	// Either a pattern which will be placed on the rectangle.
	// Or a reference to the left/top or right/bottom pieces which it was cut 
	// into because the value of the sum of the two pieces is greater than the
	// optimal pattern that fits in this rectangle.
	private Pattern optimalPattern = null;
	
	private ClothRectangle leftTop = null;
	private ClothRectangle rightBottom = null;
	
	// This stores the cut information for the rectangle. If null then no cut will be made.
	private Cut optimalCut = null;
	
    
	/**
	 * This method will enforce Singleton for each rectangle type.
     * 
	 * @param _width
	 * @param _height
	 * @param _patterns
	 * @return
     * This method returns a reference to the specified ClothRectangle size.
	 */
	public static ClothRectangle create(int _width, int _height, 
			ArrayList<Pattern> _patterns, int _minPatternWidth, int _minPatternHeight) {
		// Look up target rectangle size.  Return if found.
        //ClothRectangle temp = solvedRectangles.find(getKey(_width, _height));
        ClothRectangle temp = solvedRectangles.get(getKey(_width, _height));
        
		// Otherwise construct new ClothRectangle, insert into data structure
		// and return reference to it.
        if (temp == null) {
        	temp = new ClothRectangle(_width, _height, _patterns, 
        			_minPatternWidth, _minPatternHeight);
            
        	// Insert into data structure.
            //solvedRectangles.insert(temp);
            solvedRectangles.put(temp.getKey(), temp);
        }
        
        return temp;
	}
    
	
	/**
	 * Class ctor.
	 * 
	 * @param _width
	 * 		Width of the rectangle.
	 * @param _height
	 * 		Height of the rectangle.
	 * @param _patterns
	 * 		List of patterns to be placed on the rectangle.
	 */
	 ClothRectangle(int _width, int _height, ArrayList<Pattern> _patterns,
			 int _minPatternWidth, int _minPatternHeight) {
        // DEBUG
		//System.out.println("Constructing new ClothRectangle of size [" + _width + "," + _height + "]");
        
		width = _width;
		height = _height;
		patterns = _patterns;
        minPatternWidth = _minPatternWidth;
        minPatternHeight = _minPatternHeight;
		
		// Determine the max zero cut value.
		optimalValue = getMaxZeroCutValue();
		
        // DEBUG
		//System.out.println("Optimal zero cut value is: " + optimalValue);
        
        // If the optimal value at this rectangle is 0 then there is no reason
		// to cut because nothing will fit.
        if (optimalValue > 0) {
        	// Determine if the value is greater by cutting vertically at all
        	// possible points.
        	int cutValue = 0;

        	for (int x = minPatternWidth; x <= (width/2); ++x) {
        		// DEBUG
        		//System.out.println("Vert Cut [w=" + width + " h=" + height + "] Cutting at [" + x + "]");

        		// Cut this rectangle vertically.
        		//*****
        		// TODO: (goldsy) Lookup to see if resulting rectangle has already been computed.
        		//*****
        		//ClothRectangle tempLeft = new ClothRectangle(x, height, _patterns);
        		//ClothRectangle tempRight = new ClothRectangle(width - x, height, patterns);
        		ClothRectangle tempLeft = create(x, height, _patterns, minPatternWidth, minPatternHeight);
        		ClothRectangle tempRight = create(width - x, height, patterns, minPatternWidth, minPatternHeight);

        		// If value from this cut is greater than current max value, then
        		// save this cut as optimal.
        		cutValue = (tempLeft.getOptimalValue() + tempRight.getOptimalValue());

        		if (cutValue > optimalValue) {
        			leftTop = tempLeft;
        			rightBottom = tempRight;
        			optimalValue = cutValue;
        			optimalCut = new Cut(Cut.VERTICAL_CUT, x, height);
        		}
        	}

        	// Determine if the value is greater by cutting horizontally at all
        	// possible points.
        	for (int y = minPatternHeight; y <= (height/2); ++ y) {
        		// DEBUG
        		//System.out.println("Horiz Cut [w=" + width + " h=" + height + "] Cutting at [" + y + "]");

        		// Cut this rectangle vertically.
        		//*****
        		// TODO: (goldsy) Lookup to see if resulting rectangle has already been computed.
        		//*****
        		ClothRectangle tempTop = create(width, y, _patterns, minPatternWidth, minPatternHeight);
        		ClothRectangle tempBottom = create(width, height - y, patterns, minPatternWidth, minPatternHeight);

        		// If value from this cut is greater than current max value, then
        		// save this cut as optimal.
        		cutValue = (tempTop.getOptimalValue() + tempBottom.getOptimalValue());

        		if (cutValue > optimalValue) {
        			leftTop = tempTop;
        			rightBottom = tempBottom;
        			optimalValue = cutValue;
        			optimalCut = new Cut(Cut.HORIZONTAL_CUT, y, width);
        		}
        	}
        }
	}
	
	
	/**
	 * Returns the optimalValue for this rectangle.
	 * 
	 * @return
	 * 		Returns the optimal maximum value for the rectangle.
	 */
	public int getOptimalValue() {
		return optimalValue;
	}
	
	
	/**
	 * Gets the cuts for this rectangle and any subsequent cut of this rectangle.
	 * 
	 * @param target
	 * 		The target list to stuff the cuts into.
	 */
	public void getCuts(ArrayList<Cut> target) {
		if (optimalCut != null) {
			target.add(optimalCut);
			
			// Recursively get all of the cuts.
			leftTop.getCuts(target);
			rightBottom.getCuts(target);
		}
	}
    
	
    /**
     * Returns list of garments and their locations. This function should only
     * be called from the top level rectangle as it assumes all other rectangles
     * are relative to it.
     * 
     * @param target
     */
	public void getGarments(ArrayList<Garment> target) {
		getGarments(target, 0, 0);
	}
    
	
	public void getGarments(ArrayList<Garment> target, int relativeWidthStart, int relativeHeightStart) {
        // If there is no more cuts, then this is where the garment goes.
		if (optimalCut == null) {
			target.add(new Garment(relativeWidthStart, relativeHeightStart, optimalPattern));
		}
		else {
			// Use the cut information to adjust the relative start locations for the
			// resulting rectangles since they only know size.
            if (optimalCut.isVertical()) {
            	leftTop.getGarments(target, relativeWidthStart, relativeHeightStart);
            	rightBottom.getGarments(target, 
            			(relativeWidthStart + optimalCut.getLocation()), 
            			relativeHeightStart);
            }
            else {
            	// We know there was a cut and if it wasn't vertical, it must
            	// be horizontal.
                leftTop.getGarments(target, relativeWidthStart, relativeHeightStart);
                rightBottom.getGarments(target, relativeWidthStart, 
                		(relativeHeightStart + optimalCut.getLocation()));
            }
		}
	}
	
    
    /**
     * This gets the unique key for this size of rectangle. It is determined
     * by concatenating the width and height values as strings and then
     * casting them into ints which will guarantee that the key will never
     * duplicate for two different rectangle sizes.
     * @return
     */
	public Integer getKey() {
		return getKey(width, height);
	}
    
    
    /**
     * This version of get key allows getting the key without having constructed
     * the rectangle which is what we want to avoid if possible.
     * 
     * @param _width
     * Width of the rectangle to look up.
     * @param _height
     * Height of the rectangle to look up.
     * @return
     * This method returns an int which represents a unique key for the 
     * specified rectangle size.
     */
	public static Integer getKey(int _width, int _height) {
		return Integer.parseInt(new Integer(_width).toString() + new Integer(_height).toString());
	}
	
	/**
	 * Determines what pattern fits on the rectangle and is worth the most.
	 * 
	 * @return
	 * 		This method returns the
	 */
	private int getMaxZeroCutValue() {
		for (Pattern p : patterns) {
			if (p.getWidth() <= width && p.getHeight() <= height && p.getValue() > optimalValue) {
				optimalValue = p.getValue();
				optimalPattern = p;
			}
		}

		return optimalValue;
	}
}
