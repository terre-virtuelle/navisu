/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.charts.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.bathymetry.view.DisplayBathymetryServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.S57GlobalCatalogServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.core.util.OS;
import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.stl.charts.impl.controller.charts.StlChartController;
import bzh.terrevirtuelle.navisu.stl.charts.impl.controller.StlChartComponentController;
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
import java.util.Properties;
import bzh.terrevirtuelle.navisu.stl.charts.StlChartComponentServices;
import bzh.terrevirtuelle.navisu.stl.charts.StlChartComponent;

/**
 *
 * @author serge
 * @date Feb 25, 2017
 */
public class StlChartComponentImpl
        implements StlChartComponent, StlChartComponentServices,
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
    @UsedService
    GeodesyServices geodesyServices;
    @UsedService
    BathymetryDBServices bathymetryDBServices;
    @UsedService
    DisplayBathymetryServices displayBathymetryServices;

    private static final String NAME = "S57Stl";
    protected static final String GROUP = "S57 charts";

    protected List<Layer> layers;
    protected Layer layer;
    protected List<Layer> enabledLayers;
    protected List<CheckBoxTreeItem<GeoLayer>> rootItems;
    protected List<GeoLayer<Layer>> geoLayerList;
    protected List<String> groupNames = new ArrayList<>();

    protected StlChartComponentController s57StlComponentController;
    protected StlChartController s57StlChartComponentController;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();

    static private int i = 0;
    private boolean first = true;
    protected String CONFIG_FILE_NAME = System.getProperty("user.home") + "/.navisu/config/config.properties";
    protected Properties properties;
    protected static final Logger LOGGER = Logger.getLogger(StlChartComponentImpl.class.getName());

    @Override
    public void openChart(String fileName) {
        guiAgentServices.getJobsManager().newJob("", (progressHandle) -> {
            handleOpenFile(progressHandle, fileName);
        });
    }

    /* Lors de l'init du composant on instancie les deux controlleurs
       s57StlChartComponentController il permet de générer le x3D
       s57StlComponentController c'est le composant d'IHM qui permet
       de sélectionner les zones à générer et défini l'ensemble des
       parametres nécessaires. Ce composant est un widget.
     */
    @Override
    public void componentInitiated() {

        s57StlChartComponentController = new StlChartController(geodesyServices);
        s57StlComponentController = new StlChartComponentController(this,
                guiAgentServices, // pour afficher le widget
                layerTreeServices, // pour indiquer dans l'arbre à gauche ou est la couche
                layersManagerServices, // pour afficher la couche
                instrumentDriverManagerServices, // pour envoyer un signal sonore en fin de génération
                geodesyServices,
                bathymetryDBServices,
                displayBathymetryServices,
                s57StlChartComponentController, // la composant de génération x3D
                GROUP, NAME, // pour se positionner dans l'arborescence des couches
                wwd);                            // le lien avec WordlWind
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
        if (file != null) {
            openChart(file);
        }
        return this;
    }

    /* 
    Après appui sur F1 est clic droit sur une carte du catalogue 
    Lecture du fic 57, ecriture de l'ensemble des fichiers Shapefile
    Affichage dans l'arbre et fourniture des Layers
     */
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
            s57StlChartComponentController.init("data/shp/shp_" + i++, fileName);

            layers = s57StlChartComponentController.getLayers();
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

    /**
     *
     * @param polygon
     */
    /* 
     Après appui sur F1 et clic droit sur une carte du catalogue 
     */
    @Override
    public void showGUI(KMLSurfacePolygonImpl polygon) {
        s57StlComponentController.showGUI(polygon);
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }
}
