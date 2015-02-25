package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage08;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsSpare;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.BitVector;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ESI AIS Parser
 *
 * Copyright 2011/2012 by Pierre van de Laar (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 *
 * @author Pierre van de Laar
 * @author Brian C. Lane
 */
/**
 * AIS Message 8 Binary Broadcast Message
 *
 * Field Nr Field Name NrOf Bits (from, to )
 * ------------------------------------------------------------------------ 1
 * messageID 6	( 1, 6) 2	repeatIndicator 2	( 7, 8) 3	mmsi 30	( 9, 38) 4	spare
 * 2	( 39, 40) 5	applicationID 16	( 41, 56) 6	applicationBinaryData 952	(
 * 57,1008) ---- + (maximum) number of bits 1008
 */
@XmlRootElement(name = "ais08")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS08 extends AISMessageImpl implements AISMessage08 {

    /**
     *
     */
    public static final int MINLENGTH = 56;

    /**
     *
     */
    public static final int MAXLENGTH = 1008;

    /**
     *
     * @param length
     * @return
     */
    public static boolean validLength(int length) {
        return (MINLENGTH <= length && length <= MAXLENGTH);
    }

    /**
     * Return the difference in available and needed bits to parse this sixbit
     * as an AISMessage A positive difference indicates that there are more bits
     * available than needed by the standard.
     *
     * @param sb
     * @return
     */
    public static int differenceInBits(Sixbit sb) {
        final int available = sb.available();
        if (available > MAXLENGTH) {
            return (available - MAXLENGTH);
        } else if (available < MINLENGTH) {
            return available - MINLENGTH;
        } else {
            return 0;
        }
    }

    private static final int SPARE_FROM = 39;
    private static final int SPARE_TO = 40;

    private int spare;

    /**
     * spare
     *
     * @return int value of spare (2 bits [39,40])
     */
    public int getSpare() {
        return spare;
    }

    private static final int APPLICATIONID_FROM = 41;
    private static final int APPLICATIONID_TO = 56;

    private int applicationID;

    /**
     * applicationID
     *
     * @return int value of applicationID (16 bits [41,56])
     */
    public int getApplicationID() {
        return applicationID;
    }

    private static final int APPLICATIONBINARYDATA_FROM = 57;
    //private static final int APPLICATIONBINARYDATA_TO = 1008;

    private BitVector applicationBinaryData;

    /**
     * applicationBinaryData
     *
     * @return BitVector value of applicationBinaryData (maximally 952 bits
     * [57,1008])
     */
    public BitVector getApplicationBinaryData() {
        return applicationBinaryData;
    }

    /**
     * AIS08 default constructor
     */
    public AIS08() {
    }

    /**
     * AISMessage 8 constructor
     *
     * @param content AIS content
     * @param prov the provenance of the message
     * @precondition validLength(content.length()) &&
     * AISMessageBase.getMessageId(content)==8
     */
    public AIS08(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);
        assert (validLength(content.length()));
        assert (getMessageID() == 8);

        spare = content.getIntFromTo(SPARE_FROM, SPARE_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare", spare, UtilsSpare.range));
        }
        applicationID = content.getIntFromTo(APPLICATIONID_FROM, APPLICATIONID_TO);
        applicationBinaryData = content.getBitVectorFromTo(APPLICATIONBINARYDATA_FROM, content.length());
    }

    /**
     * Generates a String representing the AIS message. Format: all fields are
     * shown in the order as specified by the standard separated by the
     * SEPARATOR string.
     */
    @Override
    public String toString() {
        return super.toString() + SEPARATOR + Integer.toString(applicationID)
                + SEPARATOR + applicationBinaryData.toString();

    }
}
