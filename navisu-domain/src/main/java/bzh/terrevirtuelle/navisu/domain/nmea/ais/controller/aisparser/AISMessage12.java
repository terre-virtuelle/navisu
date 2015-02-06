/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

/** This interface represents an AIS message of type 12: Addressed Safety Related Message.
*
* @author Pierre van de Laar
* @author Pierre America
* @author Brian C. Lane
*/
public interface AISMessage12 extends AISMessage {

	/** sequenceNumber
	 * @return int value of sequenceNumber (2 bits [39,40])
	 */
	public int getSequenceNumber();

	/** destinationID
	 * @return int value of destinationID (30 bits [41,70])
	 */
	public int getDestinationID();

	/** retransmitFlag
	 * @return boolean value of retransmitFlag (bit 71)
	 */
	public boolean getRetransmitFlag();

	/** spare1
	 * @return int value of spare1 (1 bits [72,72])
	 */
	public int getSpare1();

	/** spare2
	 * @return int value of spare2 (at end to adhere to byte boundaries)
	 */
	public int getSpare2();

	/** safetyRelatedText
	 * @return String value of safetyRelatedText (936 bits [73,1008])
	 */
	public String getSafetyRelatedText();

}