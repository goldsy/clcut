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
	
	// Either a pattern which will be placed on the rectangle.
	private Pattern optimalPattern = null;
	
	// Or a reference to the left/top or right/bottom pieces which it was cut
	// into because the value of the sum of the two pieces is greater than the
	// optimal pattern that fits in this rectangle.
	private ClothRectangle leftTop = null;
	private ClothRectangle rightBottom = null;
}
