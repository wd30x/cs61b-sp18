package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.*;

public class Game implements Serializable {
    transient TERenderer ter = new TERenderer();
    private static final long serialVersionUID = 123123123123123L;
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;
    private static final int midWidth = WIDTH / 2;
    private static final int midHeight = HEIGHT / 2;
    private static final int quaHeight = midHeight / 2;
    private long seed;
    private TETile[][] world;
    private boolean gameOver;
    private Position playerPos;
    private Position doorPos;
    private TETile hidden = Tileset.FLOOR;

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
                // N: prompt user to enter seed
                if (cmd == 'N') {
                    promptSeed();
                    world = MapGenerator.generate(seed, WIDTH, HEIGHT);
                    ter.initialize(WIDTH, HEIGHT + 5);
                } else if (cmd == 'L') {
                    // L: load game from saved file
                    loadGame();
                }
                quit(cmd);
            }
            if (world != null) {
                ter.renderFrame(world);
                interact();
                mouseHover();
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
        File f = new File("./game.ser");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                Game loadGame = (Game) os.readObject();
                os.close();

                //load saved game state
                this.seed = loadGame.seed;
                this.world = loadGame.world;
                this.gameOver = loadGame.gameOver;
                this.playerPos = loadGame.playerPos;
                this.doorPos = loadGame.doorPos;
                this.hidden = loadGame.hidden;
                ter.initialize(WIDTH, HEIGHT + 5);
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }
    }

    /**
     * Save game to file
     */
    private void saveGame() {
        File f = new File("./game.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(this);
            os.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    private void quit(char cmd) {
        if (cmd == ':') {
            while (true) {
                if (StdDraw.hasNextKeyTyped() && StdDraw.nextKeyTyped() == 'Q') {
                    // :Q: save and quit game
                    saveGame();
                    System.exit(0);
                }
            }
        }
    }

    /**
     * Interact with world: WSAD or Enter
     */
    private void interact() {
        initializePosition();
        if (StdDraw.hasNextKeyTyped()) {

            char cmd = StdDraw.nextKeyTyped();
            quit(cmd);
            move(cmd);
        }
    }

    /**
     * Character move
     */
    private void move(char c) {
        int x = (c == 'A' ? -1 : (c == 'D' ? 1 : 0));
        int y = (c == 'S' ? -1 : (c == 'W' ? 1 : 0));
        if (!world[playerPos.x + x][playerPos.y + y].equals(Tileset.WALL)) {
            world[playerPos.x][playerPos.y] = hidden;
            playerPos.x += x;
            playerPos.y += y;
            hidden = world[playerPos.x][playerPos.y];
            world[playerPos.x][playerPos.y] = Tileset.PLAYER;
        }
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

    /**
     * display description when mouse hovers on tiles
     */
    private void mouseHover() {

    }

    /**
     * draw seed on the screen
     *
     * @param s
     */
    private void drawSeedFrame(String s) {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font font = new Font("Monaco", Font.BOLD, 60);
        StdDraw.setFont(font);
        StdDraw.text(midWidth, quaHeight + midHeight, "Please enter your seed:");
        StdDraw.text(midWidth, quaHeight + midHeight - 5, s);
        StdDraw.show();
    }

    private void initializePosition() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (world[i][j].equals(Tileset.PLAYER)) {
                    playerPos = new Position(i, j);
                }
                if (world[i][j].equals(Tileset.UNLOCKED_DOOR)) {
                    doorPos = new Position(i, j);
                }
            }
        }
    }
}
