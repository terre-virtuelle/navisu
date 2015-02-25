package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage09;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.CommunicationState;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsAngle12;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsLatitude27;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsLongitude28;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsPositionInfo;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsSpare;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsTimeStamp;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsTwosComplement;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar, Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
/**
 * Implementation of AIS Message type 9: Standard SAR Aircraft Position Report.
 * <pre>
 * Field Nr     Field Name                          NrOf Bits   (from,  to)
 * ------------------------------------------------------------------------
 *  1           messageID                                  6    (   1,   6)
 *  2           repeatIndicator                            2    (   7,   8)
 *  3           mmsi                                    30    (   9,  38)
 *  4           altitude                                  12    (  39,  50)
 *  5           speedOverGround                           10    (  51,  60)
 *  6           positionAccuracy                           1    (  61,  61)
 *  7           longitude                                 28    (  62,  89)
 *  8           latitude                                  27    (  90, 116)
 *  9           courseOverGround                          12    ( 117, 128)
 * 10           timeStamp                                  6    ( 129, 134)
 * 11           altitudeSensor                             1    ( 135, 135)
 * 12           spare1                                     7    ( 136, 142)
 * 13           dte                                        1    ( 143, 143)
 * 14           spare2                                     3    ( 144, 146)
 * 15           assignedModeFlag                           1    ( 147, 147)
 * 16           raimFlag                                   1    ( 148, 148)
 * 17           communicationStateSelectorFlag             1    ( 149, 149)
 * 18           communicationState                        19    ( 150, 168)
 *                                                      ---- +
 *                           (maximum) number of bits    168
 * </pre>
 *
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
@XmlRootElement(name = "ais09")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS09 extends AISMessageImpl implements AISMessage09 {

    /**
     *
     */
    public static final int LENGTH = 168;

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

    private static final int ALTITUDE_FROM = 39;
    private static final int ALTITUDE_TO = 50;

    private int altitude;

    /**
     * altitude
     *
     * @return int value of altitude (12 bits [39,50])
     */
    public int getAltitude() {
        return altitude;
    }

    private static final int SPEEDOVERGROUND_FROM = 51;
    private static final int SPEEDOVERGROUND_TO = 60;

    private int speedOverGround;

    /**
     * speedOverGround
     *
     * @return int value of speedOverGround (10 bits [51,60])
     */
    public int getSpeedOverGround() {
        return speedOverGround;
    }

    private static final int POSITIONACCURACY_BITINDEX = 61;

    private boolean positionAccuracy;

    /**
     * positionAccuracy
     *
     * @return boolean value of positionAccuracy (bit 61)
     */
    public boolean getPositionAccuracy() {
        return positionAccuracy;
    }

    private static final int LONGITUDE_FROM = 62;
    private static final int LONGITUDE_TO = 89;

    private double longitude;

    /**
     * longitude
     *
     * @return double value of longitude (28 bits [62,89])
     */
    public double getLongitudeInDegrees() {
        return longitude;
    }

    private static final int LATITUDE_FROM = 90;
    private static final int LATITUDE_TO = 116;

    private double latitude;

    /**
     * latitude
     *
     * @return double value of latitude (27 bits [90,116])
     */
    public double getLatitudeInDegrees() {
        return latitude;
    }

    private static final int COURSEOVERGROUND_FROM = 117;
    private static final int COURSEOVERGROUND_TO = 128;

    private int courseOverGround;

    /**
     * courseOverGround
     *
     * @return int value of courseOverGround (12 bits [117,128])
     */
    public int getCourseOverGround() {
        return courseOverGround;
    }

    private static final int TIMESTAMP_FROM = 129;
    private static final int TIMESTAMP_TO = 134;

    private int timeStamp;

    /**
     * timeStamp
     *
     * @return int value of timeStamp (6 bits [129,134])
     */
    public int getTimeStamp() {
        return timeStamp;
    }

    private static final int ALTITUDESENSOR_BITINDEX = 135;

    private boolean altitudeSensor;

    /**
     * altitudeSensor
     *
     * @return boolean value of altitudeSensor (bit 135)
     */
    public boolean getAltitudeSensor() {
        return altitudeSensor;
    }

    private static final int SPARE1_FROM = 136;
    private static final int SPARE1_TO = 142;

    private int spare1;

    /**
     * spare1
     *
     * @return int value of spare1 (7 bits [136,142])
     */
    public int getSpare1() {
        return spare1;
    }

    private static final int DTE_BITINDEX = 143;

    private boolean dte;

    /**
     * dte
     *
     * @return boolean value of dte (bit 143)
     */
    public boolean getDte() {
        return dte;
    }

    private static final int SPARE2_FROM = 144;
    private static final int SPARE2_TO = 146;

    private int spare2;

    /**
     * spare2
     *
     * @return int value of spare2 (3 bits [144,146])
     */
    public int getSpare2() {
        return spare2;
    }

    private static final int ASSIGNEDMODEFLAG_BITINDEX = 147;

    private boolean assignedModeFlag;

    /**
     * Returns the assigned mode flag.
     *
     * @return a boolean value representing the assigned mode flag: <br>
     * false = Station operating in autonomous and continuous mode <br>
     * true = Station operating in assigned mode
     */
    public boolean getAssignedModeFlag() {
        return assignedModeFlag;
    }

    private static final int RAIMFLAG_BITINDEX = 148;

    private boolean raimFlag;

    /**
     * raimFlag
     *
     * @return boolean value of raimFlag (bit 148)
     */
    public boolean getRaimFlag() {
        return raimFlag;
    }

    private static final int COMMUNICATIONSTATESELECTORFLAG_BITINDEX = 149;

    private boolean communicationStateSelectorFlag;

    /**
     * communicationStateSelectorFlag
     *
     * @return boolean value of communicationStateSelectorFlag (bit 149)
     */
    public boolean getCommunicationStateSelectorFlag() {
        return communicationStateSelectorFlag;
    }

    private static final int COMMUNICATIONSTATE_FROM = 150;
    //private static final int COMMUNICATIONSTATE_TO = 168;

    private CommunicationState communicationState;

    /**
     * communicationState
     *
     * @return CommunicationState value of communicationState (19 bits
     * [150,168])
     */
    @Override
    public CommunicationState getCommunicationState() {
        return communicationState;
    }

    /**
     * AIS09 default constructor
     */
    public AIS09() {
    }

    /**
     * AISMessage 9 constructor
     *
     * @param content AIS content
     * @param prov the provenance of the message
     * @precondition validLength(content.length()) &&
     * AISMessageBase.getMessageId(content)==9
     */
    public AIS09(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);
        assert (validLength(content.length()));
        assert (getMessageID() == 9);

        altitude = content.getIntFromTo(ALTITUDE_FROM, ALTITUDE_TO);
        speedOverGround = content.getIntFromTo(SPEEDOVERGROUND_FROM, SPEEDOVERGROUND_TO);
        positionAccuracy = content.getBoolean(POSITIONACCURACY_BITINDEX);
        longitude = UtilsLongitude28.toDegrees(UtilsTwosComplement.convertFrom28Bits(content.getIntFromTo(LONGITUDE_FROM, LONGITUDE_TO)));
        if (!UtilsPositionInfo.isLongitudeSemanticallyCorrect(longitude)) {
            annotations.add(new AISIllegalValueAnnotation("getLongitudeInDegrees", longitude, UtilsPositionInfo.rangeLongitude));
        }
        latitude = UtilsLatitude27.toDegrees(UtilsTwosComplement.convertFrom27Bits(content.getIntFromTo(LATITUDE_FROM, LATITUDE_TO)));
        if (!UtilsPositionInfo.isLatitudeSemanticallyCorrect(latitude)) {
            annotations.add(new AISIllegalValueAnnotation("getLatitudeInDegrees", latitude, UtilsPositionInfo.rangeLatitude));
        }
        courseOverGround = content.getIntFromTo(COURSEOVERGROUND_FROM, COURSEOVERGROUND_TO);
        timeStamp = content.getIntFromTo(TIMESTAMP_FROM, TIMESTAMP_TO);
        altitudeSensor = content.getBoolean(ALTITUDESENSOR_BITINDEX);
        spare1 = content.getIntFromTo(SPARE1_FROM, SPARE1_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare1)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare1", spare1, UtilsSpare.range));
        }
        dte = content.getBoolean(DTE_BITINDEX);
        spare2 = content.getIntFromTo(SPARE2_FROM, SPARE2_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare2)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare2", spare2, UtilsSpare.range));
        }
        assignedModeFlag = content.getBoolean(ASSIGNEDMODEFLAG_BITINDEX);
        raimFlag = content.getBoolean(RAIMFLAG_BITINDEX);
        communicationStateSelectorFlag = content.getBoolean(COMMUNICATIONSTATESELECTORFLAG_BITINDEX);
        if (communicationStateSelectorFlag) {
            communicationState = new Itdma(COMMUNICATIONSTATE_FROM, content);
        } else {
            communicationState = new Sotdma(COMMUNICATIONSTATE_FROM, content);
        }
    }

    /**
     * Returns the speed over ground as a string.
     *
     * @return a string representing the speed over ground
     */
    public String getAltitudeString() {
        String altString;
        if (altitude == 4095) {
            altString = "not available";
        } else if (altitude == 4094) {
            altString = ">=4094";
        } else {
            altString = Integer.toString(altitude);
        }
        return altString;
    }

    /**
     * Returns the speed over ground as a string.
     *
     * @return a string representing the speed over ground
     */
    public String getSpeedOverGroundString() {
        String sogString;
        if (speedOverGround == 1023) {
            sogString = "no SOG";
        } else if (speedOverGround == 1022) {
            sogString = ">=1022";
        } else {
            sogString = Integer.toString(speedOverGround);
        }
        return sogString;
    }

    /**
     * Generates a String representing the AIS message. Format: all fields are
     * shown in the order as specified by the standard separated by the
     * SEPARATOR string.
     */
    @Override
    public String toString() {
        String result = super.toString();
        result += SEPARATOR + getAltitudeString();
        result += SEPARATOR + getSpeedOverGroundString();
        result += SEPARATOR + (positionAccuracy ? "high" : "low") + " accuracy";
        result += SEPARATOR + UtilsPositionInfo.longitudeToString(longitude);
        result += SEPARATOR + UtilsPositionInfo.latitudeToString(latitude);
        result += SEPARATOR + UtilsAngle12.toString(courseOverGround);
        result += SEPARATOR + UtilsTimeStamp.toString(timeStamp);
        result += SEPARATOR + (altitudeSensor ? "barometic" : "GNSS");
        result += SEPARATOR + (dte ? "no DTE" : "with DTE");
        result += SEPARATOR + (assignedModeFlag ? "assigned" : "autonomous") + " mode";
        result += SEPARATOR + "RAIM " + (raimFlag ? "in use" : "not in use");
        result += SEPARATOR + (communicationStateSelectorFlag ? "ITDMA" : "SOTDMA");
        result += SEPARATOR + communicationState.toString();
        return result;
    }
}
