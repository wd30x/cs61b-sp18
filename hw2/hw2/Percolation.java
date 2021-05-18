package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int numOpen;
    private WeightedQuickUnionUF uf;    //top and bottom site
    private WeightedQuickUnionUF uf2;   //top site
    private boolean[] grid;
    private int topIndex;
    private int botIndex;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        numOpen = 0;
        uf = new WeightedQuickUnionUF(N * N + 2);
        uf2 = new WeightedQuickUnionUF(N * N + 1);
        grid = new boolean[N * N + 2];
        topIndex = N * N;
        botIndex = topIndex + 1;
        for (int i = 0; i < N; i++) {
            uf.union(i, topIndex);
            uf2.union(i, topIndex);
        }
        for (int i = N * N - N; i < N * N; i++) {
            uf.union(i, botIndex);
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        int index = xyTo1D(row, col);
        if (!grid[index]) {
            grid[index] = true;
            if (col - 1 >= 0 && isOpen(row, col - 1)) {
                uf.union(index, index - 1);
                uf2.union(index, index - 1);
            }
            if (col + 1 < N && isOpen(row, col + 1)) {
                uf.union(index, index + 1);
                uf2.union(index, index + 1);
            }
            if (row - 1 >= 0 && isOpen(row - 1, col)) {
                uf.union(index, index - N);
                uf2.union(index, index - N);
            }
            if (row + 1 < N && isOpen(row + 1, col)) {
                uf.union(index, index + N);
                uf2.union(index, index + N);
            }
            numOpen++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int index = xyTo1D(row, col);
        return grid[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int index = xyTo1D(row, col);
        return uf2.connected(index, topIndex) && isOpen(row, col);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        if (N == 1) {
            return isOpen(0, 0);
        }
        return uf.connected(topIndex, botIndex);
    }

    // use for unit testing (not required)
    public static void main(String[] args) {
        Percolation p = new Percolation(1);
    }

    // convert x,y coordinates to 1d number
    private int xyTo1D(int row, int col) {
        return row * N + col;
    }
}
