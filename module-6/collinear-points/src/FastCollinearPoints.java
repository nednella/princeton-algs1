/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] segments;
    private int segCount;

    public FastCollinearPoints(Point[] points) {
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

    // finds all line segments in the points array
    private LineSegment[] findCollinearPoints(Point[] points) {
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        ArrayList<LineSegment> segmentsList = new ArrayList<>();

        // sorting-based solution to finding the collinear points
        for (Point p : points) {
            Arrays.sort(pointsCopy, p.slopeOrder());
            findSegments(p, pointsCopy, segmentsList);
        }

        LineSegment[] segmentsArr = new LineSegment[segmentsList.size()];
        return segmentsList.toArray(segmentsArr);
    }

    // creates and appends segments of any lines that contain 4 or more collinear points
    // in the array, with respect to point p
    private void findSegments(Point p, Point[] points, ArrayList<LineSegment> segmentsList) {
        int collinearCount = 2; // Any 2 given points are always collinear (prevSlope)
        int segmentStartIndex = 1;
        int segmentEndIndex = 1;

        // iterate through all points starting at index 1, as point p itself is always the 0th index
        for (int i = 1; i < points.length; i++) {
            double prevSlope = p.slopeTo(points[i - 1]);
            double currentSlope = p.slopeTo(points[i]);

            // if current slope is equal to the previous, count the additional collinear point
            if (Double.compare(prevSlope, currentSlope) == 0) {
                collinearCount++;
                segmentEndIndex = i;

                // if there are more points, continue, and check if they are also collinear
                if (i + 1 < points.length) continue;
            }

            // else, slope is not equal to previous, or there are no more points...
            // check if a valid line segment was formed and create
            if (collinearCount >= 4) {
                Point[] collinearPoints = getSegment(p, points, segmentStartIndex, segmentEndIndex);
                Point start = collinearPoints[0];
                Point end = collinearPoints[collinearPoints.length - 1];

                if (isValidSegment(p, collinearPoints)) {
                    LineSegment seg = new LineSegment(start, end);
                    segmentsList.add(seg);
                }
            }

            // reset trackers for new segment
            collinearCount = 2;
            segmentStartIndex = i;
        }
    }

    // returns a new array containing a valid set of sorted collinear points
    private Point[] getSegment(Point p, Point[] points, int start, int end) {
        // sort the collinear points in ascending order (uses the Point.compareTo method)
        ArrayList<Point> pointsList = new ArrayList<>();
        pointsList.add(p);
        pointsList.addAll(Arrays.asList(points).subList(start, end + 1)); // inclusive of end index
        pointsList.sort(null);

        Point[] pointsArr = new Point[pointsList.size()];
        return pointsList.toArray(pointsArr);
    }

    // check if set of collinear points forms a valid segment (segment starts with Point p)
    private boolean isValidSegment(Point p, Point[] points) {
        return p.compareTo(points[0]) == 0;
    }

}
