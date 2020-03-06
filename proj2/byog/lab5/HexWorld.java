package byog.lab5;

import javafx.geometry.Pos;
import org.junit.Test;

import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 10;

    static class Position {
        int x;
        int y;

        public Position(int X, int Y) {
            x = X;
            y = Y;
        }
    }

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        int count = s;
        int dy = p.y + s - 1;
        int dx = p.x;
        int mid = 3 * s - 2;
        while (count > 0) {
            for (int x = dx; x < mid; x += 1) {
                world[x][dy] = t;
            }
            mid--;
            dx++;
            dy--;
            count--;
        }

        count = s;
        dy = p.y + s;
        dx = p.x;
        mid = 3 * s - 2;
        while (count > 0) {
            for (int x = dx; x < mid; x += 1) {
                world[x][dy] = t;
            }
            mid--;
            dx++;
            dy++;
            count--;
        }

    }

    public static void fillNothing(TETile[][] tiles) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        //fill
        fillNothing(world);

        Position p = new Position(0, 0);
        addHexagon(world, p, 3, Tileset.WALL);

        ter.renderFrame(world);
    }
}
