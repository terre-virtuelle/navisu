package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import java.io.Serializable;

public class Light extends Location
        implements NavigationData, Serializable {

    private String categoryOfLight;
    private String colour;
    private String dateEnd;
    private String dateStart;
    private String exhibitionConditionOfLight;
    private String height;
    private String lightCharacteristic;
    private String marksNavigationalSystemof;
    private String lightVisibility;
    private String multiplicityOfLights;
    private String objectName;
    private String orientation;
    private String periodicDateEnd;
    private String periodicDateStart;
    private String recordIngdate;
    private String recordingIndication;
    private String sectorLimitTwo;
    private String sectorLimitOne;
    private String scaleMaximum;
    private String signalGroup;
    private String signalPeriod;
    private String signalsequence;
    private String status;
    private String valueOfNominalRange;
    private String verticaldatum;
    private String objectNameInNationalLanguage;

    public Light(Long id) {
        this.id = id;
    }

    public Light() {
    }

    public String getCategoryOfLight() {
        return categoryOfLight;
    }

    public void setCategoryOfLight(String value) {
        this.categoryOfLight = value;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String value) {
        this.colour = value;
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

    public String getExhibitionConditionOfLight() {
        return exhibitionConditionOfLight;
    }

    public void setExhibitionConditionOfLight(String value) {
        this.exhibitionConditionOfLight = value;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String value) {
        this.height = value;
    }

    public String getLightCharacteristic() {
        return lightCharacteristic;
    }

    public void setLightCharacteristic(String value) {
        this.lightCharacteristic = value;
    }

    public String getLightVisibility() {
        return lightVisibility;
    }

    public void setLightVisibility(String value) {
        this.lightVisibility = value;
    }

    public String getMarksNavigationalSystemof() {
        return marksNavigationalSystemof;
    }

    public void setMarksNavigationalSystemof(String value) {
        this.marksNavigationalSystemof = value;
    }

    public String getMultiplicityOfLights() {
        return multiplicityOfLights;
    }

    public void setMultiplicityOfLights(String value) {
        this.multiplicityOfLights = value;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String value) {
        this.objectName = value;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String value) {
        this.orientation = value;
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

    public String getSectorLimitOne() {
        return sectorLimitOne;
    }

    public void setSectorLimitOne(String value) {
        this.sectorLimitOne = value;
    }

    public String getSectorLimitTwo() {
        return sectorLimitTwo;
    }

    public void setSectorLimitTwo(String value) {
        this.sectorLimitTwo = value;
    }

    public String getSignalGroup() {
        return signalGroup;
    }

    public void setSignalGroup(String value) {
        this.signalGroup = value;
    }

    public String getSignalPeriod() {
        return signalPeriod;
    }

    public void setSignalPeriod(String value) {
        this.signalPeriod = value;
    }

    public String getSignalSequence() {
        return signalsequence;
    }

    public void setSignalSequence(String value) {
        this.signalsequence = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

    public String getValueOfNominalRange() {
        return valueOfNominalRange;
    }

    public void setValueOfNominalRange(String value) {
        this.valueOfNominalRange = value;
    }

    private String verticalAccuracy;

    public String getVerticalAccuracy() {
        return verticalAccuracy;
    }

    public void setVerticalAccuracy(String value) {
        this.verticalAccuracy = value;
    }

    public String getVerticaldatum() {
        return verticaldatum;
    }

    public void setVerticaldatum(String value) {
        this.verticaldatum = value;
    }

    public String getObjectNameInNationalLanguage() {
        return objectNameInNationalLanguage;
    }

    public void setObjectNameInNationalLanguage(String value) {
        this.objectNameInNationalLanguage = value;
    }

    @Override
    public String getGeometry() {
        return "POINT(" + Double.toString(longitude) + " " + Double.toString(latitude) + ")";
    }

    @Override
    public String toString() {
        return "Light{"+super.toString()+ "categoryOfLight=" + categoryOfLight + ", colour=" + colour + ", dateEnd=" + dateEnd + ", dateStart=" + dateStart + ", exhibitionConditionOfLight=" + exhibitionConditionOfLight + ", height=" + height + ", lightCharacteristic=" + lightCharacteristic + ", marksNavigationalSystemof=" + marksNavigationalSystemof + ", lightVisibility=" + lightVisibility + ", multiplicityOfLights=" + multiplicityOfLights + ", objectName=" + objectName + ", orientation=" + orientation + ", periodicDateEnd=" + periodicDateEnd + ", periodicDateStart=" + periodicDateStart + ", recordIngdate=" + recordIngdate + ", recordingIndication=" + recordingIndication + ", sectorLimitTwo=" + sectorLimitTwo + ", sectorLimitOne=" + sectorLimitOne + ", scaleMaximum=" + scaleMaximum + ", signalGroup=" + signalGroup + ", signalPeriod=" + signalPeriod + ", signalsequence=" + signalsequence + ", status=" + status + ", valueOfNominalRange=" + valueOfNominalRange + ", verticaldatum=" + verticaldatum + ", objectNameInNationalLanguage=" + objectNameInNationalLanguage + ", verticalAccuracy=" + verticalAccuracy + '}';
    }
    
}
