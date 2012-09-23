import java.awt.*;
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
    private boolean inited = false;
    
    
    /**
     * Class ctor.
     */
	public Cloth(int _width, int _height, int _pixelsPerUnit) {
		width = _width;
		height = _height;
		pixelsPerUnit = _pixelsPerUnit;
        
		setPreferredSize(new Dimension(width * pixelsPerUnit, height * pixelsPerUnit));
	}
    
	
    /**
     * This method initializes the color for the background.
     * 
     * Precondition:
     * The graphics context must exist before calling this method.
     */
	public void paintComponent() {
        Graphics g = getGraphics();
		g.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);
        
		inited = true;
	}
    
	
    /**
     * Draws the cut on the screen.
     * 
     * @param c
     * The cut to be drawn.
     */
	public void drawCut(Cut c) {
        //if (!inited) {
        //	init();
        //}
        
        Graphics g = getGraphics();

        // Always use black for the cut lines.
		g.setColor(Color.BLACK);

        // Draw the line relative to the cut information.
		g.drawLine(c.getXStart() * pixelsPerUnit, c.getYStart() * pixelsPerUnit, 
				c.getXEnd() * pixelsPerUnit, c.getYEnd() * pixelsPerUnit);

        repaint();
	}
    
	
    /**
     * Draws the garment on the screen.
     * 
     * @param g
     * The garment to be drawn.
     */
	public void drawGarment(Garment g) {
        //if (!inited) {
        //	init();
        //}
	}
}
