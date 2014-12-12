package bzh.terrevirtuelle.navisu.app.drivers.impl;

import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import bzh.terrevirtuelle.navisu.app.drivers.DriverManager;
import bzh.terrevirtuelle.navisu.app.drivers.DriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.DefaultMenuEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuManagerServices;
import bzh.terrevirtuelle.navisu.core.util.Checker;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator.tr;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 18:55
 */
public class DriverManagerImpl implements DriverManager, DriverManagerServices, ComponentState {

    protected final Logger LOGGER = Logger.getLogger(DriverManagerImpl.class.getName());

    @UsedService
    MenuManagerServices menuBarServices;
    @UsedService
    GuiAgentServices guiAgentServices;

    protected FileChooser fileChooser;

    protected List<Driver> availableDriverList = new ArrayList<>();

    @Override
    public void componentInitiated() {
        // ?
    }

    @Override
    public void init() {
        this.fileChooser = new FileChooser();
        this.fileChooser.setTitle(tr("popup.fileChooser.open"));
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("properties/user.properties"));
        } catch (IOException ex) {
            Logger.getLogger(DriverManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String userInitialDirectory = properties.getProperty("dataDir");
        if (userInitialDirectory.equals("")) {
            this.fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/data"));
        }
        MenuItem menuItem = new MenuItem(tr("menu.file.open"));
        menuBarServices.addMenuItem(DefaultMenuEnum.FILE, menuItem);
        menuItem.setOnAction((e) -> {
            // Show the file chooser
            List<File> selectedFiles = this.fileChooser.showOpenMultipleDialog(null);
            // If files has been selected
            if (selectedFiles != null) {
                if (fileChooser.getSelectedExtensionFilter() != null) {
                    System.out.println(fileChooser.getSelectedExtensionFilter().getDescription());
                }
                // Open them

                this.handleOpenFiles(fileChooser.getSelectedExtensionFilter().getDescription(), selectedFiles);
                System.out.println(selectedFiles.get(0).getAbsolutePath());

            }
        });
    }

    protected void handleOpenFiles(String category, List<File> files) {

        files.stream().forEach((file) -> {
            Driver driver = this.findDriverForFile(category, file.getAbsolutePath());
            if (driver != null) {
                guiAgentServices.getJobsManager().newJob(file.getName(), (progressHandle) -> {
                    driver.open(progressHandle, file.getAbsolutePath());
                });
            } else {
                LOGGER.log(Level.WARNING, "Unable to find a driver for file \"{0}\"", file.getName());
            }
        });
    }

    protected Driver findDriverForFile(String category, String file) {

        Driver compatibleDriver = null;

        for (Driver driver : this.availableDriverList) {
            if (driver.canOpen(category, file)) {
                compatibleDriver = driver;
                break;
            } else {
                if (driver.canOpen(file)) {
                    compatibleDriver = driver;
                    break;
                }
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
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }
}
