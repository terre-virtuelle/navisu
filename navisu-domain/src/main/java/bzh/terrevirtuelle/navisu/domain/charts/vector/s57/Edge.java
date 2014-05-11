package bzh.terrevirtuelle.navisu.domain.charts.vector.s57;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.controler.analyzer.DataSet;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


public class Edge extends VectorRecord {

    /**
     * Enregistrements spatiaux associés à l'objet repérés par leurs
     * identifiants
     */
    protected HashMap<Long, VectorUsage> spatialRecordById = new HashMap<>(2);

    /**
     * Enregistrements spatiaux associés à l'objet
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
     * Récupère les noeuds de début et de fin, les faces de droite et de gauche
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
     * Récupère les points définissant la ligne
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
            pt.setX(x / DataSet.getCOMF());
            pt.setY(y / DataSet.getCOMF());
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
                Spatial spObj = DataSet.getSpatialObject(id);
                spatialRecord.put(spObj, vu);
                if (vu.getTopi() == 1) {
                    bNode = (ConnectedNode) spObj;
                    vuExtremite = vu;
                } else if (vu.getTopi() == 2) {
                    eNode = (ConnectedNode) spObj;
                    vuExtremite = vu;
                }
            }
            /* Cas où les noeuds de début et de fin sont les même */
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
