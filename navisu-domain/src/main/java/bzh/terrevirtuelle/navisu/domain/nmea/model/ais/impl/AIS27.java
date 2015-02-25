package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage27;
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
 * AIS Message 27 Class Long Range AIS Broadcast message
 *
 * Field Nr Field Name NrOf Bits (from, to )
 * ------------------------------------------------------------------------ 1
 * messageID 6	( 1, 6) 2	repeatIndicator 2	( 7, 8) 3	mmsi 30	( 9, 38) 4
 * positionAccuracy 1	( 39, 39) 5	raimFlag 1	( 40, 40) 6	navigationalStatus 4	(
 * 41, 44) 7	longitude 18	( 45, 62) 8	latitude 17	( 63, 79) 9	speedOverGround 6
 * ( 80, 85) 10	courseOverGround 9	( 86, 94) 11	statusOfCurrentGNSSPosition 1	(
 * 95, 95) 12	spare 1	( 96, 96) ---- + (maximum) number of bits 96
 */
@XmlRootElement(name = "ais27")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS27 extends AISMessageImpl implements AISMessage27 {

    /**
     *
     */
    public static final int LENGTH = 96;

    /**
     *
     * @param length
     * @return
     */
    public static boolean validLength(int length) {
        return (length == LENGTH);
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
        return (sb.available() - LENGTH);
    }

    private static final int POSITIONACCURACY_BITINDEX = 39;

    private boolean positionAccuracy;

    /**
     * positionAccuracy
     *
     * @return boolean value of positionAccuracy (bit 39)
     */
    public boolean getPositionAccuracy() {
        return positionAccuracy;
    }

    private static final int RAIMFLAG_BITINDEX = 40;

    private boolean raimFlag;

    /**
     * raimFlag
     *
     * @return boolean value of raimFlag (bit 40)
     */
    public boolean getRaimFlag() {
        return raimFlag;
    }

    private static final int NAVIGATIONALSTATUS_FROM = 41;
    private static final int NAVIGATIONALSTATUS_TO = 44;

    private int navigationalStatus;

    /**
     * navigationalStatus
     *
     * @return int value of navigationalStatus (4 bits [41,44])
     */
    public int getNavigationalStatus() {
        return navigationalStatus;
    }

    private static final int LONGITUDE_FROM = 45;
    private static final int LONGITUDE_TO = 62;

    private double longitude;

    /**
     * longitude
     *
     * @return double value of longitude in degrees (18 bits [45,62])
     */
    public double getLongitudeInDegrees() {
        return longitude;
    }

    private static final int LATITUDE_FROM = 63;
    private static final int LATITUDE_TO = 79;

    private double latitude;

    /**
     * latitude
     *
     * @return double value of latitude in degrees (17 bits [63,79])
     */
    public double getLatitudeInDegrees() {
        return latitude;
    }

    private static final int SPEEDOVERGROUND_FROM = 80;
    private static final int SPEEDOVERGROUND_TO = 85;

    private int speedOverGround;

    /**
     * speedOverGround
     *
     * @return int value of speedOverGround (6 bits [80,85])
     */
    public int getSpeedOverGround() {
        return speedOverGround;
    }

    private static final int COURSEOVERGROUND_FROM = 86;
    private static final int COURSEOVERGROUND_TO = 94;

    private int courseOverGround;

    /**
     * courseOverGround
     *
     * @return int value of courseOverGround (9 bits [86,94])
     */
    public int getCourseOverGround() {
        return courseOverGround;
    }

    private static final int STATUSOFCURRENTGNSSPOSITION_BITINDEX = 95;

    private boolean statusOfCurrentGNSSPosition;

    /**
     * statusOfCurrentGNSSPosition
     *
     * @return boolean value of statusOfCurrentGNSSPosition (bit 95)
     */
    public boolean getStatusOfCurrentGNSSPosition() {
        return statusOfCurrentGNSSPosition;
    }

    private static final int SPARE_FROM = 96;
    private static final int SPARE_TO = 96;

    private int spare;

    /**
     * spare
     *
     * @return int value of spare (1 bits [96,96])
     */
    public int getSpare() {
        return spare;
    }

    /**
     *  AIS27 default constructor
     */
    public AIS27() {
    }

    /**
     * AISMessage 27 constructor
     *
     * @param content AIS content
     * @param prov the provenance of the message
     * @precondition validLength(content.length()) &&
     * AISMessageBase.getMessageId(content)==27
     */
    public AIS27(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);

        assert (validLength(content.length()));
        assert (getMessageID() == 27);

        // According to ITU-R M.1371-4 page 135 - table 81: Repeat Indicator Always 3 
        if (getRepeatIndicator() != 3) {
            annotations.add(new AISIllegalValueAnnotation("getRepeatIndicator", getRepeatIndicator(), "{3}"));
        }

        /* Parse the Message 27 */
        positionAccuracy = content.getBoolean(POSITIONACCURACY_BITINDEX);
        raimFlag = content.getBoolean(RAIMFLAG_BITINDEX);
        navigationalStatus = content.getIntFromTo(NAVIGATIONALSTATUS_FROM, NAVIGATIONALSTATUS_TO);
        longitude = UtilsLongitude18.toDegrees(UtilsTwosComplement.convertFrom18Bits(content.getIntFromTo(LONGITUDE_FROM, LONGITUDE_TO)));
        if (!UtilsPositionInfo.isLongitudeSemanticallyCorrect(longitude)) {
            annotations.add(new AISIllegalValueAnnotation("getLongitudeInDegrees", longitude, UtilsPositionInfo.rangeLongitude));
        }
        latitude = UtilsLatitude17.toDegrees(UtilsTwosComplement.convertFrom17Bits(content.getIntFromTo(LATITUDE_FROM, LATITUDE_TO)));
        if (!UtilsPositionInfo.isLatitudeSemanticallyCorrect(latitude)) {
            annotations.add(new AISIllegalValueAnnotation("getLatitudeInDegrees", latitude, UtilsPositionInfo.rangeLatitude));
        }
        speedOverGround = content.getIntFromTo(SPEEDOVERGROUND_FROM, SPEEDOVERGROUND_TO);
        courseOverGround = content.getIntFromTo(COURSEOVERGROUND_FROM, COURSEOVERGROUND_TO);
        statusOfCurrentGNSSPosition = content.getBoolean(STATUSOFCURRENTGNSSPOSITION_BITINDEX);
        spare = content.getIntFromTo(SPARE_FROM, SPARE_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare", spare, UtilsSpare.range));
        }
    }
}
