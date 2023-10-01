package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;

    private static final int midWidth = WIDTH / 2;
    private static final int midHeight = HEIGHT / 2;
    private static final int quaHeight = midHeight / 2;
    private static long seed;
    private static TETile[][] world;

    /**
     * Game constructor
     */
    public Game() {
        StdDraw.setCanvasSize(WIDTH * 16, HEIGHT * 16);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        //draw main menu
        drawMainMenu();
        //user type command
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char cmd = StdDraw.nextKeyTyped();
                //N: prompt user to enter seed
                if (cmd == 'N') {
                    promptSeed();
                    world = MapGenerator.generate(seed, WIDTH, HEIGHT);
                    ter.initialize(WIDTH, HEIGHT);
                } else if (cmd == 'L') {
                    //load game
//                    world = loadGame();
                } else if (cmd == ':') {
                    while (true) {
                        if (StdDraw.hasNextKeyTyped() && StdDraw.nextKeyTyped() == 'Q') {
                            // save and quit game
                            saveGame();
                            System.exit(0);
                        }
                    }
                }
            }
            if (world != null) {
                ter.renderFrame(world);
                interact();
            }
        }

    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        //TODO: need more design
        input = input.substring(1, input.length() - 1);
        seed = Long.parseLong(input);
        world = MapGenerator.generate(seed, WIDTH, HEIGHT);
        return world;
    }

    /**
     * Draw main menu
     */
    private static void drawMainMenu() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font font = new Font("Monaco", Font.BOLD, 80);
        StdDraw.setFont(font);
        StdDraw.text(midWidth, quaHeight + midHeight, "CS61B: THE GAME");
        font = new Font("Monaco", Font.BOLD, 50);
        StdDraw.setFont(font);
        //draw
        StdDraw.text(midWidth, midHeight, "New Game (N)");
        StdDraw.text(midWidth, midHeight - 3, "Load Game (L)");
        StdDraw.text(midWidth, midHeight - 6, "Quit (Q)");
        StdDraw.show();
    }

    /**
     * Load game from file
     */
    private void loadGame() {

    }

    /**
     * Save game to file
     */
    private void saveGame() {

    }

    /**
     * Interact with world
     */
    private void interact() {

    }

    /**
     * draw and user should be prompted to enter a seed
     */
    private void promptSeed() {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                if (c == 'S') {
                    seed = Long.parseLong(sb.toString());
                    break;
                }
                sb.append(c);
            }
            drawSeedFrame(sb.toString());
        }
    }

    private void drawSeedFrame(String s) {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font font = new Font("Monaco", Font.BOLD, 60);
        StdDraw.setFont(font);
        StdDraw.text(midWidth, quaHeight + midHeight, "Please enter your seed:");
        StdDraw.text(midWidth, quaHeight + midHeight - 5, s);
        StdDraw.show();
    }
}
