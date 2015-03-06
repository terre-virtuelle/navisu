/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.wms.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.WebDriver;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.impl.WebDriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.widgets.textlist.TextListController;
import bzh.terrevirtuelle.navisu.wms.WMS;
import bzh.terrevirtuelle.navisu.wms.WMSServices;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVListImpl;
import gov.nasa.worldwind.ogc.wms.WMSCapabilities;
import gov.nasa.worldwind.wms.Capabilities;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * @date 4 mars 2015
 * @author Serge Morvan
 */
public class WMSImpl
        implements WMS, WMSServices, WebDriver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    GuiAgentServices guiAgentServices;

    protected static final String GROUP = "WMS";
    protected static final String NAME = "WMS";
    protected WorldWindow wwd = null;
    protected URI serverURI = null;
    protected WMSCapabilities caps;
    protected StackPane root;

    /**
     *
     */
    public void init() {
        wwd = GeoWorldWindViewImpl.getWW();
        root = guiAgentServices.getRoot();
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

    @Override
    public void load(String server) {
        TextListController textListController = new TextListController();
        try {
            this.serverURI = new URI(server.trim());
            caps = WMSCapabilities.retrieve(this.serverURI);
            caps.parse();
            System.out.println("caps " + caps);
            Platform.runLater(() -> {
                root.getChildren().add(textListController);
            });
        } catch (Exception ex) {
            Logger.getLogger(WebDriverManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public WebDriver getDriver() {
        return this;
    }

    @Override
    public Capabilities GetCapabilities() {
        return null;
    }

    @Override
    public boolean canOpen(String category) {
        if (category.equalsIgnoreCase("WMS")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void open(ProgressHandle progressHandle, String url) {
        load(url);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void load() {
    }

    protected static class LayerInfo {

        protected WMSCapabilities caps;
        protected AVListImpl params = new AVListImpl();

        protected String getTitle() {
            return params.getStringValue(AVKey.DISPLAY_NAME);
        }

        protected String getName() {
            return params.getStringValue(AVKey.LAYER_NAMES);
        }

        protected String getAbstract() {
            return params.getStringValue(AVKey.LAYER_ABSTRACT);
        }
    }

}
