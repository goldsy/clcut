import java.util.ArrayList;


/**
 * Class to initiate the cloth cutting optimization process.
 * 
 * @author Jeff
 *
 */
public class ClothCutter {
    // This stores the list of patterns to be placed on the cloth.
	private ArrayList<Pattern> patterns = null;
    
	// These members store the intial size of the cloth.
	private int initClothWidth = 0;
	private int initClothHeight = 0;

    // This is the cloth rectangle representing the original cloth.
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
	public ClothCutter(int _width, int _height) {
		patterns = new ArrayList<Pattern>();
		
		initClothWidth = _width;
		initClothHeight = _height;
	}

	
	/**
	 * Initiates the optimize operation.
	 */
	public void optimize() {
        // First push the pattern list to the Cloth Rectangle class.
        ClothRectangle.setPatterns(patterns);
        
		//origCloth = new ClothRectangle(initClothWidth, initClothHeight);
        origCloth = ClothRectangle.create(initClothWidth, initClothHeight);
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
            return origCloth.getOptimalProfit();
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
    
	
    /**
     * Gets the list of garments formatted as a string.
     * 
     * @return
     * This method returns a string representation of the optimal pattern/
     * garment layout.
     */
	public String formattedGarments() {
		String returnVal = "[";
        
		//for(Garment g : target) {
		for(Garment g : garments()) {
			returnVal += g.toString();
		}
        
		returnVal += "]";
        
        return returnVal;
	}


    /**
     * Gets a list of the optimal cuts.
     * 
     * @return
     * This method returns the list of drawable cuts.
     */
	public ArrayList<DrawableCut> cuts() {
		ArrayList<DrawableCut> target = new ArrayList<DrawableCut>();
		
		// Populate the cut list.
		origCloth.getCuts(target);
        
		return target;
	}
    
	
    /**
     * This method adds the specified pattern to the list of patterns, but
     * does it only if the pattern will be possibly used. Also the method
     * keeps track of the minimum pattern width and height since it is no
     * use cutting any smaller than the smallest pattern.
     * 
     * @param p
     * The pattern to be added to the list of patterns to consider.
     */
	public void addPattern(Pattern p) {
        // This boolean indicates if the pattern to be added is significant.
		// That is whether there is possibility that the pattern will be used.
		// Any pattern larger in both dimensions with a lower value will never
		// be used.  Don't waste time checking it.
        boolean patternSignificant = true;
        
        patternCheck:
		for (Pattern i : patterns) {
			if ((p.getWidth() >= i.getWidth()) 
					&& (p.getHeight() >= i.getHeight()) 
					&& (p.getValue() <= i.getValue())) {
                // TODO: (goldsy) SHOULD PROBABLY CHECK FOR INVALID PATTERNS HERE.
                // DEBUG
                System.out.println("Omitting rectangle [" + p.getWidth() + ", "
                		+ p.getHeight() + ", " + p.getValue() + "]");
                patternSignificant = false;
				break patternCheck;
			}
		}
        
        if (patternSignificant) {
            // If this pattern has the potential to be placed then store it.
        	patterns.add(p);
        }
	}
}
