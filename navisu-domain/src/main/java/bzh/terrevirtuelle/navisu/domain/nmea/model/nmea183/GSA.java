/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Satellite Status
 * @author Camille AUBRY, Alexis BURGER, Chintana CHANTHABOURY, Teddy VALLEE aubryc
 */
@XmlRootElement(name="GSA")
@XmlAccessorType(XmlAccessType.FIELD)
public class GSA extends NMEA {

    private String autoOrManualSelection;
    private int dimensionFix;
    private final List<Integer> listPRNsOfSatellitesUsed = new ArrayList<>();
    private float pdop;
    private float hdop;
    private float vdop;

    /**
     * GSA - GPS DOP and active satellites. This sentence provides
     * details on the nature of the fix. It includes the numbers of
     * the satellites being used in the current solution and the DOP.
     * DOP (dilution of precision) is an indication of the effect of
     * satellite geometry on the accuracy of the fix.
     * It is a unitless number where smaller is better.
     * For 3D fixes using 4 satellites a 1.0 would be considered
     * to be a perfect number, however for overdetermined solutions
     * it is possible to see numbers below 1.0.
    $GPGSA,A,3,04,05,,09,12,,,24,,,,,2.5,1.3,2.1*39

    Where:
    GSA      Satellite status
    A        Auto selection of 2D or 3D fix (M = manual)
    3        3D fix - values include: 1 = no fix
    2 = 2D fix
    3 = 3D fix
    04,05... PRNs of satellites used for fix (space for 12)
    2.5      pdop (dilution of precision)
    1.3      Horizontal dilution of precision (hdop)
    2.1      Vertical dilution of precision (vdop)
     *39      the checksum data, always begins with *
     * @param device
     * @param sentence
     * @param autoOrManualSelection
     * @param dimensionFix
     * @param pdop
     * @param hdop
     * @param vdop
     */
    public GSA(
            String device,
            String sentence,
            String autoOrManualSelection,
            int dimensionFix,
            float pdop,
            float hdop,
            float vdop) {
        super(device, sentence);
        this.autoOrManualSelection = autoOrManualSelection;
        this.dimensionFix = dimensionFix;
        this.pdop = pdop;
        this.hdop = hdop;
        this.vdop = vdop;
    }

    /**
     *
     */
    public GSA() {
    }

    /**
     *
     * @return
     */
    public float getHdop() {
        return hdop;
    }

    /**
     *
     * @param hdop
     */
    public void setHdop(float hdop) {
        this.hdop = hdop;
    }

    /**
     *
     * @return
     */
    public float getPdop() {
        return pdop;
    }

    /**
     *
     * @param pdop
     */
    public void setPdop(float pdop) {
        this.pdop = pdop;
    }

    /**
     *
     * @return
     */
    public float getVdop() {
        return vdop;
    }

    /**
     *
     * @param vdop
     */
    public void setVdop(float vdop) {
        this.vdop = vdop;
    }

    /**
     *
     * @return
     */
    public String getAutoOrManualSelection() {
        return autoOrManualSelection;
    }

    /**
     *
     * @param autoOrManualSelection
     */
    public void setAutoOrManualSelection(String autoOrManualSelection) {
        this.autoOrManualSelection = autoOrManualSelection;
    }

    /**
     *
     * @return
     */
    public int getDimensionFix() {
        return dimensionFix;
    }

    /**
     *
     * @param dimensionFix
     */
    public void setDimensionFix(int dimensionFix) {
        this.dimensionFix = dimensionFix;
    }

    /**
     *
     * @param prn
     */
    public void addPRNOfSatelliteUsed(int prn) {
        listPRNsOfSatellitesUsed.add(prn);
    }

    /**
     *
     * @param pos
     * @return
     */
    public int getPRNOfSatelliteUsed(int pos) {
        return listPRNsOfSatellitesUsed.get(pos);
    }

    /**
     *
     * @return
     */
    public int getNumPRNOfSatelliteUsed() {
        return listPRNsOfSatellitesUsed.size();
    }

    public List<Integer> getListPRNsOfSatellitesUsed() {
        return listPRNsOfSatellitesUsed;
    }

    @Override
    public String toString() {
        return "GSA{" + "autoOrManualSelection=" + autoOrManualSelection + ", dimensionFix=" + dimensionFix + ", listPRNsOfSatellitesUsed=" + listPRNsOfSatellitesUsed + ", PDOP=" + pdop + ", HDOP=" + hdop + ", VDOP=" + vdop + '}';
    }
    
}
