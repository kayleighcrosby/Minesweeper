/*----------------------------------------------------------------
 *  Author:   Kayleigh Crosby
 *  Email:    kacros23@g.holycross.edu
 *  Written:  7/13/2015
 *  Edit:     4/182020
 *  
 *  Minesweeper game. This class implements the game window and most
 *  of the game logic.
 * 
 *  Extra Credit: This game auto-reveals all empty cells after the 
 *  first empty cell is selected. The player is able to left click a 
 *  cell to place a marker flag to plan moves. 
 *  Once you reveal all cells other than mines, a winner banner will 
 *  reveal 
 *  If user left click mines, it will end the game, reveal all the 
 *  cells, and a losing banner will appear  
 * 
 *----------------------------------------------------------------*/

import GUI.*;

/* A <i>Game</i> object manages all information about a minesweeper game as it
 * is being played and displayed on the screen. This includes information about
 * all of the cells (this is stored in a 2-D array of Cell objects), how many
 * flags have been planted, how many mines have been deployed, etc. Game extends
 * Window, so it can be drawn on the screen. It also extends EventListener so it
 * can respond to user interaction.
 */
public class Game extends Window implements EventListener {

  /**
     * Number of cells tall the game board will be.
     */
  public static final int NUM_ROWS = 20;

  /**
     * Number of cells wide the game board will be.
     */
  public static final int NUM_COLS = 30;

  // Example game screen layout:
  // +---------------------------------------------------------+
  // |      M A R G I N = 50                                   |
  // | M  + - - - - - - - - - - - - - - - - - - - - - - - + M  |
  // | A  |                                               | A  |
  // | R  |                                               | R  |
  // | G  |                Grid of Cells                  | G  |
  // | I  |                                               | I  |
  // | N  |                                               | N  |
  // | =  |       600 = NUM_COLS * Cell.SIZE wide         | =  |
  // | 50 |                      by                       | 50 |
  // |    |       400 = NUM_ROWS * Cell.SIZE tall         |    |
  // |    |                                               |    |
  // |    |                                               |    |
  // |    |                                               |    |
  // |    + - - - - - - - - - - - - - - - - - - - - - - - +    |
  // |            SPACE     S   SPACE   S    SPACE             |
  // |    + - - - - - - - + P + - - - + P + - - - - - - - +    |
  // |    |    Status     | A | Timer | A |     Help      |    |
  // |    |       Box     | C |       | C |      Box      |    |
  // |    + - - - - - - - + E + - - - + E + - - - - - - - +    |
  // |     M A R G I N = 50                                    |
  // +-- ------------------------------------------------------+

  /**
     * Width of the game window, in pixels.
     * Equal to 2*MARGIN + GRID_WIDTH
     * or 2*MARGIN + 2*SPACE + StatusBox.WIDTH, Timer.WIDTH, HelpBox.WIDTH,
     * whichever is larger.
     */


  public static final int WIDTH = 700;

  /**
     * Height of the game window, in pixels.
     * Equal to 2*MARGIN + SPACE
     *     + GRID_HEIGHT
     *     + max(StatusBox.Height, Timer.HEIGHT, HelpBox.HEIGHT)
     */
  public static final int HEIGHT = 600; 

  /**
     * Width of the grid part of the window, in pixels.
     * Equal to NUM_COLS * Cell.SIZE.
     */
  public static final int GRID_WIDTH = NUM_COLS * Cell.SIZE;

  /**
     * Height of the grid part of the window, in pixels.
     * Equal to NUM_ROWS * Cell.SIZE.
     */
  public static final int GRID_HEIGHT = NUM_ROWS * Cell.SIZE;

  /**
     * Margin around the edges of the canvas.
     */
  private static final int MARGIN = 50;

  /**
     * Space between elements on the canvas.
     */
  private static final int SPACE = 25;

  // A 2-D array of Cell objects to keep track of the board state.
  private Cell[][] cells = new Cell[NUM_ROWS][NUM_COLS];

  private int numMines = 0;    // number of mines deployed
  private int numRevealed = 0; // number of cells revealed so far
  private int numFlags = 0; 

  // Whether or not the game has been won.
  private boolean gameWon = false;

  // Whether or not the game has been lost
  private boolean gameLost = false;

  // Name of the user playing the game.
  private String username;

  // The difficulty level of the game, used for tracking top scores.
  private String difficulty;

  // The status box that appears in the top left.
  private StatusBox status;

  // The timer that appears in the top middle.
  private Timer timer;

  // The help box that appears in the top right.
  private HelpBox help;

