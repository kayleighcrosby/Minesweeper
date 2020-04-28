
/*----------------------------------------------------------------
 *  Author:   Kayleigh Crosby 
 *  Email:    kacros23@g.holycross.edu
 *  Written:  7/13/2015
 *  Update:   4/23/2020
 
 *  
 *  This class implements the initial "splash" title screen window.
 *
 *  You can test this class using:
 *    java TitleScreen
 *----------------------------------------------------------------*/

import GUI.*;

/**
 * <i>TitleScreen</i> object represents the "splash screen" at the start of the
 * game. It shows the title and allows the user to select the game difficulty
 * and possibly other options. TitleScreen extends Window, so it can be drawn on
 * the screen. It also extends EventListener so it can respond to user
 * interaction. See main() for an example of how to use this class.
 */
public class TitleScreen extends Window implements EventListener {

  // The user's choice. Uses "quit" as the default if nothing else is chosen.
  // Possible values are "quit", "easy", "medium", and "hard".
  private String selection = "quit";

  // Buttons for the different options.
  private Button easy, okay, hard, quit, save, restore;

  /**
     * Initialize a new title screen. 
     */
  public TitleScreen() {
    super("Minesweeper!", 600, 400);

    setBackgroundColor(Canvas.LIGHT_GRAY);

    // Add all the buttons
    easy = new Button(280, 120, 160, 40, "1 - Easy");
    okay = new Button(280, 170, 160, 40, "2 - Medium");
    hard = new Button(280, 220, 160, 40, "3 - Hard");
    quit = new Button(280, 300, 160, 40, "Q - Quit");
    // save = new Button(280, 350, 160, 40, "S - Save"); 
    //  restore = new Button(280, 400, 160, 40, "R - Restore"); 
    easy.setForegroundColor(Canvas.WHITE);
    easy.setBackgroundColor(Canvas.GREEN.darker());
    easy.setBorderColor(null);

    okay.setForegroundColor(Canvas.WHITE);
    okay.setBackgroundColor(Canvas.BLUE.darker());
    okay.setBorderColor(null);

    hard.setForegroundColor(Canvas.WHITE);
    hard.setBackgroundColor(Canvas.ORANGE.darker());
    hard.setBorderColor(null);

    quit.setForegroundColor(Canvas.WHITE);
    quit.setBackgroundColor(Canvas.RED.darker());
    quit.setBorderColor(null);

    /*  save.setForegroundColor(Canvas.WHITE);
    save.setBackgroundColor(Canvas.GRAY.darker());
    save.setBorderColor(null);*/ 

    add(easy);
    add(okay);
    add(hard);
    add(quit);
    // add(save); 
    //  add(restore); 

    // Add a title at the top
    Label title = new Label(300, 70, "Minesweeper!");
    title.setFont("SansSerif Bold", 48);
    title.setForegroundColor(Canvas.DARK_BLUE);
    add(title);
  }

  /**
     * Paint the background for this window on the canvas. Don't call this
     * directly, it is called by the GUI system automatically. This function
     * should draw something on the canvas. 
     * @param canvas the canvas on which to draw.
     */
  public void repaintWindowBackground(GUI.Canvas canvas) {
    // Put a nice logo on the left side
    canvas.picture(70, 100, "logo.png");

    // Also draw some "raised 3D" beveled rectangles, so they look like
    // mines. We will use a mostly gray ones, and a few odd colors. I use a
    // clever trick to pick the colors.
    int r = (int)System.currentTimeMillis() / 500;

    for (int i = 0; i < 30; i++) {
      for (int j = 0; j < 20; j++) {
        if (i >= 3 && i < 27 && j >= 2 && j < 18)
          continue; // skip the middle part of the window
        int x = i*20;
        int y = j*20;
        setRandomColor(canvas, r, i, j);
        canvas.raisedBevelRectangle(x, y, 20, 20, 4.0);
      }
    }
  }

  // Pick a random color, but usually gray, and set the pen to that color.
  private void setRandomColor(Canvas canvas, int r, int i, int j) {
    int h = r ^ ((i+1) * (j+1));
    h = h ^ (h >> 16);
    h = h ^ (h >> 8);
    switch(h % 97) {
      case 0:  canvas.setPenColor(Canvas.BLUE); break;
      case 1:  canvas.setPenColor(Canvas.CYAN); break;
      case 2:  canvas.setPenColor(Canvas.GREEN); break;
      case 3:  canvas.setPenColor(Canvas.MAGENTA); break;
      case 4:  canvas.setPenColor(Canvas.ORANGE); break;
      case 5:  canvas.setPenColor(Canvas.PINK); break;
      case 6:  canvas.setPenColor(Canvas.YELLOW); break;
      case 7:  canvas.setPenColor(Canvas.MAROON); break;
      case 8:  canvas.setPenColor(Canvas.TURQUOISE); break;
      default: canvas.setPenColor(Canvas.GRAY); break;
    }
  }


  /**
     * Respond to key presses. This function will be called each time the user
     * presses a key. The parameter indicates the character the user pressed.
     * The function updates the state according to what character the user has
     * pressed. 
     * @param c the character that was typed.
     */
  public void keyTyped(char c) {
    if (c == '1') {
      selection = "easy";
      hide();
    } else if (c == '2') {
      selection = "medium";
      hide();
    } else if (c == '3') {
      selection = "hard";
      hide();
    } else if (c == 'q' || c == 'Q') {
      selection = "quit";
      hide();
    }
  } 
  /**
     * Respond to a mouse click. This function will be called each time the user
     * clicks on the title window. The x, y parameters indicate the screen
     * coordinates where the user has clicked, and the button parameter
     * indicates which mouse button was clicked (either "left", "middle", or
     * "right"). The function updates the state according to what the user has
     * clicked.
     * @param x the x coordinate where the user clicked, in pixels.
     * @param y the y coordinate where the user clicked, in pixels.
     * @param button either "left", "middle", or "right".
     */
  public void mouseClicked(double x, double y, String button) {
    if (button.equals("left")) {
      if (easy.containsPoint(x, y)) {
        selection = "easy";
        hide();
      } else if (okay.containsPoint(x, y)) {
        selection = "medium";
        hide();
      } else if (hard.containsPoint(x, y)) {
        selection = "hard";
        hide();
      } else if (quit.containsPoint(x, y)) {
        selection = "quit";
        hide();

      }
    }
  }

  /**
     * Get the selection. Call this after calling showAndWait() to find out
     * what the user selected.
     */
  public String getSelection() {
    return selection;
  }

  /**
     * A main() function, for testing.
     */
  public static void main(String args[]) {
    TitleScreen t = new TitleScreen();
    t.showAndAnimate(4);
    System.out.println("You chose: " + t.getSelection());
  }
}