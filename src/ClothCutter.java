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
    
	private int minPatternWidth = -1;
	private int minPatternHeight = -1;
	
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
	//public ClothCutter(int _width, int _height, ArrayList<Pattern> _patterns) {
	//	patterns = _patterns;
	public ClothCutter(int _width, int _height) {
		patterns = new ArrayList<Pattern>();
		
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
		origCloth = new ClothRectangle(initClothWidth, initClothHeight, 
				patterns, minPatternWidth, minPatternHeight);
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
     * Gets a list of garments.
     * 
     * @return
     * This method returns an array list of the garments.
     */
	public String formattedGarments() {
		//ArrayList<Garment> target = new ArrayList<Garment>();
		
        // Populate the garment list.
        // TODO: (goldsy) This shouldn't take long, but we could cache this list for the next call.
		//origCloth.getGarments(target);
        
		String returnVal = "[";
        
		//for(Garment g : target) {
		for(Garment g : garments()) {
			returnVal += g.toString();
		}
        
		returnVal += "]";
        
        return returnVal;
	}
    
	
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
                
                System.out.println("Omitting rectangle [" + p.getWidth() + ", "
                		+ p.getHeight() + ", " + p.getValue() + "]");
                patternSignificant = false;
				break patternCheck;
			}
		}
        
        if (patternSignificant) {
        	patterns.add(p);
            
            // If the minimum pattern width has not been initialized or if
        	// the current pattern is smaller than the previous smallest
        	// pattern in this orientation then store the new minimum.
        	if (minPatternWidth > p.getWidth() || minPatternWidth == -1) {
        		minPatternWidth = p.getWidth();
                // DEBUG
                System.out.println("Min Pattern Width is: " + minPatternWidth);
        	}
            
        	if (minPatternHeight > p.getHeight() || minPatternHeight == -1) {
        		minPatternHeight = p.getHeight();
                // DEBUG
                System.out.println("Min Pattern Height is: " + minPatternHeight);
        	}
        }
	}
}
