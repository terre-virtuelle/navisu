/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.Annotation;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.ChannelIdAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.NrOfFillBitsAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.Provenance;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMLineProvenance;


/** VDMLine
 *
 */
public class VDMLine 
{
	/**Is Valid?
     * @param sensorData
	 * @return boolean representing whether sensorData can be use to create a VDMLine (i.e. whether it is valid Sensor Data) 
	 */
	static public boolean isValid(String sensorData)
    {
		return VdmPattern.matcher(sensorData).matches();
    }	
	
	/** Is ChannelId according to standard?
	 * @param channelId the channel Id
	 * @return boolean representing whether the channelId is according to standard
	 */
	static private boolean isChannelIdAccordingToStandard(String channelId)
    {
		return "A".equals(channelId) || "B".equals(channelId);
    }

	private final static String templateTalkerID = "(?:AB|AI|BS)";		// known talker ids  	 	
	private final static String templateNrOfParts = "[1-9]"; 		//There can be only 9 parts
	private final static String templatePartId = "[1-9]"; 			//PartId <= NrOfParts
	private final static String templateMessageId = "\\d?";			//Message Id [0-9]
	/*
	 * According to the Standard ITU-R M.1371-4 channel is a single character with value either A or B 
	 * See e.g. page 5 figure 1 and page 30 figure 10. 
	 * Yet, paragraph 4.4.2 on page 93 mentions four frequencies.
	 * And, paragraph 4.2.1 on page 77 mentions regional frequencies and default channel 1 and 2 (see also Table 3 on page 7)
	 * 
	 *  In practice, also '1','2' and 'C' is observed.
	 *  Furthermore, also empty string is observed.
	 */
	private final static String templateChannel = "[ABC12]?";
	
	private final static String templatePayLoad = "[0-W`-w]+"; 		//At least one character expected. Acceptable since no Payload has no information
																	//TODO: is there a maximal length?
	private final static String templateNrOfFillBits = "\\d"; 		//FillBits: one character has 6 bits -> maximal 5 fill bits 
	private final static String templateCheckSum = "[0-9A-F]{2}";	//Two hexadecimal values: never observed lowercase

	private final static Pattern VdmPattern = Pattern.compile("!(" 
	        + templateTalkerID
			+ "VD[MO],("											//external and own ship messages 
            + templateNrOfParts +"),("
            + templatePartId +"),("
            + templateMessageId +"),("
            + templateChannel + "),("
            + templatePayLoad +"),("
            + templateNrOfFillBits + "))\\*(" 
            + templateCheckSum +")");

	private final static int indexPartCheckSum = 1;
	private final static int indexNrOfParts = 2;
	private final static int indexPartId = 3;
	private final static int indexMessageId = 4;
	private final static int indexChannel = 5;
	private final static int indexPayLoad = 6;
	private final static int indexNrOfFillBits = 7;
	private final static int indexCheckSum = 8;
	
	private Provenance source;
	private List<Annotation> annotations = new ArrayList<Annotation>();

    public VDMLine() {
    }

	/** VDMLine constructor
	 * @param source describing where sensorData comes from
	 * @param sensorData the data received by the sensor
	 * @precondition isValid(sensorData) {@link #isValid(String)}
	 */
	public VDMLine(Provenance source, String sensorData)
	{
		this.source = source;
		
		Matcher matcher = VdmPattern.matcher(sensorData);
		boolean match = matcher.matches();		//matches() must always be executed before matcher.group can be called!
		assert( match ); 						//Precondition check
		nrOfParts = Integer.parseInt(matcher.group(indexNrOfParts));
		partId = Integer.parseInt(matcher.group(indexPartId));
		messageId = matcher.group(indexMessageId);
		channelId = matcher.group(indexChannel);
		if (!isChannelIdAccordingToStandard(channelId)) //add annotation related to channelId if needed
		{
			annotations.add(new ChannelIdAnnotation(channelId));
		}
		payLoad = matcher.group(indexPayLoad);
		nrOfFillBits = Integer.parseInt(matcher.group(indexNrOfFillBits));
		if (nrOfFillBits > 5) //add annotation related to deviation of number of fill bits
		{
			annotations.add(new NrOfFillBitsAnnotation(nrOfFillBits));
		}
		checkSum = Integer.parseInt(matcher.group(indexCheckSum),16);
		isCheckSumCorrect = (checkSum == calculateCheckSum( matcher.group(indexPartCheckSum) ) );
		//TODO: should we annotated and parse messages with an incorrect checksum?
	}
	
	/** provenance of VDMLine
	 * @return the provenance of the VDM line
	 */
	public VDMLineProvenance getProvenance() {
		return new VDMLineProvenance(source, annotations);
	}

		
	private int nrOfParts;
	/** Get Number Of Parts in VDM Line
	 * @return number of parts 
	 */
	public int getNrOfParts()
	{
		return nrOfParts; 
	}

	private int partId;
	/** Get Part Id in VDM Line
	 * @return part identifier 
	 */
	public int getPartId() {
		return partId;
	}
	
	private String messageId; 
	/** Get Message Id in VDM Line
	 * @postcondition messageId().length() <= 1
	 * @return message identifier 
	 */
	public String getMessageId() {
		return messageId;
	}

	private String channelId;
	/** Get Channel in VDM Line
	 * according to standard the channel is a single character with value A or B
	 * But in practice also 1 , 2 and even C is observed
	 * Furthermore, the channel is sometimes missing resulting in an empty string 
	 * @postcondition channelId().length() <= 1
	 * @return channel 
	 */
	public String getChannelId() {
		return channelId;
	}
	
	private String payLoad;
	/** Get PayLoad in VDM Line
	 * @return pay load 
	 */
	public String getPayLoad() {
		return payLoad;
	}
	
	private int nrOfFillBits;
	/** Get NrOf FillBits in VDM Line
	 * According to the standard the NrOf FillBits is between 0 and 5
	 * @return number of FillBits 
	 */
	public int getNrOfFillBits() {
		return nrOfFillBits;
	}

	private int checkSum;
	/** Get CheckSum in VDM Line
	 * @postcondition 0 <= return value < 256
	 * @return message identifier 
	 */
	public int getCheckSum() {
		return checkSum;
	}
	
	/**Is VDM Line semantically valid?
	 * @return boolean representing the semantical validity
	 */
	public boolean isSemanticallyValid()
    {
    	return isCheckSumCorrect() && isPartIdCorrect();
    }

	private boolean isCheckSumCorrect;
	/**Is CheckSum correct?
	 * @return boolean representing the equality of the parsed and calculated checksum
	 */
	public boolean isCheckSumCorrect()
    {
    	return isCheckSumCorrect;
    }
	
	/**Calculate Checksum 
	 * @param characters characters of which the XOR checksum of individual characters will be calculated 
	 * @return calculated checksum
	 */
	static public int calculateCheckSum(String characters)
    {
		int checksum = 0;
		for (int i = 0; i < characters.length(); i++)
		{
			checksum ^= characters.charAt(i);
		}
		return checksum; 
    }

	/**Is PartId correct?
	 * @return boolean representing whether partId() <= nrOfParts() 
	 */
	public boolean isPartIdCorrect()
    {
    	return getPartId() <= getNrOfParts();
    }	
}