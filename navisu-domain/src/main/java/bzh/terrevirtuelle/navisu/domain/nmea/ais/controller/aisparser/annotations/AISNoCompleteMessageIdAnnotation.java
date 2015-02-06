/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser.annotations;


/** This class represents annotations related to deviations from the AIS standard that are detected during parsing of AIS messages.
 * These annotations are stored in the provenance of the resulting message objects, so that they can analyzed by a client program.
 * @author Pierre America
 * @author Pierre van de Laar
 */
public class AISNoCompleteMessageIdAnnotation implements AISAnnotation {
	private int length;
	
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
