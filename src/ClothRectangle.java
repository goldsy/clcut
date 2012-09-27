import java.util.ArrayList ;
import java.util.HashMap;
import java.util.Vector ;



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
	
	private int optimalProfit = 0;
	
	// Either a pattern which will be placed on the rectangle.
	// Or a reference to the left/top or right/bottom pieces which it was cut 
	// into because the value of the sum of the two pieces is greater than the
	// optimal pattern that fits in this rectangle.
	private Pattern optimalPattern = null;
	
	private ClothRectangle leftTop = null;
	private ClothRectangle rightBottom = null;
	
	// This stores the cut information for the rectangle. If null then no cut 
	// will be made.
	private Cut optimalCut = null;
    
    // Static members of the class.
	private static ArrayList<Pattern> patterns = new ArrayList<Pattern>();
    private static int minPatternWidth = 0;
    private static int minPatternHeight = 0;
    private static HashMap<String, ClothRectangle> solvedRectangles = 
    		new HashMap<String, ClothRectangle>(10000);
	
    
	/**
	 * This method will enforce Singleton for each rectangle type.
     * 
	 * @param _width
	 * @param _height
	 * @param _patterns
	 * @return
     * This method returns a reference to the specified ClothRectangle size.
	 */
	public static ClothRectangle create(int _width, int _height) {
		// Look up target rectangle size.  Return if found.
        ClothRectangle temp = solvedRectangles.get(getKey(_width, _height));
        
		// Otherwise construct new ClothRectangle, insert into data structure
		// and return reference to it.
        if (temp == null) {
        	temp = new ClothRectangle(_width, _height);
            
        	// Insert into data structure.
            solvedRectangles.put(temp.getKey(), temp);
            //System.out.println("Map Size: " + solvedRectangles.size());
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
	 */
	 private ClothRectangle(int _width, int _height) {
        // DEBUG
		//System.out.println("Constructing new ClothRectangle of size [" + _width + "," + _height + "]");

		width = _width;
		height = _height;
		
		// Just check that the min width and height have been inited, otherwise
		// just use worst case of size 1.
        if (minPatternWidth == 0) {
        	minPatternWidth = 1;
        }
        
        if (minPatternHeight == 0) {
        	minPatternHeight = 1;
        }
		
		// Determine the max zero cut profit.
		calcMaxZeroCutProfit();
		
        // DEBUG
		//System.out.println("Optimal zero cut value is: " + optimalValue);
        
        // If the optimal value at this rectangle is 0 then there is no reason
		// to cut because nothing will fit.
        if (optimalProfit > 0) {
        	// Determine if the value is greater by cutting vertically at all
        	// possible points.
        	int cutProfit = 0;

        	for (int cutAtX = minPatternWidth; cutAtX <= (width/2); ++cutAtX) {
        		// DEBUG
        		//System.out.println("Vert Cut [w=" + width + " h=" + height + "] Cutting at [" + x + "]");

        		// Cut this rectangle vertically.
        		ClothRectangle tempLeft = create(cutAtX, height);
        		ClothRectangle tempRight = create((width - cutAtX), height);

        		// If value from this cut is greater than current max value, then
        		// save this cut as optimal.
        		cutProfit = (tempLeft.getOptimalProfit() + tempRight.getOptimalProfit());

        		if (cutProfit > optimalProfit) {
        			optimalProfit = cutProfit;
        			leftTop = tempLeft;
        			rightBottom = tempRight;
        			optimalCut = new Cut(Cut.VERTICAL_CUT, cutAtX, height);
        		}
        	}

        	// Determine if the value is greater by cutting horizontally at all
        	// possible points.
        	for (int cutAtY = minPatternHeight; cutAtY <= (height/2); ++cutAtY) {
        		// DEBUG
        		//System.out.println("Horiz Cut [w=" + width + " h=" + height + "] Cutting at [" + y + "]");

        		// Cut this rectangle horizontally.
        		ClothRectangle tempTop = create(width, cutAtY);
        		ClothRectangle tempBottom = create(width, (height - cutAtY));

        		// If value from this cut is greater than current max value, then
        		// save this cut as optimal.
        		cutProfit = (tempTop.getOptimalProfit() + tempBottom.getOptimalProfit());

        		if (cutProfit > optimalProfit) {
        			optimalProfit = cutProfit;
        			leftTop = tempTop;
        			rightBottom = tempBottom;
        			optimalCut = new Cut(Cut.HORIZONTAL_CUT, cutAtY, width);
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
	public int getOptimalProfit() {
		return optimalProfit;
	}
	
	
	/**
	 * Gets the cuts for this rectangle and any subsequent cut of this rectangle.
     * This function will make all drawable cuts relative to the top level
     * rectangle.
	 * 
	 * @param target
	 * 		The target list to stuff the cuts into.
	 */
	public void getCuts(ArrayList<DrawableCut> target) {
		getCuts(target, 0, 0);
	}
    
	
    /**
     * Gets the cuts for this rectangle and any subsequent cut of this rectangle.
     * 
     * @param target
     * The target list to store the cut information.
     * 
     * @param absoluteXStart
     * The X start position of rectangle in question relative to the overall cloth
     * size.
     * 
     * @param absoluteYStart
     * The Y start position of rectangle in question relative to the overall cloth
     * size.
     */
	public void getCuts(ArrayList<DrawableCut> target, int absoluteXStart, int absoluteYStart) {
		if (optimalCut != null) {
			target.add(new DrawableCut(optimalCut, absoluteXStart, absoluteYStart));
			
			// Recursively get all of the cuts.
            // Adjust the coordinates of the start of the rectangle by the cut.
			leftTop.getCuts(target, optimalCut.getLeftTopXStart(absoluteXStart),
					optimalCut.getLeftTopYStart(absoluteYStart));
			rightBottom.getCuts(target, optimalCut.getRightBottomXStart(absoluteXStart),
					optimalCut.getRightBottomYStart(absoluteYStart));
		}
	}
    
	
    /**
     * Returns list of garments and their locations. This function should only
     * be called from the top level rectangle as it assumes all other rectangles
     * are relative to it.
     * 
     * @param target
     */
	public void getGarments(Vector<Garment> target) {
		getGarments(target, 0, 0);
	}
    
	
    /**
     * 
     * @param target
     * @param absoluteXStart
     * @param absoluteYStart
     */
	public void getGarments(Vector<Garment> target, int absoluteXStart, int absoluteYStart) {
        // If there is no more cuts, then this is where the garment goes.
		if (optimalCut == null) {
			target.add(new Garment(absoluteXStart, absoluteYStart, optimalPattern));
		}
		else {
			// Use the cut information to adjust the relative start locations for the
			// resulting rectangles since they only know size.
            if (optimalCut.isVertical()) {
            	leftTop.getGarments(target, absoluteXStart, absoluteYStart);
            	rightBottom.getGarments(target, 
            			(absoluteXStart + optimalCut.getLocation()), 
            			absoluteYStart);
            }
            else {
            	// We know there was a cut and if it wasn't vertical, it must
            	// be horizontal.
                leftTop.getGarments(target, absoluteXStart, absoluteYStart);
                rightBottom.getGarments(target, absoluteXStart, 
                		(absoluteYStart + optimalCut.getLocation()));
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
	public String getKey() {
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
	public static String getKey(int _width, int _height) {
		//return Integer.parseInt(new Integer(_width).toString()+ "," + new Integer(_height).toString());
		return (_width + "," + _height);
	}
	
    
	/**
	 * Determines what pattern fits on the rectangle and is worth the most.
	 * 
	 * @return
	 * 		This method returns the
	 */
	private void calcMaxZeroCutProfit() {
		for (Pattern p : patterns) {
			if ((p.getWidth() <= width) && (p.getHeight() <= height) 
					&& (p.getValue() > optimalProfit)) 
			{
				optimalProfit = p.getValue();
				optimalPattern = p;
			}
		}
	}
    
	
    /**
     * This adds the pattern to the array list and determines the min width
     * and height which is used to set the starting point of cuts.
     * 
     * @param source
     * Source array list of patterns.
     */
	public static void setPatterns(ArrayList<Pattern> source) {
		patterns = source;
        
		for (Pattern p : patterns) {
            // Zero means that the min width has not been initialized.
			if ((minPatternWidth == 0) || p.getWidth() < minPatternWidth) {
				minPatternWidth = p.getWidth();
			}
            
            // Zero means that the min height has not been initialized.
			if ((minPatternHeight == 0) || p.getHeight() < minPatternHeight) {
				minPatternHeight = p.getHeight();
			}
		}
	}
}
