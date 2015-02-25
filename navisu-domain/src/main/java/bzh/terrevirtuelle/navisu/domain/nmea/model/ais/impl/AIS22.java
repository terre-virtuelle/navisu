package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage22;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.Destination;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsPositionInfo;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsSpare;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/** ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 * @author Pierre van de Laar
 * @author Brian C. Lane
 */


/** AIS Message 22 class
 * Channel Management
 * 
 * Field Nr     Field Name                          NrOf Bits   (from, to )
 * ------------------------------------------------------------------------
 *  1	messageID                               	   6	(   1,   6)
 *  2	repeatIndicator                         	   2	(   7,   8)
 *  3	mmsi                                  	  30	(   9,  38)
 *  4	spare1                                  	   2	(  39,  40)
 *  5	channelA                                	  12	(  41,  52)
 *  6	channelB                                	  12	(  53,  64)
 *  7	txrxMode                                	   4	(  65,  68)
 *  8	power                                   	   1	(  69,  69)
 *  9	destination                             	  70	(  70, 139)
 * 10	addressedMessageIndicator               	   1	( 140, 140)
 * 11	channelABandwidth                       	   1	( 141, 141)
 * 12	channelBBandwidth                       	   1	( 142, 142)
 * 13	transitionalZoneSize                    	   3	( 143, 145)
 * 14	spare2                                  	  23	( 146, 168)
 *                                                      ---- +
 *                           (maximum) number of bits    168
 */
