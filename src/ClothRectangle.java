import java.util.ArrayList ;


/**
 * This class stores the dimentions of a piece of cloth cut from the original
 * piece of cloth.
 * 
 * @author Jeff
 *
 */
public class ClothRectangle {
	private int width = 0;
	private int height = 0;
	private ArrayList<Pattern> patterns = null;
	
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
	 * Class ctor.
	 * 
	 * @param _width
	 * 		Width of the rectangle.
	 * @param _height
	 * 		Height of the rectangle.
	 * @param _patterns
	 * 		List of patterns to be placed on the rectangle.
	 */
	public ClothRectangle(int _width, int _height, ArrayList<Pattern> _patterns) {
		width = _width;
		height = _height;
		patterns = _patterns;
		
		// Determine the max zero cut value.
		optimalValue = getMaxZeroCutValue();
		
		// Determine if the value is greater by cutting vertically at all
		// possible points.
		int cutValue = 0;
		
		for (int x = 1; x < width; ++x) {
			// Cut this rectangle vertically.
			//*****
			// TODO: (goldsy) Lookup to see if resulting rectangle has already been computed.
			//*****
			ClothRectangle tempLeft = new ClothRectangle(x, height, _patterns);
			ClothRectangle tempRight = new ClothRectangle(width - x, height, patterns);
			
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
		for (int y = 1; y < height; ++ y) {
			// Cut this rectangle vertically.
			//*****
			// TODO: (goldsy) Lookup to see if resulting rectangle has already been computed.
			//*****
			ClothRectangle tempTop = new ClothRectangle(y, height, _patterns);
			ClothRectangle tempBottom = new ClothRectangle(width - y, height, patterns);
			
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
