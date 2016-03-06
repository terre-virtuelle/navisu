/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.earth.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.charts.earth.ProjectionsComponent;
import bzh.terrevirtuelle.navisu.charts.earth.ProjectionsComponentServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import static gov.nasa.worldwind.formats.tiff.GeoTiff.GeoKey.Projection;
import gov.nasa.worldwind.globes.EarthFlat;
import gov.nasa.worldwind.globes.ElevationModel;
import gov.nasa.worldwind.globes.FlatGlobe;
import gov.nasa.worldwind.globes.GeographicProjection;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.globes.projections.ProjectionMercator;
import gov.nasa.worldwind.terrain.BathymetryFilterElevationModel;
import gov.nasa.worldwind.terrain.ZeroElevationModel;
import org.capcaval.c3.component.ComponentState;

/**
 *
 * @author serge
 * @date Feb 22, 2016
 *
 */
public class ProjectionsComponentImpl
        implements ProjectionsComponent, ProjectionsComponentServices, InstrumentDriver, ComponentState {

    private final String COMPONENT_KEY_NAME_0 = "Elevation";
    private final String COMPONENT_KEY_NAME_1 = "noElevation";
    private final String COMPONENT_KEY_NAME_2 = "Bathymetry";
    private final String COMPONENT_KEY_NAME_3 = "noBathymetry";
    private final String COMPONENT_KEY_NAME_4 = "FlatGlobe";
    private final String COMPONENT_KEY_NAME_5 = "RoundGlobe";
    private WorldWindow wwd;
    private ElevationModel elevationModel;
    private BathymetryFilterElevationModel noDepthModel;
    private Globe roundGlobe;
    private FlatGlobe flatGlobe;
    private GeographicProjection projection;

    @Override
    public void componentInitiated() {
        wwd = GeoWorldWindViewImpl.getWW();
        this.elevationModel = wwd.getModel().getGlobe().getElevationModel();
        this.roundGlobe = wwd.getModel().getGlobe();
    }

    @Override
    public boolean canOpen(String category) {
        boolean canOpen = false;

        if (category.equals(COMPONENT_KEY_NAME_0)
                || category.equals(COMPONENT_KEY_NAME_1)
                || category.equals(COMPONENT_KEY_NAME_2)
                || category.equals(COMPONENT_KEY_NAME_3)
                || category.equals(COMPONENT_KEY_NAME_4)
                || category.equals(COMPONENT_KEY_NAME_5)) {
            canOpen = true;
        }
        return canOpen;
    }

    @Override
    public void on(String... files) {
        String[] cmd = files;
        if (cmd != null) {
            switch (cmd[0]) {
                case COMPONENT_KEY_NAME_0:
                    roundGlobe.setElevationModel(elevationModel);
                    break;
                case COMPONENT_KEY_NAME_1:
                    roundGlobe.setElevationModel(new ZeroElevationModel());
                    break;
                case COMPONENT_KEY_NAME_2:
                    roundGlobe.setElevationModel(elevationModel);
                    break;
                case COMPONENT_KEY_NAME_3:
                    noDepthModel = new BathymetryFilterElevationModel(elevationModel);
                    roundGlobe.setElevationModel(noDepthModel);
                    break;
                case COMPONENT_KEY_NAME_4:
                    //TODO
                    wwd.getModel().setGlobe(new EarthFlat());
                    this.flatGlobe = (FlatGlobe) wwd.getModel().getGlobe();
                    wwd.getModel().getGlobe().setElevationModel(new ZeroElevationModel());
                    flatGlobe.setProjection(new ProjectionMercator());
                    break;
                case COMPONENT_KEY_NAME_5:
                    //TODO
                    wwd.getModel().setGlobe(roundGlobe);
                    wwd.getModel().getGlobe().setElevationModel(elevationModel);
                    break;
                default:
                    break;
            }

        }
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
}
