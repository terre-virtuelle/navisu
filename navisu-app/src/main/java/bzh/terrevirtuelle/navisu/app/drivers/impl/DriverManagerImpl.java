package bzh.terrevirtuelle.navisu.app.drivers.impl;

import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import bzh.terrevirtuelle.navisu.app.drivers.DriverManager;
import bzh.terrevirtuelle.navisu.app.drivers.DriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.DefaultMenuEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuManagerServices;
import bzh.terrevirtuelle.navisu.core.utility.Checker;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator.tr;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 18:55
 */
public class DriverManagerImpl implements DriverManager, DriverManagerServices, ComponentState {

    protected final Logger LOGGER = Logger.getLogger(DriverManagerImpl.class.getName());

    @UsedService MenuManagerServices menuBarServices;

    protected FileChooser fileChooser;

    protected List<Driver> availableDriverList = new ArrayList<>();

    @Override
    public void componentInitiated() {

        this.fileChooser = new FileChooser();
        this.fileChooser.setTitle(tr("popup.fileChooser.open"));
        this.fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        MenuItem menuItem = new MenuItem(tr("menu.file.open"));
        menuBarServices.addMenuItem(DefaultMenuEnum.FILE, menuItem);
        menuItem.setOnAction((e) -> {

            // Show the file chooser
            List<File> selectedFiles = this.fileChooser.showOpenMultipleDialog(null);

            // If files has been selected
            if(selectedFiles != null) {

                // Open them
                this.handleOpenFiles(selectedFiles);
            }
        });
    }

    protected void handleOpenFiles(List<File> files) {

        for(File file : files) {

            Driver driver = this.findDriverForFile(file.getAbsolutePath());
            if(driver != null) {

                driver.open(file.getAbsolutePath());
            }
            else {
                LOGGER.warning("Unable to find a driver for file \"" + file.getName() + "\"");
            }
        }
    }

    protected Driver findDriverForFile(String file) {

        Driver compatibleDriver = null;

        for(Driver driver : this.availableDriverList) {

            if(driver.canOpen(file)) {
                compatibleDriver = driver;
                break;
            }
        }

        return compatibleDriver;
    }

    @Override
    public void registerNewDriver(Driver driver) {

        Checker.notNull(driver, "Driver must not be null.");

        // Hold the driver
        this.availableDriverList.add(driver);

        // Add the supported extension to the file chooser
        this.fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(driver.getName(), driver.getExtensions())
        );
    }

    @Override
    public void componentStarted() {}
    @Override
    public void componentStopped() {}
}
