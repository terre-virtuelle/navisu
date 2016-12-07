/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.drivers.webdriver.impl;

import bzh.terrevirtuelle.navisu.app.drivers.webdriver.WebDriver;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.WebDriverManager;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.WebDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.impl.controller.WMSCatalogController;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuManagerServices;
import bzh.terrevirtuelle.navisu.core.util.Checker;
import gov.nasa.worldwind.ogc.wms.WMSCapabilities;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    protected final String NAME_0 = "WMS";
    protected final String NAME_1 = "WMS_CATALOG";
    protected WebDriver driver;
    protected WMSCatalogController wmsCatalogController;
    protected String urlCatalog;

    @Override
    public void init(String server) {
    }

    @Override
    public void handleOpenFiles(String name, String url) {
        if (name.equals(NAME_0) || name.equals(NAME_1)) {
            driver = this.findDriverForUrl(NAME_0);
            if (driver != null) {
                if (!url.equals("")) {
                    guiAgentServices.getJobsManager().newJob(url, (progressHandle) -> {
                        driver.open(progressHandle, url);
                    });
                } else {
                    wmsCatalogController = new WMSCatalogController(guiAgentServices, driver);
                }
            } else {
                LOGGER.log(Level.WARNING, "Unable to find a driver for url \"{0}\"", url);
            }
        }
    }

    @Override
    public void registerNewDriver(WebDriver driver
    ) {
        Checker.notNull(driver, "Driver must not be null.");
        this.availableDriverList.add(driver);
    }

    protected WebDriver findDriverForUrl(String ws) {
        WebDriver compatibleDriver = null;
        for (WebDriver d : this.availableDriverList) {
            if (d.canOpen(ws)) {
                compatibleDriver = d;
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
