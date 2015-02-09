package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.AISMessageUTCReport;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.Sotdma;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.UtilsLatitude27;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.UtilsLongitude28;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.UtilsPositionInfo;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.UtilsPositioningDevice;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.UtilsSpare;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.UtilsTwosComplement;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.annotations.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.provenance.VDMMessageProvenance;


/** ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 * @author Pierre van de Laar
 * @author Brian C. Lane
 */


/**  AIS Base station and Mobile Station UTC reporting
 *
 * Field Nr     Field Name                          NrOf Bits   (from, to )
 * ------------------------------------------------------------------------
 *  1	messageID                        		       	   6	(   1,   6)
 *  2	repeatIndicator                         		   2	(   7,   8)
 *  3	userID                                  		  30	(   9,  38)
 *  4	utcYear                        		         	  14	(  39,  52)
 *  5	utcMonth                            	    	   4	(  53,  56)
 *  6	utcDay                                  		   5	(  57,  61)
 *  7	utcHour                                 		   5	(  62,  66)
 *  8	utcMinute                              		 	   6	(  67,  72)
 *  9	utcSecond                          	    	 	   6	(  73,  78)
 * 10	positionAccuracy                    	    	   1	(  79,  79)
 * 11	longitude                               		  28	(  80, 107)
 * 12	latitude                              		  	  27	( 108, 134)
 * 13	typeOfElectronicPositionFixingDevice    		   4	( 135, 138)
 * 14	transmissionControlForLongRangeBroadcastMessage	   1	( 139, 139)
 * 15	spare                                   	 	   9	( 140, 148)
 * 16	raimFlag                              		  	   1	( 149, 149)
 * 17	communicationState                      		  19	( 150, 168)
 *                                                      ---- +
 *                           (maximum) number of bits    168
 */
abstract class AISMessageUTCReportImpl extends AISMessageImpl implements AISMessageUTCReport {
	public static boolean validLength(int length)
	{
		return (length == LENGTH);
	}
	
	/**
	 * Return the difference in available and needed bits to parse this sixbit as an AISMessageUTCReport
	 * A positive difference indicates that there are more bits available than needed by the standard.
	 * @param sb
	 * @return
	 */
	public static int differenceInBits(Sixbit sb)
	{
		return (sb.available() - LENGTH);
	}

	private static final int UTCYEAR_FROM = 39;
	private static final int UTCYEAR_TO = 52;

	private int utcYear;
	/** utcYear
	 * @return int value of utcYear (14 bits [39,52])
	 */
	public int getUtcYear() { return utcYear; }


	private static final int UTCMONTH_FROM = 53;
	private static final int UTCMONTH_TO = 56;

	private int utcMonth;
	/** utcMonth
	 * @return int value of utcMonth (4 bits [53,56])
	 */
	public int getUtcMonth() { return utcMonth; }


	private static final int UTCDAY_FROM = 57;
	private static final int UTCDAY_TO = 61;

	private int utcDay;
	/** utcDay
	 * @return int value of utcDay (5 bits [57,61])
	 */
	public int getUtcDay() { return utcDay; }


	private static final int UTCHOUR_FROM = 62;
	private static final int UTCHOUR_TO = 66;

	private int utcHour;
	/** utcHour
	 * @return int value of utcHour (5 bits [62,66])
	 */
	public int getUtcHour() { return utcHour; }


	private static final int UTCMINUTE_FROM = 67;
	private static final int UTCMINUTE_TO = 72;

	private int utcMinute;
	/** utcMinute
	 * @return int value of utcMinute (6 bits [67,72])
	 */
	public int getUtcMinute() { return utcMinute; }


	private static final int UTCSECOND_FROM = 73;
	private static final int UTCSECOND_TO = 78;

	private int utcSecond;
	/** utcSecond
	 * @return int value of utcSecond (6 bits [73,78])
	 */
	public int getUtcSecond() { return utcSecond; }


	private static final int POSITIONACCURACY_BITINDEX = 79;

	private boolean positionAccuracy;
	/** positionAccuracy
	 * @return boolean value of positionAccuracy (bit 79)
	 */
	public boolean getPositionAccuracy() { return positionAccuracy; }


	private static final int LONGITUDE_FROM = 80;
	private static final int LONGITUDE_TO = 107;

	private double longitude;
	/** longitude
	 * @return double value of longitude (28 bits [80,107])
	 */
	public double getLongitudeInDegrees() { return longitude; }


	private static final int LATITUDE_FROM = 108;
	private static final int LATITUDE_TO = 134;

	private double latitude;
	/** latitude
	 * @return double value of latitude (27 bits [108,134])
	 */
	public double getLatitudeInDegrees() { return latitude; }


	private static final int TYPEOFELECTRONICPOSITIONFIXINGDEVICE_FROM = 135;
	private static final int TYPEOFELECTRONICPOSITIONFIXINGDEVICE_TO = 138;

	private int typeOfElectronicPositionFixingDevice;
	/** typeOfElectronicPositionFixingDevice
	 * @return int value of typeOfElectronicPositionFixingDevice (4 bits [135,138])
	 */
	public int getTypeOfElectronicPositionFixingDevice() { return typeOfElectronicPositionFixingDevice; }


	private static final int TRANSMISSIONCONTROLFORLONGRANGEBROADCASTMESSAGE_BITINDEX = 139;

