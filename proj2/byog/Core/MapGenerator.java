package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MapGenerator {

    private static int mapWidth;
    private static int mapHeight;
    private static long seed;
    private static Random rand;
    private static ArrayList<Room> roomList;
    private static TETile[][] world;

    public static TETile[][] generate(long Seed, int Width, int Height) {
        seed = Seed;
        mapWidth = Width;
        mapHeight = Height;
        rand = new Random(seed);
        roomList = new ArrayList<>();
        world = new TETile[mapWidth][mapHeight];
        fillNothing(world);

        int count = RandomUtils.uniform(rand, 40, 50);
        for (int i = 0; i < count; i++) {
            Position p = new Position(rand.nextInt(100), rand.nextInt(100));
            int roomWidth = RandomUtils.uniform(rand, 2, 10);
            int roomHeight = RandomUtils.uniform(rand, 2, 10);
            drawRoom(world, p, roomWidth, roomHeight);
        }
        Collections.sort(roomList);
        for (int i = 0; i < roomList.size() - 1; i++) {
            drawLHallway(world, randomPointInRoom(roomList.get(i)), randomPointInRoom(roomList.get(i + 1)));
        }

        //generate character and door
        Position p = randomPointInRoom(roomList.get(0));
        world[p.x][p.y] = Tileset.PLAYER;
        p = randomPointInWall(roomList.get(0));
        world[p.x][p.y] = Tileset.LOCKED_DOOR;
        return world;
    }

    //draw one room
    private static void drawRoom(TETile[][] tiles, Position p, int width, int height) {
        int outerWidth = width + 2;
        int outerHeight = height + 2;
        Room room = new Room(p, outerWidth, outerHeight);

        boolean flag = true;
        //if rooms overlap
        for (int i = 0; i < roomList.size(); i++) {
            if (room.overlap(roomList.get(i))) {
                flag = false;
                break;
            }
        }
        //if border is exceeded
        if (p.x + outerWidth > mapWidth || p.y + outerHeight > mapHeight) {
            flag = false;
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
        Position p3, p4, p5, p6;
        if (p1.y < p2.y) {
            p3 = new Position(p1.x, p1.y + 1);
            p5 = new Position(p1.x, p1.y - 1);
        } else {
            p3 = new Position(p1.x, p1.y - 1);
            p5 = new Position(p1.x, p1.y + 1);
        }
        p4 = new Position(p2.x - 1, p2.y);
        p6 = new Position(p2.x + 1, p2.y);
        drawLLine(tiles, t, p3, p4);
        drawLLine(tiles, t, p5, p6);
    }

    private static Position randomPointInRoom(Room r) {
        Position rPos = r.getP();
        int randX = RandomUtils.uniform(rand, rPos.x + 1, rPos.x + r.getWidth() - 2);
        int randY = RandomUtils.uniform(rand, rPos.y + 1, rPos.y + r.getHeight() - 2);
        return new Position(randX, randY);
    }

    private static Position randomPointInWall(Room r) {
        Position rPos = r.getP();
        int randX = 0;
        int randY = 0;
        while (!world[randX][randY].equals(Tileset.WALL)) {
            randX = RandomUtils.uniform(rand, rPos.x, rPos.x + r.getWidth() - 1);
            randY = RandomUtils.uniform(rand, rPos.y, rPos.y + r.getHeight() - 1);
        }
        return new Position(randX, randY);
    }

    private static void fillNothing(TETile[][] tiles) {
        for (int x = 0; x < mapWidth; x += 1) {
            for (int y = 0; y < mapHeight; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }
}