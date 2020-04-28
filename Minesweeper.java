/*----------------------------------------------------------------
 *  Author:   K. Walsh
 *  Email:    kwalsh@holycross.edu
 *  Written:  7/13/2015
 *  Update:
 *  
 *  A game of minesweeper. This class only really holds the
 *  main() fucntion. Most of the interesting stuff happens in
 *  the Game and Cell classes.
 *
 *  Example: java Minesweeper # This runs a fresh, randomized game
 *
 *  Example: java Minesweeper 1234 # This repeats the game with seed 1234
 *
 *----------------------------------------------------------------*/

public class Minesweeper {


    /**
     * The main() program for the minesweeper game. It takes one optional
     * parameter, an integer seed used for randomizing the mines. If you use the
     * same seed twice, you should get the exact same deployment of mines.
     */
    public static void main(String args[]) {

        // Print a customized welcome message.
        String username = System.getenv("USER");
        StdOut.println("--=== Welcome to Minesweeper, " + username + "! ===--");

        // Initialize the random number generator. We either use a random seed
        // provided as an optional command-line argument, or we print out the
        // seed being used so the user can play the exact same game again if
        // they like.
        int seed;
        if (args.length == 0) {
            seed = StdRandom.uniform(1000, 9999);
            System.out.println("The seed for this game is: " + seed);
            System.out.println("If you wish to play this exact same game");
            System.out.println("again, use this command: java Minesweeper " + seed); 
        } else {
            seed = Integer.parseInt(args[0]);
            System.out.println("You are re-playing the game with seed: " + seed);
        }

        // Create the "splash" title screen, and wait for the user to make a
        // selection.
        TitleScreen t = new TitleScreen();
        t.showAndAnimate(1.0);

        // If the user wants to quit, do so.
        String selection = t.getSelection();
        if (selection.equals("quit")) {
            System.out.println("I'm sorry you don't want to play. Maybe later?");
            return;
        }

        StdRandom.setSeed(seed);

        // Create a game, and deploy the correct number of mines.
        Game game = new Game(username, selection);

        StdRandom.setSeed(seed);

        if (selection.equals("hard"))
            game.deployMines(100);
        else if (selection.equals("medium"))
            game.deployMines(20);
        else if (selection.equals("easy"))
            game.deployMines(5);
        else
            game.deployMines(StdRandom.uniform(5, 100));

        // Wait for the user to play the game. We turn on animation so the timer
        // display is updated properly.
        game.showAndAnimate(10);
    }

}
