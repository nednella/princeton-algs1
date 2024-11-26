/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int size;
    private final int virtualTop;
    private final int virtualBottom;
    private boolean[] grid; // 0 = closed, 1 = open
    private WeightedQuickUnionUF gridMap;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be a positive integer.");
        }

        this.size = n;
        this.virtualTop = 0;
        this.virtualBottom = (n * n) + 1;
        this.grid = new boolean[n * n]; // model grid as a 1-d array
        this.gridMap = new WeightedQuickUnionUF((n * n) + 2); // account for virtual sites sites

        for (int i = 0; i < this.grid.length; i++) {
            this.grid[i] = false;
        }
    }

    // returns index value for the 1-d array given a row & col (1 - n)
    private int getIndex(final int row, final int col) {
        return (row - 1) * this.size + (col - 1);
    }

    // returns a boolean indicating if the given row & col is within bounds of the 2-d grid
    private boolean validArguments(final int row, final int col) {
        return row >= 1 && row <= this.size && col >= 1 && col <= this.size;
    }

    // opens the site (row, col) if it is not open already
    public void open(final int row, final int col) {
        if (!validArguments(row, col)) {
            throw new IllegalArgumentException("Argument(s) outside of prescribed range.");
        }

        if (this.isOpen(row, col)) {
            return;
        }

        int selectedIndex = getIndex(row, col);
        this.grid[selectedIndex] = true; // open indexed site
        this.openSites++;

        // if element is on first or last row, create a union with relevant virtual site
        if (row == 1) {
            this.gridMap.union(selectedIndex + 1, virtualTop);
        }
        if (row == this.size) {
            this.gridMap.union(selectedIndex + 1, virtualBottom);
        }

        // top neighbour
        if (validArguments(row - 1, col) && isOpen(row - 1, col)) {
            this.gridMap.union(selectedIndex + 1, getIndex(row - 1, col) + 1);
        }

        // left neighbour
        if (validArguments(row, col - 1) && isOpen(row, col - 1)) {
            this.gridMap.union(selectedIndex + 1, getIndex(row, col - 1) + 1);
        }

        // right neighbour
        if (validArguments(row, col + 1) && isOpen(row, col + 1)) {
            this.gridMap.union(selectedIndex + 1, getIndex(row, col + 1) + 1);
        }

        // bottom neighbour
        if (validArguments(row + 1, col) && isOpen(row + 1, col)) {
            this.gridMap.union(selectedIndex + 1, getIndex(row + 1, col) + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(final int row, final int col) {
        if (!validArguments(row, col)) {
            throw new IllegalArgumentException("Argument(s) outside of prescribed range.");
        }

        return this.grid[getIndex(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(final int row, final int col) {
        if (!validArguments(row, col)) {
            throw new IllegalArgumentException("Argument(s) outside of prescribed range.");
        }

        return this.gridMap.find(this.virtualTop) == this.gridMap.find(getIndex(row, col) + 1);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.gridMap.find(this.virtualTop) == this.gridMap.find(this.virtualBottom);
    }

    // test client (optional)
    public static void main(String[] args) {

    }

}

