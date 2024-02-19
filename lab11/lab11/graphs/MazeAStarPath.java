package lab11.graphs;

import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /**
     * Estimate of the distance from v to the target.
     */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t)) +
                Math.abs(maze.toY(v) - maze.toY(t));
    }

    /**
     * Performs an A star search from vertex s.
     */
    private void astar(int s) {
        IndexMinPQ<Integer> pq = new IndexMinPQ<>(300);
        for (int i = 0; i < maze.V(); i++) {
            if (i == s) {
                pq.insert(s, 0);
            } else {
                pq.insert(i, Integer.MAX_VALUE);
                distTo[i] = Integer.MAX_VALUE;
            }
        }
        marked[s] = true;
        announce();

        while (!pq.isEmpty()) {
            int p = pq.delMin();
            marked[p] = true;
            for (int v : maze.adj(p)) {
                if (distTo[p] + 1 < distTo[v]) {
                    distTo[v] = distTo[p] + 1;
                    edgeTo[v] = p;
                    pq.changeKey(v, distTo[v] + h(v));
                }
                announce();
                if (v == t) {
                    marked[v] = true;
                    announce();
                    return;
                }
            }
        }


    }

    @Override
    public void solve() {
        astar(s);
    }

}

