package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessageClassBPositionReport;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsAngle12;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsAngle9;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsLatitude27;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsLongitude28;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsPositionInfo;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsSpare;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsTimeStamp;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsTwosComplement;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
import java.text.DecimalFormat;


/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar, Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
/**
 * Common implementation superclass for AIS Message 18 and 19: Standard/Extended
 * Class B Equipment Position Report.
 * <pre>
 * Field Nr     Field Name                          NrOf Bits   (from, to )
 * ------------------------------------------------------------------------
 *  1           messageID                                  6    (   1,   6)
 *  2           repeatIndicator                            2    (   7,   8)
 *  3           mmsi                                    30    (   9,  38)
 *  4           spare1                                     8    (  39,  46)
 *  5           speedOverGround                           10    (  47,  56)
 *  6           positionAccuracy                           1    (  57,  57)
 *  7           longitude                                 28    (  58,  85)
 *  8           latitude                                  27    (  86, 112)
 *  9           courseOverGround                          12    ( 113, 124)
 * 10           trueHeading                                9    ( 125, 133)
 * 11           timeStamp                                  6    ( 134, 139)
 * </pre>
 *
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
abstract class AISMessageClassBPositionReportImpl
        extends AISMessageImpl
        implements AISMessageClassBPositionReport {

    /**
     * The first position of the first set of spare bits.
     */
    protected static final int SPARE1_FROM = 39;

    /**
     * The last position of the first set of spare bits.
     */
    protected static final int SPARE1_TO = 46;

    /**
     * The value of the first set of spare bits.
     */
    protected int spare1;

    /**
     * Returns the first set of spare bits.
     *
     * @return the value of the first set of spare bits. This should be zero.
     */
    public int getSpare1() {
        return spare1;
    }

    /**
     * The position of the first bit of the speed over ground.
     */
    protected static final int SPEEDOVERGROUND_FROM = 47;

    /**
     * The position of the last bit of the speed over ground.
     */
    protected static final int SPEEDOVERGROUND_TO = 56;
    protected int speedOverGround;

    /**
     * Returns the speed over ground.
     *
     * @return an integer value in the range of 0 to 1023, representing the
     * speed over ground in 1/10 knot steps (0-102.2 knots) <br>
     * 1023 = not available <br>
     * 1022 = 102.2 knots or higher
     */
    public int getSpeedOverGround() {
        return speedOverGround;
    }

    /**
     * The position of the position accuracy flag.
     */
    protected static final int POSITIONACCURACY_BITINDEX = 57;

    /**
     * The value of the position accuracy flag.
     */
    protected boolean positionAccuracy;

    /**
     * Returns the position accuracy.
     *
     * @return a boolean value representing position accuracy: <br>
     * true = high (&le; 10 m) <br>
     * false = low (&gt; 10 m)
     */
    public boolean getPositionAccuracy() {
        return positionAccuracy;
    }

    /**
     * The position of the first bit of the longitude.
     */
    protected static final int LONGITUDE_FROM = 58;

    /**
     * The position of the last bit of the longitude.
     */
    protected static final int LONGITUDE_TO = 85;

    /**
     * The longitude.
     */
    protected double longitude;

    /**
     * Returns the longitude.
     *
     * @return a double value representing the longitude in degrees
     * (&plusmn;180&deg;, East = positive, West = negative). <br>
     * 181&deg; = (6791AC0 hex) = not available
     */
    public double getLongitudeInDegrees() {
        return longitude;
    }

    /**
     * The position of the first bit of the latitude.
     */
    protected static final int LATITUDE_FROM = 86;

    /**
     * The position of the last bit of the latitude.
     */
    protected static final int LATITUDE_TO = 112;

    /**
     * The latitude
     */
    protected double latitude;

    /**
     * Returns the latitude.
     *
     * @return a double value representing the latitude in degrees
     * (&plusmn;90&deg;, North = positive, South = negative). <br>
     * 91&deg; = (3412140 hex) = not available
     */
    public double getLatitudeInDegrees() {
        return latitude;
    }

    /**
     * The position of the first bit of the course over ground.
     */
    protected static final int COURSEOVERGROUND_FROM = 113;

    /**
     * The position of the last bit of the course over ground.
     */
    protected static final int COURSEOVERGROUND_TO = 124;

    /**
     * The course over ground.
     */
    protected int courseOverGround;

    /**
     * Returns the course over ground. This value can be analyzed further with
     * utility class {@link UtilsCourseOverGround12}.
     *
     * @return an integer value representing the course over ground in 1/10&deg;
     * for values in the range of 0 to 3599.<br>
     * 3600 (E10h) = not available. <br>
     * 3601 or higher should not be used
     */
    public int getCourseOverGround() {
        return courseOverGround;
    }

    /**
     * The position of the first bit of the true heading.
     */
    protected static final int TRUEHEADING_FROM = 125;

    /**
     * The position of the last bit of the true heading.
     */
    protected static final int TRUEHEADING_TO = 133;

    /**
     * The true heading.
     */
    protected int trueHeading;

    /**
     * Returns the true heading.
     *
     * @return an integer value representing the true heading in degrees
     * (0-359). <br>
     * 511 indicates not available
     */
    public int getTrueHeading() {
        return trueHeading;
    }

    /**
     * The position of the first bit of the time stamp.
     */
    protected static final int TIMESTAMP_FROM = 134;

    /**
     * The position of the last bit of the time stamp.
     */
    protected static final int TIMESTAMP_TO = 139;

    /**
     * The time stamp.
     */
    protected int timeStamp;

    /**
     * Returns the time stamp contained in the message. This can be analyzed
     * further using utility class {@link UtilsTimeStamp}.
     *
     * @return an integer value representing the UTC second when the report was
     * generated by the electronic position fixing system (EPFS) (0-59) <br>
     * 60 if time stamp is not available <br>
     * 61 if positioning system is in manual input mode <br>
     * 62 if electronic position fixing system operates in estimated (dead
     * reckoning) mode <br>
     * 63 if the positioning system is inoperative
     */
    public int getTimeStamp() {
        return timeStamp;
    }

    /**
     * The value of the second set of spare bits. Note that the number of bits
     * differs among the subclasses.
     */
    protected int spare2;

    /**
     * Returns the second set of spare bits.
     *
     * @return the value of the second set of spare bits. This should be zero.
     */
    public int getSpare2() {
        return spare2;
    }

    /**
     * The value of the RAIM flag. Note that the position in the message differs
     * among the subclasses.
     */
    protected boolean raimFlag;

    /**
     * Returns the RAIM flag, which describes the receiver autonomous integrity
     * monitoring status of the electronic position fixing device.
     *
     * @return a boolean value: <br>
     * false = RAIM not in use <br>
     * true = RAIM in use
     */
    public boolean getRaimFlag() {
        return raimFlag;
    }

    /**
     * The value of the assigned mode flag. Note that the position in the
     * message differs among the subclasses.
     */
    protected boolean assignedModeFlag;

    /**
     * The format we use to format the speed over ground
     */
    private static final DecimalFormat SOG_FORMAT = new DecimalFormat("##0.0");

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
            sogString = ">=102.2";
        } else {
            sogString = SOG_FORMAT.format(speedOverGround / 10.0);
        }
        return sogString;
    }

    /**
     * Returns the true heading as a string.
     *
     * @return a string representing the true heading
     */
    public String getTrueHeadingString() {
        String headingString;
        if (trueHeading == 511) {
            headingString = "no heading";
        } else if (trueHeading > 359) {
            headingString = "invalid heading";
        } else {
            headingString = Integer.toString(trueHeading);
        }
        return headingString;
    }

    public AISMessageClassBPositionReportImpl() {
    }

    /**
     * Constructor for type 18 or 19 AIS messages. This initializes the fields
     * that have the same positions in the message for both message types.
     *
     * @param content the content of the message
     * @param provenance the provenance of the message
     */
    public AISMessageClassBPositionReportImpl(Sixbit content, VDMMessageProvenance provenance) {
        super(content, provenance);
        spare1 = content.getIntFromTo(SPARE1_FROM, SPARE1_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare1)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare1", spare1, UtilsSpare.range));
        }
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
        if (!UtilsAngle12.isAngleSemanticallyCorrect(courseOverGround)) {
            annotations.add(new AISIllegalValueAnnotation("getCourseOverGround", courseOverGround, UtilsAngle12.range));
        }
        trueHeading = content.getIntFromTo(TRUEHEADING_FROM, TRUEHEADING_TO);
        if (!UtilsAngle9.isAngleSemanticallyCorrect(trueHeading)) {
            annotations.add(new AISIllegalValueAnnotation("getTrueHeading", trueHeading, UtilsAngle9.range));
        }
        timeStamp = content.getIntFromTo(TIMESTAMP_FROM, TIMESTAMP_TO);
    }

    /**
     * Generates a String representing the AIS message. Format: all fields are
     * shown in the order as specified by the standard separated by the
     * SEPARATOR string.
     */
    @Override
    public String toString() {
        String result = super.toString();
        result += SEPARATOR + getSpeedOverGroundString();
        result += SEPARATOR + (positionAccuracy ? "high" : "low") + " accuracy";
        result += SEPARATOR + UtilsPositionInfo.longitudeToString(longitude);
        result += SEPARATOR + UtilsPositionInfo.latitudeToString(latitude);
        result += SEPARATOR + UtilsAngle12.toString(courseOverGround);
        result += SEPARATOR + getTrueHeadingString();
        result += SEPARATOR + UtilsTimeStamp.toString(timeStamp);
        return result;
    }
}
