package nl.esi.metis.aisparser;

import nl.esi.metis.aisparser.provenance.Provenance;

public interface HandleSensorData {
	public void handleSensorData (Provenance source, String sensorData);
	public void finished();
}
