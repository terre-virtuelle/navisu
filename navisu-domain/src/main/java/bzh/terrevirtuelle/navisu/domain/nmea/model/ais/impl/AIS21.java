package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage21;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsLatitude27;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsLongitude28;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsPositionInfo;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsSpare;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsString;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsTwosComplement;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.BitVector;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * AIS Message 21 Aids-to-Navigation Report (AtoN)
 *
 * Field Nr Field Name NrOf Bits (from, to )
 * ------------------------------------------------------------------------ 1
 * messageID 6	( 1, 6) 2	repeatIndicator 2	( 7, 8) 3	mmsi 30	( 9, 38) 4
 * typeOfAtoN 5	( 39, 43) 5	nameOfAtoN 120	( 44, 163) 6	positionAccuracy 1	(
 * 164, 164) 7	longitude 28	( 165, 192) 8	latitude 27	( 193, 219) 9	dimension 30
 * ( 220, 249) 10	typeOfElectronicPositionFixingDevice 4	( 250, 253) 11
 * timeStamp 6	( 254, 259) 12	offPositionIndicator 1	( 260, 260) 13	statusAtoN 8
 * ( 261, 268) 14	raimFlag 1	( 269, 269) 15	virtualAtoNFlag 1	( 270, 270) 16
 * assignedModeFlag 1	( 271, 271) 17	spare1 1	( 272, 272) 18	nameOfAtoNExtension
 * 84	( 273, 356) 19	spare2 4	( 357, 360) ---- + (maximum) number of bits 360
 */
