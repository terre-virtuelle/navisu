package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage26;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.CommunicationState;
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
 * AIS Message 26 class Multiple Slot Binary Message
 *
 * Field Nr Field Name NrOf Bits (from, to )
 * ------------------------------------------------------------------------ 1
 * messageID 6	( 1, 6) 2	repeatIndicator 2	( 7, 8) 3	mmsi 30	( 9, 38) 4
 * destinationIndicator 1	( 39, 39) 5	binaryDataFlag 1	( 40, 40) 6
 * conditionalIDsAndData 1004	( 41,1044) destinationID	30 ( 41, 70)
 * //conditional on destionIndicator applicationID	16 ( 41, 56) or ( 71, 86)
 * //conditional on binaryDataFlag applicationBinaryData	maximally 1004 (
 * 41,1044) or ( 71,1044) or ( 57,1044) or ( 87,1044) 7
 * communicationStateSelectorFlag 1	(1045,1045) 8	communicationState 19
 * (1046,1064) ---- + (maximum) number of bits 1064
 */
@XmlRootElement(name = "ais26")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS26 extends AISMessageImpl implements AISMessage26 {

    /**
     *
     */
    public static final int MINLENGTH = 60;

    /**
     *
     */
    public static final int MAXLENGTH = 1064;

    /**
     *
     * @param content
     * @return
     */
    public static boolean valid(Sixbit content) {
        if (content.length() < MINLENGTH || content.length() > MAXLENGTH) {
            return false;
        } else {
            int minLengthContent = MINLENGTH;
            if (content.getBoolean(DESTINATIONINDICATOR_BITINDEX)) //destinationIndicator
            {
                minLengthContent += 30;
            }
            if (content.getBoolean(BINARYDATAFLAG_BITINDEX)) //	binaryDataFlag 
            {
                minLengthContent += 16;
            }
            return minLengthContent <= content.length();
        }
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
            int minLengthContent = MINLENGTH;
            if (sb.getBoolean(DESTINATIONINDICATOR_BITINDEX)) //destinationIndicator
            {
                minLengthContent += 30;
            }
            if (sb.getBoolean(BINARYDATAFLAG_BITINDEX)) //	binaryDataFlag 
            {
                minLengthContent += 16;
            }
            if (available < minLengthContent) {
                //too few bits
                return available - minLengthContent;
            } else {
                //enough bits
                return 0;
            }
        }
    }

    private static final int DESTINATIONINDICATOR_BITINDEX = 39;

    private boolean destinationIndicator;

    /**
     * destinationIndicator
     *
     * @return boolean value of destinationIndicator (bit 39)
     */
    public boolean getDestinationIndicator() {
        return destinationIndicator;
    }

    private static final int DESTINATIONID_FROM = 41;
    private static final int DESTINATIONID_TO = 70;

    private int destinationID;

    /**
     * destinationID
     *
     * @return int value of destinationID (30 bits [41,70])
     * @precondition getDestinationIndicator() == true
     */
    public int getDestinationID() {
        return destinationID;
    }

    private static final int BINARYDATAFLAG_BITINDEX = 40;

    private boolean binaryDataFlag;

    /**
     * binaryDataFlag
     *
     * @return boolean value of binaryDataFlag (bit 40)
     */
    public boolean getBinaryDataFlag() {
        return binaryDataFlag;
    }

    private int applicationID;

    /**
     * applicationID
     *
     * @return int value of applicationID (16 bits)
     * @precondition getBinaryDataFlag() == true
     */
    public int getApplicationID() {
        return applicationID;
    }

    private BitVector applicationBinaryData;

    /**
     * applicationBinaryData
     *
     * @return BitVector value of applicationBinaryData (maximally 1004 bits
     * [41,1044])
     */
    public BitVector getApplicationBinaryData() {
        return applicationBinaryData;
    }

    private boolean communicationStateSelectorFlag;

    /**
     * communicationStateSelectorFlag
     *
     * @return boolean value of communicationStateSelectorFlag (bit 1045)
     */
    public boolean getCommunicationStateSelectorFlag() {
        return communicationStateSelectorFlag;
    }

    private CommunicationState communicationState;

    /**
     * communicationState
     *
     * @return CommunicationState value of communicationState (19 bits
     * [1046,1064])
     */
    public CommunicationState getCommunicationState() {
        return communicationState;
    }

    /**
     * AIS26 default constructor
     */
    public AIS26() {
    }

    /**
     * AISMessage 26 constructor
     *
     * @param content AIS content
     * @param prov the provenance of the message
     * @precondition valid(content) && AISMessageBase.getMessageId(content)==26
     */
    public AIS26(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);

        assert (valid(content));
        assert (getMessageID() == 26);

        /* Parse the Message 26 */
        destinationIndicator = content.getBoolean(DESTINATIONINDICATOR_BITINDEX);
        binaryDataFlag = content.getBoolean(BINARYDATAFLAG_BITINDEX);
        int startPos = DESTINATIONID_FROM;
        if (destinationIndicator) {
            destinationID = content.getIntFromTo(DESTINATIONID_FROM, DESTINATIONID_TO);
            startPos = DESTINATIONID_TO + 1;
        }
        if (binaryDataFlag) {
            applicationID = content.getIntFromTo(startPos, startPos + 15);
            startPos += 16;
        }
        applicationBinaryData = content.getBitVectorFromTo(startPos, content.length() - 20);
        communicationStateSelectorFlag = content.getBoolean(content.length() - 19);
        if (communicationStateSelectorFlag) {
            communicationState = new Itdma(content.length() - 18, content);
        } else {
            communicationState = new Sotdma(content.length() - 18, content);
        }
    }
}
