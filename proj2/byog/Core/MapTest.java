package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class MapTest {
    private static final int WIDTH = 90;
    private static final int HEIGHT = 30;
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = MapGenerator.generate("123",WIDTH,HEIGHT);
        ter.renderFrame(world);
    }
}