  /**
     * Constructor: Initializes a new game, but does not deploy any mines, plant
     * any flags, etc. The difficulty is either "easy", "medium", or "hard", and
     * will be used to load the proper top scores file. Name is used as the
     * user's name.
     */
  public Game(String name, String difficulty) {
    super("Minesweeper!", WIDTH, HEIGHT);

    this.username = name;
    this.difficulty = difficulty;

    // Create the background
    setBackgroundColor(Canvas.DARK_GRAY);

    // Create a border around the grid
    Box border = new Box(MARGIN-1.5, MARGIN-1.5, GRID_WIDTH+3, GRID_HEIGHT+3);
    border.setBackgroundColor(null);
    border.setBorderColor(Canvas.BLACK);
    add(border);

    // Create the help boxes
    help = new HelpBox(
      WIDTH - MARGIN - HelpBox.WIDTH,
      HEIGHT - MARGIN - HelpBox.HEIGHT);
    add(help);

    // Creates the timer box
    timer = new Timer( 
      WIDTH - MARGIN - HelpBox.WIDTH - SPACE - Timer.WIDTH,
      HEIGHT - MARGIN - Timer.HEIGHT);
    add(timer);


    // Creates the statusbox 
    status = new StatusBox( this, 
                           WIDTH - MARGIN - HelpBox.WIDTH - SPACE - Timer.WIDTH - SPACE - StatusBox.WIDTH, 
                           HEIGHT - MARGIN - StatusBox.HEIGHT);
    add(status);  

    // FINISHED: Create and add the other info boxes, besides help. Also,
    // initialize and add all of the cells to form the game grid. As an
    // example, I will add a label and a few mines, just to show how to add
    // these kinds of widgets to the window. You will want to eventually
    // replace these next few lines with real code.
    // Here is a giant red label, centered exactly in the middle of where
    // the game grid was meant to go...

    //FINISHED: Draws 600 untouched cells 
    int row, col;    
    for (row =0; row < NUM_ROWS; row++) { 
      for (col = 0; col< NUM_COLS; col++) { 
        cells[row][col] = new Cell(MARGIN+Cell.SIZE*col, MARGIN+Cell.SIZE*row);
        add(cells[row][col]); 
      } 
    } 

  } // end game 

  /**
     * Get the number of mines that are deployed.
     */
  public int getNumMinesDeployed() {
    return numMines;
  }

  /**
     * Get the number of hidden cells remaining to be revealed.
     */
  public int getNumCellsRemaining() {
    return NUM_ROWS * NUM_COLS - numRevealed;
  }

  /**
     * Deploy the given number of mines. This gets called once during game
     * setup. The game doesn't actually begin officially until the user clicks
     * a cell, so the timer should not start yet.
     */
  public void deployMines(int mines) {
    // FINISHED: Add code here to deploy mines by calling makeMine() on some of
    // the cells, at random. Use StdRandom() for picking random numbers.
    // Each cell also has a variable to track how many of its neighboring
    // cells contain a mine, so you will need to update that variable too.
    // See the increementNeighborMineCount() and setNeighborMineCount()
    // functions in the Cell class, which are meant for this purpose

    //  picks random cells to plant mines 
    for (int i =0; i<mines; i++){
      numMines++; 
      int pickRow = StdRandom.uniform(0, NUM_ROWS-1); 
      int pickCol = StdRandom.uniform(0, NUM_COLS-1);
      if (!cells[pickRow][pickCol].isMine() ){
        cells[pickRow][pickCol].makeMine(); 


        // keeps track of and increments neighbor mine count 
        for (int a = pickRow-1; a <= pickRow+1; a++) { 
          for (int b = pickCol-1; b <= pickCol+1; b++){ 
            if (!(a == pickRow && b == pickCol)) { 
              if (a>=0 && a<NUM_ROWS && b>=0 && b<NUM_COLS){
                cells[a][b].incrementNeighborMineCount();
              } 
            }	
          } // end of col for 
        }// end fo row for 

      }
    } // end for 
  } // end deployMines 


