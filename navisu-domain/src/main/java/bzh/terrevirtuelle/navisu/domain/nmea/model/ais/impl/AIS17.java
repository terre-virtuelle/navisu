package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage17;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsLatitude17;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsLongitude18;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsPositionInfo;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsSpare;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsTwosComplement;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
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
 * AIS Message 17 GNSS Broadcast binary message
 *
 * Field Nr Field Name NrOf Bits (from, to )
 * ------------------------------------------------------------------------ 1
 * messageID 6	( 1, 6) 2	repeatIndicator 2	( 7, 8) 3	mmsi 30	( 9, 38) 4	spare1
 * 2	( 39, 40) 5	longitude 18	( 41, 58) 6	latitude 17	( 59, 75) 7	spare2 5	( 76,
 * 80) 8	messageType 6	( 81, 86) 9	stationID 10	( 87, 96) 10	zCount 13	( 97,
 * 109) 11	sequenceNumber 3	( 110, 112) 12	n 5	( 113, 117) 13	health 3	( 118,
 * 120) 14	dgnssDataWords 696	( 121, 816) ---- + (maximum) number of bits 816
 */
@XmlRootElement(name = "ais17")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS17 extends AISMessageImpl implements AISMessage17 {

    /**
     *
     */
    public static final int MINLENGTH = 80;

    /**
     *
     */
    public static final int STEPSIZE = 24;

    /**
     *
     */
    public static final int ONEELEMENTLENGTH = 144; 		//TODO: is bits without words allowed? i.e. 120

    /**
     *
     */
    public static final int MAXLENGTH = 816;

    /**
     *
     * @param length
     * @return
     */
    public static boolean validLength(int length) {
        return (length == MINLENGTH)
                || ((ONEELEMENTLENGTH <= length)
                && (length <= MAXLENGTH)
                && ((MAXLENGTH - length) % STEPSIZE == 0));
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
        if (available >= MAXLENGTH) {
            return (available - MAXLENGTH);
        } else if (available >= ONEELEMENTLENGTH) {
            return (available - ONEELEMENTLENGTH) % STEPSIZE;
        } else {
            return available - MINLENGTH;
        }
    }

    private static final int SPARE1_FROM = 39;
    private static final int SPARE1_TO = 40;

    private int spare1;

    /**
     * spare1
     *
     * @return int value of spare1 (2 bits [39,40])
     */
    public int getSpare1() {
        return spare1;
    }

    private static final int LONGITUDE_FROM = 41;
    private static final int LONGITUDE_TO = 58;

    private double longitude;

    /**
     * longitude
     *
     * @return double value of longitude in degrees (18 bits [41,58])
     */
    public double getLongitudeInDegrees() {
        return longitude;
    }

    private static final int LATITUDE_FROM = 59;
    private static final int LATITUDE_TO = 75;

    private double latitude;

    /**
     * latitude
     *
     * @return double value of latitude in degrees (17 bits [59,75])
     */
    public double getLatitudeInDegrees() {
        return latitude;
    }

    private static final int SPARE2_FROM = 76;
    private static final int SPARE2_TO = 80;

    private int spare2;

    /**
     * spare2
     *
     * @return int value of spare2 (5 bits [76,80])
     */
    public int getSpare2() {
        return spare2;
    }

    private static final int MESSAGETYPE_FROM = 81;
    private static final int MESSAGETYPE_TO = 86;

    private int messageType;

    /**
     * messageType
     *
     * @return int value of messageType (6 bits [81,86])
     * @precondition getDgnssDataWords().length > 0
     */
    public int getMessageType() {
        return messageType;
    }

    private static final int STATIONID_FROM = 87;
    private static final int STATIONID_TO = 96;

    private int stationID;

    /**
     * stationID
     *
     * @return int value of stationID (10 bits [87,96])
     * @precondition getDgnssDataWords().length > 0
     */
    public int getStationID() {
        return stationID;
    }

    private static final int ZCOUNT_FROM = 97;
    private static final int ZCOUNT_TO = 109;

    private int zCount;

    /**
     * zCount
     *
     * @return int value of zCount (13 bits [97,109])
     * @precondition getDgnssDataWords().length > 0
     */
    public int getZCount() {
        return zCount;
    }

    private static final int SEQUENCENUMBER_FROM = 110;
    private static final int SEQUENCENUMBER_TO = 112;

    private int sequenceNumber;

    /**
     * sequenceNumber
     *
     * @return int value of sequenceNumber (3 bits [110,112])
     * @precondition getDgnssDataWords().length > 0
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    private static final int N_FROM = 113;
    private static final int N_TO = 117;

    private int n;

    /**
     * n
     *
     * @return int value of n (5 bits [113,117])
     * @precondition getDgnssDataWords().length > 0
     */
    public int getN() {
        return n;
    }

    private static final int HEALTH_FROM = 118;
    private static final int HEALTH_TO = 120;

    private int health;

    /**
     * health
     *
     * @return int value of health (3 bits [118,120])
     * @precondition getDgnssDataWords().length > 0
     */
    public int getHealth() {
        return health;
    }

    private static final int DGNSSDATAWORDS_FROM = 121;
//	private static final int DGNSSDATAWORDS_TO = 816;

    private int[] dgnssDataWords;

    /**
     * dgnssDataWords
     *
     * @return int[] value of dgnssDataWords (696 bits [121,816])
     */
    public int[] getDgnssDataWords() {
        return dgnssDataWords;
    }

    /**
     *  AIS17 default constructor
     */
    public AIS17() {
    }

    /**
     * AISMessage 17 constructor
     *
     * @param content AIS content
     * @param prov the provenance of the message
     * @precondition validLength(content.length()) &&
     * AISMessageBase.getMessageId(content)==17
     */
    public AIS17(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);
        assert (validLength(content.length()));
        assert (getMessageID() == 17);

        spare1 = content.getIntFromTo(SPARE1_FROM, SPARE1_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare1)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare1", spare1, UtilsSpare.range));
        }
        longitude = UtilsLongitude18.toDegrees(UtilsTwosComplement.convertFrom18Bits(content.getIntFromTo(LONGITUDE_FROM, LONGITUDE_TO)));
        if (!UtilsPositionInfo.isLongitudeSemanticallyCorrect(longitude)) {
            annotations.add(new AISIllegalValueAnnotation("getLongitudeInDegrees", longitude, UtilsPositionInfo.rangeLongitude));
        }
        latitude = UtilsLatitude17.toDegrees(UtilsTwosComplement.convertFrom17Bits(content.getIntFromTo(LATITUDE_FROM, LATITUDE_TO)));
        if (!UtilsPositionInfo.isLatitudeSemanticallyCorrect(latitude)) {
            annotations.add(new AISIllegalValueAnnotation("getLatitudeInDegrees", latitude, UtilsPositionInfo.rangeLatitude));
        }
        spare2 = content.getIntFromTo(SPARE2_FROM, SPARE2_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare2)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare2", spare2, UtilsSpare.range));
        }
        if (content.length() > MINLENGTH) {
            messageType = content.getIntFromTo(MESSAGETYPE_FROM, MESSAGETYPE_TO);
            stationID = content.getIntFromTo(STATIONID_FROM, STATIONID_TO);
            zCount = content.getIntFromTo(ZCOUNT_FROM, ZCOUNT_TO);
            sequenceNumber = content.getIntFromTo(SEQUENCENUMBER_FROM, SEQUENCENUMBER_TO);
            n = content.getIntFromTo(N_FROM, N_TO);
            //TODO: check consistency between N and (content.length()-DGNSSDATAWORDS_FROM)/STEPSIZE   	
            health = content.getIntFromTo(HEALTH_FROM, HEALTH_TO);
            dgnssDataWords = new int[(content.length() - DGNSSDATAWORDS_FROM) / STEPSIZE];
            for (int i = 0; i < dgnssDataWords.length; i++) {
                int fromIndex = i * STEPSIZE + DGNSSDATAWORDS_FROM;
                int toIndex = fromIndex + STEPSIZE - 1;
                dgnssDataWords[i] = content.getIntFromTo(fromIndex, toIndex);
            }
        } else {
            dgnssDataWords = new int[0];
        }
    }
}
