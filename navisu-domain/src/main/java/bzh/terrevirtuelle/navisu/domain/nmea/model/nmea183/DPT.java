package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Depth and offset
 */
@XmlRootElement(name="DPT")
@XmlAccessorType(XmlAccessType.FIELD)
public class DPT
        extends NMEA {

    /**
     *
     * @param device
     * @param sentence
     * @param depth
     * @param offset
     */
    public DPT(
            String device,
            String sentence,
            float depth,
            float offset) {
        super(device, sentence);
        this.depth = depth;
        this.offset = offset;
    }

    /**
     *
     */
    public DPT() {
    }
    private float depth;

    /**
     *
     * @return
     */
    public float getDepth() {
        return this.depth;
    }

    /**
     *
     * @param value
     */
    public void setDepth(float value) {
        this.depth = value;
    }
    private float offset;

    /**
     *
     * @return
     */
    public float getOffset() {
        return this.offset;
    }

    /**
     *
     * @param value
     */
    public void setOffset(float value) {
        this.offset = value;
    }

    @Override
    public String toString() {
        return "DPT{" + "depth=" + depth + ", offset=" + offset + '}';
    }
}
