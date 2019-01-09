package bzh.terrevirtuelle.navisu.buildings.osmb.impl;

;

import gov.nasa.worldwind.render.Renderable;
import java.util.List;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.buildings.osmb.OsmbComponent;
import bzh.terrevirtuelle.navisu.buildings.osmb.OsmbComponentServices;
import bzh.terrevirtuelle.navisu.buildings.osmb.impl.controller.OsmbComponentController;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * NaVisu
 *
 * @date Jan 3, 2019 10:09:49 AM
 * @author Serge Morvan
 */


public class OsmbComponentImpl
        implements OsmbComponent, OsmbComponentServices, InstrumentDriver, ComponentState {

    private static final String COMPONENT_KEY_NAME_0 = "OSM_BUILDINGS";

    protected OsmbComponentController controller;
    protected WorldWindow wwd;
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    LayersManagerServices layersManagerServices;

    @Override
    public List<Renderable> retrieveObjectsIn(double latMin, double lonMin, double latMax, double lonMax) {
        return controller.retrieveObjectsIn(latMin, lonMin, latMax, lonMax);
    }

    @Override
    public boolean canOpen(String category) {
        boolean canOpen = false;

        if (category.equals(COMPONENT_KEY_NAME_0)) {
            canOpen = true;
        }
        return canOpen;
    }

    @Override
    public void on(String... files) {
        String[] cmd = files;
        if (cmd != null) {
            if (cmd[0].equals(COMPONENT_KEY_NAME_0)) {
                controller = new OsmbComponentController(this, guiAgentServices, layersManagerServices);
                controller.setVisible(true);
            }
        }
    }

    @Override
    public void off() {
        if (controller != null) {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, controller);
            guiAgentServices.getRoot().getChildren().remove(controller);
            controller.setVisible(false);
        }
    }

    @Override
    public void componentInitiated() {
        wwd = GeoWorldWindViewImpl.getWW();
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    public String getName() {
        return COMPONENT_KEY_NAME_0;
    }

}
