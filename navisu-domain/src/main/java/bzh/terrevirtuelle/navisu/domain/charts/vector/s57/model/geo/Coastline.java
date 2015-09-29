package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Edge;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Spatial;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.VectorUsage;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Coastline extends Geo
        implements Serializable {

    public Coastline(Long id) {
        this.id = id;
    }

    public Coastline() {
    }

    private String categoryOfcoastline;

    public String getCategoryOfCoastline() {
        return categoryOfcoastline;
    }

    public void setCategoryOfCoastline(String value) {
        this.categoryOfcoastline = value;
    }

    private String colour;

    public String getColour() {
        return colour;
    }

    public void setColour(String value) {
        this.colour = value;
    }

    private String conspicuousRadar;

    public String getConspicuousRadar() {
        return conspicuousRadar;
    }

    public void setConspicuousRadar(String value) {
        this.conspicuousRadar = value;
    }

    private String conspicuousVisually;

    public String getConspicuousVisually() {
        return conspicuousVisually;
    }

    public void setConspicuousVisually(String value) {
        this.conspicuousVisually = value;
    }

    private String elevation;

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String value) {
        this.elevation = value;
    }

    private String objectName;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String value) {
        this.objectName = value;
    }

    private String pictorialRepresentation;

    public String getPictorialRepresentation() {
        return pictorialRepresentation;
    }

    public void setPictorialRepresentation(String value) {
        this.pictorialRepresentation = value;
    }

    private String recordIngdate;

    public String getRecordIngdate() {
        return recordIngdate;
    }

    public void setRecordIngdate(String value) {
        this.recordIngdate = value;
    }

    private String recordingIndication;

    public String getRecordingIndication() {
        return recordingIndication;
    }

    public void setRecordingIndication(String value) {
        this.recordingIndication = value;
    }

    private String scaleMaximum;

    public String getScaleMaximum() {
        return scaleMaximum;
    }

    public void setScaleMaximum(String value) {
        this.scaleMaximum = value;
    }

    private String verticalAccuracy;

    public String getVerticalAccuracy() {
        return verticalAccuracy;
    }

    public void setVerticalAccuracy(String value) {
        this.verticalAccuracy = value;
    }

    private String verticaldatum;

    public String getVerticaldatum() {
        return verticaldatum;
    }

    public void setVerticaldatum(String value) {
        this.verticaldatum = value;
    }

    private String objectNameInNationalLanguage;

    public String getObjectNameInNationalLanguage() {
        return objectNameInNationalLanguage;
    }

    public void setObjectNameInNationalLanguage(String value) {
        this.objectNameInNationalLanguage = value;
    }

    public Set<Edge> getEdges() {
        Set<Edge> edges = new HashSet<>();

        HashMap<Spatial, VectorUsage> sr = getSpatialRecord();
        sr.keySet().stream().forEach((s) -> {
            Edge n = (Edge) s;
            edges.add(n);
        });
        return edges;
    }
}
