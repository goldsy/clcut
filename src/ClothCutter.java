import java.util.ArrayList;


/**
 * Class to initiate the cloth cutting optimization process.
 * 
 * @author Jeff Goldsworthy
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
		
        // Make sure that the width is valid.
        if (_width < 1) {
            System.out.println("Width is invalid using cloth width of 1.");
        	initClothWidth = 1;
        }
        else {
        	initClothWidth = _width;
        }
        
        // Make sure that the height is valid.
        if (_height < 1) {
            System.out.println("Height is invalid using cloth height of 1.");
            initClothHeight = 1;
        }
        else {
        	initClothHeight = _height;
        }
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
        // Check for invalid pattern size.
        if (p.getWidth() < 1 || p.getHeight() < 1) {
            // DEBUG
        	//System.out.println("Invalid pattern size. Excluding pattern. ["
            //    		+ p.getWidth() + "," + p.getHeight() 
            //    		+ "," + p.getValue() + "," + p.getName() + "]");
            return;
        }
        
        // Check if the pattern to be added is significant.
		// That is whether there is possibility that the pattern will be used.
		// Any pattern larger in both dimensions with a lower value will never
		// be used.  Don't waste time checking it.
		for (Pattern i : patterns) {
            // Check for duplicate names.
            if (p.getName() == i.getName()) {
            	// This name has already been used.
                // DEBUG
            	//System.out.println("Name is duplicate. Excluding pattern [" 
                //		+ p.getWidth() + "," + p.getHeight() 
                //		+ "," + p.getValue() + "," + p.getName() + "]");
                
            	// It won't be used; bail.
                return;
            }
            
            // Check if pattern will be used.
			if ((p.getWidth() >= i.getWidth()) 
					&& (p.getHeight() >= i.getHeight()) 
					&& (p.getValue() <= i.getValue())) {
                // DEBUG
                //System.out.println("Excluding pattern because existing" 
                //        + " pattern with equal or smaller size has equal"
                //        + " or greater value. Excluded pattern ["
                //		+ p.getWidth() + "," + p.getHeight() 
                //		+ "," + p.getValue() + "," + p.getName() + "]");
                
            	// It won't be used; bail.
                return;
			}
		}
        
		// If this pattern has the potential to be placed then store it.
		patterns.add(p);
	}
}
