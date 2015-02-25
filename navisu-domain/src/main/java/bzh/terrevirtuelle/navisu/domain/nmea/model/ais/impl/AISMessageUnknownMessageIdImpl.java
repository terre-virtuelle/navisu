package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessageUnknownMessageId;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar, Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */

/** Implementation of AIS Message with Unknown Message ID.
 * The Message ID is outside the range of [1,27] as defined by ITU-R M.1371-4
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
class AISMessageUnknownMessageIdImpl extends AISMessageImpl implements AISMessageUnknownMessageId{
    
	/** Constructs an AIS message with an unknown message ID.
	 * @param content the content of the message
	 * @param prov the provenance of the message
	 */
	public AISMessageUnknownMessageIdImpl (Sixbit content, VDMMessageProvenance prov)
    {
    	super(content, prov);
    }
}
