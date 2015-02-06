package nl.esi.metis.aisparser;

import nl.esi.metis.aisparser.provenance.Provenance;

public interface HandleInvalidSensorData {
	public void handleInvalidSensorData (Provenance source, String sensorData);
}
