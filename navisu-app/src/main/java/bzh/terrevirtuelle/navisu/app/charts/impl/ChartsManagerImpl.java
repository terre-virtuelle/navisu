package bzh.terrevirtuelle.navisu.app.charts.impl;

import bzh.terrevirtuelle.navisu.app.charts.ChartsManager;
import bzh.terrevirtuelle.navisu.app.charts.ChartsManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import bzh.terrevirtuelle.navisu.core.formats.kap.controller.parser.kap.KapParser;
import bzh.terrevirtuelle.navisu.core.formats.kap.controller.parser.kap.KapParserFactory;
import bzh.terrevirtuelle.navisu.core.formats.kap.model.KAP;
import javafx.stage.FileChooser;
import org.capcaval.c3.component.ComponentState;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 12:51
 */
public class ChartsManagerImpl implements ChartsManager, ChartsManagerServices, ComponentState {

    protected final Logger LOGGER = Logger.getLogger(ChartsManagerImpl.class.getName());

    protected Driver driver;

    @Override
    public void componentInitiated() {

        // Create the driver
        this.driver = this.createDriver();
    }

    protected Driver createDriver() {
        return new Driver() {

            static final String EXTENSION = ".kap";

            @Override
            public boolean canOpen(String file) {

                boolean canOpen = false;

                if(file.toLowerCase().endsWith(EXTENSION)) {
                    canOpen = true;
                }

                return canOpen;
            }

            @Override
            public void open(String... files) {

                for(String file : files) {
                    LOGGER.info("Opening " + file + " ...");
                }
            }

            @Override
            public String getName() {
                return "BSB/KAP";
            }

            @Override
            public String[] getExtensions() {
                return new String[] { "*" + EXTENSION };
            }
        };
    }

    @Override
    public Driver getDriver() {
        return this.driver;
    }

    @Override
    public void componentStarted() { /* Nothing to do here */ }

    @Override
    public void componentStopped() { /* Nothing to do here */ }
}
