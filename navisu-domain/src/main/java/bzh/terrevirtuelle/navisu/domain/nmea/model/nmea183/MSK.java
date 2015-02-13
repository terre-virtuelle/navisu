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
 * Control for a Beacon Receiver
 * @author Camille AUBRY, Alexis BURGER, Chintana CHANTHABOURY, Teddy VALLEE aubryc
 */
@XmlRootElement(name="MSK")
@XmlAccessorType(XmlAccessType.FIELD)
public class MSK extends NMEA {

    private float frequencyToUse;
    private String frequencyMode;
    private int beaconBitRate;
    private String bitRateMode;
    private int frequencyForMSS;

    /**
     * @param device
     * @param sentence
     * @param frequencyToUse
     * @param frequencyMode
     * @param beaconBitRate
     * @param bitRateMode
     * @param frequencyForMSS
     */
    public MSK(
            String device,
            String sentence,
            float frequencyToUse,
            String frequencyMode,
            int beaconBitRate,
            String bitRateMode,
            int frequencyForMSS) {
        super(device, sentence);
        this.frequencyToUse = frequencyToUse;
        this.frequencyMode = frequencyMode;
        this.beaconBitRate = beaconBitRate;
        this.bitRateMode = bitRateMode;
        this.frequencyForMSS = frequencyForMSS;
    }

    /**
     *
     * @param frequencyToUse
     * @param frequencyMode
     * @param beaconBitRate
     * @param bitRateMode
     */
    public MSK(float frequencyToUse, String frequencyMode, int beaconBitRate, String bitRateMode) {
        this.frequencyToUse = frequencyToUse;
        this.frequencyMode = frequencyMode;
        this.beaconBitRate = beaconBitRate;
        this.bitRateMode = bitRateMode;
    }

    /**
     *
     */
    public MSK() {
    }

    @Override
    public String toString() {
        return "MSK{" + "frequencyToUse=" + frequencyToUse + ", frequencyMode=" + frequencyMode + ", beaconBitRate=" + beaconBitRate + ", bitRateMode=" + bitRateMode + ", frequencyForMSS=" + frequencyForMSS + '}';
    }

   
    

    /**
     *
     * @return
     */
    public int getBeaconBitRate() {
        return beaconBitRate;
    }

    /**
     *
     * @param beaconBitRate
     */
    public void setBeaconBitRate(int beaconBitRate) {
        this.beaconBitRate = beaconBitRate;
    }

    /**
     *
     * @return
     */
    public String getBitRateMode() {
        return bitRateMode;
    }

    /**
     *
     * @param bitRateMode
     */
    public void setBitRateMode(String bitRateMode) {
        this.bitRateMode = bitRateMode;
    }

    /**
     *
     * @return
     */
    public int getFrequencyForMSS() {
        return frequencyForMSS;
    }

    /**
     *
     * @param frequencyForMSS
     */
    public void setFrequencyForMSS(int frequencyForMSS) {
        this.frequencyForMSS = frequencyForMSS;
    }

    /**
     *
     * @return
     */
    public String getFrequencyMode() {
        return frequencyMode;
    }

    /**
     *
     * @param frequencyMode
     */
    public void setFrequencyMode(String frequencyMode) {
        this.frequencyMode = frequencyMode;
    }

    /**
     *
     * @return
     */
    public float getFrequencyToUse() {
        return frequencyToUse;
    }

    /**
     *
     * @param frequencyToUse
     */
    public void setFrequencyToUse(float frequencyToUse) {
        this.frequencyToUse = frequencyToUse;
    }
}
