/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {

    private TreeSet<Point2D> tree;

    public PointSET() {
        this.tree = new TreeSet<>();
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public int size() {
        return tree.size();
    }

    public void insert(Point2D p) {
        isValidPoint(p);
        if (contains(p)) return;
        tree.add(p);
    }

    public boolean contains(Point2D p) {
        isValidPoint(p);
        return tree.contains(p);
    }

    public void draw() {
        for (Point2D p : tree) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        isValidRect(rect);
        
        ArrayList<Point2D> points = new ArrayList<>();
        for (Point2D p : tree) {
            if (rect.contains(p)) points.add(p);
        }
        return points;
    }

    public Point2D nearest(Point2D p) {
        isValidPoint(p);
        if (isEmpty()) return null;

        double dist = Double.POSITIVE_INFINITY;
        Point2D closest = null;

        for (Point2D q : tree) {
            if (p.distanceSquaredTo(q) < dist) {
                dist = p.distanceSquaredTo(q);
                closest = q;
            }
        }

        return closest;
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

    public static void main(String[] args) {

    }

}
