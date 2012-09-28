/**
 * This class stores information about the garment produced from a pattern.
 * 
 * @author Jeff Goldsworthy
 *
 */
public class Garment {
	private Pattern sourcePattern = null;
	
	// Location information.
	private int widthLocation = 0;
	private int heightLocation = 0;
    
	public Garment(int _widthLocation, int _heightLocation, Pattern _sourcePattern) {
		widthLocation = _widthLocation;
		heightLocation = _heightLocation;
		sourcePattern = _sourcePattern;
	}
    
	public int getXStart() {
		return widthLocation;
	}
    
	public int getYStart() {
		return heightLocation;
	}
    
	public int getWidth() {
		return sourcePattern.getWidth();
	}
    
	public int getHeight() {
		return sourcePattern.getHeight();
	}
    
	public Pattern getSourcePattern() {
		return sourcePattern;
	}
    
	public String toString() {
		return ("[" + sourcePattern.getName() + ", " + widthLocation + ", "
				+ heightLocation + "]");
	}
}
