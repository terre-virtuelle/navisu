package bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.Provenance;

public interface HandleSensorData {
	public void handleSensorData (Provenance source, String sensorData);
	public void finished();
}
