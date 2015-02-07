package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.provenance.Provenance;

public interface HandleInvalidSensorData {
	public void handleInvalidSensorData (Provenance source, String sensorData);
}
