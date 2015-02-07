package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.HandleAISMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.HandleInvalidVDMMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.HandleVDMMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.VDMMessage;

public class HandleVDMMessageImpl implements HandleVDMMessage {

	private HandleAISMessage handler;
	private HandleInvalidVDMMessage errorHandler;
	
	public HandleVDMMessageImpl(HandleAISMessage handler, HandleInvalidVDMMessage errorHandler)
	{
		this.handler = handler;
		this.errorHandler = errorHandler;
	}


	public void handleVDMMessage(VDMMessage message) {
		if(AISMessageFactory.IsValid(message))
		{
			handler.handleAISMessage(AISMessageFactory.getAISMessage(message));
		}
		else
		{
			errorHandler.handleInvalidVDMMessage(message);
		}
	}
}
