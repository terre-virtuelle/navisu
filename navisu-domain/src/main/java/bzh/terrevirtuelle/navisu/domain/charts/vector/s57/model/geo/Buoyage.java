/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.COLOUR_NAME;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.COLPAT;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Serge Morvan
 * @date 2 oct. 2014 NaVisu project
 */
public class Buoyage extends Location {

    protected String shape;
    protected String categoryOfMark = "0";
    protected String colour;
    protected String colourPattern;
    protected String navigationalSystemOfMarks;
    protected String condition;
    protected String conspicuousRadar;
    protected String conspicuousVisually;
    protected String dateEnd;
    protected String dateStart;
    protected String elevation;
    protected String height;
    protected String natureOfConstruction;
    protected String objectName;
    protected String periodicDateStart;
    protected String periodicDateEnd;
    protected String pictorialRepresentation;
    protected String recordIngdate;
    protected String scaleMaximum;
    protected String verticalAccuracy;
    protected String recordingIndication;
    protected String verticaldatum;
    protected String verticalLength;
    protected String objectNameInNationalLanguage;
    protected String status;
    @XmlTransient
    protected String label;
    protected String imageAddress;

    /**
     * Get the value of navigationalSystemOfMarks
     *
     * @return the value of navigationalSystemOfMarks
     */
    public String getNavigationalSystemOfMarks() {
        return navigationalSystemOfMarks;
    }

    /**
     * Set the value of navigationalSystemOfMarks
     *
     * @param navigationalSystemOfMarks new value of navigationalSystemOfMarks
     */
    public void setNavigationalSystemOfMarks(String navigationalSystemOfMarks) {
        this.navigationalSystemOfMarks = navigationalSystemOfMarks;
    }

    /**
     * Get the value of categoryOfMark
     *
     * @return the value of categoryOfMark
     */
    public String getCategoryOfMark() {
        return categoryOfMark;
    }

    /**
     * Get the value of categoryOfMark
     *
     * @param cat
     * @return the value of categoryOfMark
     */
    public String getCategoryOfMarkMeaning(String cat) {
        return categoryOfMark;
    }

    /**
     * Set the value of categoryOfMark
     *
     * @param categoryOfMark new value of categoryOfMark
     */
    public void setCategoryOfMark(String categoryOfMark) {
        this.categoryOfMark = categoryOfMark;
    }

    /**
     * Get the value of colourPattern
     *
     * @return the value of colourPattern
     */
    public String getColourPattern() {
        return colourPattern;
    }

    /**
     * Get the value of colourPattern
     *
     * @param colPat
     * @return the value of colourPattern
     */
    public String getColourPatternMeaning(String colPat) {
        return COLPAT.ATT.get(colPat);
    }

    /**
     * Set the value of colourPattern
     *
     * @param colourPattern new value of colourPattern
     */
    public void setColourPattern(String colourPattern) {
        this.colourPattern = colourPattern;
    }

    /**
     * Get the value of colour
     *
     * @return the value of colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * Get the value of colour
     *
     * @param col
     * @return the value of colour
     */
    public String getColourMeaning(String col) {
        return COLOUR_NAME.ATT.get(col);
    }

    /**
     * Set the value of colour
     *
     * @param colour new value of colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * Get the value of shape
     *
     * @return the value of shape
     */
    public String getShape() {
        return shape;
    }

    /**
     * Set the value of shape
     *
     * @param shape new value of shape
     */
    public void setShape(String shape) {
        this.shape = shape;
    }

    /**
     * Set the value of shape
     *
     * @param shape new value of shape
     */
    public String getShapeMeaning(String shape) {
        return shape;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getConspicuousRadar() {
        return conspicuousRadar;
    }

    public void setConspicuousRadar(String conspicuousRadar) {
        this.conspicuousRadar = conspicuousRadar;
    }

    public String getConspicuousVisually() {
        return conspicuousVisually;
    }

    public void setConspicuousVisually(String conspicuousVisually) {
        this.conspicuousVisually = conspicuousVisually;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getNatureOfConstruction() {
        return natureOfConstruction;
    }

    public void setNatureOfConstruction(String natureOfConstruction) {
        this.natureOfConstruction = natureOfConstruction;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getPeriodicDateStart() {
        return periodicDateStart;
    }

    public void setPeriodicDateStart(String periodicDateStart) {
        this.periodicDateStart = periodicDateStart;
    }

    public String getPeriodicDateEnd() {
        return periodicDateEnd;
    }

    public void setPeriodicDateEnd(String periodicDateEnd) {
        this.periodicDateEnd = periodicDateEnd;
    }

    public String getPictorialRepresentation() {
        return pictorialRepresentation;
    }

    public void setPictorialRepresentation(String pictorialRepresentation) {
        this.pictorialRepresentation = pictorialRepresentation;
    }

    public String getRecordIngdate() {
        return recordIngdate;
    }

    public void setRecordIngdate(String recordIngdate) {
        this.recordIngdate = recordIngdate;
    }

    public String getScaleMaximum() {
        return scaleMaximum;
    }

    public void setScaleMaximum(String scaleMaximum) {
        this.scaleMaximum = scaleMaximum;
    }

    public String getVerticalAccuracy() {
        return verticalAccuracy;
    }

    public void setVerticalAccuracy(String verticalAccuracy) {
        this.verticalAccuracy = verticalAccuracy;
    }

    public String getRecordingIndication() {
        return recordingIndication;
    }

    public void setRecordingIndication(String recordingIndication) {
        this.recordingIndication = recordingIndication;
    }

    public String getVerticaldatum() {
        return verticaldatum;
    }

    public void setVerticaldatum(String verticaldatum) {
        this.verticaldatum = verticaldatum;
    }

    public String getVerticalLength() {
        return verticalLength;
    }

    public void setVerticalLength(String verticalLength) {
        this.verticalLength = verticalLength;
    }

    public String getObjectNameInNationalLanguage() {
        return objectNameInNationalLanguage;
    }

    public void setObjectNameInNationalLanguage(String objectNameInNationalLanguage) {
        this.objectNameInNationalLanguage = objectNameInNationalLanguage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Buoyage{"
                + shape != null ? "shape=" + shape : ""
                        + ", categoryOfMark=" + categoryOfMark
                        + ", colour=" + colour
                        + ", colourPattern=" + colourPattern
                        + ", navigationalSystemOfMarks=" + navigationalSystemOfMarks
                        + ", condition=" + condition
                        + ", conspicuousRadar=" + conspicuousRadar
                        + ", conspicuousVisually=" + conspicuousVisually
                        + ", dateEnd=" + dateEnd
                        + ", dateStart=" + dateStart
                        + ", elevation=" + elevation
                        + ", height=" + height
                        + ", natureOfConstruction=" + natureOfConstruction
                        + ", objectName=" + objectName
                        + ", periodicDateStart=" + periodicDateStart
                        + ", periodicDateEnd=" + periodicDateEnd
                        + ", pictorialRepresentation=" + pictorialRepresentation
                        + ", recordIngdate=" + recordIngdate
                        + ", scaleMaximum=" + scaleMaximum
                        + ", verticalAccuracy=" + verticalAccuracy
                        + ", recordingIndication=" + recordingIndication
                        + ", verticaldatum=" + verticaldatum
                        + ", verticalLength=" + verticalLength
                        + ", objectNameInNationalLanguage=" + objectNameInNationalLanguage
                        + ", status=" + status
                        + '}';
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

}
