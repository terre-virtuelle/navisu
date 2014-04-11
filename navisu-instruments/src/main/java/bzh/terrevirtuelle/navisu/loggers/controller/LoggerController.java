/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.loggers.controller;

import bzh.terrevirtuelle.navisu.client.nmea.controller.events.NMEAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.PGN128267Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.PGN130306Event;
import bzh.terrevirtuelle.navisu.loggers.view.Printer;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.PGN128267;
import bzh.terrevirtuelle.navisu.domain.nmea.model.PGN130306;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Serge
 */
public class LoggerController<T extends NMEA> {

    ComponentManager cm = ComponentManager.componentManager;
    ComponentEventSubscribe<NMEAEvent> nmeaES = cm.getComponentEventSubscribe(NMEAEvent.class);
    ComponentEventSubscribe<PGN130306Event> pgn130306ES = cm.getComponentEventSubscribe(PGN130306Event.class);
    ComponentEventSubscribe<PGN128267Event> pgn128267ES = cm.getComponentEventSubscribe(PGN128267Event.class);

    public LoggerController() {
        pgn130306ES.subscribe(new PGN130306Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {

                PGN130306 d = (PGN130306) data;
                Printer.getPrinter().display(d);
            }
        });
        pgn128267ES.subscribe(new PGN128267Event() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {

                PGN128267 d = (PGN128267) data;
                Printer.getPrinter().display(d);
            }
        });
    }

    public LoggerController(T nmea) {
        nmeaES.subscribe(new NMEAEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T data) {

                NMEA d = (NMEA) data;
                Printer.getPrinter().display(d);
            }
        });
    }
//TODO faire un Logger generique
}
