package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import javax.print.DocFlavor;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private int index;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
            "You got this!", "You're a star!", "Go Bears!",
            "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);

        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
        
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 25);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        StringBuilder s = new StringBuilder();
        while (n > 0) {
            int index = rand.nextInt(25);
            s.append(CHARACTERS[index]);
            n--;
        }
        return s.toString();
    }

    public void drawFrame(String s) {
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 25);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(this.width / 2.0, this.height / 2.0, s);
        StdDraw.show();
        drawInfo();
    }

    public void drawInfo() {
        if (!gameOver) {
            StdDraw.setPenRadius(0.005);
            StdDraw.line(0, 37, 40, 37);
            StdDraw.text(4, 38, "Round: " + round);
            if (playerTurn) {
                StdDraw.text(20, 38, "Type!");
            } else {
                StdDraw.text(20, 38, "Watch!");
            }
            StdDraw.text(34, 38, ENCOURAGEMENT[index]);
            StdDraw.show();
        }
    }

    public void flashSequence(String letters) {
        try {
            System.out.println(letters);
            for (int i = 0; i < letters.length(); i++) {
                drawFrame(String.valueOf(letters.charAt(i)));
                Thread.sleep(1000);
                StdDraw.clear(Color.BLACK);
                drawInfo();
                StdDraw.show();
                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String solicitNCharsInput(int n) {
        playerTurn = true;
        StringBuilder s = new StringBuilder();
        StdDraw.clear(Color.BLACK);
        drawInfo();
        while (n > 0) {
            if (StdDraw.hasNextKeyTyped()) {
                s.append(StdDraw.nextKeyTyped());
                System.out.println(s);

                drawFrame(s.toString());
                n--;
            }
        }
        playerTurn = false;
        return s.toString();
    }

    public void startGame() {
        try {
            StringBuilder sb = new StringBuilder();
            String s = "";
            round = 1;
            index = rand.nextInt(6);
            while (!gameOver) {
                drawFrame("Round: " + round);
                Thread.sleep(1000);
                sb.append(generateRandomString(1));
                s = sb.toString();
                flashSequence(s);
                String playerInput = solicitNCharsInput(round);
                if (!playerInput.equals(s)) {
                    gameOver = true;
                    break;
                }
                Thread.sleep(500);
                drawFrame("Correct!");
                Thread.sleep(1000);
                round++;
            }
            drawFrame("Game Over! You made it to round:" + round);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
