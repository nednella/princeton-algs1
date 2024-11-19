/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private final int n;
    private final int[] board;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.n = tiles.length;
        if (n < 2 || n > 128) {
            throw new IllegalArgumentException("N must be a positive integer 2 <= N <= 128.");
        }

        this.board = flatten(tiles);
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.n + "\n");
        for (int i = 0; i < board.length; i++) {
            s.append(String.format("%2d ", this.board[i]));
            if ((i + 1) % n == 0) {
                s.append("\n");
            }
        }

        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // return the hamming distance of a board
    public int hamming() {
        int hamming = 0;

        for (int i = 0; i < board.length; i++) {
            int tile = board[i];
            int expectedTile = i + 1;
            if (tile == 0) continue; // do not compute "empty" tile
            if (tile != expectedTile) hamming++;
        }

        return hamming;
    }

    // return the sum of the manhattan distances of a board
    public int manhattan() {
        int manhattan = 0;

        for (int i = 0; i < board.length; i++) {
            int tile = board[i];
            int expectedTile = i + 1;
            if (tile == 0 || tile == expectedTile)
                continue; // do not compute "empty" or correct tiles

            int row = i / n;
            int col = i % n;
            int correctRow = (tile - 1) / n;
            int correctCol = (tile - 1) % n;
            manhattan += Math.abs(row - correctRow) + Math.abs(col - correctCol);
        }

        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        return Arrays.equals(this.board, that.board);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> boards = new ArrayList<>();

        // map 1-d array index of empty tile to 2-d matrix
        int empty = getEmptyIndex();
        int row = empty / n;
        int col = empty % n;

        // matrix of modifiers to determine valid neighbours
        int[][] neighbours = {
                { -1, 0 }, // top
                { 1, 0 }, // bottom
                { 0, -1 }, // left
                { 0, 1 } // right
        };

        for (int[] neighbour : neighbours) {
            // obtain modified position
            int neighbourRow = row + neighbour[0];
            int neighbourCol = col + neighbour[1];

            if (isValidNeighbour(neighbourRow, neighbourCol)) {
                // create a duplicate board and swap the empty tile with each valid neighbour
                int[] arr = getCopyOf(board);
                int neighbourIdx = (neighbourRow * n) + neighbourCol;
                swap(arr, empty, neighbourIdx);
                boards.add(new Board(reshape(arr)));
            }
        }

        return boards;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[] copy = getCopyOf(board);
        int i = getFirstNonEmptyIndex();
        int j = getFirstNonEmptyIndexExcluding(i);
        swap(copy, i, j);
        return new Board(reshape(copy));
    }

    // flatten 2-d matrix into 1-d array
    private int[] flatten(int[][] matrix) {
        int[] array = new int[n * n];

        for (int i = 0; i < array.length; i++) {
            int row = i / n;
            int col = i % n;
            array[i] = matrix[row][col];
        }

        return array;
    }

    // reshape 1-d array into 2-d matrix
    private int[][] reshape(int[] array) {
        int[][] matrix = new int[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                matrix[row][col] = array[(n * row) + col];
            }
        }

        return matrix;
    }

    // create a copy of the supplied array
    private int[] getCopyOf(int[] array) {
        return Arrays.copyOf(array, array.length);
    }

    // swap values in the array at index i and j
    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    // return index of the "empty" tile
    private int getEmptyIndex() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) return i;
        }

        return -1;
    }

    // is the element at the supplied (row, col) in bounds of the 2-d matrix
    private boolean isValidNeighbour(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    // return the first non-zero element in the array
    private int getFirstNonEmptyIndex() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] != 0) return i;
        }

        return -1;
    }

    // return the first non-zero element in the array, excluding the supplied index
    private int getFirstNonEmptyIndexExcluding(int exl) {
        for (int i = 0; i < board.length; i++) {
            if (board[i] != 0 && i != exl) return i;
        }

        return -1;
    }

    // unit testing
    public static void main(String[] args) {

    }

}
