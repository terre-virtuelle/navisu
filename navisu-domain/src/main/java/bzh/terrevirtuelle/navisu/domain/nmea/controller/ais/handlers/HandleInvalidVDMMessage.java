package bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.VDMMessage;

public interface HandleInvalidVDMMessage {
	public void handleInvalidVDMMessage (VDMMessage invalidVDMMessage);
}
