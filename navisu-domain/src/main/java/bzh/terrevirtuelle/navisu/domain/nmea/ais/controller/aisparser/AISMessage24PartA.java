/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

/** This interface represents AIS messages of type 24 Part A: Static data report.
 * @author Pierre van de Laar
 * @author Pierre America
 */
public interface AISMessage24PartA extends AISMessage24 {

	/** Returns the name of the transmitting ship.
	 * @return a String value, containing maximum 20 characters, representing the name. <br>
	 * "" = not available. <br>
	 * For SAR aircraft, it should be set to "SAR AIRCRAFT NNNNNNN" where NNNNNNN equals the aircraft registration number
	 */
	public String getName();

}