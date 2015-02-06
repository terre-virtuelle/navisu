/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser.impl;

import nl.esi.metis.aisparser.HandleInvalidInput;
import nl.esi.metis.aisparser.VDMLine;
import nl.esi.metis.aisparser.VDMMessage;
import nl.esi.metis.aisparser.provenance.Provenance;

/** This class implements a handler that ignores Invalid Input.
 * We hope you will do something more useful with these invalid input notifications such as logging, estimating sensor health, or even system awareness for new AIS versions.
 * 
 * This class helps to quickly migrate from the 0.1 to 0.2 version. 
 * This class might be removed in later versions.
 * 
 * @author Pierre America
 * @author Pierre van de Laar
 */
public class HandleInvalidInputImpl implements HandleInvalidInput{

	@Override
	public void handleInvalidVDMMessage(VDMMessage invalidVDMMessage) {
	}

	@Override
	public void handleInvalidVDMLine(VDMLine invalidVDMLine) {
	}

	@Override
	public void handleInvalidSensorData(Provenance source, String sensorData) {
	}
	
}
