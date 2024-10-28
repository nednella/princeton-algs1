/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double confidence95;
    private int siteCount;
    private int trials;
    private double[] openSiteFraction;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and T must be positive integers.");
        }

        this.confidence95 = 1.96;
        this.siteCount = n * n;
        this.trials = trials;
        this.openSiteFraction = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);

            while (!perc.percolates()) {
                int p = StdRandom.uniformInt(1, n + 1);
                int q = StdRandom.uniformInt(1, n + 1);

                if (!perc.isOpen(p, q)) {
                    perc.open(p, q);
                }
            }

            this.openSiteFraction[i] = (double) perc.numberOfOpenSites() / this.siteCount;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.openSiteFraction);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.openSiteFraction);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - ((this.confidence95 * this.stddev()) / Math.sqrt(this.trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + ((this.confidence95 * this.stddev()) / Math.sqrt(this.trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, t);

        String confidence = stats.confidenceLo() + ", " + stats.confidenceHi();
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + confidence + "]");
    }

}
