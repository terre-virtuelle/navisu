package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
public class S57Object implements Serializable {

    @XmlTransient
    protected boolean feature = false;
    @XmlTransient
    protected boolean spatial = false;
    @XmlTransient
    protected boolean dataSet = false;
    @XmlTransient
    protected Map<String, String> labels;
    
    protected long id = 0;

    public S57Object(long id) {
        this.id = id;
        labels=new HashMap<>();
    }

    public S57Object() {
        labels=new HashMap<>();
    }

    public Map<String, String> getLabels() {
        return labels;
    }

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
        return "S57Object{" + "id=" + id + '}' + '\n';
    }

}
