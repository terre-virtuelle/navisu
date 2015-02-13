/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Camille AUBRY, Alexis BURGER, Chintana CHANTHABOURY, Teddy VALLEE c9aubry
 * @author Serge Morvan
 */
@XmlRootElement(name = "satellite")
@XmlAccessorType(XmlAccessType.FIELD)
public class GPSSatellite {

    private int satellitePRNNumber;
    private int elevationDegrees;
    private int azimuthDegrees;
    private int snr;

    /**
     *
     * @param satellitePRNNumber
     * @param elevationDegrees
     * @param azimuthDegrees
     * @param snr
     */
    public GPSSatellite(int satellitePRNNumber, int elevationDegrees, int azimuthDegrees, int snr) {
        this.satellitePRNNumber = satellitePRNNumber;
        this.elevationDegrees = elevationDegrees;
        this.azimuthDegrees = azimuthDegrees;
        this.snr = snr;
    }

    /**
     *
     */
    public GPSSatellite () {

    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Satellite n° ").append(satellitePRNNumber).append(" : ");
        buffer.append("elevationDegrees : ").append(elevationDegrees);
        buffer.append(", azimuthDegrees : ").append(azimuthDegrees);
        buffer.append(", SNR : ").append(snr);

        return new String(buffer);
    }

    /**
     *
     * @return
     */
    public int getSnr() {
        return snr;
    }

    /**
     *
     * @param snr
     */
    public void setSnr(int snr) {
        this.snr = snr;
    }

    /**
     *
     * @return
     */
    public int getAzimuthDegrees() {
        return azimuthDegrees;
    }

    /**
     *
     * @param azimuthDegrees
     */
    public void setAzimuthDegrees(int azimuthDegrees) {
        this.azimuthDegrees = azimuthDegrees;
    }

    /**
     *
     * @return
     */
    public int getElevationDegrees() {
        return elevationDegrees;
    }

    /**
     *
     * @param elevationDegrees
     */
    public void setElevationDegrees(int elevationDegrees) {
        this.elevationDegrees = elevationDegrees;
    }

    /**
     *
     * @return
     */
    public int getSatellitePRNNumber() {
        return satellitePRNNumber;
    }

    /**
     *
     * @param satellitePRNNumber
     */
    public void setSatellitePRNNumber(int satellitePRNNumber) {
        this.satellitePRNNumber = satellitePRNNumber;
    }

}
