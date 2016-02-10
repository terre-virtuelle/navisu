package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.Geo;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import java.io.Serializable;

public class LightFloat extends Geo
        implements NavigationData, Serializable {

    public LightFloat(Long id) {
        this.id = id;
    }

    public LightFloat() {
    }

    @Override
    public double getLatitude() {
        return 0.0;
    }

    @Override
    public double getLongitude() {
        return 0.0;
    }

    private String colour;

    public String getColour() {
        return colour;
    }

    public void setColour(String value) {
        this.colour = value;
    }

    private String colourPattern;

    public String getColourPattern() {
        return colourPattern;
    }

    public void setColourPattern(String value) {
        this.colourPattern = value;
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

    private String dateEnd;

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String value) {
        this.dateEnd = value;
    }

    private String dateStart;

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String value) {
        this.dateStart = value;
    }

    private String horizontalAccuracy;

    public String getHorizontalAccuracy() {
        return horizontalAccuracy;
    }

    public void setHorizontalAccuracy(String value) {
        this.horizontalAccuracy = value;
    }

    private String horizontalLength;

    public String getHorizontalLength() {
        return horizontalLength;
    }

    public void setHorizontalLength(String value) {
        this.horizontalLength = value;
    }

    private String horizontalWidth;

    public String getHorizontalWidth() {
        return horizontalWidth;
    }

    public void setHorizontalWidth(String value) {
        this.horizontalWidth = value;
    }

    private String natureOfConstruction;

    public String getNatureOfConstruction() {
        return natureOfConstruction;
    }

    public void setNatureOfConstruction(String value) {
        this.natureOfConstruction = value;
    }

    private String objectName;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String value) {
        this.objectName = value;
    }

    private String periodicDateEnd;

    public String getPeriodicDateEnd() {
        return periodicDateEnd;
    }

    public void setPeriodicDateEnd(String value) {
        this.periodicDateEnd = value;
    }

    private String periodicDateStart;

    public String getPeriodicDateStart() {
        return periodicDateStart;
    }

    public void setPeriodicDateStart(String value) {
        this.periodicDateStart = value;
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

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

    private String verticalAccuracy;

    public String getVerticalAccuracy() {
        return verticalAccuracy;
    }

    public void setVerticalAccuracy(String value) {
        this.verticalAccuracy = value;
    }

    private String verticalLength;

    public String getVerticalLength() {
        return verticalLength;
    }

    public void setVerticalLength(String value) {
        this.verticalLength = value;
    }

    private String objectNameInNationalLanguage;

    public String getObjectNameInNationalLanguage() {
        return objectNameInNationalLanguage;
    }

    public void setObjectNameInNationalLanguage(String value) {
        this.objectNameInNationalLanguage = value;
    }

    @Override
    public String getGeometry() {
        return "POINT(" + Double.toString(0.0) + " " + Double.toString(0.0) + ")";
    }
}