@XmlRootElement(name = "ais22")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS22 extends AISMessageImpl implements AISMessage22 {

    /**
     *
     */
    public static final int LENGTH = 168;

    /**
     *
     * @param length
     * @return
     */
    public static boolean validLength(int length)
	{
		return (length == LENGTH);
	}

	/**
	 * Return the difference in available and needed bits to parse this sixbit as an AisMessage
	 * A positive difference indicates that there are more bits available than needed by the standard.
	 * @param sb
	 * @return
	 */
	public static int differenceInBits(Sixbit sb)
	{
		return (sb.available() - LENGTH);
	}
	
	private static final int SPARE1_FROM = 39;
	private static final int SPARE1_TO = 40;

	private int spare1;
	/** spare1
	 * @return int value of spare1 (2 bits [39,40])
	 */
	public int getSpare1() { return spare1; }


	private static final int CHANNELA_FROM = 41;
	private static final int CHANNELA_TO = 52;

	private int channelA;
	/** channelA
	 * @return int value of channelA (12 bits [41,52])
	 */
	public int getChannelA() { return channelA; }


	private static final int CHANNELB_FROM = 53;
	private static final int CHANNELB_TO = 64;

	private int channelB;
	/** channelB
	 * @return int value of channelB (12 bits [53,64])
	 */
	public int getChannelB() { return channelB; }


	private static final int TXRXMODE_FROM = 65;
	private static final int TXRXMODE_TO = 68;

	private int txrxMode;
	/** txrxMode
	 * @return int value of txrxMode (4 bits [65,68])
	 */
	public int getTxrxMode() { return txrxMode; }


	private static final int POWER_BITINDEX = 69;

	private boolean power;
	/** power
	 * @return boolean value of power (bit 69)
	 */
	public boolean getPower() { return power; }


	private static final int DESTINATION_FROM = 70;
	//private static final int DESTINATION_TO = 139;

	private Destination destination;
	/** destination
	 * @return Destination value of destination (70 bits [70,139])
	 */
	public Destination getDestination() { return destination; }


	private static final int ADDRESSEDMESSAGEINDICATOR_BITINDEX = 140;

	private boolean addressedMessageIndicator;
	/** addressedMessageIndicator
	 * @return boolean value of addressedMessageIndicator (bit 140)
	 */
	public boolean getAddressedMessageIndicator() { return addressedMessageIndicator; }


	private static final int CHANNELABANDWIDTH_BITINDEX = 141;

	private boolean channelABandwidth;
	/** channelABandwidth
	 * @return boolean value of channelABandwidth (bit 141)
	 */
	public boolean getChannelABandwidth() { return channelABandwidth; }


	private static final int CHANNELBBANDWIDTH_BITINDEX = 142;

	private boolean channelBBandwidth;
	/** channelBBandwidth
	 * @return boolean value of channelBBandwidth (bit 142)
	 */
	public boolean getChannelBBandwidth() { return channelBBandwidth; }


	private static final int TRANSITIONALZONESIZE_FROM = 143;
	private static final int TRANSITIONALZONESIZE_TO = 145;

	private int transitionalZoneSize;
	/** transitionalZoneSize
	 * @return int value of transitionalZoneSize (3 bits [143,145])
	 */
	public int getTransitionalZoneSize() { return transitionalZoneSize; }


	private static final int SPARE2_FROM = 146;
	private static final int SPARE2_TO = 168;

	private int spare2;
	/** spare2
	 * @return int value of spare2 (23 bits [146,168])
	 */
	public int getSpare2() { return spare2; }

    /**
     * AIS22 default constructor
     */
    public AIS22() {
    }

	/** AISMessage 22 constructor
	 * @param content AIS content
	 * @param prov the provenance of the message
	 * @precondition validLength(content.length()) && AISMessageBase.getMessageId(content)==22
	 */
	public AIS22(Sixbit content, VDMMessageProvenance prov)
	{
		super(content, prov);
		assert(validLength(content.length()));
		assert(getMessageID() == 22);

		spare1 = content.getIntFromTo(SPARE1_FROM,SPARE1_TO);
	    if (!UtilsSpare.isSpareSemanticallyCorrect(spare1))
	    {
	    	annotations.add(new AISIllegalValueAnnotation("getSpare1", spare1, UtilsSpare.range));
	    }
		channelA = content.getIntFromTo(CHANNELA_FROM,CHANNELA_TO);
		channelB = content.getIntFromTo(CHANNELB_FROM,CHANNELB_TO);
		txrxMode = content.getIntFromTo(TXRXMODE_FROM,TXRXMODE_TO);
		power = content.getBoolean(POWER_BITINDEX);
		addressedMessageIndicator = content.getBoolean(ADDRESSEDMESSAGEINDICATOR_BITINDEX);
		if (addressedMessageIndicator)
		{
			destination = new DestinationIDs(DESTINATION_FROM, content);
		}
		else
		{
			destination = new DestinationArea(DESTINATION_FROM, content);
			if (!UtilsPositionInfo.isLongitudeSemanticallyCorrect(((DestinationArea)destination).longitudeUpperRightCornerInDegrees()))
		    {
		    	annotations.add (new AISIllegalValueAnnotation("getDestination.longitudeUpperRightCorner",((DestinationArea)destination).longitudeUpperRightCornerInDegrees(), UtilsPositionInfo.rangeLongitude));
		    }
			if (!UtilsPositionInfo.isLongitudeSemanticallyCorrect(((DestinationArea)destination).longitudeLowerLeftCornerInDegrees()))
		    {
		    	annotations.add (new AISIllegalValueAnnotation("getDestination.longitudeLowerLeftCorner",((DestinationArea)destination).longitudeLowerLeftCornerInDegrees(), UtilsPositionInfo.rangeLongitude));
		    }
	    	if (!UtilsPositionInfo.isLatitudeSemanticallyCorrect(((DestinationArea)destination).latitudeUpperRightCornerInDegrees()))
		    {
		    	annotations.add (new AISIllegalValueAnnotation("getDestination.latitudeUpperRightCorner",((DestinationArea)destination).latitudeUpperRightCornerInDegrees(), UtilsPositionInfo.rangeLatitude));
		    }
	    	if (!UtilsPositionInfo.isLatitudeSemanticallyCorrect(((DestinationArea)destination).latitudeLowerLeftCornerInDegrees()))
		    {
		    	annotations.add (new AISIllegalValueAnnotation("getDestination.latitudeLowerLeftCorner",((DestinationArea)destination).latitudeLowerLeftCornerInDegrees(), UtilsPositionInfo.rangeLatitude));
		    }
		}
		channelABandwidth = content.getBoolean(CHANNELABANDWIDTH_BITINDEX);
		channelBBandwidth = content.getBoolean(CHANNELBBANDWIDTH_BITINDEX);
		transitionalZoneSize = content.getIntFromTo(TRANSITIONALZONESIZE_FROM,TRANSITIONALZONESIZE_TO);
		spare2 = content.getIntFromTo(SPARE2_FROM,SPARE2_TO);
	    if (!UtilsSpare.isSpareSemanticallyCorrect(spare2))
	    {
	    	annotations.add(new AISIllegalValueAnnotation("getSpare2", spare2, UtilsSpare.range));
	    }
	}
}