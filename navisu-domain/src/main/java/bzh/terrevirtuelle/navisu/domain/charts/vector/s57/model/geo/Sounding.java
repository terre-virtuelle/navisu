package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sounding")
public class Sounding extends Location
        implements Serializable, NavigationData {

    private String expositionOfSounding;
    private String objectName;
    private String qualityOfSoundingMeasurement;
    private String recordIngdate;
    private String recordingIndication;
    private String scaleMaximum;
    private String soundingAccuracy;
    private String status;
    private String techniqueOfSoundingMeasurement;
    private String objectNameInNationalLanguage;
    private String verticaldatum;
    private double depth;

    public Sounding(Long id) {
        this.id = id;
    }

    public Sounding() {
    }

    public Sounding(double lat, double lon) {
        super(lat, lon);
    }

    public Sounding(double lat, double lon, double depth) {
        super(lat, lon);
        this.depth = depth;
    }

    public Point3D getPoint3D() {
        return new Point3D(latitude, longitude, depth);
    }

    /**
     * Get the value of depth
     *
     * @return the value of depth
     */
    public double getDepth() {
        return depth;
    }

    /**
     * Set the value of depth
     *
     * @param depth new value of depth
     */
    public void setDepth(double depth) {
        this.depth = depth;
    }

    public String getExpositionOfSounding() {
        return expositionOfSounding;
    }

    public void setExpositionOfSounding(String value) {
        this.expositionOfSounding = value;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String value) {
        this.objectName = value;
    }

    public String getQualityOfSoundingMeasurement() {
        return qualityOfSoundingMeasurement;
    }

    public void setQualityOfSoundingMeasurement(String value) {
        this.qualityOfSoundingMeasurement = value;
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

    public String getSoundingAccuracy() {
        return soundingAccuracy;
    }

    public void setSoundingAccuracy(String value) {
        this.soundingAccuracy = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

    public String getTechniqueOfSoundingMeasurement() {
        return techniqueOfSoundingMeasurement;
    }

    public void setTechniqueOfSoundingMeasurement(String value) {
        this.techniqueOfSoundingMeasurement = value;
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

}
