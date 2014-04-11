/******************************************************************************
 * Modified in 05/2007.
 *
 * Original Designers : RAY
 *
 ******************************************************************************/
package bzh.terrevirtuelle.navisu.domain.nmea.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Safety related Broadcast Message
 * 
 */
@XmlRootElement(name="ais14")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS14 extends AISMessage {

    private String SecurityMessage;

    public AIS14() {
    }

    /**
     * decodeFrame : decode AIS message of type 14
     *
     */
    @Override
    public void decodeFrame() {

        if (messageAisBinary.length() == 1008) {

            MMSI = binaryToInt(messageAisBinary,8, 38);
            SecurityMessage = binaryToString(messageAisBinary,40, 1008);
        }
    }

   
    @Override
    public String toString() {
        return new String("AIS14{ISMM=" + MMSI + ", MESS=" + SecurityMessage + "}");
    }

    /**
     *
     * @return
     */
    public String getSecurityMessage() {
        return SecurityMessage;
    }

    /**
     *
     * @param SecurityMessage
     */
    public void setSecurityMessage(String SecurityMessage) {
        this.SecurityMessage = SecurityMessage;
    }

}
/** end AISMessageType14 */
