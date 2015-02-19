package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;

public class Topmark extends Location
        implements Serializable {

    public Topmark(Long id) {
        this.id = id;
    }

    public Topmark() {
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

    private String height;

    public String getHeight() {
        return height;
    }

    public void setHeight(String value) {
        this.height = value;
    }

    private String marksNavigationalSystemof;

    public String getMarksNavigationalSystemof() {
        return marksNavigationalSystemof;
    }

    public void setMarksNavigationalSystemof(String value) {
        this.marksNavigationalSystemof = value;
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

    private String topMark_daymarkshape;

    public String getTopMark_daymarkshape() {
        return topMark_daymarkshape;
    }

    public void setTopMark_daymarkshape(String value) {
        this.topMark_daymarkshape = value;
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

    private String verticalLength;

    public String getVerticalLength() {
        return verticalLength;
    }

    public void setVerticalLength(String value) {
        this.verticalLength = value;
    }

}
