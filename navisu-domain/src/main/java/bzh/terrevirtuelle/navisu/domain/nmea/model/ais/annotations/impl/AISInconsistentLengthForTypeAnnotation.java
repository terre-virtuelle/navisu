/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.AISAnnotation;

/**
 * This class represents annotations related to deviations from the AIS standard
 * that are detected during parsing of AIS messages. These annotations are
 * stored in the provenance of the resulting message objects, so that they can
 * analyzed by a client program.
 *
 * @author Pierre America
 * @author Pierre van de Laar
 */
public class AISInconsistentLengthForTypeAnnotation implements AISAnnotation {

    private int originalMessageId;
    private int originalLength;

    public int getOriginalLength() {
        return originalLength;
    }

    public AISInconsistentLengthForTypeAnnotation() {
    }

    /**
     * Constructor.
     *
     * @param originalMessageId Original message Id of message
     * @param originalLength Original length of message
     */
    public AISInconsistentLengthForTypeAnnotation(int originalMessageId, int originalLength) {
        this.originalMessageId = originalMessageId;
        this.originalLength = originalLength;
    }

    /**
     * Equality
     *
     * @param obj Object to compare to
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        AISInconsistentLengthForTypeAnnotation annotation = (AISInconsistentLengthForTypeAnnotation) obj;
        return (this.originalLength == annotation.originalLength) && (this.originalMessageId == annotation.originalMessageId);
    }

    public String toString() {
        return "Violation of standard: Message with messageId " + originalMessageId + " has invalid length " + originalLength;
    }
}
