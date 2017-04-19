/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.system.files.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.server.DataServerServices;
import bzh.terrevirtuelle.navisu.system.files.Files;
import bzh.terrevirtuelle.navisu.system.files.FilesServices;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * NaVisu
 *
 * @date 18 avr. 2015
 * @author Serge Morvan
 */
public class FilesImpl
        implements Files, FilesServices, Driver, ComponentState {

    @UsedService
    DataServerServices dataServerServices;

    protected static final Logger LOGGER = Logger.getLogger(FilesImpl.class.getName());
    protected static final String EXTENSION_0 = ".nmea";
    private static final String EXTENSION_1 = ".n2k";
    private static final String EXTENSION_2 = ".ais";
    private static final String EXTENSION_3 = ".txt";
    protected static final String NAME = "NMEA";

    @Override
    public boolean canOpen(String file) {

        boolean canOpen = false;

        if (file.toLowerCase().endsWith(EXTENSION_0)
                || file.toLowerCase().endsWith(EXTENSION_1)
                || file.toLowerCase().endsWith(EXTENSION_2)
                || file.toLowerCase().endsWith(EXTENSION_3)) {
            canOpen = true;
        }

        return canOpen;
    }

    @Override
    public Driver getDriver() {
        return this;
    }

    @Override
    public void openFile(String file) {
        this.open(null, file);
    }

    @Override
    public void open(ProgressHandle pHandle, String... files) {

        for (String file : files) {
            this.handleOpenFile(pHandle, file);
        }
    }

    protected void handleOpenFile(ProgressHandle pHandle, String file) {
        LOGGER.log(Level.INFO, "Opening {0} ...", file);
        dataServerServices.openFile(file);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String[] getExtensions() {
        return new String[]{"*" + EXTENSION_0,
            "*" + EXTENSION_1,
            "*" + EXTENSION_2,
            "*" + EXTENSION_3
        };
    }

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
