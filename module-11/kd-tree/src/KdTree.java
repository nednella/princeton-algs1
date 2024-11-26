/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {

    private static class Node {

        private Point2D p;
        private RectHV rect;
        private Node left;
        private Node right;

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
            this.left = null;
            this.right = null;
        }

    }

    private static final double MIN_X = 0.0;
    private static final double MIN_Y = 0.0;
    private static final double MAX_X = 1.0;
    private static final double MAX_Y = 1.0;

    private Node root;
    private int size;

    public KdTree() {
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        isValidPoint(p);
        root = insert(root, p, 0, MIN_X, MIN_Y, MAX_X, MAX_Y);
    }

    private Node insert(Node current, Point2D p, int level, double xmin, double ymin, double xmax, double ymax) {
        if (current == null) {
            size++;
            return new Node(p, new RectHV(xmin, ymin, xmax, ymax));
        }

        if (current.p.equals(p)) return current; // no duplicates

        int compare = compare(p, current.p, level);
        if (compare < 0) {
            if (isEvenLevel(level)) { // left -- update x_max
                current.left = insert(current.left, p, level + 1, xmin, ymin, current.p.x(), ymax);
            }
            else { // bottom -- update y_max
                current.left = insert(current.left, p, level + 1, xmin, ymin, xmax, current.p.y());
            }
        }
        else {
            if (isEvenLevel(level)) { // right -- update x_min
                current.right = insert(current.right, p, level + 1, current.p.x(), ymin, xmax, ymax);
            }
            else { // top -- update y_min
                current.right = insert(current.right, p, level + 1, xmin, current.p.y(), xmax, ymax);
            }
        }

        return current;
    }

    public boolean contains(Point2D p) {
        isValidPoint(p);
        return contains(root, p, 0) != null;
    }

    private Node contains(Node current, Point2D p, int depth) {
        if (current == null) return null;

        if (current.p.equals(p)) return current; // query point found

        if (compare(p, current.p, depth) < 0) {
            return contains(current.left, p, depth + 1);
        }
        else {
            return contains(current.right, p, depth + 1);
        }
    }

    public void draw() {
        draw(root, 0);
    }

    private void draw(Node current, int level) {
        if (current == null) return;
        drawNode(current, level);
        draw(current.left, level + 1);
        draw(current.right, level + 1);
    }

    private void drawNode(Node n, int level) {
        // draw 2D point
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        n.p.draw(); // draw the 2D point

        // draw splitting line
        StdDraw.setPenRadius();
        if (isEvenLevel(level)) { // vertical
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(n.p.x(), n.rect.ymin(), n.p.x(), n.rect.ymax());
        }
        else { // horizontal
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(n.rect.xmin(), n.p.y(), n.rect.xmax(), n.p.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        isValidRect(rect);
        ArrayList<Point2D> points = new ArrayList<>();
        range(root, rect, points);
        return points;
    }

    private void range(Node current, RectHV rect, ArrayList<Point2D> points) {
        if (current == null || !rect.intersects(current.rect)) return;

        if (rect.contains(current.p)) points.add(current.p);
        range(current.left, rect, points);
        range(current.right, rect, points);
    }

    public Point2D nearest(Point2D p) {
        isValidPoint(p);
        if (isEmpty()) return null;
        return nearest(root, null, p, 0).p;
    }

    private Node nearest(Node current, Node champion, Point2D target, int level) {
        // check champion against current node
        if (champion == null) {
            champion = current;
        }
        else {
            champion = closest(current, champion, target);
        }

        // determine order of traversal
        Node first;
        Node second;
        if (compare(target, current.p, level) < 0) {
            first = current.left;
            second = current.right;
        }
        else {
            first = current.right;
            second = current.left;
        }

        // determine champion of current and left subtree
        if (first != null) {
            Node bestFromFirst = nearest(first, champion, target, level + 1);
            champion = closest(current, bestFromFirst, target);
        }

        // check for pruning of second branch when recursing back up
        if (second != null) {
            double distanceToSecondTreeRect = second.rect.distanceSquaredTo(target);
            double distanceToChampion = champion.p.distanceSquaredTo(target);

            // if second subtree may contain a successor, search that tree
            if (distanceToSecondTreeRect < distanceToChampion) {
                Node bestFromSecond = nearest(second, champion, target, level + 1);
                champion = closest(champion, bestFromSecond, target);
            }
        }

        return champion;
    }

    /* helper functions */

    private void isValidPoint(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Supplied point is null.");
        }
    }

    private void isValidRect(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("Supplied rect is null.");
        }
    }

    private boolean isEvenLevel(int level) {
        return level % 2 == 0;
    }

    private int compare(Point2D a, Point2D b, int level) {
        if (isEvenLevel(level)) {
            return Double.compare(a.x(), b.x());
        }
        else {
            return Double.compare(a.y(), b.y());
        }
    }

    private Node closest(Node a, Node b, Point2D target) {
        double aDist = a.p.distanceSquaredTo(target);
        double bDist = b.p.distanceSquaredTo(target);
        if (aDist < bDist) return a;
        else return b;
    }

    public static void main(String[] args) {

    }
    
}
