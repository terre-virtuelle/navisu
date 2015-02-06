/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

public interface AISMessage16Unit {

	/** destinationID
	 * @return int value of destinationID (30 bits offset + [0,29])
	 */
	public int getDestinationID();

	/** offset
	 * @return int value of offset (12 bits offset+ [30,41])
	 */
	public int getOffset();

	/** increment
	 * @return int value of increment (10 bits offset+ [42,51])
	 */
	public int getIncrement();

}