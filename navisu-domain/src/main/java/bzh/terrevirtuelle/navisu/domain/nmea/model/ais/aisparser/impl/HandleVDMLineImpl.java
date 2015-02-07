package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.HandleInvalidVDMLine;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.HandleVDMLine;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.HandleVDMMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.VDMLine;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.VDMMessage;

public class HandleVDMLineImpl implements HandleVDMLine {

	private HandleVDMMessage handler;
	private HandleInvalidVDMLine errorHandler;
	
	public HandleVDMLineImpl(HandleVDMMessage handler, HandleInvalidVDMLine errorHandler)
	{
		this.handler = handler;
		this.errorHandler = errorHandler;
	}

	private VDMMessage current = null;
	public void handleVDMLine(VDMLine line) {
		if (line.isSemanticallyValid())
		{
			if(current == null)
			{
				current = new VDMMessage(line);
			}
			else
			{
				if (current.isPartOfMessage(line))
				{
					current.add(line);
				}
				else
				{
					handler.handleVDMMessage(current);	//correctness must be checked by VDMMessageHandler	 
					current = new VDMMessage(line);
				}
			}
			
			//When message is complete, we don't wait for the next message before we process it.
			if (current.atEnd())
			{
				handler.handleVDMMessage(current);
				current = null;
			}
		}
		else
		{
			errorHandler.handleInvalidVDMLine(line);
		}
	}
	
	public void finished() {
		if(current != null)
		{
			handler.handleVDMMessage(current);
			//TODO: must finished message be passed on even further?
		}
	}
}