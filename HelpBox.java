/*----------------------------------------------------------------
 *  Author:   K. Walsh
 *  Email:    kwalsh@holycross.edu
 *  Written:  7/13/2015
 *  
 *  HelpBox just displays some helpful text in a box on the scren.
 *----------------------------------------------------------------*/

import GUI.*;

/**
 * A <i>HelpBox</i> object represents the help menu. It can draw itself on a
 * canvas.
 */
public class HelpBox extends Widget {

    /**
     * The width of the box as it is shown on the screen.
     */
    public static final int WIDTH = 200;

    /**
     * The height of the box as it is shown on the screen.
     */
    public static final int HEIGHT = 75;

    /**
     * Initialize a new HelpBox object. It will be drawn at the specified
     * position.
     * @param x the x coordinate where the help box will be drawn.
     * @param y the y coordinate where the help box will be drawn.
     */
    public HelpBox(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }

    /**
     * Paint the help box on a canvas. Don't call this directly, it is called by
     * the GUI system automatically. This function should draw something on the
     * canvas. Usually the drawing should stay within the bounds (x, y, width,
     * height) which are protected member variables of GUI.Widget, which this
     * class extends.
     * @param canvas the canvas on which to draw.
     */
    public void repaint(GUI.Canvas canvas) {
        // Draw a white box with a black outline.
        canvas.setPenColor(Canvas.WHITE);
        canvas.filledRectangle(x, y, width, height);
        canvas.setPenColor(Canvas.BLACK);
        canvas.setPenRadius(1.0);
        canvas.rectangle(x+0.5, y+0.5, width-1, height-1);

        // Draw some help text.
        canvas.setFont(Canvas.DEFAULT_FONT);
        canvas.textLeft(x + 15, y + 15, "Q - Quit");
        canvas.textLeft(x + 15, y + 37.5, "Left button - Reveal");
    }

}
