package nl.esi.metis.aisparser.impl;

import nl.esi.metis.aisparser.HandleAISMessage;
import nl.esi.metis.aisparser.HandleInvalidVDMMessage;
import nl.esi.metis.aisparser.HandleVDMMessage;
import nl.esi.metis.aisparser.VDMMessage;

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
