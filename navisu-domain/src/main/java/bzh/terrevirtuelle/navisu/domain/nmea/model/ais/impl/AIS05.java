package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage05;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsDimensions30;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsEta;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsSpare;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsString;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
import java.text.NumberFormat;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.BitVector;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsPositioningDevice;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsShipType8;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
 * AIS Message 5 implementation: Ship Static and Voyage Related Data.
 * <pre>
 * Field Nr     Field Name                          NrOf Bits   (from, to )
 * ------------------------------------------------------------------------
 *  1	messageID                               	   6	(   1,   6)
 *  2	repeatIndicator                         	   2	(   7,   8)
 *  3	mmsi                                  	          30	(   9,  38)
 *  4	aisVersionIndicator                     	   2	(  39,  40)
 *  5	imoNumber                               	  30	(  41,  70)
 *  6	callSign                                	  42	(  71, 112)
 *  7	name                                    	 120	( 113, 232)
 *  8	typeOfShipAndCargoType                  	   8	( 233, 240)
 *  9	dimension                               	  30	( 241, 270)
 * 10	typeOfElectronicPositionFixingDevice    	   4	( 271, 274)
 * 11	eta                                     	  20	( 275, 294)
 * 12	maximumPresentStaticDraught             	   8	( 295, 302)
 * 13	destination                             	 120	( 303, 422)
 * 14	dte                                     	   1	( 423, 423)
 * 15	spare                                   	   1	( 424, 424)
 *                                                  ---- +
 *                       (maximum) number of bits    424
 * </pre>
 */
