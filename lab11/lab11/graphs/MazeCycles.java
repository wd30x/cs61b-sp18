package lab11.graphs;

/**
 * @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private Maze maze;
    private boolean cycleFound = false;
    private boolean cycleVertexFound = false;
    private int cycleVertex;
    private int gprev;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1, 1);
        edgeTo[s] = s;
    }

    @Override
    public void solve() {
        dfs(s);
    }

    private void dfs(int v) {
        marked[v] = true;
        announce();
        if (cycleFound) {
            return;
        }
        int prev = gprev;
        for (int w : maze.adj(v)) {
            if (marked[w] && prev != w) {
                edgeTo[w] = v;
                cycleVertex = w;
                cycleFound = true;
                announce();
                return;
            }
            if (!marked[w]) {
                distTo[w] = w;
                edgeTo[w] = Integer.MAX_VALUE;
                gprev = v;
                announce();
                dfs(w);
                if (cycleFound && (!cycleVertexFound)) {
                    edgeTo[w] = v;
                    announce();
                    if (v == cycleVertex) {
                        cycleVertexFound = true;
                    }
                    return;
                }
                if (cycleVertexFound) {
                    return;
                }
            }
        }
    }
}

