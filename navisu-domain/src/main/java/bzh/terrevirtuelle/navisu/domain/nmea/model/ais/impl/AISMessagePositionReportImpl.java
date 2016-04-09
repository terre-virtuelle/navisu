package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import java.text.DecimalFormat;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsAngle12;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsLatitude27;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsLongitude28;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsNavStatus;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsPositionInfo;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsRateOfTurn8;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsSpecialManoeuvreIndicator2;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsTimeStamp;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsAngle9;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsSpare;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsTwosComplement;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * ESI AIS Parser
 *
 * Copyright 2011/2012 by Pierre van de Laar (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 */
/**
 * This is the base class implementation for all AIS position report messages.
 * It implements the fields that they all have in common.
 * <pre>
 * Field Nr     Field Name                          NrOf Bits   (from,  to)
 * ------------------------------------------------------------------------
 *  1           messageID                                  6    (   1,   6)
 *  2           repeatIndicator                            2    (   7,   8)
 *  3           mmsi                                    30    (   9,  38)
 *  4           navigationalStatus                         4    (  39,  42)
 *  5           rateOfTurn                                 8    (  43,  50)
 *  6           speedOverGround                           10    (  51,  60)
 *  7           positionAccuracy                           1    (  61,  61)
 *  8           longitude                                 28    (  62,  89)
 *  9           latitude                                  27    (  90, 116)
 * 10           courseOverGround                          12    ( 117, 128)
 * 11           trueHeading                                9    ( 129, 137)
 * 12           timeStamp                                  6    ( 138, 143)
 * 13           specialManoeuvre                           2    ( 144, 145)
 * 14           spare                                      3    ( 146, 148)
 * 15           raimFlag                                   1    ( 149, 149)
 * 16           communicationState                        19    ( 150, 168)
 *                                                       ---- +
 *                       (maximum) number of bits        168
 * </pre>
 *
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
@XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
abstract class AISMessagePositionReportImpl
        extends AISMessageImpl
        implements PosReportInterface {

    /**
     * The position of the first bit of the navigational status.
     */
    private static final int NAVIGATIONALSTATUS_FROM = 39;

    /**
     * The position of the last bit of the navigational status.
     */
    private static final int NAVIGATIONALSTATUS_TO = 42;

    /**
     * The navigational status.
     */
    @XmlElement(name = "ns")
    private int navigationalStatus;
    
    @XmlElement(name = "nst")
    private String navigationalStatusText;

    /**
     * Get the value of navigationalStatusText
     *
     * @return the value of navigationalStatusText
     */
    public String getNavigationalStatusText() {
        return navigationalStatusText;
    }

    /**
     * Set the value of navigationalStatusText
     *
     * @param navigationalStatusText new value of navigationalStatusText
     */
    public void setNavigationalStatusText(String navigationalStatusText) {
        this.navigationalStatusText = navigationalStatusText;
    }


    /**
     * Returns the navigational status. This can be further analyzed using
     * utility class {@link UtilsNavStatus}.
     *
     * @return an integer in the range of 0 to 15
     */
    @Override
    public int getNavigationalStatus() {
        return navigationalStatus;
    }

    /**
     * The position of the first bit of the rate of turn.
     */
    private static final int RATEOFTURN_FROM = 43;

    /**
     * The position of the last bit of the rate of turn.
     */
    private static final int RATEOFTURN_TO = 50;

    /**
     * The rate of turn.
     */
    private int rateOfTurn;

    /**
     * Returns the rate of turn. This value can be analyzed further with utility
     * class {@link UtilsRateOfTurn8}.
     *
     * @return an integer value in the range of -128 to 127
     */
    @Override
    public int getRateOfTurn() {
        return rateOfTurn;
    }

    public float getRot() {
        return rateOfTurn;
    }
    /**
     * The position of the first bit of the speed over ground.
     */
    private static final int SPEEDOVERGROUND_FROM = 51;

    /**
     * The position of the last bit of the speed over ground.
     */
    private static final int SPEEDOVERGROUND_TO = 60;

    /**
     * The speed over ground
     */
    @XmlElement(name = "sog")
    private int speedOverGround;

    /**
     * Returns the speed over ground.
     *
     * @return an integer value in the range of 0 to 1023, representing the
     * speed over ground in 1/10 knot steps (0-102.2 knots) <br>
     * 1023 = not available <br>
     * 1022 = 102.2 knots or higher
     */
    @Override
    public int getSpeedOverGround() {
        return speedOverGround;
    }

    public float getSog() {
        return speedOverGround / 10.0f;
    }
    /**
     * The format we use to format the speed over ground
     */
    private static final DecimalFormat SOG_FORMAT = new DecimalFormat("##0.0");

    /**
     * Returns the speed over ground as a string.
     *
     * @return a string representing the speed over ground
     */
    @Override
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
     * The position of the position accuracy flag
     */
    private static final int POSITIONACCURACY_BITINDEX = 61;

    /**
     * The position accuracy flag
     */
    private boolean positionAccuracy;

    /**
     * Returns the position accuracy.
     *
     * @return a boolean value representing position accuracy: <br>
     * true = high (&le; 10 m) <br>
     * false = low (&gt; 10 m)
     */
    @Override
    public boolean getPositionAccuracy() {
        return positionAccuracy;
    }

    /**
     * The position of the first bit of the longitude.
     */
    private static final int LONGITUDE_FROM = 62;

    /**
     * The position of the last bit of the longitude.
     */
    private static final int LONGITUDE_TO = 89;

    /**
     * The longitude.
     */
    @XmlElement(name = "lon")
    private double longitude;

    /**
     * Returns the longitude in degrees. This value can be analyzed further with
     * utility class {@link UtilsPositionInfo}.
     *
     * @return a double value representing the longitude in degrees
     * (&plusmn;180&deg;, East = positive, West = negative; 181&deg; = (6791AC0
     * hex) = not available = default).
     */
    @Override
    public double getLongitudeInDegrees() {
        return longitude;
    }

    public double getLongitude() {
        return longitude;
    }
    /**
     * The position of the first bit of the latitude.
     */
    private static final int LATITUDE_FROM = 90;

    /**
     * The position of the last bit of the latitude.
     */
    private static final int LATITUDE_TO = 116;

    /**
     * The latitude.
     */
    @XmlElement(name = "lat")
    private double latitude;

    /**
     * Returns the latitude in degrees. This value can be analyzed further with
     * utility class {@link UtilsPositionInfo}.
     *
     * @return a double value representing the latitude in degrees
     * (&plusmn;90&deg;, North = positive, South = negative; 91&deg; = (3412140
     * hex) = not available = default).
     */
    @Override
    public double getLatitudeInDegrees() {
        return latitude;
    }

    public double getLatitude() {
        return latitude;
    }
    /**
     * The position of the first bit of the course over ground.
     */
    private static final int COURSEOVERGROUND_FROM = 117;

    /**
     * The position of the last bit of the course over ground.
     */
    private static final int COURSEOVERGROUND_TO = 128;

    /**
     * The course over ground.
     */
    @XmlElement(name = "cog")
    private int courseOverGround;

    /**
     * Returns the course over ground. This value can be analyzed further with
     * utility class {@link UtilsAngle12}.
     *
     * @return an integer value representing the course over ground in 1/10&deg;
     * for values in the range of 0 to 3599.<br>
     * 3600 (E10h) = not available. <br>
     * 3601 or higher should not be used
     */
    @Override
    public int getCourseOverGround() {
        return courseOverGround;
    }

    public float getCog() {
        return courseOverGround / 10.0f;
    }
    /**
     * The position of the first bit of the true heading.
     */
    private static final int TRUEHEADING_FROM = 129;

    /**
     * The position of the last bit of the true heading.
     */
    private static final int TRUEHEADING_TO = 137;

    /**
     * The true heading.
     */
    @XmlElement(name = "heading")
    private int trueHeading;

    /**
     * Returns the true heading.
     *
     * @return an integer value representing the true heading in degrees
     * (0-359). <br>
     * 511 indicates not available
     */
    @Override
    public int getTrueHeading() {
        return trueHeading;
    }

    public float getHeading() {
        return trueHeading;
    }
    /**
     * The position of the first bit of the time stamp.
     */
    private static final int TIMESTAMP_FROM = 138;

    /**
     * The position of the last bit of the time stamp.
     */
    private static final int TIMESTAMP_TO = 143;

    /**
     * The time stamp.
     */
    private int timeStamp;

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
    @Override
    public int getTimeStamp() {
        return timeStamp;
    }

    /**
     * The position of the first bit of the special maneuver indicator.
     */
    private static final int SPECIALMANOEUVRE_FROM = 144;

    /**
     * The position of the last bit of the special maneuver indicator.
     */
    private static final int SPECIALMANOEUVRE_TO = 145;

    /**
     * The special maneuver indicator.
     */
    @XmlElement(name = "smi")
    private int specialManoeuvreIndicator;

    /**
     * Returns the special maneuver indicator.
     *
     * @return an integer value with the following meaning: <br>
     * 0 = not available <br>
     * 1 = not engaged in special maneuver <br>
     * 2 = engaged in special maneuver (i.e.: regional passing arrangement on
     * Inland Waterway)
     */
    @Override
    public int getSpecialManoeuvre() {
        return specialManoeuvreIndicator;
    }

    /**
     * The position of the first of the spare bits.
     */
    private static final int SPARE_FROM = 146;

    /**
     * The position of the last of the spare bits.
     */
    private static final int SPARE_TO = 148;

    /**
     * The value of the spare bits
     */
    private int spare;

    /**
     * Returns the spare bits.
     *
     * @return the integer value of the spare bits, which should be zero.
     */
    @Override
    public int getSpare() {
        return spare;
    }

    /**
     * The position of the RAIM flag
     */
    private static final int RAIMFLAG_BITINDEX = 149;

    /**
     * The RAIM flag
     */
    private boolean raimFlag;

    /**
     * Returns the RAIM flag, which describes the receiver autonomous integrity
     * monitoring status of the electronic position fixing device.
     *
     * @return a boolean value: <br>
     * false = RAIM not in use <br>
     * true = RAIM in use
     */
    @Override
    public boolean getRaimFlag() {
        return raimFlag;
    }

    /**
     * The position of the first bit of the communication state (for use in
     * subclasses).
     */
    protected static final int COMMUNICATIONSTATE_FROM = 150;

    /**
     * Checks whether the given number can be the length in bytes of the
     * contents of a valid AIS position report message.
     *
     * @param length an integer value representing the length of a message in
     * bytes
     * @return true if this can be the length of a valid AIS position report
     * message
     */
    public static boolean validLength(int length) {
        return (length == LENGTH);
    }

    /**
     * Return the difference in available and needed bits to parse this sixbit
     * as an AisMessagePositionReport A positive difference indicates that there
     * are more bits available than needed by the standard.
     *
     * @param sb the sixbit
     * @return the difference in available and needed bits
     */
    public static int differenceInBits(Sixbit sb) {
        return (sb.available() - LENGTH);
    }

    public AISMessagePositionReportImpl() {
    }

    /**
     * Constructs an AIS Message Position Report.
     *
     * @param content AIS content
     * @param prov the provenance of the message
     * @precondition validLength(content.length())
     */
    protected AISMessagePositionReportImpl(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);
        assert (validLength(content.length()));

        navigationalStatus = content.getIntFromTo(NAVIGATIONALSTATUS_FROM, NAVIGATIONALSTATUS_TO);
        if (!UtilsNavStatus.isNavStatusSemanticallyCorrect(navigationalStatus)) {
            annotations.add(new AISIllegalValueAnnotation("getNavigationalStatus", navigationalStatus, UtilsNavStatus.range));
        }
        rateOfTurn = UtilsTwosComplement.convertFrom8Bits(content.getIntFromTo(RATEOFTURN_FROM, RATEOFTURN_TO));
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
        specialManoeuvreIndicator = content.getIntFromTo(SPECIALMANOEUVRE_FROM, SPECIALMANOEUVRE_TO);
        if (!UtilsSpecialManoeuvreIndicator2.isSpecialManoeuvreIndicatorSemanticallyCorrect(specialManoeuvreIndicator)) {
            annotations.add(new AISIllegalValueAnnotation("getSpecialManoeuvre", specialManoeuvreIndicator, UtilsSpecialManoeuvreIndicator2.range));
        }
        spare = content.getIntFromTo(SPARE_FROM, SPARE_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare", spare, UtilsSpare.range));
        }
        raimFlag = content.getBoolean(RAIMFLAG_BITINDEX);
    }

    public void setNavigationalStatus(int navigationalStatus) {
        this.navigationalStatus = navigationalStatus;
    }

    public void setRateOfTurn(int rateOfTurn) {
        this.rateOfTurn = rateOfTurn;
    }

    public void setSpeedOverGround(int speedOverGround) {
        this.speedOverGround = speedOverGround;
    }

    public void setPositionAccuracy(boolean positionAccuracy) {
        this.positionAccuracy = positionAccuracy;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setCourseOverGround(int courseOverGround) {
        this.courseOverGround = courseOverGround;
    }

    public void setTrueHeading(int trueHeading) {
        this.trueHeading = trueHeading;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setSpecialManoeuvreIndicator(int specialManoeuvreIndicator) {
        this.specialManoeuvreIndicator = specialManoeuvreIndicator;
    }

    public void setSpare(int spare) {
        this.spare = spare;
    }

    public void setRaimFlag(boolean raimFlag) {
        this.raimFlag = raimFlag;
    }

    /**
     * Generates a String representing the AIS message. Format: all fields are
     * shown in the order as specified by the standard separated by the
     * SEPARATOR string.
     */
    @Override
    public String toString() {

        String result = super.toString();

        result += SEPARATOR + UtilsNavStatus.toString(navigationalStatus);
        result += SEPARATOR + navigationalStatusText;
        result += SEPARATOR + UtilsRateOfTurn8.toString(rateOfTurn);
        result += SEPARATOR + getSpeedOverGroundString();
        result += SEPARATOR + (positionAccuracy ? "high" : "low") + " accuracy";
        result += SEPARATOR + UtilsPositionInfo.longitudeToString(longitude);
        result += SEPARATOR + UtilsPositionInfo.latitudeToString(latitude);
        result += SEPARATOR + UtilsAngle12.toString(courseOverGround);
        result += SEPARATOR + UtilsAngle9.toString(trueHeading);
        //  result += SEPARATOR + UtilsTimeStamp.toString(timeStamp);
        result += SEPARATOR + UtilsSpecialManoeuvreIndicator2.toString(specialManoeuvreIndicator);
        result += SEPARATOR + "RAIM " + (raimFlag ? "in use" : "not in use");
        return result;
    }
}
