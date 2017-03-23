/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.S57GlobalCatalogServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.S57ChartComponentController;
//import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.S57GlobalCatalogServices;
//import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
//import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.S57ChartComponentController;
import bzh.terrevirtuelle.navisu.core.util.OS;
import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.S57StlComponent;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.S57StlComponentServices;
import bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.S57StlComponentController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.ogc.kml.impl.KMLSurfacePolygonImpl;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.CheckBoxTreeItem;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author serge
 * @date Feb 25, 2017
 */
public class S57StlComponentImpl
        implements S57StlComponent, S57StlComponentServices,
        InstrumentDriver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    InstrumentDriverManagerServices instrumentDriverManagerServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    LayersManagerServices layersManagerServices;
    @UsedService
    S57GlobalCatalogServices s57GlobalCatalogServices;
    @UsedService
    S57ChartComponentServices s57ChartComponentServices;

    private static final String NAME = "S57Stl";
    protected static final String GROUP = "S57 charts";
    private static final String EXTENSION_0 = ".000";
    private static final String EXTENSION_1 = ".001";
    private static final String EXTENSION_2 = ".002";
    private static final String EXTENSION_3 = ".003";

    protected List<Layer> layers;
    protected Layer layer;
    protected List<Layer> enabledLayers;
    protected List<CheckBoxTreeItem<GeoLayer>> rootItems;
    protected List<GeoLayer<Layer>> geoLayerList;
    protected List<String> groupNames = new ArrayList<>();

    protected S57ChartComponentController s57ComponentController;
    protected WorldWindow wwd;
    static private int i = 0;
    private boolean first = true;
    protected static final Logger LOGGER = Logger.getLogger(S57StlComponentImpl.class.getName());

    @SuppressWarnings("unchecked")
    protected void handleOpenFile(ProgressHandle pHandle, String fileName) {

        try {
            if (first == true) {
                first = false;
            }
            new File("data/shp").mkdir();
            new File("data/shp/shp_" + i).mkdir();
            new File("data/shp/shp_" + i + "/soundg").mkdir();

            LOGGER.log(Level.INFO, "Opening {0} ...", fileName);

            Path inputFile = Paths.get(fileName);
            Map<String, String> environment = new HashMap<>(System.getenv());
            String options
                    = "\"RECODE_BY_DSSI=ON, "
                    + "ENCODING=UTF8, "
                    + "UPDATES=APPLY, "
                    + "RETURN_PRIMITIVES=ON, "
                    + "RETURN_LINKAGES=ON, "
                    + "LNAM_REFS=ON, "
                    + "SPLIT_MULTIPOINT=ON, "
                    + "ADD_SOUNDG_DEPTH=ON\" \n";
            environment.put("OGR_S57_OPTIONS", options);
            options = System.getProperty("user.dir") + "/gdal/data";
            environment.put("GDAL_DATA", options);

            String cmd = null;

            if (OS.isWindows()) {
                cmd = "gdal/win/ogr2ogr";
            } else if (OS.isLinux()) {
                cmd = "/usr/bin/ogr2ogr";
            } else if (OS.isMac()) {
                cmd = "gdal/osx/ogr2ogr";
            } else {
                System.out.println("OS not found");
            }
            try {
                Path tmp = Paths.get(inputFile.toString());
                Proc.BUILDER.create()
                        .setCmd(cmd)
                        .addArg("-skipfailures ").addArg("-overwrite ")
                        .addArg("data/shp/shp_" + i)// + "/out.shp ")
                        .addArg(tmp.toString())
                        .setOut(System.out)
                        .setErr(System.err)
                        .exec(environment);
                inputFile = tmp;
            } catch (IOException | InterruptedException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }

            cmd = cmd + " -nlt POINT25D";
            try {
                Path tmp = Paths.get(inputFile.toString());
                Proc.BUILDER.create()
                        .setCmd(cmd)
                        .addArg("-skipfailures ").addArg("-append ")
                        .addArg("data/shp/shp_" + i + "/soundg/SOUNDG.shp")
                        .addArg(tmp.toString())
                        .addArg("SOUNDG")
                        .setOut(System.out)
                        .setErr(System.err)
                        .exec(environment);
                inputFile = tmp;
            } catch (IOException | InterruptedException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
            s57ComponentController.init("data/shp/shp_" + i++);

            layers = s57ComponentController.getLayers();
            geoLayerList = geoViewServices.getLayerManager().getGroup(GROUP);
            groupNames.clear();
            geoLayerList.stream().forEach((l) -> {
                groupNames.add(l.getName());
            });

            layers.stream().filter((l) -> (!groupNames.contains(l.getName()))).map((l) -> GeoLayer.factory.newWorldWindGeoLayer(l)).map((gl) -> {
                geoViewServices.getLayerManager().insertGeoLayer(GROUP, gl);
                return gl;
            }).forEach((gl) -> {
                layerTreeServices.addGeoLayer(GROUP, gl);
            });

        } catch (Exception e) {
            System.out.println("handleOpenFile e " + e);
        }
    }

    @Override
    public void openChart(String fileName) {
        guiAgentServices.getJobsManager().newJob("", (progressHandle) -> {
            handleOpenFile(progressHandle, fileName);
        });
    }

    /**
     *
     * @param polygon
     */
    @Override
    public void showGUI(KMLSurfacePolygonImpl polygon) {
        s57ComponentController.showGUI(polygon);
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public void componentInitiated() {
        s57ComponentController = new S57StlComponentController(guiAgentServices,
                layersManagerServices,
                instrumentDriverManagerServices);

    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public boolean canOpen(String category) {
        boolean canOpen = false;
        if (!category.equals(NAME)) {
        } else {
            canOpen = true;
        }
        return canOpen;
    }

    @Override
    public void on(String... files) {
        String[] tab = files;
        openChart(tab[0]);
    }

    @Override
    public InstrumentDriver openFile(String category, String file) {
        openChart(file);
        return this;
    }
}
