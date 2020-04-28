/*----------------------------------------------------------------
 *  Author:   K. Walsh
 *  Email:    kwalsh@holycross.edu
 *  Written:  7/13/2015
 *  
 *  StatusBox displays game statistics in a box on the screen.
 *----------------------------------------------------------------*/

import GUI.*;

/**
 * A <i>StatusBox</i> object represents the status of the game as it is represented on
 * the screen. It shows the number of mines that are deployed and how many cells
 * are remaining to be revealed. A StatusBox object knows how to draw itself in
 * on a Canvas.
 */
public class StatusBox extends Widget {

    /**
     * The width of the box as it is shown on the screen.
     */
    public static final int WIDTH = 200;

    /**
     * The height of the box as it is shown on the screen.
     */
    public static final int HEIGHT = 75;

    // The game for which this box will show statistics.
    private Game game;

    /**
     * Initialize a new status box for the given game.
     * @param g the game for which this box will show statistics.
     * @param x the x coordinate of the location to draw the box.
     * @param y the y coordinate of the location to draw the box.
     */
    public StatusBox(Game g, int x, int y) {
        super(x, y, WIDTH, HEIGHT);
        game = g;
    }

    /**
     * Draw the status box on a canvas. Don't call this directly, it is called
     * by the GUI system automatically. This function should draw something on
     * the canvas. Usually the drawing should stay within the bounds (x, y,
     * width, height) which are protected member variables of GUI.Widget, which
     * this class extends.
     * @param canvas the canvas on which to draw.
     */
    public void repaint(GUI.Canvas canvas) {
        // Draw a white rectangle with a black border.
        canvas.setPenColor(Canvas.WHITE);
        canvas.filledRectangle(x, y, width, height);
        canvas.setPenColor(Canvas.BLACK);
        canvas.setPenRadius(1.0);
        canvas.rectangle(x+0.5, y+0.5, width-1, height-1);

        // Draw the labels in plain font, black
        canvas.setFont(Canvas.DEFAULT_FONT);
        canvas.setPenColor(Canvas.BLACK);
        canvas.text(x + 40, y + 15, "Mines");
        canvas.text(x + 160, y + 15, "Cells");

        // Draw the number of mines in large blue bold font.
        canvas.setFont(Canvas.BOLD_FONT);
        canvas.setFont(24);
        canvas.setPenColor(Canvas.DARK_BLUE);
        canvas.text(x+40, y+45, "" + game.getNumMinesDeployed());

        // Draw the number of remaining cells in large blue or green font.
        if (game.getNumCellsRemaining() > game.getNumMinesDeployed())
            canvas.setPenColor(Canvas.DARK_BLUE);
        else 
            canvas.setPenColor(Canvas.DARK_GREEN);
        canvas.text(x+160, y+45, "" + game.getNumCellsRemaining());
    }

}
