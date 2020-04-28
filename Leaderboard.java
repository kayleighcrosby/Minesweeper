/*----------------------------------------------------------------
 *  Author:   K. Walsh
 *  Email:    kwalsh@holycross.edu
 *  Written:  7/13/2015
 *  Update:
 *  
 *  Leaderboard keeps track of and displays a list of top scores.
 *
 *  You can test this class using:
 *    java Leaderboard
 *----------------------------------------------------------------*/

import GUI.*;
import java.awt.Color;

/**
 * A LeaderBoard object keeps track of the top few names and scores for each
 * difficulty level. It can draw itself in a popup window or print to the
 * console.
 */
public class Leaderboard extends Window {

    /**
     * The maximum number of top scores we should keep. The worst (largest)
     * scores are dropped to ensure that no more than this number are ever
     * displayed or stored.
     */
    public static final int MAX_NUM_SCORES = 10;

    /**
     * The width of the window as it is shown on the screen.
     * This is three columns, each 200 pixels wide.
     */
    public static final int WIDTH = 600;

    /**
     * The height of the window as it is shown on the screen.
     */
    public static final int HEIGHT = 450;

    // TODO: Add member variables here as needed, e.g. for the head of the
    // linked list or for an array or several arrays. You can keep separate
    // lists for each difficulty level, "easy", "medium", and "hard". Or you
    // could just ignore difficulty levels and lump everyone in the same top
    // scores list.
    //
    // Depending on how you want to keep track of the top names and scores, you
    // might want to define a new class, e.g. PlayerScore, to hold information
    // about each score, or to create a linked list. Create those classes in
    // their own files, then add member variables here that use them.

    /**
     * Initialize a new leaderboard. This does not load any data from the saved
     * file, so the caller should call load() next.
     */
    public Leaderboard() {
        super("Minesweeper Leaderboard", WIDTH, HEIGHT);
        // TODO: Add code here to initialize member variables as needed.
    }


    /**
     * Load top scores from leaderboard.dat
     */
    public void load() {
        In input = new In("leaderboard.dat");
        while (input.hasNextLine()) {
            String line = input.readLine();
            String[] parts = line.split(" ", 3);
            String difficulty = parts[0];
            int score = Integer.parseInt(parts[1]);
            String name = parts[2];
            insert(difficulty, name, score);
        }
    }

    /**
     * Save top scores to leaderboard.dat
     */
    public void save() {
        Out out = new Out("leaderboard.dat");
        String[] levels = { "easy", "medium", "hard" };
        for (String level : levels) {
            int n = getNumPlayerScores(level);
            for (int i = 0; i < n; i++) {
                int score = getPlayerScore(level, i);
                String name = getPlayerName(level, i);
                // Ignore anything that is blank. Should not
                // happen, but just in case...
                if (score >= 0 && name != null && name.length() > 0) {
                    out.printf("%s %d %s\n", level, score, name);
                }
            }
            out.close();
        }
    }

    /**
     * Insert the given name and score, if it is good enough to make the list.
     * This also returns the position of the newly inserted name, where 0 is
     * the best score, 1 is the next best, etc. If the score was not
     * inserted for any reason (e.g. if the list was full and the score was
     * too poor), this function returns -1 instead.
     * @param difficulty the difficulty level
     * @param name the user's name
     * @param score the user's score
     * @return the ranking of the new score, or -1 if the score didn't make
     * the list.
     */
    public int insert(String difficulty, String name, int score) {
        // TODO: Add code here to insert the name and score into your linked
        // list, array, or whatever. You can ignore the difficulty level. Or you
        // can keep track of it in order to have different top score lists for
        // each level. Or maybe penalize "easy" players?
        return -1; // For now, just return -1 to indicate failure (delete me!)
    }

    /**
     * Get the number of player scores in the list.
     * @param difficulty the difficulty level
     * @return the number of scores known at that difficulty level
     */
    public int getNumPlayerScores(String difficulty) {
        return 0; // TODO: Replace this code with something sensible.
    }

    /**
     * Get the i^th player score in the list. If no such score exists, then
     * instead return -1 instead.
     * @param difficulty the difficulty level
     * @return the list position to get, counting from zero.
     * @return the score at position i in the list for that difficulty
     * level, or -1 if no such position exists.
     */
    public int getPlayerScore(String difficulty, int i) {
        return -1; // TODO: Replace this code with something sensible.
    }

    /**
     * Get the i^th player name in the list. If no such name exists, then
     * instead return null instead.
     * @param difficulty the difficulty level
     * @return the list position to get, counting from zero.
     * @return the score at position i in the list for that difficulty
     * level, or null if no such position exists.
     */
    public String getPlayerName(String difficulty, int i) {
        return null; // TODO: Replace this code with something sensible.
    }

    // Print all of the top scores to the console, with a nice header.
    public void print() {
        int numEasy = getNumPlayerScores("easy");
        int numMedium = getNumPlayerScores("medium");
        int numHard = getNumPlayerScores("hard");

        StdOut.printf("   --======================= Top Scores =======================--\n");
        StdOut.printf("   --== Easy ==--          --== Medium ==--        --== Hard ==--\n");
        //             SCORE NAME(14)........  SCORE NAME(14)........  SCORE NAME(14)........
        for (int i = 0; i < MAX_NUM_SCORES; i++) {
            StdOut.printf("%2d.", (i+1)); // line number
            if (i < numEasy)
                StdOut.printf("%5d %-14s  ", getPlayerScore("easy", i), getPlayerName("easy", i));
            else
                StdOut.printf("   -  %-14s  ", "  -");
            if (i < numMedium)
                StdOut.printf("%5d %-14s  ", getPlayerScore("medium", i), getPlayerName("medium", i));
            else
                StdOut.printf("   -  %-14s  ", "  -");
            if (i < numHard)
                StdOut.printf("%5d %-14s\n ", getPlayerScore("hard", i), getPlayerName("hard", i));
            else
                StdOut.printf("   -  %-14s\n", "  -");
        }
    }

    /**
     * Paint the leaderboard background on a canvas. Don't call this directly,
     * it is called by the GUI system automatically. 
     * @param canvas the canvas on which to draw.
     */
    public void repaintWindowBackground(Canvas canvas) {
        // TODO: If you want a graphical leaderboard, add code here to draw the
        // leaderboard background. Or add code in the constructor to add widgets
        // like labels, boxes, buttons, etc. Or use a combination of a
        // background with some widgets.
    }

    /**
     * A main() function, for testing.
     */
    public static void main(String args[]) {
        Leaderboard b = new Leaderboard();
        b.load();
        b.insert("medium", "test", 550);
        b.save();
        b.print();
        b.showAndWait();
    }
}
