package bzh.terrevirtuelle.navisu.app.ddriver.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuManagerServices;
import bzh.terrevirtuelle.navisu.core.util.Checker;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import bzh.terrevirtuelle.navisu.app.ddriver.DDriver;
import bzh.terrevirtuelle.navisu.app.ddriver.DDriverManager;
import bzh.terrevirtuelle.navisu.app.ddriver.DDriverManagerServices;
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
public class DDriverManagerImpl implements DDriverManager, DDriverManagerServices, ComponentState {

    protected static final Logger LOGGER = Logger.getLogger(DDriverManagerImpl.class.getName());

    @UsedService
    MenuManagerServices menuBarServices;
    @UsedService
    GuiAgentServices guiAgentServices;

    //  protected FileChooser fileChooser;
    protected DirectoryChooser directoryChooser = new DirectoryChooser();
    protected List<DDriver> availableDriverList = new ArrayList<>();

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
        /*
         Path directory = Paths.get(file.getAbsolutePath());
         try {

         Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
         @Override
         public FileVisitResult visitFile(Path dir, BasicFileAttributes attrs) throws IOException {
         String fileName = dir.toFile().getAbsolutePath();
         DDriver driver = findDriverForFile(fileName);
         if (driver != null) {
         guiAgentServices.getJobsManager().newJob(dir.toFile().getName(), (progressHandle) -> {
         driver.open(progressHandle, fileName);
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
         */
        /*
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    // Appel récursif sur les sous-répertoires
                    handleOpenFiles(list[i]);
                }
            } else {
                System.err.println(file + " : Erreur de lecture.");
            }
        } else {
            String fileName = file.getAbsolutePath();
            DDriver driver = findDriverForFile(fileName);
            if (driver != null) {
                guiAgentServices.getJobsManager().newJob(file.getName(), (progressHandle) -> {
                    driver.open(progressHandle, fileName);
                });
            }
        }
        */
    }

    protected DDriver findDriverForFile(String file) {

        DDriver compatibleDriver = null;

        for (DDriver driver : this.availableDriverList) {

            if (driver.canOpen(file)) {
                compatibleDriver = driver;
                break;
            }
        }

        return compatibleDriver;
    }

    @Override
    public void registerNewDriver(DDriver driver) {

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
