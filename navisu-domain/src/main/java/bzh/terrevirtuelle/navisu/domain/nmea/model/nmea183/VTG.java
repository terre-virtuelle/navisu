/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Track made good and ground speed
 *
 * @author Camille AUBRY, Alexis BURGER, Chintana CHANTHABOURY, Teddy VALLEE
 * aubryc
 * @author Serge Morvan
 */
@XmlRootElement(name = "VTG")
@XmlAccessorType(XmlAccessType.FIELD)
public class VTG extends NMEA {

    private float trueTrackMadeGoodDegrees;
    private float magneticTrackMadeGood;
    private float sog;

    /**
     * VTG - Velocity made good. The gps receiver may use the LC prefix instead
     * of GP if it is emulating Loran output.
     *
     * @param device
     * @param sentence
     * @param trueTrackMadeGoodDegrees
     * @param magneticTrackMadeGood
     * @param sog
     */
    public VTG(
            String device,
            String sentence,
            float trueTrackMadeGoodDegrees,
            float magneticTrackMadeGood,
            float sog) {
        super(device, sentence);
        this.trueTrackMadeGoodDegrees = trueTrackMadeGoodDegrees;
        this.magneticTrackMadeGood = magneticTrackMadeGood;
        this.sog = sog;
    }

    public VTG() {
    }

    public float getSog() {
        return sog;
    }

    public void setSog(float sog) {
        this.sog = sog;
    }

    public float getCog() {
        return trueTrackMadeGoodDegrees;
    }

    /**
     *
     * @return
     */
    public float getMagneticTrackMadeGood() {
        return magneticTrackMadeGood;
    }

    /**
     *
     * @param magneticTrackMadeGood
     */
    public void setMagneticTrackMadeGood(float magneticTrackMadeGood) {
        this.magneticTrackMadeGood = magneticTrackMadeGood;
    }

    /**
     *
     * @return
     */
    public float getTrueTrackMadeGoodDegrees() {
        return trueTrackMadeGoodDegrees;
    }

    /**
     *
     * @param trueTrackMadeGoodDegrees
     */
    public void setTrueTrackMadeGoodDegrees(float trueTrackMadeGoodDegrees) {
        this.trueTrackMadeGoodDegrees = trueTrackMadeGoodDegrees;
    }

    @Override
    public String toString() {
        return "VTG{" + "trueTrackMadeGoodDegrees=" + trueTrackMadeGoodDegrees
                + ", magneticTrackMadeGood=" + magneticTrackMadeGood
                + ", groundSpeed=" + sog + '}';
    }

}
