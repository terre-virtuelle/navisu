package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.provenance.Provenance;

public interface HandleSensorData {
	public void handleSensorData (Provenance source, String sensorData);
	public void finished();
}
