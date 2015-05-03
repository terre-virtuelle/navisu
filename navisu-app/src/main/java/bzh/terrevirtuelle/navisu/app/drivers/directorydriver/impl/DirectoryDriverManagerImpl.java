package bzh.terrevirtuelle.navisu.app.drivers.directorydriver.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuManagerServices;
import bzh.terrevirtuelle.navisu.core.util.Checker;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import bzh.terrevirtuelle.navisu.app.drivers.directorydriver.DirectoryDriver;
import bzh.terrevirtuelle.navisu.app.drivers.directorydriver.DirectoryDriverManager;
import bzh.terrevirtuelle.navisu.app.drivers.directorydriver.DirectoryDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.DefaultMenuEnum;
import static bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator.tr;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 18:55
 */
public class DirectoryDriverManagerImpl
        implements DirectoryDriverManager, DirectoryDriverManagerServices, ComponentState {

    protected static final Logger LOGGER = Logger.getLogger(DirectoryDriverManagerImpl.class.getName());

    @UsedService
    MenuManagerServices menuBarServices;
    @UsedService
    GuiAgentServices guiAgentServices;

    //  protected FileChooser fileChooser;
    protected DirectoryChooser directoryChooser = new DirectoryChooser();
    protected List<DirectoryDriver> availableDriverList = new ArrayList<>();
    protected DirectoryDriver driver;

    @Override
    public void componentInitiated() {
        // ?
    }

    @Override
    public void init() {

        this.directoryChooser = new DirectoryChooser();
        this.directoryChooser.setTitle(tr("popup.directoryChooser.open"));
        this.directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        MenuItem menuItem = new MenuItem(tr("menu.file.catalog"));
        menuBarServices.addMenuItem(DefaultMenuEnum.FILE, menuItem);
        menuItem.setOnAction((e) -> {

            // Show the file chooser
            File selectedFile = this.directoryChooser.showDialog(null);
            // If files has been selected
            if (selectedFile != null) {
                // Open them
                this.handleOpenFiles(selectedFile);
            }
        });

    }

    protected void handleOpenFiles(File file) {
        try {
            Path directory = Paths.get(file.getAbsolutePath());
            driver = findDriverForFile(file.getAbsolutePath());
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path dir, BasicFileAttributes attrs) throws IOException {
                    String fileName = dir.toFile().getAbsolutePath();
                    if (driver != null) {
                        guiAgentServices.getJobsManager().newJob(dir.toFile().getName(), (progressHandle) -> {
                            driver.open(progressHandle, fileName);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(DirectoryDriverManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    } else {
                        LOGGER.log(Level.WARNING, "Unable to find a driver for file \"{0}\"", dir.toFile().getName());
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(DirectoryDriverManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //driver.parse();
        //driver.parse();
    }

    protected DirectoryDriver findDriverForFile(String file) {

        DirectoryDriver compatibleDriver = null;

        for (DirectoryDriver driver : this.availableDriverList) {

            if (driver.canOpen(file)) {
                compatibleDriver = driver;
                break;
            }
        }

        return compatibleDriver;
    }

    @Override
    public void registerNewDriver(DirectoryDriver driver) {

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

   
}
