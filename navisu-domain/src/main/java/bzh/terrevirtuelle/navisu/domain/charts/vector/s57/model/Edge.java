package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


public class Edge extends VectorRecord {

    /**
     * Enregistrements spatiaux associes a l'objet reperes par leurs
     * identifiants
     */
    protected HashMap<Long, VectorUsage> spatialRecordById = new HashMap<>(2);

    /**
     * Enregistrements spatiaux associes a l'objet
     */
    protected HashMap<Spatial, VectorUsage> spatialRecord = new HashMap<>();

    protected List<Point2D> points = new ArrayList<>();

    public int cardSpatialRecordById() {
        return this.spatialRecordById.size();
    }

    public Point2D getPoints(int i) {
        return this.points.get(i);
    }

    public int cardPoints() {
        return this.points.size();
    }

    /**
     * Recupere les noeuds de debut et de fin, les faces de droite et de gauche
     */
    @Override
    public void decodVRPT(byte[] fieldValue) {
        for (int i = 0; i < fieldValue.length; i += 9) {
            long id0;
            id0 = fieldValue[i] & 0xFF;
            id0 = id0 * 256 + (fieldValue[i + 4] & 0xFF);
            id0 = id0 * 256 + (fieldValue[i + 3] & 0xFF);
            id0 = id0 * 256 + (fieldValue[i + 2] & 0xFF);
            id0 = id0 * 256 + (fieldValue[i + 1] & 0xFF);

            spatialRecordById.put(id0, new VectorUsage(fieldValue[i + 5], fieldValue[i + 6], fieldValue[i + 7], fieldValue[i + 8], 0));

        }
    }

    /**
     * Recupere les points definissant la ligne
     *
     * @param fieldValue
     */
    @Override
    public void decodSG2D(byte[] fieldValue) {
        java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(fieldValue.length);
        bb.put(fieldValue);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        for (int i = 0; i < fieldValue.length; i += 8) {
            double y = (double) bb.getInt(i);
            double x = (double) bb.getInt(i + 4);
            Point2D pt = new Point2D();
            pt.setX(x / S57Model.getCOMF());
            pt.setY(y / S57Model.getCOMF());
            points.add(pt);
        }
    }

    @Override
    public String toString() {
        return "Edge{" + "spatialRecordById=" + spatialRecordById + ", spatialRecord=" + spatialRecord + ", points=" + points + '}';
    }

    @Override
    public void linkObjects() {
        if (this.cardSpatialRecordById() != 0) {
            HashSet<Long> spatials = new HashSet<>(spatialRecordById.keySet());
            Iterator<Long> it = spatials.iterator();

            ConnectedNode bNode = null;
            ConnectedNode eNode = null;
            VectorUsage vuExtremite = null;
            while (it.hasNext()) {
                Long id = it.next();
                VectorUsage vu = this.spatialRecordById.get(id);
                Spatial spObj = S57Model.getSpatialObject(id);
                spatialRecord.put(spObj, vu);
                if (vu.getTopi() == 1) {
                    bNode = (ConnectedNode) spObj;
                    vuExtremite = vu;
                } else if (vu.getTopi() == 2) {
                    eNode = (ConnectedNode) spObj;
                    vuExtremite = vu;
                }
            }
            /* Cas ou les noeuds de debut et de fin sont les meme */
            if (bNode != null && eNode == null) {
                spatialRecord.put(bNode.clone(), new VectorUsage(vuExtremite.getOrnt(), vuExtremite.getUsag(), 2, vuExtremite.getMask(), 2));
            }
            if (eNode != null && bNode == null) {
                spatialRecord.put(eNode.clone(), new VectorUsage(vuExtremite.getOrnt(), vuExtremite.getUsag(), 1, vuExtremite.getMask(), 1));
            }
        }
    }

    public Edge(long rcid) {
        this.setId(rcid);
    }

    public Edge() {

    }

    public HashMap<Spatial, VectorUsage> getSpatialRecord() {
        return spatialRecord;
    }

    public List<Point2D> getPoints() {
        return points;
    }

    public void setSpatialRecord(HashMap<Spatial, VectorUsage> spatialRecord) {
        this.spatialRecord = spatialRecord;
    }
}
