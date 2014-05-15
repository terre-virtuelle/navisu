package bzh.terrevirtuelle.navisu.domain.charts.vector.s57;

import java.io.Serializable;



public class S57Object implements Serializable {

    protected boolean feature = false;
    protected boolean spatial = false;
    protected boolean dataSet = false;

    protected long id;

    public long getId() {
        return this.id;
    }

    /**
     * Recupere les donnees pertinentes a partir des champs de l'objet
     *
     * @param fieldName
     * @param fieldValue
     * @return
     */
    public S57Object setField(String fieldName, byte[] fieldValue) {
        try {
            switch (fieldName) {
                case "FRID":
                    Feature ft = new Feature();
                    ft = ft.decodFRID(fieldValue);
                    return ft;
                case "VRID":
                    Spatial sp = new Spatial();
                    sp = sp.decodVRID(fieldValue);
                    return sp;
                case "DSPM":
                    DataSetGeographicReference ds = new DataSetGeographicReference();
                    ds = ds.decodDSPM(fieldValue);
                    return ds;
            }
            return this;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return vrai si objet de donn�e
     */
    public boolean isFeatureObject() {
        return feature;
    }

    /**
     * @return vrai si objet spatial
     */
    public boolean isSpatialObject() {
        return spatial;
    }

    /**
     * @return vrai si c'est un dataSet
     */
    public boolean isDataSet() {
        return dataSet;
    }

    /**
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    public boolean isFeature() {
        return feature;
    }

    public boolean isSpatial() {
        return spatial;
    }


    public void linkObjects() {
    }

    @Override
    public String toString() {
        return "S57Object{" + "id=" + id + '}' +'\n';
    }
    
}
