package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

import java.nio.ByteOrder;

public class Node extends VectorRecord {

    protected Point point;

    /**
     * Recupere les coordonnees 2D du point
     * @param fieldValue
     */
    @Override
    public void decodSG2D(byte[] fieldValue) {
        java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(fieldValue.length);

        bb.put(fieldValue);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        double y = (double) bb.getInt(0);
        double x = (double) bb.getInt(4);
        Point2D pt = new Point2D();
        pt.setX(x / S57Model.getCOMF());
        pt.setY(y / S57Model.getCOMF());
        setPoint(pt);
    }

    /**
     * Recupere les coordonnees 3D du point
     * @param fieldValue
     */
    @Override
    public void decodSG3D(byte[] fieldValue) {
        java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(fieldValue.length);

        bb.put(fieldValue);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        double y = (double) bb.getInt(0);
        double x = (double) bb.getInt(4);
        double z = (double) bb.getInt(8);
        Point3D pt = new Point3D();
        pt.setX(x / S57Model.getCOMF());
        pt.setY(y / S57Model.getCOMF());
        pt.setZ(z / S57Model.getSOMF());
        setPoint(pt);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point pt) {
        this.point = pt;
    }

    @Override
    public String toString() {
        return "Node{" + "point=" + point + '}';
    }

}
