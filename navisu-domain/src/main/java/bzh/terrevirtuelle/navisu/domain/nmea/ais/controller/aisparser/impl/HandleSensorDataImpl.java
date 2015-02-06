package nl.esi.metis.aisparser.impl;

import nl.esi.metis.aisparser.HandleInvalidSensorData;
import nl.esi.metis.aisparser.HandleSensorData;
import nl.esi.metis.aisparser.HandleVDMLine;
import nl.esi.metis.aisparser.VDMLine;
import nl.esi.metis.aisparser.provenance.Provenance;

public class HandleSensorDataImpl implements HandleSensorData {
	private HandleVDMLine handler;
	private HandleInvalidSensorData errorHandler;
	public HandleSensorDataImpl(HandleVDMLine handler, HandleInvalidSensorData errorHandler)
	{
		this.handler = handler;
		this.errorHandler = errorHandler;
	}

	public void handleSensorData(Provenance source, String sensorData) {
		if (VDMLine.isValid(sensorData))
		{
			handler.handleVDMLine( new VDMLine(source,sensorData) );
		}
		else
		{
			/* report invalid sensor data: 
			 * not only to enable logging
			 * but also to enable health assessments: is my sensor suddenly generating more invalid lines than normal?
			 */
			errorHandler.handleInvalidSensorData(source, sensorData);
		}
	}
	
	public void finished() {
		handler.finished();
	}
}
