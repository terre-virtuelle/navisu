package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Face extends VectorRecord {

    /**
     * Enregistrements spatiaux associes a l'objet reperes par leurs
     * identifiants
     */
    protected HashMap<Long, VectorUsage> spatialRecordById = new HashMap<>();

    /**
     * Enregistrements spatiaux associes a l'objet
     */
    protected HashMap<Spatial, VectorUsage> spatialRecord = new HashMap<>();

    public int cardSpatialRecordById() {
        return this.spatialRecordById.size();
    }

    @Override
    public void decodVRPT(byte[] fieldValue) {
        for (int i = 0; i < fieldValue.length; i += 8) {
            long id0;
            id0 = fieldValue[i] & 0xFF;
            id0 = id0 * 256 + (fieldValue[i + 4] & 0xFF);
            id0 = id0 * 256 + (fieldValue[i + 3] & 0xFF);
            id0 = id0 * 256 + (fieldValue[i + 2] & 0xFF);
            id0 = id0 * 256 + (fieldValue[i + 1] & 0xFF);

            spatialRecordById.put(id0, new VectorUsage(fieldValue[i + 5], fieldValue[i + 6], fieldValue[i + 7], fieldValue[i + 8], 0));
        }
        //System.out.println("Nombre de segments: " + this.cardSpatialRecordById());

    }

    @Override
    public String toString() {
        return "  Face{" + "spatialRecordById=" + spatialRecordById + ", spatialRecord=" + spatialRecord + '}';
    }

    public HashMap<Spatial, VectorUsage> getSpatialRecord() {
        return spatialRecord;
    }

    public void setSpatialRecord(HashMap<Spatial, VectorUsage> spatialRecord) {
        this.spatialRecord = spatialRecord;
    }

    @Override
    public void linkObjects() {
        if (this.cardSpatialRecordById() != 0) {
            HashSet<Long> spatials = new HashSet<>(spatialRecordById.keySet());
            Iterator<Long> it = spatials.iterator();
            while (it.hasNext()) {
                Long id0 = it.next();
                spatialRecord.put(S57Model.getSpatialObject(id0), this.spatialRecordById.get(id0));
            }
        }
    }

    public Face(long rcid) {
        this.setId(rcid);
    }

}
