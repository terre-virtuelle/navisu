/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.AISAnnotation;


/** This class represents annotations related to deviations from the AIS standard that are detected during parsing of AIS messages.
 * These annotations are stored in the provenance of the resulting message objects, so that they can analyzed by a client program.
 * @author Pierre America
 * @author Pierre van de Laar
 */
public class AISNoCompleteMessageIdAnnotation implements AISAnnotation {
	private int length;

    public AISNoCompleteMessageIdAnnotation() {
    }
	
	/** Constructor. 
	 * @param length actual length of message Id
	 */
	public AISNoCompleteMessageIdAnnotation(int length)
	{
		this.length = length;
	}
	
	public String toString()
	{
		return "Violation of standard: Message Id length is " + length + " instead of 6"; 
	}
}
