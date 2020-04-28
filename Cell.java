/*----------------------------------------------------------------
 *  Author:   Kayleigh Crosby
 *  Email:    kacros23@g.holycross.edu
 *  Written:  7/13/2015
 *  Edit:     4/182020
 *  
 *  Each Cell object manages information about and draws a
 *  single "cell" of the game grid. 
 *  Based on the status of the cell, it will be repainted and shown
 *  different images 
 *----------------------------------------------------------------*/

import GUI.*;
import java.awt.Color;
import java.awt.Font;

/**
 * A <i>Cell</i> object holds all information about the state of a single cell
 * of the minesweeper game board. This includes:
 *   - whether a mine is hidden in this cell or not
 *   - how many of its neighboring cells contain mines
 *   - whether it has been revealed yet or is still hidden
 * Each Cell object knows how to draw itself in a graphical window, and it will
 * draw itself in different styles depending on all the above state information.
 */
public class Cell extends Widget {

  /**
     * Size of one cell when it is drawn on the screen, in pixels.
     */
  public static final int SIZE = 20;

  /**
     * Whether a mine is hidden in this cell or not.
     */
  protected boolean isMine;

  /**
     * Whether this cell is "revealed" or not.
     */
  protected boolean isRevealed;

  /**
     * Count of how many neighboring cells have mines.
     */
  protected int neighborMineCount;

  protected boolean isFlag; 

  protected int flagCount; 

  /**
     * Constructor: Initialize a cell to be drawn at the given x, y coordinates
     * on the screen. The cell will be blank. That is, it will not be a mine,
     * and it will have no neighboring mines so a neighbor mine count of zero.
     */
  public Cell(int x, int y) {
    super(x, y, SIZE, SIZE);
    this.isMine = false;
    this.isRevealed = false;
    this.neighborMineCount = 0;
  }

  /**
     * Hide a mine in this cell by changing the isMine variable to true.
     */
  public void plantMine() {
    isMine = true;
  }

  /**
     * Returns true if a mine is hidden in this cell, otherwise returns false.
     */
  public boolean isMine() {
    return isMine;
  }

  /**
     * Increment the neighbor mine count variable by one. 
     */
  public void incrementNeighborMineCount() {
    neighborMineCount++;
  }

  /**
     * Set the neighbor mine count variable to a given value.
     */
  public void setNeighborMineCount(int count) {
    neighborMineCount = count;
  }

  /**
     * Returns the value of the neighbor mine count variable.
     */
  public int getNeighborMineCount() {
    return neighborMineCount;
  }

  /**
     * Change this cell so that it is "revealed" by setting isRevealed to true.
     */
  public void reveal() {
    isRevealed = true;
  }

  /**
     * Returns true if this cell is "revealed", otherwise returns false.
     */
  public boolean isRevealed() {
    return isRevealed;
  }

  /**
     * Hide a mine in this cell by changing the isMine variable to true.
     */
  public void makeMine() {
    isMine = true;
  }

  /**
     * Change this cell so that it shows the mine that is hiding in it.
     */
  public void showMine() {
    if (isMine)
      isRevealed = true;
  }

  /**
     * Check whether there are neighboring mines.
     */
  public boolean coastIsClear() {
    return (neighborMineCount == 0);
  }

  // Extra Credit FINISHED: these methods are used to keep track of flags 
  // checks if cell should repaint a flag 
  public boolean isFlag() { 
    return isFlag; 
  } 


  public void  makeFlag() { 
    isFlag = true; 
  }

  public int getFlagCount() { 
    return flagCount; 
  }

  // sets flag to false so the cell will paint over flag png 
  public void undoFlag() { 
    isFlag = false; 
  }

  /**
     * Paint this cell on the canvas. Don't call this directly, it is called by
     * the GUI system automatically. This function should draw something on the
     * canvas. Usually the drawing should stay within the bounds (x, y, width,
     * height) which are protected member variables of GUI.Widget, which this
     * class extends.
     * @param canvas the canvas on which to draw.
     */
  public void repaint(GUI.Canvas canvas) {
    // FINISHED: Add code here to draw this cell. The look of the cell should
    // depend on its current state, e.g. if it has been revealed or not, how
    // many neighbors it has, and so on.

    // draws dark rectangles for 600 cells 
    canvas.setPenColor(Canvas.DARK_GRAY); 
    canvas.raisedBevelRectangle(x, y, 20, 20, 4.0);

    if (isRevealed) {
      if (!isMine){
        canvas.setPenColor(Canvas.LIGHT_GRAY); 
        canvas.sunkenBevelRectangle(x , y ,20,20,4.0); 

        Font font = new Font ("Arial", Font.BOLD , 18); 

        // prints number of neighboring mines over cell 
        switch (neighborMineCount){ 

          case 1: 
            canvas.setPenColor(Canvas.DARK_GREEN);
            canvas.setFont(font); 
            canvas.text(x + Cell.SIZE/2, y + Cell.SIZE/2, "1"  );
            break; 

          case 2: 
            canvas.setPenColor(Canvas.BLUE);
            canvas.setFont(font); 
            canvas.text(x + Cell.SIZE/2, y + Cell.SIZE/2, "2"  );
            break; 

          case 3: 
            canvas.setPenColor(Canvas.MAGENTA);
            canvas.setFont(font); 
            canvas.text(x + Cell.SIZE/2, y + Cell.SIZE/2, "3"  );
            break; 

          case 4: 
            canvas.setPenColor(Canvas.RED);
            canvas.setFont(font); 
            canvas.text(x + Cell.SIZE/2, y + Cell.SIZE/2, "4"  );
            break; 

          case 5: 
            canvas.setPenColor(Canvas.BLACK);
            canvas.setFont(font); 
            canvas.text(x + Cell.SIZE/2, y + Cell.SIZE/2, "5"  );
            break; 

          case 6: 
            canvas.setPenColor(Canvas.ORANGE);
            canvas.setFont(font); 
            canvas.text(x + Cell.SIZE/2, y + Cell.SIZE/2, "6"  );
            break; 

          case 7: 
            canvas.setFont(font); 
            canvas.setPenColor(Canvas.YELLOW);
            canvas.text(x + Cell.SIZE/2, y + Cell.SIZE/2, "7"  );
            break; 

          case 8: 
            canvas.setPenColor(Canvas.GRAY);
            canvas.setFont(font); 
            canvas.text(x + Cell.SIZE/2, y + Cell.SIZE/2, "8"  );
            break; 

          case 0: 
            canvas.setPenColor(Canvas.GREEN); 
            canvas.text(x + Cell.SIZE/2, y + Cell.SIZE/2, " "  );
            break; 

          default:
            break; 
        }
      } // ends printing neighboring mine count switch 

      // prints poop emoji to indicate mines when revealed 
      if (isMine){
        canvas.setPenColor(Canvas.RED); 
        canvas.filledRectangle(x, y, 20,20); 
        canvas.pictureCentered(x + Cell.SIZE/2 , y + Cell.SIZE/2 , "poop.png", 18, 18);
      } 

    } // end of if isRevealed 

    // prints flag png over cell if flag is true 
    if (isFlag){
      canvas.setPenColor(Canvas.DARK_GRAY ); 
      canvas.filledRectangle(x, y, 20,20); 
      canvas.pictureCentered(x + Cell.SIZE/2 , y + Cell.SIZE/2 , "flag.png", 18, 18);
    } 
  } // end of repaint 

} // end of cell