package bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.VDMLine;

public interface HandleVDMLine {
	public void handleVDMLine (VDMLine line);
	public void finished();
}
