package bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.HandleAISMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.HandleInvalidVDMMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.HandleVDMMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.VDMMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AISMessageFactory;

public class HandleVDMMessageImpl implements HandleVDMMessage {

    private final HandleAISMessage handler;
    private final HandleInvalidVDMMessage errorHandler;

    public HandleVDMMessageImpl(HandleAISMessage handler, HandleInvalidVDMMessage errorHandler) {
        this.handler = handler;
        this.errorHandler = errorHandler;
    }

    @Override
    public void handleVDMMessage(VDMMessage message) {
        if (AISMessageFactory.IsValid(message)) {
           handler.handleAISMessage(AISMessageFactory.getAISMessage(message));
        } else {
            errorHandler.handleInvalidVDMMessage(message);
        }
    }
}
