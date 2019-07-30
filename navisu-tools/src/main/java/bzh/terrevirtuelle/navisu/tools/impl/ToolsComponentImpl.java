package bzh.terrevirtuelle.navisu.tools.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.cartography.projection.Pro4JServices;
import bzh.terrevirtuelle.navisu.cartography.projection.lambert.LambertServices;
import bzh.terrevirtuelle.navisu.charts.raster.geotiff.GeoTiffChartServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.tools.ToolsComponent;
import bzh.terrevirtuelle.navisu.tools.ToolsComponentServices;
import bzh.terrevirtuelle.navisu.tools.impl.controller.ToolsComponentController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import bzh.terrevirtuelle.navisu.dem.db.DemDBServices;
import bzh.terrevirtuelle.navisu.geo.raster.RasterServices;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.obj.ObjComponentServices;

/**
 * @author Serge Morvan
 * @date 1/02/2018 12:49
 */
public class ToolsComponentImpl
        implements ToolsComponent, ToolsComponentServices, InstrumentDriver, ComponentState {

    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    S57ChartComponentServices s57ChartComponentServices;
    @UsedService
    GeoTiffChartServices geoTiffChartServices;
    @UsedService
    DatabaseServices databaseServices;
    @UsedService
    BathymetryDBServices bathymetryDBServices;
    @UsedService
    DemDBServices demDBComponentServices;
    @UsedService
    InstrumentDriverManagerServices instrumentDriverManagerServices;
    @UsedService
    LambertServices lambertServices;
    @UsedService
    RasterServices rasterServices;
    @UsedService
    GeodesyServices geodesyServices;
    @UsedService
    JTSServices jtsServices;
    @UsedService
    Pro4JServices pro4JServices;
    @UsedService
    ObjComponentServices objComponentServices;

    private final String COMPONENT_KEY_NAME_0 = "DbS57";
    private final String COMPONENT_KEY_NAME_1 = "DbBathy";
    private final String COMPONENT_KEY_NAME_2 = "DbElevation";
    private final String COMPONENT_KEY_NAME_3 = "DbBeacons";
    private final String COMPONENT_KEY_NAME_4 = "DbBuildings";
    private String componentKeyName;
    ToolsComponentController controller;

    @SuppressWarnings("unchecked")
    @Override
    public void componentInitiated() {

    }

    @Override
    public void on(String... files) {

        String[] cmd = files;
        if (cmd != null) {
            componentKeyName = cmd[0];
            if (cmd[0].equals(COMPONENT_KEY_NAME_0)
                    || cmd[0].equals(COMPONENT_KEY_NAME_1)
                    || cmd[0].equals(COMPONENT_KEY_NAME_2)
                    || cmd[0].equals(COMPONENT_KEY_NAME_3)
                    || cmd[0].equals(COMPONENT_KEY_NAME_4)) {
                controller = new ToolsComponentController(this, componentKeyName, KeyCode.T, KeyCombination.CONTROL_DOWN,
                        guiAgentServices, s57ChartComponentServices, geoTiffChartServices,
                        databaseServices, bathymetryDBServices, demDBComponentServices,
                        instrumentDriverManagerServices, lambertServices, rasterServices,
                        geodesyServices, jtsServices, pro4JServices, objComponentServices);
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
    public boolean canOpen(String category) {
        boolean canOpen = false;

        if (category.equals(COMPONENT_KEY_NAME_0)
                || category.equals(COMPONENT_KEY_NAME_1)
                || category.equals(COMPONENT_KEY_NAME_2)
                || category.equals(COMPONENT_KEY_NAME_3)
                || category.equals(COMPONENT_KEY_NAME_4)) {
            canOpen = true;
        }
        return canOpen;
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public void componentStarted() {

    }

    @Override
    public void componentStopped() {

    }

    public String getComponentKeyName() {
        return componentKeyName;
    }

}
