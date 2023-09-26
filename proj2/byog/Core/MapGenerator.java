package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MapGenerator {

    private static int WIDTH;
    private static int HEIGHT;
    private static long SEED;
    private static Random RANDOM;
    private static ArrayList<Room> roomList;

    public static TETile[][] generate(String input,int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        SEED = Long.parseLong(input);
        RANDOM = new Random(SEED);
        roomList = new ArrayList<>();
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        fillNothing(world);

        int count = 30;
        for (int i = 0; i < count; i++) {
            Position p = new Position(RANDOM.nextInt(80), RANDOM.nextInt(20));
            int roomWidth =  RandomUtils.uniform(RANDOM,2,10);
            int roomHeight = RandomUtils.uniform(RANDOM,2,10);
            drawRoom(world, p, roomWidth, roomHeight);
        }
        Collections.sort(roomList);
        for (int i = 0; i < roomList.size() - 1; i++) {
            drawLHallway(world, randomPointInRoom(roomList.get(i)), randomPointInRoom(roomList.get(i + 1)));
        }

        return world;
    }

    //draw one room
    private static void drawRoom(TETile[][] tiles, Position p, int width, int height) {
        int outerWidth = width + 2;
        int outerHeight = height + 2;
        Room room = new Room(p, outerWidth, outerHeight);

        boolean flag = true;
        for (int i = 0; i < roomList.size(); i++) {
            if (room.overlap(roomList.get(i))) {
                flag = false;
                break;
            }
        }
        if (flag) {
            TETile tile;
            for (int i = p.x; i < p.x + outerWidth; i++) {
                for (int j = p.y; j < p.y + outerHeight; j++) {
                    if (i == p.x || i == p.x + outerWidth - 1 || j == p.y || j == p.y + outerHeight - 1) {
                        tile = Tileset.WALL;
                    } else {
                        tile = Tileset.FLOOR;
                    }
                    tiles[i][j] = tile;
                }
            }
            roomList.add(room);
        }
    }

    private static void drawRectangle(TETile[][] tiles, TETile tile, Position p, int width, int height) {
        for (int i = p.x; i < p.x + width; i++) {
            for (int j = p.y; j < p.y + height; j++) {
                tiles[i][j] = tile;
            }
        }
    }

    private static void drawHorizontalLine(TETile[][] tiles, TETile tile, Position p, int width) {
        for (int i = p.x; i < p.x + width + 1; i++) {
            if (tiles[i][p.y] != Tileset.FLOOR) {
                tiles[i][p.y] = tile;
            }
        }
    }

    private static void drawVerticalLine(TETile[][] tiles, TETile tile, Position p, int height) {
        for (int i = p.y; i < p.y + height; i++) {
            if (tiles[p.x][i] != Tileset.FLOOR) {
                tiles[p.x][i] = tile;
            }
        }
    }

    private static void drawLLine(TETile[][] tiles, TETile tile, Position p1, Position p2) {
        int width = p2.x - p1.x;
        int height = Math.abs(p1.y - p2.y);
        if (p1.y < p2.y) {
            drawHorizontalLine(tiles, tile, p1, width);
            drawVerticalLine(tiles, tile, new Position(p2.x, p1.y), height);
        } else if (p1.y > p2.y) {
            drawHorizontalLine(tiles, tile, p1, width);
            drawVerticalLine(tiles, tile, p2, height);
        } else {
            drawHorizontalLine(tiles, tile, p1, width);
        }
    }

    private static void drawLHallway(TETile[][] tiles, Position p1, Position p2) {
        TETile t = Tileset.FLOOR;
        drawLLine(tiles, t, p1, p2);
        t = Tileset.WALL;
        Position p3,p4,p5,p6;
        if (p1.y < p2.y) {
            p3 = new Position(p1.x,p1.y+1);
            p5 = new Position(p1.x,p1.y-1);
        }else{
            p3 = new Position(p1.x,p1.y-1);
            p5 = new Position(p1.x,p1.y+1);
        }
        p4 = new Position(p2.x-1,p2.y);
        p6 = new Position(p2.x+1,p2.y);
        drawLLine(tiles, t, p3, p4);
        drawLLine(tiles, t, p5, p6);
    }

    private static Position randomPointInRoom(Room r) {
        Position rPos = r.getP();
        int randX = RandomUtils.uniform(RANDOM,rPos.x + 1, rPos.x + r.getWidth() - 2);
        int randY = RandomUtils.uniform(RANDOM,rPos.y + 1, rPos.y + r.getHeight() - 2);
        return new Position(randX, randY);
    }

    private static void fillNothing(TETile[][] tiles) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }
}