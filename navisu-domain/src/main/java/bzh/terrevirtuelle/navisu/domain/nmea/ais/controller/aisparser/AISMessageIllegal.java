/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

public interface AISMessageIllegal extends AISMessage {

	/** messageID
	 * @return int value of messageID (6 bits [1,6])
	 */
	public int getMessageID();

	/** repeatIndicator
	 * @return int value of repeatIndicator (2 bits [7,8])
	 */
	public int getRepeatIndicator();

	/** userID
	 * @return int value of userID (30 bits [9,38])
	 */
	public int getUserID();

	/* (non-Javadoc)
	 * Generates a String representing the parsed AISMessage 
	 * Format:
	 * all fields are shown in the order and units as specified by the standard separated by SEPARATORs
	 * @see java.lang.Object#toString()
	 */
	public String toString();

}