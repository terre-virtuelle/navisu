package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage19;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsDimensions30;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsPositioningDevice;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsShipType8;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsSpare;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsString;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.BitVector;
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
 * Implementation of AIS Message 19: Extended Class B Equipment Position Report.
 * Several fields are covered by the superclass
 * {@link AISMessageClassBPositionReportImpl}. Here are the remaining fields:
 * <pre>
 * Field Nr     Field Name                          NrOf Bits   (from, to )
 * ------------------------------------------------------------------------
 * 12           spare2                                     4    ( 140, 143)
 * 13           name                                     120    ( 144, 263)
 * 14           typeOfShipAndCargoType                     8    ( 264, 271)
 * 15           dimension                                 30    ( 272, 301)
 * 16           typeOfElectronicPositionFixingDevice       4    ( 302, 305)
 * 17           raimFlag                                   1    ( 306, 306)
 * 18           dte                                        1    ( 307, 307)
 * 19           assignedModeFlag                           1    ( 308, 308)
 * 20           spare3                                     4    ( 309, 312)
 *                                                      ---- +
 *                           (maximum) number of bits    312
 * </pre>
 *
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
@XmlRootElement(name = "ais19")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS19 extends AISMessageClassBPositionReportImpl implements AISMessage19 {

    /**
     * The first position of the second set of spare bits.
     */
    protected static final int SPARE2_FROM = 140;

    /**
     * The last position of the second set of spare bits. Note that this is
     * different from type 18 messages, and therefore not covered by the
     * superclass {@link AISMessageClassBPositionReportImpl}.
     */
    protected static final int SPARE2_TO = 143;

    /**
     * The position of the first bit of the name.
     */
    private static final int NAME_FROM = 144;

    /**
     * The position of the last bit of the name.
     */
    private static final int NAME_TO = 263;

    /**
     * The name.
     */
    private String name;

    /**
     * Returns the name of the transmitting ship.
     *
     * @return a String value, containing maximum 20 characters, representing
     * the name. <br>
     * "" = not available.
     */
    public String getName() {
        return name;
    }

    /**
     * The position of the first bit of the type of ship and cargo type.
     */
    private static final int TYPEOFSHIPANDCARGOTYPE_FROM = 264;

    /**
     * The position of the last bit of the type of ship and cargo type.
     */
    private static final int TYPEOFSHIPANDCARGOTYPE_TO = 271;

    /**
     * The type of ship and cargo type.
     */
    private int typeOfShipAndCargoType;

    /**
     * Returns the type of ship and cargo type. This can be converted to a
     * String by utility class {@link UtilsShipType8}.
     *
     * @return an integer value representing the type of ship and cargo type:
     * <br>
     * 0 = not available or no ship <br>
     * 1-99 = as defined in the standard (see {@link UtilsShipType8}) <br>
     * 100-199 = reserved for regional use <br>
     * 200-255 = reserved for future use  <br>
     */
    public int getTypeOfShipAndCargoType() {
        return typeOfShipAndCargoType;
    }

    /**
     * The position of the first bit of the dimensions.
     */
    private static final int DIMENSION_FROM = 272;

    /**
     * The position of the last bit of the dimensions.
     */
    private static final int DIMENSION_TO = 301;

    /**
     * The dimensions.
     */
    private BitVector dimension;

    /**
     * Returns the dimensions of the ship and the reference point for the
     * reported position. These can be analyzed further with utility class
     * {@link UtilsDimensions30}.
     *
     * @return a <code>BitVector</code> value representing the dimensions.
     */
    public BitVector getDimension() {
        return dimension;
    }
    

    public int getLength() {
        return UtilsDimensions30.getBow(dimension) + UtilsDimensions30.getStern(dimension);
    }

    public int getWidth() {
        return UtilsDimensions30.getPort(dimension) + UtilsDimensions30.getStarboard(dimension);
    }

    public int getBow() {
        return UtilsDimensions30.getBow(dimension);
    }

    public int getStern() {
        return UtilsDimensions30.getStern(dimension);
    }

    public int getPort() {
        return UtilsDimensions30.getPort(dimension);
    }

    public int getStarboard() {
        return UtilsDimensions30.getStarboard(dimension);
    }

    /**
     * The position of the first bit of the electronic position fixing device.
     */
    private static final int TYPEOFELECTRONICPOSITIONFIXINGDEVICE_FROM = 302;

    /**
     * The position of the last bit of the electronic position fixing device.
     */
    private static final int TYPEOFELECTRONICPOSITIONFIXINGDEVICE_TO = 305;

    /**
     * The electronic position fixing device.
     */
    private int typeOfElectronicPositionFixingDevice;

    /**
     * Returns the type of electronic position fixing device.
     *
     * @return an integer value representing of the type of electronic position
     * fixing device: <br>
     * 0 = undefined (default) <br>
     * 1 = global positioning system (GPS) <br>
     * 2 = GNSS (GLONASS) <br>
     * 3 = combined GPS/GLONASS <br>
     * 4 = Loran-C <br>
     * 5 = Chayka <br>
     * 6 = integrated navigation system <br>
     * 7 = surveyed <br>
     * 8 = Galileo <br>
     * 9-14 = not used <br>
     * 15 = internal GNSS
     */
    public int getTypeOfElectronicPositionFixingDevice() {
        return typeOfElectronicPositionFixingDevice;
    }

    /**
     * The position of the RAIM flag. Note that the position of this flag is
     * different for type 18 messages.
     */
    private static final int RAIMFLAG_BITINDEX = 306;

    /**
     * The position of the DTE status.
     */
    private static final int DTE_BITINDEX = 307;

    /**
     * The DTE status
     */
    private boolean dte;

    /**
     * Returns the data terminal equipment (DTE) status.
     *
     * @return a boolean value describing whether the data terminal equipment is
     * ready: <br>
     * false = available <br>
     * true = not available
     */
    public boolean getDte() {
        return dte;
    }

    /**
     * The position of the assigned mode flag. Note that the position of this
     * flag is different for type 18 messages.
     */
    private static final int ASSIGNEDMODEFLAG_BITINDEX = 308;

    /**
     * The first position of the third set of spare bits.
     */
    private static final int SPARE3_FROM = 309;

    /**
     * The last position of the third set of spare bits.
     */
    private static final int SPARE3_TO = 312;

    /**
     * The value of the third set of spare bits.
     */
    protected int spare3;

    /**
     * Returns the third set of spare bits.
     *
     * @return the value of the third set of spare bits. This should be zero.
     */
    public int getSpare3() {
        return spare3;
    }

    /**
     * The length in bytes that a valid AIS message 19 should have
     */
    public static final int LENGTH = 312;

    /**
     * Checks whether the given number can be the length in bytes of the
     * contents of a valid AIS message 19.
     *
     * @param length an integer value representing the length of a message in
     * bytes
     * @return true if this can be the length of a valid AIS message 19
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

    /**
     * AIS19 default constructor
     */
    public AIS19() {
    }

    /**
     * AISMessage 19 constructor
     *
     * @param content AIS content
     * @param prov the provenance of the message
     * @precondition validLength(content.length()) &&
     * AISMessageBase.getMessageId(content)==19
     */
    public AIS19(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);
        assert (validLength(content.length()));
        assert (getMessageID() == 19);

        spare2 = content.getIntFromTo(SPARE2_FROM, SPARE2_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare2)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare2", spare2, UtilsSpare.range));
        }
        name = UtilsString.stripAtSigns(content.getStringFromTo(NAME_FROM, NAME_TO));
        typeOfShipAndCargoType = content.getIntFromTo(TYPEOFSHIPANDCARGOTYPE_FROM, TYPEOFSHIPANDCARGOTYPE_TO);
        dimension = content.getBitVectorFromTo(DIMENSION_FROM, DIMENSION_TO);
        typeOfElectronicPositionFixingDevice = content.getIntFromTo(TYPEOFELECTRONICPOSITIONFIXINGDEVICE_FROM, TYPEOFELECTRONICPOSITIONFIXINGDEVICE_TO);
        raimFlag = content.getBoolean(RAIMFLAG_BITINDEX);
        dte = content.getBoolean(DTE_BITINDEX);
        assignedModeFlag = content.getBoolean(ASSIGNEDMODEFLAG_BITINDEX);
        spare3 = content.getIntFromTo(SPARE3_FROM, SPARE3_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare3)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare3", spare3, UtilsSpare.range));
        }
    }

    /**
     * Generates a String representing the AIS message. Format: all fields are
     * shown in the order and units as specified by the standard separated by
     * the SEPARATOR string.
     */
    @Override
    public String toString() {
        String result = super.toString();
        result += SEPARATOR + name;
        result += SEPARATOR + UtilsShipType8.shipTypeToString(typeOfShipAndCargoType);
        result += SEPARATOR + UtilsDimensions30.toString(dimension);
        result += SEPARATOR + UtilsPositioningDevice.toString(typeOfElectronicPositionFixingDevice);
        result += SEPARATOR + "RAIM " + (raimFlag ? "in use" : "not in use");
        result += SEPARATOR + (dte ? "no DTE" : "with DTE");
        result += SEPARATOR + (assignedModeFlag ? "assigned" : "autonomous") + " mode";
        return result;
    }
}
