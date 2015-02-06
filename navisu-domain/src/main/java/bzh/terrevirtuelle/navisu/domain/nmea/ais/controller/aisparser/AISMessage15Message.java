/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

public interface AISMessage15Message {

	/** messageID
	 * @return int value of messageID (6 bits offset+[0,5])
	 */
	public int getMessageID();

	/** slotOffset
	 * @return int value of slotOffset (12 bitsoffset+[6,17])
	 */
	public int getSlotOffset();

}