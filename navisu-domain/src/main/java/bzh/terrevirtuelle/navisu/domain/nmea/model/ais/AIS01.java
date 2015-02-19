/**
 *
 * Original Designers : RAY, STROH, ALESSIO 
 * Modified : Serge Morvan Enib 09/2009, 2014
 *
 *****************************************************************************
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.ais;
        
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "ais1")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS01
        extends AIS135 {

    public AIS01() {
    }
 
    public AIS01(float rot, float cog, float sog, int navigationalStatus, float heading, float latitude, float longitude, int second, int MMSI, String device) {
        super(rot, cog, sog, navigationalStatus, heading, latitude, longitude, second, MMSI, device);
    }

    

    /**
     * decodeFrame : decode AIS message of type 1
     *
     */
    @Override
    public void decodeFrame() {
        if (messageAisBinary.length() == 167) {
            MMSI = binaryToInt(messageAisBinary, 8, 38);
            navigationalStatus = binaryToInt(messageAisBinary, 38, 42);
            sog = (float) (0.1 * binaryToInt(messageAisBinary, 50, 60));
            cog = (float) (0.1 * binaryToInt(messageAisBinary, 116, 128));
            heading = (float) binaryToInt(messageAisBinary, 128, 137);
            rot = (float) complementToInt(messageAisBinary, 42, 50);
            longitude = ((float) (0.0001 * complementToInt(messageAisBinary, 61, 89))) / 60;
            latitude = ((float) (0.0001 * complementToInt(messageAisBinary, 89, 116))) / 60;
        }
    }

    @Override
    public String toString() {
        return "AIS1{" + "Device = " + device
                + "MMSI = " + MMSI
                + ", STATUS = " + navigationalStatus
                + ", HEAD =" + heading
                + ", COG = " + cog
                + ", SOG = " + sog
                + ", LAT = " + latitude
                + ", LONG = " + longitude
                + ", ROT = " + rot + '}';
    }

}
/**
 * end AISMessageType1
 */
