/**
 * This class represents a pattern which is to be placed on the cloth.
 * 
 * @author Jeff
 *
 */
public class Pattern {
	private int width = 0;
	private int height = 0;
	private int value = 0;
	private String name;
	
	/**
	 * Class ctor.
	 * 
	 * @param _width
	 * 		Width of cloth that the pattern takes up.
	 * @param _height
	 * 		Height of cloth that the pattern takes up.
	 * @param _value
	 * 		How much the garment that is created from this pattern is worth.
	 */
	public Pattern(int _width, int _height, int _value, String _name) {
		width = _width;
		height = _height;
		value = _value;
		name = _name;
	}
	
	/**
	 * Width property getter.
	 * 
	 * @return
	 * 		This method returns the width of the pattern.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Height property getter.
	 * 
	 * @return
	 * 		This method returns the height of the pattern.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Value property getter.
	 * 
	 * @return
	 * 		This method returns the value of the pattern (or more specifically
	 * 		the garment that is produces from the pattern).
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Name property getter.
	 * 
	 * @return
	 * 		This method returns the name of the pattern.
	 */
	public String getName() {
		return name;
	}
}
