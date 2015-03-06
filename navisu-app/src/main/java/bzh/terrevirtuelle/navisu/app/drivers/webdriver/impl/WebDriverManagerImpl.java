/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.drivers.webdriver.impl;

import bzh.terrevirtuelle.navisu.app.drivers.webdriver.WebDriver;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.WebDriverManager;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.WebDriverManagerServices;
import bzh.terrevirtuelle.navisu.widgets.textfield.TextFieldController;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.DefaultMenuEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuManagerServices;
import static bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator.tr;
import bzh.terrevirtuelle.navisu.core.util.Checker;
import bzh.terrevirtuelle.navisu.widgets.textlist.TextListController;
import gov.nasa.worldwind.ogc.wms.WMSCapabilities;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * @date 4 mars 2015
 * @author Serge Morvan
 */
public class WebDriverManagerImpl
        implements WebDriverManager, WebDriverManagerServices, ComponentState {

    @UsedService
    MenuManagerServices menuBarServices;
    @UsedService
    GuiAgentServices guiAgentServices;
    protected List<WebDriver> availableDriverList = new ArrayList<>();
    protected StackPane root;
    protected URI serverURI;
    protected WMSCapabilities caps;
    protected static final Logger LOGGER = Logger.getLogger(WebDriverManagerImpl.class.getName());
    protected final String NAME = "WMS";

    @Override
    public void init() {
        root = guiAgentServices.getRoot();
        TextFieldController textFieldController = new TextFieldController();
      //   TextListController textListController = new TextListController();
        MenuItem menuItem = new MenuItem(tr("menu.url.load"));
        menuBarServices.addMenuItem(DefaultMenuEnum.URL, menuItem);
        menuItem.setOnAction((e) -> {
            textFieldController.setServer("http://ows.emodnet-bathymetry.eu/wms");
            root.getChildren().add(textFieldController);
           // root.getChildren().add(textListController);

            textFieldController.getTextField().setOnAction((event) -> {
               // System.out.println("Work in progress :-)");
                // load(textFieldController.getTextField().getText());
                handleOpenFiles(textFieldController.getTextField().getText());
                root.getChildren().remove(textFieldController);
            });
        });
    }

    protected void handleOpenFiles(String url) {

        WebDriver driver = this.findDriverForUrl(NAME);
        if (driver != null) {
            guiAgentServices.getJobsManager().newJob(url, (progressHandle) -> {
                driver.open(progressHandle, url);
            });
        } else {
            LOGGER.log(Level.WARNING, "Unable to find a driver for url \"{0}\"", url);
        }

    }

    @Override
    public void registerNewDriver(WebDriver driver) {
        Checker.notNull(driver, "Driver must not be null.");
        this.availableDriverList.add(driver);
    }

    protected WebDriver findDriverForUrl(String ws) {

        WebDriver compatibleDriver = null;

        for (WebDriver driver : this.availableDriverList) {
            if (driver.canOpen(ws)) {
                compatibleDriver = driver;
                break;
            }
        }
        return compatibleDriver;
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

    public StackPane getRoot() {
        return root;
    }

}
