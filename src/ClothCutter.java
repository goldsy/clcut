import java.util.ArrayList ;


/**
 * Class to initiate the cloth cutting optimization process.
 * 
 * @author Jeff
 *
 */
public class ClothCutter {
	private ArrayList<Pattern> patterns = null;
	private int initClothWidth = 0;
	private int initClothHeight = 0;
	
	private Cut optimalCuts = null;
	
	/**
	 * Class ctor.
	 * 
	 * @param _width
	 * 		Width of the cloth.
	 * @param _height
	 * 		Height of the cloth.
	 * @param _patterns
	 * 		List of patterns to be placed on the cloth.
	 */
	public ClothCutter(int _width, int _height, ArrayList<Pattern> _patterns) {
		patterns = _patterns;
		
		// TODO:
		// Optimize the list of patterns. Remove larger in W and H but less
		// value than another, and equal in W and H but less in value.
		
		initClothWidth = _width;
		initClothHeight = _height;
	}

	
	/**
	 * Initiates the optimize operation.
	 */
	public void optimize() {
		// TODO: (goldsy) Finish me.
	}
}
