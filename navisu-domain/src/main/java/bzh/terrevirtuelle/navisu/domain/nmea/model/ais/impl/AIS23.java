package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage23;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsPositionInfo;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsSpare;
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
 * AIS Message 23 Group Assignment Command
 *
 * Field Nr Field Name NrOf Bits (from, to )
 * ------------------------------------------------------------------------ 1
 * messageID 6	( 1, 6) 2	repeatIndicator 2	( 7, 8) 3	mmsi 30	( 9, 38) 4	spare1
 * 2	( 39, 40) 5	destinationArea 70	( 41, 110) 6	stationType 4	( 111, 114) 7
 * typeOfShipAndCargoType 8	( 115, 122) 8	spare2 22	( 123, 144) 9	txrxMode 2	(
 * 145, 146) 10	reportingInterval 4	( 147, 150) 11	quietTime 4	( 151, 154) 12
 * spare3 6	( 155, 160) ---- + (maximum) number of bits 160
 */
@XmlRootElement(name = "ais23")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS23 extends AISMessageImpl implements AISMessage23 {

    /**
     *
     */
    public static final int LENGTH = 160;

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
     * as an AisMessagePositionReport A positive difference indicates that there
     * are more bits available than needed by the standard.
     *
     * @param sb
     * @return
     */
    public static int differenceInBits(Sixbit sb) {
        return (sb.available() - LENGTH);
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

    private static final int DESTINATIONAREA_FROM = 41;
    //private static final int DESTINATIONAREA_TO = 110;

    private DestinationArea destinationArea;

    /**
     * destinationArea
     *
     * @return DestinationArea value of destinationArea (70 bits [41,110])
     */
    public DestinationArea getDestinationArea() {
        return destinationArea;
    }

    private static final int STATIONTYPE_FROM = 111;
    private static final int STATIONTYPE_TO = 114;

    private int stationType;

    /**
     * stationType
     *
     * @return int value of stationType (4 bits [111,114])
     */
    public int getStationType() {
        return stationType;
    }

    private static final int TYPEOFSHIPANDCARGOTYPE_FROM = 115;
    private static final int TYPEOFSHIPANDCARGOTYPE_TO = 122;

    private int typeOfShipAndCargoType;

    /**
     * typeOfShipAndCargoType
     *
     * @return int value of typeOfShipAndCargoType (8 bits [115,122])
     */
    public int getTypeOfShipAndCargoType() {
        return typeOfShipAndCargoType;
    }

    private static final int SPARE2_FROM = 123;
    private static final int SPARE2_TO = 144;

    private int spare2;

    /**
     * spare2
     *
     * @return int value of spare2 (22 bits [123,144])
     */
    public int getSpare2() {
        return spare2;
    }

    private static final int TXRXMODE_FROM = 145;
    private static final int TXRXMODE_TO = 146;

    private int txrxMode;

    /**
     * txrxMode
     *
     * @return int value of txrxMode (2 bits [145,146])
     */
    public int getTxrxMode() {
        return txrxMode;
    }

    private static final int REPORTINGINTERVAL_FROM = 147;
    private static final int REPORTINGINTERVAL_TO = 150;

    private int reportingInterval;

    /**
     * reportingInterval
     *
     * @return int value of reportingInterval (4 bits [147,150])
     */
    public int getReportingInterval() {
        return reportingInterval;
    }

    private static final int QUIETTIME_FROM = 151;
    private static final int QUIETTIME_TO = 154;

    private int quietTime;

    /**
     * quietTime
     *
     * @return int value of quietTime (4 bits [151,154])
     */
    public int getQuietTime() {
        return quietTime;
    }

    private static final int SPARE3_FROM = 155;
    private static final int SPARE3_TO = 160;

    private int spare3;

    /**
     * spare3
     *
     * @return int value of spare3 (6 bits [155,160])
     */
    public int getSpare3() {
        return spare3;
    }

    /**
     * AIS23 default constructor
     */
    public AIS23() {
    }

    /**
     * AISMessage 23 constructor
     *
     * @param content AIS content
     * @param prov the provenance of the message
     * @precondition validLength(content.length()) &&
     * AISMessageBase.getMessageId(content)==23
     */
    public AIS23(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);
        assert (validLength(content.length()));
        assert (getMessageID() == 23);

        spare1 = content.getIntFromTo(SPARE1_FROM, SPARE1_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare1)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare1", spare1, UtilsSpare.range));
        }
        destinationArea = new DestinationArea(DESTINATIONAREA_FROM, content);
        if (!UtilsPositionInfo.isLongitudeSemanticallyCorrect(destinationArea.longitudeUpperRightCornerInDegrees())) {
            annotations.add(new AISIllegalValueAnnotation("getDestinationArea.longitudeUpperRightCorner", destinationArea.longitudeUpperRightCornerInDegrees(), UtilsPositionInfo.rangeLongitude));
        }
        if (!UtilsPositionInfo.isLongitudeSemanticallyCorrect(destinationArea.longitudeLowerLeftCornerInDegrees())) {
            annotations.add(new AISIllegalValueAnnotation("getDestinationArea.longitudeLowerLeftCorner", destinationArea.longitudeLowerLeftCornerInDegrees(), UtilsPositionInfo.rangeLongitude));
        }
        if (!UtilsPositionInfo.isLatitudeSemanticallyCorrect(destinationArea.latitudeUpperRightCornerInDegrees())) {
            annotations.add(new AISIllegalValueAnnotation("getDestinationArea.latitudeUpperRightCorner", destinationArea.latitudeUpperRightCornerInDegrees(), UtilsPositionInfo.rangeLatitude));
        }
        if (!UtilsPositionInfo.isLatitudeSemanticallyCorrect(destinationArea.latitudeLowerLeftCornerInDegrees())) {
            annotations.add(new AISIllegalValueAnnotation("getDestinationArea.latitudeLowerLeftCorner", destinationArea.latitudeLowerLeftCornerInDegrees(), UtilsPositionInfo.rangeLatitude));
        }
        stationType = content.getIntFromTo(STATIONTYPE_FROM, STATIONTYPE_TO);
        typeOfShipAndCargoType = content.getIntFromTo(TYPEOFSHIPANDCARGOTYPE_FROM, TYPEOFSHIPANDCARGOTYPE_TO);
        spare2 = content.getIntFromTo(SPARE2_FROM, SPARE2_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare2)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare2", spare2, UtilsSpare.range));
        }
        txrxMode = content.getIntFromTo(TXRXMODE_FROM, TXRXMODE_TO);
        reportingInterval = content.getIntFromTo(REPORTINGINTERVAL_FROM, REPORTINGINTERVAL_TO);
        quietTime = content.getIntFromTo(QUIETTIME_FROM, QUIETTIME_TO);
        spare3 = content.getIntFromTo(SPARE3_FROM, SPARE3_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare3)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare3", spare3, UtilsSpare.range));
        }
    }
}
