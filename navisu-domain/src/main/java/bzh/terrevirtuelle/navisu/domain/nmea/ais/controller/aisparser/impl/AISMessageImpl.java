package nl.esi.metis.aisparser.impl;

import java.util.ArrayList;
import java.util.List;

import nl.esi.metis.aisparser.AISMessage;
import nl.esi.metis.aisparser.Sixbit;
import nl.esi.metis.aisparser.annotations.Annotation;
import nl.esi.metis.aisparser.provenance.AISMessageProvenance;
import nl.esi.metis.aisparser.provenance.VDMMessageProvenance;

/** This is the base class implementation for all AIS messages. It implements the fields that they all have in common.
 * 
 * <pre>
 * Field Nr     Field Name                          NrOf Bits   (from,  to)
 * ------------------------------------------------------------------------
 *    1         messageID                                   6   (   1,   6)
 *    2         repeatIndicator                             2   (   7,   8)
 *    3         userID                                     10   (   9,  38)
 * </pre>
 * 
 * @author Pierre van de Laar
 * @author Pierre America
 */

class AISMessageImpl implements AISMessage {
	
	/** This string is used by this class and its subclasses to separate the different fields in generated strings. */
	public static final String SEPARATOR = "|";			//bug prevention: using a string detects a nasty bug (two +'s) which is accepted by a char (but yielding unexpected results)

	/** Extracts the message ID from the content of an AIS message.
	 * @param content the content of an AIS message
	 * @return integer value of the message ID (6 bits [1,6])
	 */
	public static int getMessageID(Sixbit content)
	{
		return content.getIntFromTo(MESSAGEID_FROM,MESSAGEID_TO);
	}
	
	/** Changes the message ID from the content of an AIS message.
	 * @param content the content of an AIS message
	 */
	public static void setMessageID(Sixbit content, int id)
	{
		content.setIntFromTo(id, MESSAGEID_FROM,MESSAGEID_TO);
	}

	/** The position of the first bit of the message ID. */
	private static final int MESSAGEID_FROM = 1;
	
	/** The position of the last bit of the message ID. */
	private static final int MESSAGEID_TO = 6;

	/** The message ID. */
	private int messageID;

	/** Returns the message ID.
	 * @return integer value of message ID (6 bits [1,6])
	 */
	public int getMessageID() { return messageID; }

	/** The position of the first bit of the repeat indicator. */
	private static final int REPEATINDICATOR_FROM = 7;

	/** The position of the last bit of the repeat indicator. */
	private static final int REPEATINDICATOR_TO = 8;

	/** The repeat indicator */
	private int repeatIndicator;

	/** Returns the repeat indicator.
	 * @return integer value of repeat indicator (2 bits [7,8])
	 */
	public int getRepeatIndicator() { return repeatIndicator; }

	/** The position of the first bit of the user ID. */
	private static final int USERID_FROM = 9;

	/** The position of the last bit of the user ID. */
	private static final int USERID_TO = 38;

	/** The user ID. */
	private int userID;
	
	/** Returns the user ID.
	 * @return integer value of user ID (30 bits [9,38])
	 */
	public int getUserID() { return userID; }
	
	/** The provenance */
	private VDMMessageProvenance provenance;
	
	/** The annotations */
	protected List<Annotation> annotations = new ArrayList<Annotation>();
	
	/**
	 * Add annotation a to annotations of this AISMessage
	 * @param a
	 */
	public void AddAnnotation (Annotation a)
	{
		annotations.add(a);
	}
	
	/** Returns the provenance, i.e., a description of where the AIS message came from, including a time stamp.
	 * @return the provenance
	 */
	public AISMessageProvenance getProvenance() {
		return new AISMessageProvenance(provenance, annotations);
	}
	
	
	/** Constructor, used by subclasses to initialize the fields of this class.
	 * @param content the content of an AIS message
	 * @param provenance the provenance of the AIS message
	 */
    protected AISMessageImpl(Sixbit content, VDMMessageProvenance provenance)
	{
    	messageID = content.getIntFromTo(MESSAGEID_FROM,MESSAGEID_TO);
    	repeatIndicator = content.getIntFromTo(REPEATINDICATOR_FROM,REPEATINDICATOR_TO);
    	userID = content.getIntFromTo(USERID_FROM,USERID_TO);
    	this.provenance = provenance;
	}
    
    /** Generates a <code>String</code> representing the parsed <code>AISMessage</code>.
     * Format:
     * all fields are shown in the order and units as specified by the standard separated by the <code>SEPARATOR</code> string.
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
    	return  Integer.toString(messageID) + SEPARATOR +
    			Integer.toString(repeatIndicator) + SEPARATOR +
    			Integer.toString(userID);
    }
}