@XmlRootElement(name = "ais21")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS21 extends AISMessageImpl implements AISMessage21 {

    /**
     *
     */
    public static final int MINLENGTH = 272;

    /**
     *
     */
    public static final int MAXLENGTH = 360;

    /**
     *
     * @param length
     * @return
     */
    public static boolean validLength(int length) {
        //TODO: also check that length is aligned with (8?) byte boundary
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

    private static final int TYPEOFATON_FROM = 39;
    private static final int TYPEOFATON_TO = 43;

    private int typeOfAtoN;

    /**
     * typeOfAtoN
     *
     * @return int value of typeOfAtoN (5 bits [39,43])
     */
    public int getTypeOfAtoN() {
        return typeOfAtoN;
    }

    private static final int NAMEOFATON_FROM = 44;
    private static final int NAMEOFATON_TO = 163;

    private String nameOfAtoN;

    /**
     * nameOfAtoN
     *
     * @return String value of nameOfAtoN (120 bits [44,163])
     */
    public String getNameOfAtoN() {
        return nameOfAtoN;
    }

    private static final int POSITIONACCURACY_BITINDEX = 164;

    private boolean positionAccuracy;

    /**
     * positionAccuracy
     *
     * @return boolean value of positionAccuracy (bit 164)
     */
    public boolean getPositionAccuracy() {
        return positionAccuracy;
    }

    private static final int LONGITUDE_FROM = 165;
    private static final int LONGITUDE_TO = 192;
    @XmlElement(name = "lon")
    private double longitude;

    /**
     * longitude
     *
     * @return double value of longitude in degrees (28 bits [165,192])
     */
    public double getLongitudeInDegrees() {
        return longitude;
    }

    private static final int LATITUDE_FROM = 193;
    private static final int LATITUDE_TO = 219;
    @XmlElement(name = "lat")
    private double latitude;

    /**
     * latitude
     *
     * @return double value of latitude in degrees (27 bits [193,219])
     */
    public double getLatitudeInDegrees() {
        return latitude;
    }

    private static final int DIMENSION_FROM = 220;
    private static final int DIMENSION_TO = 249;

    private BitVector dimension;

    /**
     * dimension
     *
     * @return BitVector value of dimension (30 bits [220,249])
     */
    public BitVector getDimension() {
        return dimension;
    }

    private static final int TYPEOFELECTRONICPOSITIONFIXINGDEVICE_FROM = 250;
    private static final int TYPEOFELECTRONICPOSITIONFIXINGDEVICE_TO = 253;

    @XmlElement(name = "toepfd")
    private int typeOfElectronicPositionFixingDevice;

    /**
     * typeOfElectronicPositionFixingDevice
     *
     * @return int value of typeOfElectronicPositionFixingDevice (4 bits
     * [250,253])
     */
    public int getTypeOfElectronicPositionFixingDevice() {
        return typeOfElectronicPositionFixingDevice;
    }

    private static final int TIMESTAMP_FROM = 254;
    private static final int TIMESTAMP_TO = 259;

    private int timeStamp;

    /**
     * timeStamp
     *
     * @return int value of timeStamp (6 bits [254,259])
     */
    public int getTimeStamp() {
        return timeStamp;
    }

    private static final int OFFPOSITIONINDICATOR_BITINDEX = 260;

    private boolean offPositionIndicator;

    /**
     * offPositionIndicator
     *
     * @return boolean value of offPositionIndicator (bit 260)
     */
    public boolean getOffPositionIndicator() {
        return offPositionIndicator;
    }

    private static final int STATUSATON_FROM = 261;
    private static final int STATUSATON_TO = 268;

    private int statusAtoN;

    /**
     * statusAtoN
     *
     * @return int value of statusAtoN (8 bits [261,268])
     */
    public int getStatusAtoN() {
        return statusAtoN;
    }

    private static final int RAIMFLAG_BITINDEX = 269;

    private boolean raimFlag;

    /**
     * raimFlag
     *
     * @return boolean value of raimFlag (bit 269)
     */
    public boolean getRaimFlag() {
        return raimFlag;
    }

    private static final int VIRTUALATONFLAG_BITINDEX = 270;

    private boolean virtualAtoNFlag;

    /**
     * virtualAtoNFlag
     *
     * @return boolean value of virtualAtoNFlag (bit 270)
     */
    public boolean getVirtualAtoNFlag() {
        return virtualAtoNFlag;
    }

    private static final int ASSIGNEDMODEFLAG_BITINDEX = 271;

    private boolean assignedModeFlag;

    /**
     * assignedModeFlag
     *
     * @return boolean value of assignedModeFlag (bit 271)
     */
    public boolean getAssignedModeFlag() {
        return assignedModeFlag;
    }

    private static final int SPARE1_FROM = 272;
    private static final int SPARE1_TO = 272;

    private int spare1;

    /**
     * spare1
     *
     * @return int value of spare1 (1 bits [272,272])
     */
    public int getSpare1() {
        return spare1;
    }

    private static final int NAMEOFATONEXTENSION_FROM = 273;

    private String nameOfAtoNExtension;

    /**
     * nameOfAtoNExtension
     *
     * @return String value of nameOfAtoNExtension (84 maximally bits [273,356])
     */
    public String getNameOfAtoNExtension() {
        return nameOfAtoNExtension;
    }

    private int spare2;

    /**
     * spare2
     *
     * @return int value of spare2 (0,2,4 or 6 bits)
     */
    public int getSpare2() {
        return spare2;
    }

    /**
     * AIS21 default constructor
     */
    public AIS21() {
    }

    /**
     * AISMessage 21 constructor
     *
     * @param content AIS content
     * @param prov the provenance of the message
     * @precondition validLength(content.length()) &&
     * AISMessageBase.getMessageId(content)==21
     */
    public AIS21(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);
        assert (validLength(content.length()));
        assert (getMessageID() == 21);

        typeOfAtoN = content.getIntFromTo(TYPEOFATON_FROM, TYPEOFATON_TO);
        nameOfAtoN = UtilsString.stripAtSigns(content.getStringFromTo(NAMEOFATON_FROM, NAMEOFATON_TO));
        positionAccuracy = content.getBoolean(POSITIONACCURACY_BITINDEX);
        longitude = UtilsLongitude28.toDegrees(UtilsTwosComplement.convertFrom28Bits(content.getIntFromTo(LONGITUDE_FROM, LONGITUDE_TO)));
        if (!UtilsPositionInfo.isLongitudeSemanticallyCorrect(longitude)) {
            annotations.add(new AISIllegalValueAnnotation("getLongitudeInDegrees", longitude, UtilsPositionInfo.rangeLongitude));
        }
        latitude = UtilsLatitude27.toDegrees(UtilsTwosComplement.convertFrom27Bits(content.getIntFromTo(LATITUDE_FROM, LATITUDE_TO)));
        if (!UtilsPositionInfo.isLatitudeSemanticallyCorrect(latitude)) {
            annotations.add(new AISIllegalValueAnnotation("getLatitudeInDegrees", latitude, UtilsPositionInfo.rangeLatitude));
        }
        dimension = content.getBitVectorFromTo(DIMENSION_FROM, DIMENSION_TO);
        typeOfElectronicPositionFixingDevice = content.getIntFromTo(TYPEOFELECTRONICPOSITIONFIXINGDEVICE_FROM, TYPEOFELECTRONICPOSITIONFIXINGDEVICE_TO);
        timeStamp = content.getIntFromTo(TIMESTAMP_FROM, TIMESTAMP_TO);
        offPositionIndicator = content.getBoolean(OFFPOSITIONINDICATOR_BITINDEX);
        statusAtoN = content.getIntFromTo(STATUSATON_FROM, STATUSATON_TO);
        raimFlag = content.getBoolean(RAIMFLAG_BITINDEX);
        virtualAtoNFlag = content.getBoolean(VIRTUALATONFLAG_BITINDEX);
        assignedModeFlag = content.getBoolean(ASSIGNEDMODEFLAG_BITINDEX);
        spare1 = content.getIntFromTo(SPARE1_FROM, SPARE1_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare1)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare1", spare1, UtilsSpare.range));
        }

        /*TODO: on page 125 of ITU-R M.1371-4 is written 
         *      "That spare can be 0,2,4, or 6 in order to observe byte boundaries"
         *      So I assume that bytes are 8 bits and NOT 6.
         *      Also is written "No @-character should be used", so when 6 bits are all zero they are spare and not a character
         *      This seems to be the only distinction between six character bits and six spare bits.
         */
        int length = content.length();
        int nrof_spare = (length - MINLENGTH) % 6;
        nameOfAtoNExtension = content.getStringFromTo(NAMEOFATONEXTENSION_FROM, length - nrof_spare);
        if ((nameOfAtoNExtension.length() > 0) && nameOfAtoNExtension.charAt(nameOfAtoNExtension.length() - 1) == '@') {
            //remove spare bits
            nameOfAtoNExtension = nameOfAtoNExtension.substring(0, nameOfAtoNExtension.length() - 1);
        }
        spare2 = content.getIntFromTo(length - nrof_spare + 1, length);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare2)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare2", spare2, UtilsSpare.range));
        }
    }
}
