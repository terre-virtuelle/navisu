/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

/** This interface represents an AIS message of type 10: UTC and date inquiry.
*
* @author Pierre van de Laar
* @author Pierre America
* @author Brian C. Lane
*/
public interface AISMessage10 extends AISMessage{

	/** spare1
	 * @return int value of spare1 (2 bits [39,40])
	 */
	public int getSpare1();

	/** destinationID
	 * @return int value of destinationID (30 bits [41,70])
	 */
	public int getDestinationID();

	/** spare2
	 * @return int value of spare2 (2 bits [71,72])
	 */
	public int getSpare2();

}