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
	
	private ClothRectangle origCloth = null;
	
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
		origCloth = new ClothRectangle(initClothWidth, initClothHeight, patterns);
		// Compile list of garments.
		// TODO: (goldsy) Finish me.
	}
	
	
    /**
     * Returns the max value/profit for the cloth.
     * 
     * @return
     * 		This method returns the value/profit for the cloth. If no cloth
     * 		object exists in the cutter, then this method returns zero.
     */
	public int value() {
		if (origCloth != null) {
            return origCloth.getOptimalValue();
		}
		else {
			return 0;
		}
	}
    
	
    /**
     * Gets a list of garments.
     * 
     * @return
     * This method returns an array list of the garments.
     */
	public ArrayList<Garment> garments() {
		ArrayList<Garment> target = new ArrayList<Garment>();
		
        // Populate the garment list.
        // TODO: (goldsy) This shouldn't take long, but we could cache this list for the next call.
		origCloth.getGarments(target);
        
        return target;
	}
    
	
	public ArrayList<Cut> cuts() {
		ArrayList<Cut> target = new ArrayList<Cut>();
		
		// Populate the cut list.
		origCloth.getCuts(target);
        
		return target;
	}
}
