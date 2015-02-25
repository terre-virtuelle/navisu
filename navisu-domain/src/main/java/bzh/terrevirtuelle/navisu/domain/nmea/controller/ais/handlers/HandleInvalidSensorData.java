package bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.Provenance;

public interface HandleInvalidSensorData {
	public void handleInvalidSensorData (Provenance source, String sensorData);
}
