/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {

    private class SearchNode implements Comparable<SearchNode> {

        private Board board;
        private int priority;
        private int moves;
        private SearchNode previousNode;


        public SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.priority = board.manhattan() + moves;
            this.moves = moves;
            this.previousNode = previous;
        }

        public Board getBoard() {
            return board;
        }

        public int getMoves() {
            return moves;
        }

        public boolean hasPrevious() {
            return previousNode != null;
        }

        public SearchNode getPrevious() {
            return previousNode;
        }

        public int compareTo(SearchNode that) {
            return Integer.compare(this.priority, that.priority);
        }

    }

    private Board initialBoard;
    private int minimumMoves;
    private SearchNode goalNode;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Argument to the constructor is null.");
        }

        this.initialBoard = initial;
        this.minimumMoves = -1;
        this.goalNode = null;

        solve();
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return minimumMoves != -1;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return minimumMoves;
    }

    // sequence of boards in a given shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (goalNode == null) {
            return null;
        }

        Stack<Board> trace = new Stack<>();
        SearchNode node = goalNode;

        while (node.hasPrevious()) {
            trace.push(node.getBoard());
            node = node.getPrevious();
        }

        // edge case - push the last node onto the stack
        trace.push(node.getBoard());

        return trace;
    }

    // generate a solution for the initial board, if there is one
    private void solve() {
        // it isn't possible to compute a single board and determine its solvability.
        // however, if a board is unsolvable, then any twin of the same board is guaranteed to be solvable.
        // using this information, run the A* algorithm on two puzzle instances in lockstep -
        // one being the supplied board and the other being a twin of the supplied board.
        // while either board is not solved, perform two A* algorithms in lockstep.
        // exactly one of the two boards will lead to the goal board.

        // initialise board & twin priority queues
        Board twin = initialBoard.twin();

        MinPQ<SearchNode> boardPQ = new MinPQ<>();
        MinPQ<SearchNode> twinPQ = new MinPQ<>();

        boardPQ.insert(new SearchNode(initialBoard, 0, null));
        twinPQ.insert(new SearchNode(twin, 0, null));

        // use A* algorithm to determine if board is solvable in lockstep
        while (!boardPQ.min().getBoard().isGoal() && !twinPQ.min().getBoard().isGoal()) {

            // delete the minimum priority
            SearchNode boardNode = boardPQ.delMin();
            SearchNode twinNode = twinPQ.delMin();

            // enqueue the neighbours of the dequeued nodes
            for (Board neighbour : boardNode.getBoard().neighbors()) {
                // critical optimisation - do not enqueue any boards that are equal to the previous nodes' board
                if (boardNode.hasPrevious() && neighbour.equals(boardNode.getPrevious().getBoard()))
                    continue;

                // else enqueue the neighbour
                SearchNode node = new SearchNode(neighbour, boardNode.getMoves() + 1, boardNode);
                boardPQ.insert(node);
            }

            for (Board neighbour : twinNode.getBoard().neighbors()) {
                // critical optimisation - do not enqueue any boards that are equal to the previous nodes' board
                if (twinNode.hasPrevious() && neighbour.equals(twinNode.getPrevious().getBoard()))
                    continue;

                // else enqueue the neighbour
                SearchNode node = new SearchNode(neighbour, twinNode.getMoves() + 1, twinNode);
                twinPQ.insert(node);
            }
        }

        // solution found, check if the initial board was solved
        SearchNode node = boardPQ.delMin();

        if (node.getBoard().isGoal()) {
            this.goalNode = node;
            this.minimumMoves = goalNode.getMoves();
        }
    }

    // test client
    public static void main(String[] args) {

    }

}
