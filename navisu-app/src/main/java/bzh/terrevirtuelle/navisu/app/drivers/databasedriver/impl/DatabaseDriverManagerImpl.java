package bzh.terrevirtuelle.navisu.app.drivers.databasedriver.impl;

import bzh.terrevirtuelle.navisu.app.drivers.databasedriver.DatabaseDriver;
import bzh.terrevirtuelle.navisu.app.drivers.databasedriver.DatabaseDriverManager;
import bzh.terrevirtuelle.navisu.app.drivers.databasedriver.DatabaseDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.core.util.Checker;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * NaVisu
 *
 * @date 28 mars 2015
 * @author Serge Morvan
 */
public class DatabaseDriverManagerImpl
        implements DatabaseDriverManager, DatabaseDriverManagerServices, ComponentState {

    protected static final Logger LOGGER = Logger.getLogger(DatabaseDriverManagerImpl.class.getName());
    protected List<DatabaseDriver> availableDriverList = new ArrayList<>();

    @UsedService
    GuiAgentServices guiAgentServices;

    @Override
    public void componentInitiated() {

    }

    @Override
    public void init() {

    }

    protected DatabaseDriver findDriver(String category) {
        
        DatabaseDriver compatibleDriver = null;
        for (DatabaseDriver driver : this.availableDriverList) {
            if (driver.canOpen(category)) {
                compatibleDriver = driver;
                break;
            }
        }
        return compatibleDriver;
    }

    @Override
    public void registerNewDriver(DatabaseDriver driver) {

        Checker.notNull(driver, "Driver must not be null.");
        System.out.println("registerNewDriver " + driver);
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
    public void connect(String dbName, String hostName, String protocol, String port, String driverName, String userName, String passwd) {
        System.out.println("connect dbName : " + dbName);
        DatabaseDriver driver = findDriver(dbName);
        if (driver != null) {
            driver.connect(dbName, hostName, protocol, port, driverName, userName, passwd);
        } else {
            System.out.println("Unrecognized database");
        }
    }
    @Override
    public void close(String dbName){
        DatabaseDriver driver = findDriver(dbName);
        if (driver != null) {
            driver.close();
        } else {
            System.out.println("Unrecognized database");
        }
    }
}
