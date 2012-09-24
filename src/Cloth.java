import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;


/**
 * This class is used to display the resulting cloth cutting solution to the
 * screen.
 * 
 * @author Jeff
 *
 */
public class Cloth extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width;
    private int height;
    private int pixelsPerUnit;
    private Color backgroundColor = Color.LIGHT_GRAY;
    private ArrayList<DrawableCut> cuts = new ArrayList<DrawableCut>();
    private ArrayList<Garment> garments = new ArrayList<Garment>();
    
    
    /**
     * Class ctor.
     */
	public Cloth(int _width, int _height, int _pixelsPerUnit) {
		width = _width;
		height = _height;
		pixelsPerUnit = _pixelsPerUnit;
        
		setPreferredSize(new Dimension(getPixelSize(width), getPixelSize(height)));
	}
    
    
    /**
     * This method takes a unit size and converts it to the pixel size.
     * 
     * @param unitSize
     * Unit size to convert.
     * 
     * @return
     * This method returns a size in pixels.
     */
	public int getPixelSize(int unitSize) {
		return (unitSize * pixelsPerUnit);
	}
    
	
    /**
     * This method initializes the color for the background.
     * 
     * Precondition:
     * The graphics context must exist before calling this method.
     */
	public void paintComponent(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, getPixelSize(width), getPixelSize(height));
        
        for (DrawableCut c : cuts) {
        	// Always use black for the cut lines.
        	g.setColor(Color.BLACK);

        	// Draw the line relative to the cut information.
        	g.drawLine(getPixelSize(c.getXStart()), getPixelSize(c.getYStart()), 
        			getPixelSize(c.getXEnd()), getPixelSize(c.getYEnd()));
        }
        
        for (Garment garment : garments) {
        	g.setColor(Color.YELLOW);
            
            // Make rectangle a pixel smaller and the size a pixel smaller
        	// so that the rectangle doesn't overlay the cut lines.
        	g.fillRect(getPixelSize(garment.getXStart()) + 1, getPixelSize(garment.getYStart()) + 1, 
        			getPixelSize(garment.getWidth()) - 1, getPixelSize(garment.getHeight()) - 1);
            
        	g.setColor(Color.BLACK);
            
            // Shift drawing the text by a few pixels so it doesn't run off the
        	// edge of the screen.
            g.drawString(garment.getSourcePattern().getName(), 
            		getPixelSize(garment.getXStart()) + 5, getPixelSize(garment.getYStart()) + 15);
        }
	}
    
	
    /**
     * Draws the cut on the screen.
     * 
     * @param c
     * The cut to be drawn.
     */
	public void drawCut(DrawableCut c) {
        cuts.add(c);

        repaint();
	}
    
	
    /**
     * Draws the garment on the screen.
     * 
     * @param g
     * The garment to be drawn.
     */
	public void drawGarment(Garment g) {
        garments.add(g);
        
        repaint();
	}
}
