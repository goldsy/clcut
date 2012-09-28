/**
 * This class stores information about the garment produced from a pattern.
 * 
 * @author Jeff Goldsworthy
 *
 */
public class Garment {
	// Location information.
	private int widthLocation = 0;
	private int heightLocation = 0;
    
    // Pattern used to make this garment.
	private Pattern sourcePattern = null;
    
    /**
     * Class constructor.
     * 
     * @param _widthLocation
     * This is the x-axis location of the garment.
     * 
     * @param _heightLocation
     * This is the y-axis location of the garment.
     * 
     * @param _sourcePattern
     * This is the pattern used to make the garment.
     */
	public Garment(int _widthLocation, int _heightLocation, Pattern _sourcePattern) {
		widthLocation = _widthLocation;
		heightLocation = _heightLocation;
		sourcePattern = _sourcePattern;
	}
    
    
	/**
     * Gets the starting x-axis location.
	 * 
	 * @return
     * This method returns the start x location for the garment.
	 */
	public int getXStart() {
		return widthLocation;
	}
    
    
	/**
	 * Gets the starting y-axis location.
     * 
	 * @return
     * This method returns the start y location for the garment.
	 */
	public int getYStart() {
		return heightLocation;
	}
    
    
	/**
	 * Get the width of the underlying pattern.
     * 
	 * @return
     * This method returns the width of the pattern used to make the garment.
	 */
	public int getWidth() {
		return sourcePattern.getWidth();
	}
    
    
	/**
	 * Get the height of the underlying pattern.
     * 
	 * @return
     * This method returns the height of the pattern used to make the garment.
	 */
	public int getHeight() {
		return sourcePattern.getHeight();
	}
    
    
	/**
	 * Gets the pattern used to make this garment.
     * 
	 * @return
     * This method returns a reference to the pattern used to make this garment.
	 */
	public Pattern getSourcePattern() {
		return sourcePattern;
	}
    
    
	/**
	 * This method formats the garment as a string.
	 */
	public String toString() {
		return ("[" + sourcePattern.getName() + ", " + widthLocation + ", "
				+ heightLocation + "]");
	}
}
