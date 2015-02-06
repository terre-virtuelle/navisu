/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

import java.util.ArrayList;
import java.util.List;

import nl.esi.metis.aisparser.annotations.Annotation;
import nl.esi.metis.aisparser.annotations.ChangedChannelIdAnnotation;
import nl.esi.metis.aisparser.annotations.ChangedMessageIdAnnotation;
import nl.esi.metis.aisparser.provenance.VDMLineProvenance;
import nl.esi.metis.aisparser.provenance.VDMMessageProvenance;

public class VDMMessage {
	/**Is Valid?
	 * @return boolean representing whether VDM Line can be use to create a VDM Message (i.e. whether it is valid VDM Line) 
	 */
	static public boolean isValid(VDMLine line)
    {
		return line.isSemanticallyValid();
    }	
		
	
	private boolean isComplete;
	private int nrOfParts;
	private int lastPartId;
	private String messageId;
	private String channelId;
	private String[] messages;
	private int nrOfFillBits;
	private VDMLineProvenance[] provenances;	
	private List<Annotation> annotations = new ArrayList<Annotation>();

	/** Get Number Of Parts of VDM Message
	 * @return number of parts 
	 */
	public int getNrOfParts()
	{
		return nrOfParts;
	}

	/** Get Last Part Id of VDM Message
	 * @return part identifier 
	 */
	public int getLastPartId() {
		return lastPartId;
	}
	
	/** Get Message Id of VDM Message
	 * @return message identifier 
	 */
	public String getMessageId() {
		return messageId;
	}

	/** Get Channel Identifier of VDM Message
	 * @return channel id 
	 */
	public String getChannelId() {
		return channelId;
	}

	/** get Message of VDM Message.
	 * If the Message is incomplete, the message only contains the payLoad from the lines before the first missing line.
	 * @return the content of the VDM message 
	 */
	public String getMessage() {
		StringBuffer sb = new StringBuffer();
		//bounded adding of messages
		int x = 0;
		int y = messages.length;
		while(x != y)
		{
			if(messages[x]!= null)
			{
				sb.append(messages[x]);
				x++;
			}
			else
			{
				y=x;
			}
		}
		return  sb.toString();
	}
	
	/** Get number of fill bits of VDM Message
	 * @return number of fill bits
	 */
	//TODO: is it correct to only look at the last fill bits specified?
	public int getNrOfFillBits() {
		return nrOfFillBits;
	}

	/** provenance
	 * @return the provenance of the VDM message
	 */
	public VDMMessageProvenance getProvenance() {
		return new VDMMessageProvenance(provenances, annotations, channelId);		
	}
	

	//TODO 
    /** Constructor
	 * @precondition isValid(line) {@link #isValid(VDMLine)}	 
	 * @param line
	 */
	public VDMMessage(VDMLine line)
	{
		nrOfParts = line.getNrOfParts();
		lastPartId = line.getPartId();
		messageId = line.getMessageId();
		channelId = line.getChannelId();
		messages = new String[nrOfParts];
		messages[lastPartId-1] = line.getPayLoad();	//TODO: deep copy needed?
		nrOfFillBits = line.getNrOfFillBits();
	
		provenances = new VDMLineProvenance[nrOfParts];
		provenances[lastPartId-1]= line.getProvenance(); 
	
		isComplete = (lastPartId == 1);
	}

	/**Is VDM message complete?
	 * @return boolean indicating whether message is complete (so far)
	 */
	public boolean isComplete()
	{
		return isComplete;
	}
	
	/** Is the last part of the VDM message added? 
	 * @return true if the last part has been added, false otherwise
	 */
	public boolean atEnd()
	{
		return lastPartId == nrOfParts;
	}
	
	/** returns CharType associated with ChannelId
	 * 
	 * @param channelId channelId string
	 * @return OTHER, ALPHABETIC, or NUMERIC
	 */
	public static CharType getChannelType(String channelId)
	{
		CharType channelType = CharType.OTHER;
		if (channelId.length() == 1)
		{
			channelType = CharType.getType(channelId.charAt(0));
		}
		return channelType;
	}
	
	/** Is VDM line part of this VDM message?
	 * The parser accepts
	 * Same number of parts, higher partId, same channelId, and same messageId
	 * and
	 * Same number of parts, next partId, and either same channelId or (same messageId and same channelIdType) 
	 * @param line
	 * @return boolean indicating whether line is part of VDM message
	 */
	public boolean isPartOfMessage(VDMLine line)
	{
		if ( (nrOfParts == line.getNrOfParts() ) && (lastPartId < line.getPartId() ) )
		{
			if (lastPartId +1 == line.getPartId() )
			{
				//TODO: check assumptions that vessels either use channels ("A" and "B") or ("1" and "2") but they don't mix digits and characters! 
				return ( channelId.equals(line.getChannelId() ) || 
						 ( messageId.equals(line.getMessageId()) && getChannelType(channelId).equals(getChannelType(line.getChannelId())) )
					   );
			}
			else
			{   //lastPartId +1 < line.getPartId()
				return ( channelId.equals(line.getChannelId()) && messageId.equals(line.getMessageId()) );
			}
		}
		else
			return false;
	}


	/** Add part to VDM Message
	 * Small standard violations are accepted but documented using annotations as part of the provenance
	 * Note: missing lines are not annotated.
	 * It is a fact, and not a violation of the standard, that lines can get lost!  
	 * @param line
	 */
	public void add(VDMLine line)
	{	
		final int partId = line.getPartId();
		isComplete &= (lastPartId+1 == partId);
		
		lastPartId = partId;
		messages[lastPartId-1] = line.getPayLoad();
		provenances[lastPartId-1] = line.getProvenance();
		nrOfFillBits = line.getNrOfFillBits();		//only last nrOfFillBits is relevant  (all earlier should be zero)
		
		if (!messageId.equals(line.getMessageId()))
		{
			annotations.add(new ChangedMessageIdAnnotation(messageId, line.getMessageId()));
		}
		if (!channelId.equals(line.getChannelId())) //add annotation related to channelId if needed
		{
			annotations.add(new ChangedChannelIdAnnotation(channelId, line.getChannelId()));
		}
	}
}