package bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.core.util.Checker;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.logging.Logger;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManager;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import java.util.ArrayList;
import java.util.List;

/**
 * NaVisu
 *
 * @date 28 mars 2015
 * @author Serge Morvan
 */
public class InstrumentDriverManagerImpl
        implements InstrumentDriverManager, InstrumentDriverManagerServices, ComponentState {

    protected static final Logger LOGGER = Logger.getLogger(InstrumentDriverManagerImpl.class.getName());
    protected List<InstrumentDriver> availableDriverList = new ArrayList<>();

    @UsedService
    GuiAgentServices guiAgentServices;

    @Override
    public void componentInitiated() {

    }

    @Override
    public void init() {

    }

    @Override
    public InstrumentDriver findDriver(String category) {
        InstrumentDriver compatibleDriver = null;
        for (InstrumentDriver driver : this.availableDriverList) {
            if (driver.canOpen(category)) {
                compatibleDriver = driver;
                break;
            }
        }
        return compatibleDriver;
    }

    @Override
    public void registerNewDriver(InstrumentDriver driver) {
        Checker.notNull(driver, "Driver must not be null.");
        // Hold the driver
        this.availableDriverList.add(driver);
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public InstrumentDriver open(String... category) {

        InstrumentDriver driver = findDriver(category[0]);
        if (driver != null) {
            driver.on(category);
        } else {
            System.out.println("Unrecognized instrument");
        }
        return driver;
    }

    @Override
    public void openFile(String category, String file) {
        
     //   System.out.println("InstrumentDriverManagerImpl : " +category +" " + file);
        InstrumentDriver driver = findDriver(category);
        if (driver != null) {
            driver.openFile(category, file);
        } else {
            System.out.println("Unrecognized instrument");
        }

    }
}
