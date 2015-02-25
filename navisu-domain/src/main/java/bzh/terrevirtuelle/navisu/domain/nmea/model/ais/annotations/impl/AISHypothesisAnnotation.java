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
public class AISHypothesisAnnotation implements AISAnnotation {
	private int assumedMessageId;
	private int assumedLength;

    public AISHypothesisAnnotation() {
    }
	
	/** Constructor. 
	 * @param assumedMessageId Assumed message Id of message 
	 * @param assumedLength Assumed length of message
	 */
	public AISHypothesisAnnotation(int assumedMessageId, int assumedLength)
	{
		this.assumedMessageId = assumedMessageId;
		this.assumedLength = assumedLength;
	}
	
	/** Equality
	 * @param obj Object to compare to
	 */
	@Override
    public boolean equals(Object obj) {
         if (obj == this){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }

        AISHypothesisAnnotation aha = (AISHypothesisAnnotation) obj;
        return (this.assumedLength == aha.assumedLength) && (this.assumedMessageId == aha.assumedMessageId);
	}
	
	public String toString()
	{
		return "Treated as message with messageId  " + assumedMessageId + " with length " + assumedLength; 
	}
}
