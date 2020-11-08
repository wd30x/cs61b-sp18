package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 70;
    private static final int HEIGHT = 50;
    private static final long SEED = 1823914;
    private static final Random RANDOM = new Random(SEED);

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
        int dy1 = p.y + s - 1;
        int dy2 = p.y + s;
        int dx = p.x;
        int mid = p.x + 3 * s - 2;
        while (count > 0) {
            for (int x = dx; x < mid; x += 1) {
                world[x][dy1] = t;
                world[x][dy2] = t;
            }
            mid--;
            dx++;
            dy1--;
            dy2++;
            count--;
        }

    }

    public static Position bottomLeft(Position p, int s) {
        return new Position(p.x - (2 * s - 1), p.y - s);
    }

    public static Position bottomRight(Position p, int s) {
        return new Position(p.x + (2 * s - 1), p.y - s);
    }

    public static void drawRandomVerticalHexes(TETile[][] world, Position p, int s, int N) {
        TETile tile;
        while (N > 0) {
            tile = randomTile();
            addHexagon(world, p, s, tile);
            p.y = p.y - 2 * s;
            N--;
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0:
                return Tileset.WALL;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.GRASS;
            default:
                return Tileset.SAND;
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

        Position p = new Position(25, 40);
        drawRandomVerticalHexes(world, p, 3, 5);
        p = new Position(25, 40);
        Position pl = bottomLeft(p, 3);
        drawRandomVerticalHexes(world, pl, 3, 4);
        pl = bottomLeft(p, 3);
        drawRandomVerticalHexes(world, bottomLeft(pl, 3), 3, 3);

        p = new Position(25, 40);
        Position pr = bottomRight(p, 3);
        drawRandomVerticalHexes(world, pr, 3, 4);
        pr = bottomRight(p, 3);
        drawRandomVerticalHexes(world, bottomRight(pr, 3), 3, 3);

        ter.renderFrame(world);
    }
}
