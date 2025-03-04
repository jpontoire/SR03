package sr03;

import java.util.Scanner;

public class Point2D {
    private int x;
    private int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double calculerDistance(Point2D point) {
        return Math.sqrt(Math.pow(this.x - point.x, 2) + Math.pow(this.y - point.y, 2));
    }

    public static void main(String[] args){
        Point2D[] points = new Point2D[2];
        points[0] = new Point2D(1, 2);
        points[1] = new Point2D(4, 6);

        double distance = points[0].calculerDistance(points[1]);
        System.out.println("Distance =" + distance);
    }

//    @Override
//    public String toString() {
//
//    }
}
