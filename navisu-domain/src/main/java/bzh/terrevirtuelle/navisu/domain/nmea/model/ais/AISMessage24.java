/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.ais;

/** This is the common interface for all AIS messages of type 24: Static data report.
 * There are (currently) two variants of type 24 messages: Part A and Part B.
 * For each variant (and for invalid messages) there is a corresponding subinterface.
 * They can be distinguished by the method {@link #getPartNumber}.
 * @author Pierre van de Laar
 * @author Pierre America
 */
public interface AISMessage24 extends AISMessage {

	/** Returns the part number.
	 * @return the part number: <br>
	 * 0: Part A, see {@link AISMessage24PartA} <br>
	 * 1: Part B, see {@link AISMessage24PartB} <br>
	 * others: invalid, see {@link AISMessage}
	 */
	public int getPartNumber();

}