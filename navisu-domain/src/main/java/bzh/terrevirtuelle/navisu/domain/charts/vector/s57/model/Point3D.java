package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

public class Point3D extends Point {

    private double z;

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return super.toString() + "Point3D{" + "z=" + z + '}';
    }

}
