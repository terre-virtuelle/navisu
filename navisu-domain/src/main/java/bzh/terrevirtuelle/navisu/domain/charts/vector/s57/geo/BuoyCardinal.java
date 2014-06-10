package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Geo;
import java.io.Serializable;

public class BuoyCardinal extends Geo
        implements Serializable {

    private String buoyShape;
    private String categoryOfcardinalMark;
    private String colour;
    private String colourPattern;
    private String conspicuousRadar;
    private String dateEnd;
    private String dateStart;
    private String marksNavigationalSystemof;
    private String natureOfConstruction;
    private String objectName;
    private String periodicDateEnd;
    private String periodicDateStart;
    private String pictorialRepresentation;
    private String recordIngdate;
    private String recordingIndication;
    private String scaleMaximum;
    private String status;
    private String verticalAccuracy;
    private String verticalLength;
    private String objectNameInNationalLanguage;

    public BuoyCardinal(Long id) {
        this.id = id;
    }

    public BuoyCardinal() {
    }

    public String getBuoyShape() {
        return buoyShape;
    }

    public void setBuoyShape(String value) {
        this.buoyShape = value;
    }

    public String getCategoryOfCardinalMark() {
        return categoryOfcardinalMark;
    }

    public void setCategoryOfCardinalMark(String value) {
        this.categoryOfcardinalMark = value;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String value) {
        this.colour = value;
    }

    public String getColourPattern() {
        return colourPattern;
    }

    public void setColourPattern(String value) {
        this.colourPattern = value;
    }

    public String getConspicuousRadar() {
        return conspicuousRadar;
    }

    public void setConspicuousRadar(String value) {
        this.conspicuousRadar = value;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String value) {
        this.dateEnd = value;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String value) {
        this.dateStart = value;
    }

    public String getMarksNavigationalSystemof() {
        return marksNavigationalSystemof;
    }

    public void setMarksNavigationalSystemof(String value) {
        this.marksNavigationalSystemof = value;
    }

    public String getNatureOfConstruction() {
        return natureOfConstruction;
    }

    public void setNatureOfConstruction(String value) {
        this.natureOfConstruction = value;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String value) {
        this.objectName = value;
    }

    public String getPeriodicDateEnd() {
        return periodicDateEnd;
    }

    public void setPeriodicDateEnd(String value) {
        this.periodicDateEnd = value;
    }

    public String getPeriodicDateStart() {
        return periodicDateStart;
    }

    public void setPeriodicDateStart(String value) {
        this.periodicDateStart = value;
    }

    public String getPictorialRepresentation() {
        return pictorialRepresentation;
    }

    public void setPictorialRepresentation(String value) {
        this.pictorialRepresentation = value;
    }

    public String getRecordIngdate() {
        return recordIngdate;
    }

    public void setRecordIngdate(String value) {
        this.recordIngdate = value;
    }

    public String getRecordingIndication() {
        return recordingIndication;
    }

    public void setRecordingIndication(String value) {
        this.recordingIndication = value;
    }

    public String getScaleMaximum() {
        return scaleMaximum;
    }

    public void setScaleMaximum(String value) {
        this.scaleMaximum = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

    public String getVerticalAccuracy() {
        return verticalAccuracy;
    }

    public void setVerticalAccuracy(String value) {
        this.verticalAccuracy = value;
    }

    public String getVerticalLength() {
        return verticalLength;
    }

    public void setVerticalLength(String value) {
        this.verticalLength = value;
    }

    public String getObjectNameInNationalLanguage() {
        return objectNameInNationalLanguage;
    }

    public void setObjectNameInNationalLanguage(String value) {
        this.objectNameInNationalLanguage = value;
    }

}
