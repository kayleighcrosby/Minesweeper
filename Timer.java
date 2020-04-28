/*----------------------------------------------------------------
 *  Author:   Kayleigh Crosby 
 *  Email:    kacros23@g.holycross.edu
 *  Written:  7/13/2015
 *  Editted:  4/20/20 
 * 
 *  Timer keeps track of elapsed time.
 *----------------------------------------------------------------*/

import GUI.*;

/**
 * A <i>Timer</i> object keeps track of elapsed time. It can be started,
 * stopped, or reset. A Timer knows how to draw itself on a Canvas.
 */
public class Timer extends GUI.Widget {

    /**
     * The width of the box as it is shown on the screen.
     */
    public static final int WIDTH = 150;

    /**
     * The height of the box as it is shown on the screen.
     */
    public static final int HEIGHT = 75;

    // Whether we are currently counting or not.
    private boolean isCounting;

    // Time of day at which we started counting.
    private double startTime;

    // Number of seconds elapsed so far.
    private double elapsedSeconds;

    /**
     * Initialize a new timer.
     * @param x the x coordinate of the location to draw the timer.
     * @param y the y coordinate of the location to draw the timer.
     */
    public Timer(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
        isCounting = false;
        startTime = 0;
        elapsedSeconds = 0;
    }

    /**
     * Start the timer.
     */
    public void startCounting() {
        // If already counting, do nothing.
        if (isCounting)
            return;

        // We subtract the previously elapsed seconds in order
        // to get a total cumulative elapsed time, even if the
        // timer is stopped then restarted.
        startTime = System.currentTimeMillis()/1000.0 - elapsedSeconds;
        isCounting = true;
    }

    /**
     * Stop the timer.
     */
    public void stopCounting() {
        isCounting = false;
        elapsedSeconds = System.currentTimeMillis()/1000.0 - startTime;
    }

    /**
     * Reset the timer back to time n.
     * If the timer is counting, it will remain so.
     * @param n the number of seconds to set the timer to.
     */
    public void reset(double n) {
        elapsedSeconds = n;
        if (isCounting)
            startTime = System.currentTimeMillis()/1000.0;
    }

    /**
     * Get the time (in seconds) that has elapsed while the timer was
     * counting. The time is cumulative, even if the timer is stopped then
     * restarted.
     */
    public int getElapsedSeconds() {
        // Update the elapsed time, if needed, then return it.
        if (isCounting)
            elapsedSeconds = System.currentTimeMillis()/1000.0 - startTime;
        return (int)elapsedSeconds;
    }


    /**
     * Draw the timer box on a canvas. Don't call this directly, it is called by
     * the GUI system automatically. This function should draw something on the
     * canvas. Usually the drawing should stay within the bounds (x, y, width,
     * height) which are protected member variables of GUI.Widget, which this
     * class extends.
     * @param canvas the canvas on which to draw.
     */
    public void repaint(GUI.Canvas canvas) {
        // Draw a white rectangle with a black border.
        canvas.setPenColor(Canvas.WHITE);
        canvas.filledRectangle(x, y, width, height);
        canvas.setPenColor(Canvas.BLACK);
        canvas.setPenRadius(1.0);
        canvas.rectangle(x+0.5, y+0.5, width-1, height-1);

        // Draw a label in plain black font.
        canvas.setFont(Canvas.DEFAULT_FONT);
        canvas.setPenColor(Canvas.BLACK);
        canvas.text(x + 75, y + 15, "Elapsed Time");

        // Draw the number of mines in large red bold font.
        // We cast to an integer so it doesn't show the decimal.
        canvas.setFont(Canvas.BOLD_FONT);
        canvas.setFont(24);
        canvas.setPenColor(Canvas.DARK_RED);
        int n = getElapsedSeconds();
        String s = "" + n; // could also use String.format("%d", n)
        canvas.text(x + 75, y + 45, s);
    	// startCounting(); 
        
        
        }
      
    } // end of Timer 


