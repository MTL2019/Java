package Examples;

import java.awt.geom.Point2D;

public class Pair {

    public static void main(String[] args) {

        Point2D[] points = new Point2D[100];

        for (int i = 0; i < 100; i++) {
            points[i] = new Point2D.Double(Math.random()*100, Math.random()*100);
        }

    }

    private Point2D p1;//point 1
    private Point2D p2;//point 2

    /** Return the distance of the closest pair of points */
    public static Pair getClosestPair(double[][] points){
        return new Pair();
    }
    /** Return the distance of the closest pair of points */
    public static Pair getClosestPair(Point2D[] points){
        return new Pair();
    }
    /** Return the distance of the closest pair of points
     * in pointsOrderedOnX[low..high]. This is a recursive
     * method. pointsOrderedOnX and pointsOrderedOnY are
     * not changed in the subsequent recursive calls.
     */
    public static Pair distance(Point2D[] pointsOrderedOnX,int low, int high, Point2D[] pointsOrderedOnY){
        return new Pair();
    }


    /** Compute the distance between two points p1 and p2 */
    public static double distance(Point2D p1, Point2D p2){

        return Math.sqrt(Math.pow(p1.getX() - p2.getX(),2) + Math.pow(p1.getY() - p2.getY(),2));

    }
    /** Compute the distance between points (x1, y1) and (x2, y2) */
    public static double distance(double x1, double y1,double x2, double y2){
        return Math.sqrt(Math.pow(x1 - x2,2) + Math.pow(y1 - y2,2));
    }
}
