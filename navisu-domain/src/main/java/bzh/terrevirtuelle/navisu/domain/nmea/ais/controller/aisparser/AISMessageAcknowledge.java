/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

/** This is the common interface for all types of AIS messages that provide acknowledgements of previously received messages. 
 * @author Pierre van de Laar
 * @author Pierre America
 */
public interface AISMessageAcknowledge extends AISMessage {

	/** Returns the value of the spare bits.
	 * @return integer value of the spare bits. This should be zero. 
	 */
	public int getSpare();

	/** Returns an array of units representing the messages of which receipt is acknowledge.
	 * @return the units representing received messages
	 */
	public AISMessageAcknowledgeUnit[] getUnits();

}