  /**
     * Respond to a mouse click. This function will be called each time the user
     * clicks on the game window. The x, y parameters indicate the screen
     * coordinates where the user has clicked, and the button parameter
     * indicates which mouse button was clicked (either "left", "middle", or
     * "right"). The function should update the game state according to what the
     * user has clicked.
     * @param x the x coordinate where the user clicked, in pixels.
     * @param y the y coordinate where the user clicked, in pixels.
     * @param button either "left", "middle", or "right".
     */
  public void mouseClicked(double x, double y, String button) {
    // User clicked the mouse, see what they want to do.

    // If game is over, then ignore the mouse click.
    if (gameWon || gameLost)
      return;

    // If the user middle-clicked, ignore it.
    if (!button.equals("left") && !button.equals("right"))
      return;

    // If the user clicked outside of the game grid, ignore it.
    if (x < MARGIN || y < MARGIN
        || x >= MARGIN + GRID_WIDTH || y >= MARGIN + GRID_HEIGHT) {
      return;
    }

    // Calculate which cell the user clicked.
    int row = (int)((y - MARGIN) / Cell.SIZE);
    int col = (int)((x - MARGIN) / Cell.SIZE);

    // FINISHED: Add code here to react to mouse clicks.
    StdOut.printf("You clicked row %d column %d with button %s\n", row, col, button);

    // Reveals cell if user left clicks 
    if (button.equals("left") ) {

      // Checks if the left clicked cell was already revealed 
      if (!cells[row][col].isRevealed()) { 
        cells[row][col].reveal(); 
        cells[row][col].getNeighborMineCount(); 
        numRevealed++;
        if (numRevealed >=1 ){ 
          timer.startCounting(); 
        }


        // auto-reveals all empty cells 
        if (cells[row][col].getNeighborMineCount() == 0) { 
          for (int r =0; r < NUM_ROWS; r++) { 
            for (int c = 0; c< NUM_COLS; c++) { 
              if (cells[r][c].getNeighborMineCount() == 0){ 
                if (cells[r][c].isMine == false ){ 
                  cells[r][c].reveal(); 
                  numRevealed++; 
                } 
              } 
            } 
          }
        }

      } // ends if isRevealed 

      // if reveals mine, game will end 
      if (cells[row][col].isMine()){ 
        gameLost = true; 
        timer.stopCounting(); 

        // reveals all cells if game is lost 
        for (int r =0; r < NUM_ROWS; r++) { 
          for (int c = 0; c< NUM_COLS; c++) { 
            cells[r][c].reveal();  
            numRevealed = 600;  

            // Declares and prints label if user lost  
            Label lost = new Label(
              MARGIN + NUM_COLS * Cell.SIZE /2,
              MARGIN + NUM_ROWS * Cell.SIZE /2,
              "I'm sorry, but you lost. Better luck next time");
            lost.setFont("SansSerif Bold", 24);
            lost.setForegroundColor(Canvas.WHITE);
            lost.setBackgroundColor(Canvas.RED);
            lost.setBorderColor(Canvas.BLACK);
            add(lost);

          }
        }
      }

      // game is won if all cells other than mines are revealed 
      if ( getNumCellsRemaining() == numMines-1) { 
        gameWon = true; 
        timer.stopCounting(); 
        System.out.println("Won");

        // Declare and print winner's banner 
        Label won = new Label(
          MARGIN + NUM_COLS * Cell.SIZE /2,
          MARGIN + NUM_ROWS * Cell.SIZE /2,
          "Congrats! You cleared the board. ");
        won.setFont("SansSerif Bold", 30);
        won.setForegroundColor(Canvas.WHITE);
        won.setBackgroundColor(Canvas.GREEN);
        won.setBorderColor(Canvas.BLACK);
        add(won);

        for (int r =0; r < NUM_ROWS; r++) { 
          for (int c = 0; c< NUM_COLS; c++) { 
            cells[r][c].reveal();  
            numRevealed = 600; 
          }
        }

      }
    } // end of left click 

    if (button.equals("right")) { 
      // plants flag if user right clicks 
      if (!(cells[row][col].isRevealed()) && !(cells[row][col].isFlag())  ) { 
        cells[row][col].makeFlag(); 
        numFlags++; 
        // deletes flag if user reclicks flagged cell 
      }  else if (!(cells[row][col].isRevealed()) && cells[row][col].isFlag()) {
        cells[row][col].undoFlag();
        numFlags--; 
      }

    } // end of right click 

    return; 

  } // end of mouseclick 

  /**
     * Respond to key presses. This function will be called each time the user
     * presses a key. The parameter indicates the character the user pressed.
     * The function should update the game state according to what character the
     * user has pressed. 
     * @param c the character that was typed.
     */
  public void keyTyped(char c)
  {
    // User pressed a key, see what they want to do.
    switch (c) {
      case 'q': 
      case 'Q': 
        hide(); // user wants to quit
        break;
      default:
        break; // anything else is ignored
    }
  } // end keytyped

  /**
     * Paint the background for this window on the canvas. Don't call this
     * directly, it is called by the GUI system automatically. This function
     * should draw something on the canvas, if you like. Or the background can
     * be blank.
     * @param canvas the canvas on which to draw.
     */
  public void repaintWindowBackground(GUI.Canvas canvas) {

  }
  
} // end of Game  