	private boolean transmissionControlForLongRangeBroadcastMessage;
	/** transmissionControlForLongRangeBroadcastMessage
	 * @return boolean value of transmissionControlForLongRangeBroadcastMessage (bit 139)
	 */
	public boolean getTransmissionControlForLongRangeBroadcastMessage() { return transmissionControlForLongRangeBroadcastMessage; }


	private static final int SPARE_FROM = 140;
	private static final int SPARE_TO = 148;

	private int spare;
	/** spare
	 * @return int value of spare (9 bits [140,148])
	 */
	public int getSpare() { return spare; }


	private static final int RAIMFLAG_BITINDEX = 149;

	private boolean raimFlag;
	/** raimFlag
	 * @return boolean value of raimFlag (bit 149)
	 */
	public boolean getRaimFlag() { return raimFlag; }


	private static final int COMMUNICATIONSTATE_FROM = 150;
	//private static final int COMMUNICATIONSTATE_TO = 168;

	private Sotdma communicationState;
	public static final int LENGTH = 168;
	/** communicationState
	 * @return Sotdma value of communicationState (19 bits [150,168])
	 */
	public Sotdma getCommunicationState() { return communicationState; }

	/** AISMessage UTCReport constructor
	 * @param content AIS content
	 * @param prov the provenance of the message
	 * @precondition validLength(content.length())
	 */
	public AISMessageUTCReportImpl(Sixbit content, VDMMessageProvenance prov)
	{
		super(content, prov);
		assert(validLength(content.length()));
		
	    utcYear = content.getIntFromTo(UTCYEAR_FROM,UTCYEAR_TO);
	    utcMonth = content.getIntFromTo(UTCMONTH_FROM,UTCMONTH_TO);
	    utcDay = content.getIntFromTo(UTCDAY_FROM,UTCDAY_TO);
	    utcHour = content.getIntFromTo(UTCHOUR_FROM,UTCHOUR_TO);
	    utcMinute = content.getIntFromTo(UTCMINUTE_FROM,UTCMINUTE_TO);
	    utcSecond = content.getIntFromTo(UTCSECOND_FROM,UTCSECOND_TO);
	    positionAccuracy = content.getBoolean(POSITIONACCURACY_BITINDEX);
	    longitude = UtilsLongitude28.toDegrees( UtilsTwosComplement.convertFrom28Bits( content.getIntFromTo(LONGITUDE_FROM,LONGITUDE_TO) ) );
	    if (!UtilsPositionInfo.isLongitudeSemanticallyCorrect(longitude))
	    {
	    	annotations.add (new AISIllegalValueAnnotation("getLongitudeInDegrees", longitude, UtilsPositionInfo.rangeLongitude));
	    }
	    latitude = UtilsLatitude27.toDegrees( UtilsTwosComplement.convertFrom27Bits( content.getIntFromTo(LATITUDE_FROM,LATITUDE_TO) ) );
	    if (!UtilsPositionInfo.isLatitudeSemanticallyCorrect(latitude))
	    {
	    	annotations.add (new AISIllegalValueAnnotation("getLatitudeInDegrees", latitude, UtilsPositionInfo.rangeLatitude));
	    }
	    typeOfElectronicPositionFixingDevice = content.getIntFromTo(TYPEOFELECTRONICPOSITIONFIXINGDEVICE_FROM,TYPEOFELECTRONICPOSITIONFIXINGDEVICE_TO);
	    transmissionControlForLongRangeBroadcastMessage = content.getBoolean(TRANSMISSIONCONTROLFORLONGRANGEBROADCASTMESSAGE_BITINDEX);
	    spare = content.getIntFromTo(SPARE_FROM,SPARE_TO);
	    if (!UtilsSpare.isSpareSemanticallyCorrect(spare))
	    {
	    	annotations.add(new AISIllegalValueAnnotation("getSpare", spare, UtilsSpare.range));
	    }
	    raimFlag = content.getBoolean(RAIMFLAG_BITINDEX);
	    communicationState = new Sotdma(COMMUNICATIONSTATE_FROM,content);
	}
	
	/** Generates a String representing the AIS message. 
	 * Format: all fields are shown in the order as specified by the standard separated by the SEPARATOR string.
	 */
	@Override
	public String toString() {
		return super.toString() + SEPARATOR + getUtcYear()
								+ SEPARATOR + getUtcMonth()
								+ SEPARATOR + getUtcDay()
								+ SEPARATOR + getUtcHour()
								+ SEPARATOR + getUtcMinute()
								+ SEPARATOR + getUtcSecond()
								+ SEPARATOR + (positionAccuracy ? "high" : "low") + " accuracy"
								+ SEPARATOR + UtilsPositionInfo.longitudeToString(longitude)
								+ SEPARATOR + UtilsPositionInfo.latitudeToString(latitude)
								+ SEPARATOR + UtilsPositioningDevice.toString(typeOfElectronicPositionFixingDevice)
								+ SEPARATOR + (transmissionControlForLongRangeBroadcastMessage ? "transmit" : "stop transmission of" ) + " message 27"
								+ SEPARATOR + "RAIM " + (raimFlag ? "in use" : "not in use")
								+ SEPARATOR + communicationState.toString(); 
	}
}