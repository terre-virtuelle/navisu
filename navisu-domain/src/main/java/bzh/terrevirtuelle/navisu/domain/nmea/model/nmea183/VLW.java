package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Dual Ground/Water Distance
 *
 * @author Serge Morvan
 */
@XmlRootElement(name="VLW")
@XmlAccessorType(XmlAccessType.FIELD)
public class VLW
        extends NMEA {

    private float dataTotalWaterDistance; 
    private float dataTripWaterDistance;
    private float dataTotalGroundDistance;
    private float dataTripGroundDistance;

    public VLW(String device, 
            String sentence,
            float dataTotalWaterDistance, 
            float dataTripWaterDistance, 
            float dataTotalGroundDistance, 
            float dataTripGroundDistance) {
        super(device, sentence);
        this.dataTotalWaterDistance = dataTotalWaterDistance;
        this.dataTripWaterDistance = dataTripWaterDistance;
        this.dataTotalGroundDistance = dataTotalGroundDistance;
        this.dataTripGroundDistance = dataTripGroundDistance;
    }

    public VLW() {
    }

    /**
     * Get the value of dataTripGroundDistance
     *
     * @return the value of dataTripGroundDistance
     */
    public float getDataTripGroundDistance() {
        return dataTripGroundDistance;
    }

    /**
     * Set the value of dataTripGroundDistance
     *
     * @param dataTripGroundDistance new value of dataTripGroundDistance
     */
    public void setDataTripGroundDistance(float dataTripGroundDistance) {
        this.dataTripGroundDistance = dataTripGroundDistance;
    }

    /**
     * Get the value of dataTripWaterDistance
     *
     * @return the value of dataTripWaterDistance
     */
    public float getDataTripWaterDistance() {
        return dataTripWaterDistance;
    }

    /**
     * Set the value of dataTripWaterDistance
     *
     * @param dataTripWaterDistance new value of dataTripWaterDistance
     */
    public void setDataTripWaterDistance(float dataTripWaterDistance) {
        this.dataTripWaterDistance = dataTripWaterDistance;
    }
   
    /**
     * Get the value of dataTotalGroundDistance
     *
     * @return the value of dataTotalGroundDistance
     */
    public float getDataTotalGroundDistance() {
        return dataTotalGroundDistance;
    }

    /**
     * Set the value of dataTotalGroundDistance
     *
     * @param dataTotalGroundDistance new value of dataTotalGroundDistance
     */
    public void setDataTotalGroundDistance(float dataTotalGroundDistance) {
        this.dataTotalGroundDistance = dataTotalGroundDistance;
    }

    /**
     * Get the value of dataTotalWaterDistance
     *
     * @return the value of dataTotalWaterDistance
     */
    public float getDataTotalWaterDistance() {
        return dataTotalWaterDistance;
    }

    /**
     * Set the value of dataTotalWaterDistance
     *
     * @param dataTotalWaterDistance new value of dataTotalWaterDistance
     */
    public void setDataTotalWaterDistance(float dataTotalWaterDistance) {
        this.dataTotalWaterDistance = dataTotalWaterDistance;
    }

    @Override
    public String toString() {
        return "VLW{" + "dataTotalWaterDistance=" + dataTotalWaterDistance + ", dataTripWaterDistance=" + dataTripWaterDistance + ", dataTotalGroundDistance=" + dataTotalGroundDistance + ", dataTripGroundDistance=" + dataTripGroundDistance + '}';
    }
    
}
