package bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.VDMLine;

public interface HandleInvalidVDMLine {
	public void handleInvalidVDMLine (VDMLine invalidVDMLine);
}
