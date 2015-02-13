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
 * Sentence is transmitted once per minute regardless of the selected baud rate.
 *
 * @author Serge Morvan
 */
@XmlRootElement(name="RMT")
@XmlAccessorType(XmlAccessType.FIELD)
public class RMT
        extends NMEA {

    private String versions;
    private String romChecksum;

    public RMT(String device, String sentence, String versions, String romChecksum) {
        super(device, sentence);
        this.versions = versions;
        this.romChecksum = romChecksum;
    }

    public RMT() {
    }

    /**
     * Get the value of romChecksum
     *
     * @return the value of romChecksum
     */
    public String getRomChecksum() {
        return romChecksum;
    }

    /**
     * Set the value of romChecksum
     *
     * @param romChecksum new value of romChecksum
     */
    public void setRomChecksum(String romChecksum) {
        this.romChecksum = romChecksum;
    }

    /**
     * Get the value of versions
     *
     * @return the value of versions
     */
    public String getVersions() {
        return versions;
    }

    /**
     * Set the value of versions
     *
     * @param versions new value of versions
     */
    public void setVersions(String versions) {
        this.versions = versions;
    }
}
