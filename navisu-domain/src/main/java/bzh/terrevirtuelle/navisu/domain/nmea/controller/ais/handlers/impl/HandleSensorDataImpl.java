package bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.HandleInvalidSensorData;
import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.HandleSensorData;
import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.HandleVDMLine;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.VDMLine;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.Provenance;

public class HandleSensorDataImpl implements HandleSensorData {

    private final HandleVDMLine handler;
    private final HandleInvalidSensorData errorHandler;

    public HandleSensorDataImpl(HandleVDMLine handler, HandleInvalidSensorData errorHandler) {
        this.handler = handler;
        this.errorHandler = errorHandler;
    }

    @Override
    public void handleSensorData(Provenance source, String sensorData) {
        if (VDMLine.isValid(sensorData)) {
            handler.handleVDMLine(new VDMLine(source, sensorData));
        } else {
            /* report invalid sensor data: 
             * not only to enable logging
             * but also to enable health assessments: is my sensor suddenly generating more invalid lines than normal?
             */
            errorHandler.handleInvalidSensorData(source, sensorData);
        }
    }

    @Override
    public void finished() {
        handler.finished();
    }
}
