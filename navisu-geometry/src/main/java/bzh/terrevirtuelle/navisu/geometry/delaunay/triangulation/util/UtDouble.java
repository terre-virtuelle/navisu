package bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.util;

/**
 * avoid Double Calculating Error
 */
public final class UtDouble {
    private UtDouble() {
    }

    public static boolean isEqual(double a, double b) {
        double epsilon = 1 / Math.pow(10, 6);
        return Math.abs(a - b) <= epsilon;
    }
}
