/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

public interface AISMessage15Unit {

	/** destinationID
	 * @return int value of destinationID1 (30 bits offset+[0,29])
	 */
	public int getDestinationID();

	/** messages
	 * @return AISMessage15Message[] value of messages
	 */
	public AISMessage15Message[] getMessages();

}