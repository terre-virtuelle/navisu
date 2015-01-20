package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57Chart;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.ChartS57Controller;
import bzh.terrevirtuelle.navisu.core.util.OS;
import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.util.Pair;
import bzh.terrevirtuelle.navisu.widgets.surveyZone.controller.SurveyZoneController;
import gov.nasa.worldwind.View;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.layers.AirspaceLayer;
import gov.nasa.worldwind.layers.Layer;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class S57ChartImpl
        implements S57Chart, S57ChartServices, Driver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    LayerTreeServices layerTreeServices;

    private static final String NAME = "S57";
    //  private static final String EXTENSION = ".000";
    private static final String EXTENSION_0 = ".000";
    private static final String EXTENSION_1 = ".001";
    private static final String EXTENSION_2 = ".002";
    private static final String EXTENSION_3 = ".003";
    protected static final String GROUP = "S57 charts";
    static private int i = 0;
    protected ChartS57Controller chartS57Controller;
    private SurveyZoneController surveyZoneController;
    protected List<Layer> layers;
    protected Layer layer;
    protected List<Layer> enabledLayers;
    protected List<CheckBoxTreeItem<GeoLayer>> rootItems;
    protected List<AirspaceLayer> airspaceLayers;
    protected static final Logger LOGGER = Logger.getLogger(S57ChartImpl.class.getName());
    protected WorldWindow wwd;
    protected Scene scene;
    private boolean first = true;
    protected Map<String, Pair<Integer, Integer>> clipConditions;
    protected Set<String> clipConditionsKeySet;
    protected float altitude;

    @Override
    public void componentInitiated() {
        enabledLayers = new ArrayList<>();
        clipConditions = new HashMap<>();
        clipConditions.put("BUOYAGE", new Pair(0, 240000));
        clipConditions.put("BUILDING", new Pair(0, 240000));
        clipConditions.put("BATHYMETRY", new Pair(0, 240000));
        clipConditionsKeySet = clipConditions.keySet();
        layerTreeServices.createGroup(GROUP);
        geoViewServices.getLayerManager().createGroup(GROUP);
        wwd = GeoWorldWindViewImpl.getWW();
        View view = wwd.getView();
        wwd.addPositionListener((PositionEvent event) -> {
            //  System.out.println("altitude : " + ((int) wwd.getView().getCurrentEyePosition().getAltitude()));
            filter();
        });
    }

    private void filter() {
        enabledLayers.clear();
        CheckBoxTreeItem<GeoLayer> i = (CheckBoxTreeItem) layerTreeServices.search("S57 charts");
        List<TreeItem<GeoLayer>> childrens = i.getChildren();

        childrens.stream().forEach((t) -> {
            String value = t.getValue().getName().trim();
            if (clipConditionsKeySet.contains(value)) {
                layer = (Layer) t.getValue().getDisplayLayer();
                if (((CheckBoxTreeItem<GeoLayer>) t).isSelected()) {
                    enabledLayers.add(layer);
                }
            }
        });
        altitude = ((int) wwd.getView().getCurrentEyePosition().getAltitude());
        enabledLayers.stream().forEach((l) -> {
            clip(l, clipConditions.get(l.getName()));
        });
    }

    private void clip(Layer layer, Pair<Integer, Integer> minMax) {
        if (altitude < minMax.getX() || altitude > minMax.getY()) {
            layer.setEnabled(false);
        } else {
            layer.setEnabled(true);
        }
    }

    @Override
    public boolean canOpen(String file) {

        boolean canOpen = false;

        //  if (file.toLowerCase().endsWith(EXTENSION)) {
        //      canOpen = true;
        //  }
        if (file.toLowerCase().endsWith(EXTENSION_0)
                || file.toLowerCase().endsWith(EXTENSION_1)
                || file.toLowerCase().endsWith(EXTENSION_2)
                || file.toLowerCase().endsWith(EXTENSION_3)) {
            canOpen = true;
        }
        return canOpen;
    }

    @Override
    public void open(ProgressHandle pHandle, String... files) {

        for (String file : files) {
            this.handleOpenFile(pHandle, file);
        }
    }

    protected void handleOpenFile(ProgressHandle pHandle, String fileName) {
        try {
            //Test capture des evts par l'AreaController
            /*
             if (first == true) {
             first = false;
             surveyZoneController = new SurveyZoneController();

             Platform.runLater(new Runnable() {
             @Override
             public void run() {
             guiAgentServices.getRoot().getChildren().add(surveyZoneController);
             WidgetController widgetController = new WidgetController(KeyCode.Z, KeyCombination.CONTROL_DOWN);
             guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, widgetController);
             widgetController.add(surveyZoneController);
             }
             });
             }
             */
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
            options = System.getProperty("user.dir") + "/bin/data";
            environment.put("GDAL_DATA", options);

            String cmd = null;
            // cmd = "bin/" + (OS.isMac() ? "osx" : "win") + "/ogr2ogr";

            if (OS.isWindows()) {
                cmd = "bin/win/ogr2ogr";
            } else {
                if (OS.isLinux()) {
                    cmd = "/usr/bin/ogr2ogr";
                } else {
                    if (OS.isMac()) {
                        cmd = "bin/osx/ogr2ogr";
                    } else {
                        System.out.println("OS not found");
                    }
                }
            }
            try {
                Path tmp = Paths.get(inputFile.toString());
                Proc.builder.create()
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

            //  cmd = "bin/" + (OS.isMac() ? "osx" : "win") + "/ogr2ogr -nlt POINT25D";
            cmd = cmd + " -nlt POINT25D";
            try {
                Path tmp = Paths.get(inputFile.toString());
                Proc.builder.create()
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

            chartS57Controller = ChartS57Controller.getInstance();
            //  chartS57Controller.setSurveyZoneController(surveyZoneController);
            chartS57Controller.init("data/shp/shp_" + i++);
            layers = chartS57Controller.getLayers();

            //Filtrage des couches existant déjà
            List<GeoLayer<Layer>> lg = geoViewServices.getLayerManager().getGroup(GROUP);
            lg.stream().forEach((g) -> {
                layers.stream().filter((l) -> (g.getName().equals(l.getName()))).forEach((l) -> {
                    layers.remove(l);
                });
            });
            //On ne met que les nouvelles couches
            layers.stream().filter((l) -> (l != null)).map((l) -> {
                geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
                return l;
            }).forEach((l) -> {
                layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
            });

            airspaceLayers = chartS57Controller.getAirspaceLayers();
            airspaceLayers.stream().filter((l) -> (l != null)).map((l) -> {
                String name = l.getName();
                if (name.contains("LIGHTS")) {
                    l.setPickEnabled(true);
                } else {
                    l.setPickEnabled(false);
                }
                geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
                return l;
            }).forEach((l) -> {
                layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
            });

        } catch (Exception e) {

        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String[] getExtensions() {
        return new String[]{"*" + EXTENSION_0,
            "*" + EXTENSION_1,
            "*" + EXTENSION_2,
            "*" + EXTENSION_3
        };
    }

    @Override
    public void openChart(String file
    ) {
        this.open(null, file);
    }

    @Override
    public Driver getDriver() {
        return this;
    }

    @Override
    public void componentStarted() {

        try {
            Path directory = Paths.get("data/shp");
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }

            });
        } catch (IOException ex) {
            Logger.getLogger(S57ChartImpl.class.getName()).log(Level.INFO, "Clean tmp directories", ex);
        }

    }

    @Override
    public void componentStopped() {

    }
}
