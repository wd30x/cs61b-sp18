package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double mean;
    private double stddev;
    private double low;
    private double high;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        if (T == 1) {
            //The sample standard deviation is undefined
            stddev = Double.NaN;
        }
        Percolation p;
        int row, col;
        double fraction;
        double[] data = new double[T];
        for (int i = 0; i < T; i++) {
            p = pf.make(N);
            while (!p.percolates()) {
                row = StdRandom.uniform(0, N);
                col = StdRandom.uniform(0, N);
                while (p.isOpen(row, col)) {
                    row = StdRandom.uniform(0, N);
                    col = StdRandom.uniform(0, N);
                }
                p.open(row, col);
            }
            fraction = p.numberOfOpenSites() / (double) (N * N);
            data[i] = fraction;
        }
        this.mean = StdStats.mean(data);
        this.stddev = StdStats.stddev(data);
        low = mean - (1.96 * stddev) / Math.sqrt(T);
        high = mean + (1.96 * stddev) / Math.sqrt(T);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return low;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return high;
    }
}
