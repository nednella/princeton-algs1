/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Examines 4 points at a time and checks whether they all lie on the same line segment,
 * returning all such line segments. To check whether the 4 points p, q, r, and s are
 * collinear, check whether the three slopes between p and q, between p and r,
 * and between p and s are all equal.
 */
public class BruteCollinearPoints {

    private LineSegment[] segments;
    private int segCount;

    public BruteCollinearPoints(Point[] points) {
        validateArgument(points);
        this.segments = findCollinearPoints(points);
        this.segCount = this.segments.length;
    }

    // the number of line segments
    public int numberOfSegments() {
        return segCount;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }

    // checks for edge cases in the constructor argument
    private void validateArgument(Point[] points) {
        // throw exception if argument is null
        if (points == null) {
            throw new IllegalArgumentException("Constructor argument cannot be null.");
        }

        // throw exception if argument contains a null element or a repeated point
        ArrayList<Point> seen = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("The supplied array contains a null element.");
            }

            for (Point seenPoint : seen) {
                if (points[i].compareTo(seenPoint) == 0) {
                    throw new IllegalArgumentException(
                            "The supplied array contains a duplicate element.");
                }
            }

            seen.add(points[i]);
        }
    }

    // finds all line segments containing 4 points
    private LineSegment[] findCollinearPoints(Point[] points) {
        ArrayList<LineSegment> segList = new ArrayList<>();
        Point[] pointsCopy = Arrays.copyOf(points, points.length);

        // brute force n^4 worst case time complexity
        Arrays.sort(pointsCopy);
        for (int h = 0; h <= pointsCopy.length - 3; h++) {
            for (int i = h + 1; i < pointsCopy.length - 2; i++) {
                for (int j = i + 1; j < pointsCopy.length - 1; j++) {
                    for (int k = j + 1; k < pointsCopy.length; k++) {
                        Point p = pointsCopy[h];
                        Point q = pointsCopy[i];
                        Point r = pointsCopy[j];
                        Point s = pointsCopy[k];

                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s)) {
                            LineSegment seg = new LineSegment(p, s);
                            segList.add(seg);
                        }
                    }
                }
            }
        }

        LineSegment[] segArray = new LineSegment[segList.size()];
        return segList.toArray(segArray);
    }

}