@XmlRootElement(name = "ais05")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS05
        extends AISMessageImpl
        implements AISMessage05 {

    /**
     *
     */
    private static final int AISVERSIONINDICATOR_FROM = 39;
    private static final int AISVERSIONINDICATOR_TO = 40;
    private static final int IMONUMBER_FROM = 41;
    private static final int IMONUMBER_TO = 70;
    private static final int CALLSIGN_FROM = 71;
    private static final int CALLSIGN_TO = 112;
    private static final int NAME_FROM = 113;
    private static final int NAME_TO = 232;
    private static final int TYPEOFSHIPANDCARGOTYPE_FROM = 233;
    private static final int TYPEOFSHIPANDCARGOTYPE_TO = 240;
    private static final int DIMENSION_FROM = 241;
    private static final int DIMENSION_TO = 270;
    private static final int TYPEOFELECTRONICPOSITIONFIXINGDEVICE_FROM = 271;
    private static final int TYPEOFELECTRONICPOSITIONFIXINGDEVICE_TO = 274;
    private static final int ETA_FROM = 275;
    private static final int ETA_TO = 294;
    private static final int MAXIMUMPRESENTSTATICDRAUGHT_FROM = 295;
    private static final int MAXIMUMPRESENTSTATICDRAUGHT_TO = 302;
    private static final int DESTINATION_FROM = 303;
    private static final int DESTINATION_TO = 422;
    private static final int DTE_BITINDEX = 423;
    public static final int LENGTH = 424;
    private static final int SPARE_FROM = 424;
    private static final int SPARE_TO = 424;

    private int imoNumber;
    private String name;
    private String callSign;
    private int aisVersionIndicator;
    private int typeOfElectronicPositionFixingDevice;
    private int maximumPresentStaticDraught;
    private BitVector eta;
    private String destination;
    private BitVector dimension;
    private boolean dte;
    private int spare;
    private int typeOfShipAndCargoType;

    /**
     * AIS05 default constructor
     */
    public AIS05() {
    }

    /**
     * AISMessage 5 constructor
     *
     * @param content AIS content
     * @param prov the provenance of the message
     * @precondition validLength(content.length()) &&
     * AISMessageBase.getMessageId(content)== 5
     */
    public AIS05(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);
        assert (validLength(content.length()));
        assert (getMessageID() == 5);

        aisVersionIndicator = content.getIntFromTo(AISVERSIONINDICATOR_FROM, AISVERSIONINDICATOR_TO);
        imoNumber = content.getIntFromTo(IMONUMBER_FROM, IMONUMBER_TO);
        callSign = UtilsString.stripAtSigns(content.getStringFromTo(CALLSIGN_FROM, CALLSIGN_TO)).trim();
        name = UtilsString.stripAtSigns(content.getStringFromTo(NAME_FROM, NAME_TO)).trim();
        typeOfShipAndCargoType = content.getIntFromTo(TYPEOFSHIPANDCARGOTYPE_FROM, TYPEOFSHIPANDCARGOTYPE_TO);
        dimension = content.getBitVectorFromTo(DIMENSION_FROM, DIMENSION_TO);
        typeOfElectronicPositionFixingDevice = content.getIntFromTo(TYPEOFELECTRONICPOSITIONFIXINGDEVICE_FROM, TYPEOFELECTRONICPOSITIONFIXINGDEVICE_TO);
        eta = content.getBitVectorFromTo(ETA_FROM, ETA_TO);
        maximumPresentStaticDraught = content.getIntFromTo(MAXIMUMPRESENTSTATICDRAUGHT_FROM, MAXIMUMPRESENTSTATICDRAUGHT_TO);
        destination = UtilsString.stripAtSigns(content.getStringFromTo(DESTINATION_FROM, DESTINATION_TO)).trim();
        dte = content.getBoolean(DTE_BITINDEX);
        spare = content.getIntFromTo(SPARE_FROM, SPARE_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare", spare, UtilsSpare.range));
        }
        //System.out.println("new AIS5 "+this);
    }

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
     * as an AISMessage05 A positive difference indicates that there are more
     * bits available than needed by the standard.
     *
     * @param sb
     * @return
     */
    public static int differenceInBits(Sixbit sb) {
        return (sb.available() - LENGTH);
    }

    /**
     * aisVersionIndicator
     *
     * @return int value of aisVersionIndicator (2 bits [39,40])
     */
    @Override
    public int getAisVersionIndicator() {
        return aisVersionIndicator;
    }

    /**
     * imoNumber
     *
     * @return int value of imoNumber (30 bits [41,70])
     */
    @Override
    public int getImoNumber() {
        return imoNumber;
    }

    public int getIMO() {
        return imoNumber;
    }

    /**
     * callSign
     *
     * @return String value of callSign (42 bits [71,112])
     */
    @Override
    public String getCallSign() {
        return callSign;
    }

    /**
     * name
     *
     * @return String value of name (120 bits [113,232])
     */
    @Override
    public String getName() {
        return name;
    }

    public String getShipName() {
        return name;
    }

    /**
     * typeOfShipAndCargoType
     *
     * @return int value of typeOfShipAndCargoType (8 bits [233,240])
     */
    @Override
    public int getTypeOfShipAndCargoType() {
        return typeOfShipAndCargoType;
    }

    public int getShipType() {
        return typeOfShipAndCargoType;
    }

    /**
     * dimension
     *
     * @return BitVector value of dimension (30 bits [241,270])
     */
    @Override
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
     * typeOfElectronicPositionFixingDevice
     *
     * @return int value of typeOfElectronicPositionFixingDevice (4 bits
     * [271,274])
     */
    @Override
    public int getTypeOfElectronicPositionFixingDevice() {
        return typeOfElectronicPositionFixingDevice;
    }

    /**
     * eta
     *
     * @return BitVector value of eta (20 bits [275,294])
     */
    @Override
    public BitVector getEta() {
        return eta;
    }

    public Calendar getETA() {
        return new GregorianCalendar(Calendar.YEAR, UtilsEta.getMonth(eta), UtilsEta.getDay(eta), UtilsEta.getHour(eta), UtilsEta.getMinute(eta));
    }

    public String getEtaString() {
        return twoDigits.format(UtilsEta.getMonth(eta)) + "-" + twoDigits.format(UtilsEta.getDay(eta))
                + "T" + twoDigits.format(UtilsEta.getHour(eta)) + ":" + twoDigits.format(UtilsEta.getMinute(eta));
    }

    /**
     * maximumPresentStaticDraught
     *
     * @return int value of maximumPresentStaticDraught (8 bits [295,302])
     */
    @Override
    public int getMaximumPresentStaticDraught() {
        return maximumPresentStaticDraught;
    }

    public int getDraught() {
        return maximumPresentStaticDraught;
    }

    /**
     * destination
     *
     * @return String value of destination (120 bits [303,422])
     */
    @Override
    public String getDestination() {
        return destination;
    }

    public void setAisVersionIndicator(int aisVersionIndicator) {
        this.aisVersionIndicator = aisVersionIndicator;
    }

    public void setImoNumber(int imoNumber) {
        this.imoNumber = imoNumber;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeOfShipAndCargoType(int typeOfShipAndCargoType) {
        this.typeOfShipAndCargoType = typeOfShipAndCargoType;
    }

    public void setShipType(int typeOfShipAndCargoType) {
        this.typeOfShipAndCargoType = typeOfShipAndCargoType;
    }

    public void setDimension(BitVector dimension) {
        this.dimension = dimension;
    }

    public void setTypeOfElectronicPositionFixingDevice(int typeOfElectronicPositionFixingDevice) {
        this.typeOfElectronicPositionFixingDevice = typeOfElectronicPositionFixingDevice;
    }

    public void setEta(BitVector eta) {
        this.eta = eta;
    }

    public void setMaximumPresentStaticDraught(int maximumPresentStaticDraught) {
        this.maximumPresentStaticDraught = maximumPresentStaticDraught;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDte(boolean dte) {
        this.dte = dte;
    }

    public void setSpare(int spare) {
        this.spare = spare;
    }

    public static void setTwoDigits(NumberFormat twoDigits) {
        AIS05.twoDigits = twoDigits;
    }

    /**
     * dte
     *
     * @return boolean value of dte (bit 423)
     */
    @Override
    public boolean getDte() {
        return dte;
    }

    /**
     * spare
     *
     * @return int value of spare (1 bits [424,424])
     */
    @Override
    public int getSpare() {
        return spare;
    }

    /**
     * A number format specifying that at least two digits should be used
     */
    private static NumberFormat twoDigits = NumberFormat.getIntegerInstance();

    static {
        twoDigits.setMinimumIntegerDigits(2);
    }

    

    /**
     * Generates a String representing the AIS message. Format: all fields are
     * shown in the order and units as specified by the standard separated by
     * the SEPARATOR string.
     */
    
    @Override
    public String toString() {

        String result = super.toString();

        result += SEPARATOR + Integer.toString(imoNumber);
        result += SEPARATOR + callSign;
        result += SEPARATOR + name;
        
        result += SEPARATOR + UtilsShipType8.shipTypeToString(typeOfShipAndCargoType);
        //result += SEPARATOR + UtilsDimensions30.toString(dimension);
        //   String etaString = twoDigits.format(UtilsEta.getMonth(eta)) + "-" + twoDigits.format(UtilsEta.getDay(eta))
        //           + "T" + twoDigits.format(UtilsEta.getHour(eta)) + ":" + twoDigits.format(UtilsEta.getMinute(eta));
        //  result += SEPARATOR + etaString;
        result += SEPARATOR + maximumPresentStaticDraught;
        result += SEPARATOR + UtilsPositioningDevice.toString(typeOfElectronicPositionFixingDevice);
        result += SEPARATOR + destination;
        result += SEPARATOR + (dte ? "no DTE" : "with DTE");
        
        return result;
    }

}
