package bzh.terrevirtuelle.navisu.app.guiagent.options.server;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @date 21 mai 2016
 * @author Serge Morvan
 */
public interface ServerOptionsComponentServices 
        extends ComponentService {

    void on(String... files);

    boolean canOpen(String category);

    InstrumentDriver getDriver();
}
