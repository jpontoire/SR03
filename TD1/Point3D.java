package sr03;

public class Point3D extends Point2D {
    private int z;

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public double calculerDistance(Point3D point) {
        return Math.sqrt(Math.pow(this.getX() - point.getX(), 2) + Math.pow(this.getY() - point.getY(), 2) + Math.pow(this.z - point.z, 2));
    }

    public static void main(String[] args) {
        Point3D[] points = new Point3D[2];
        points[0] = new Point3D(134, 638, 249);
        points[1] = new Point3D(432, 666, 789);

        double distance = points[0].calculerDistance(points[1]);
        System.out.println("distance = " + distance);
    }
}
