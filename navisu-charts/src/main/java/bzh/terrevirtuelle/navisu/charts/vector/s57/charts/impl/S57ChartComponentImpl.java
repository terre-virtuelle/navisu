package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.S57GlobalCatalogServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.S57ChartComponentController;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Controller;
import bzh.terrevirtuelle.navisu.core.util.OS;
import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.util.Pair;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.ViewControlsLayer;
import gov.nasa.worldwind.layers.ViewControlsSelectListener;
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
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;
import bzh.terrevirtuelle.navisu.instruments.transponder.impl.events.TransponderActivateEvent;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponent;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view.DepareView;
import bzh.terrevirtuelle.navisu.database.relational.impl.DatabaseImpl;
import de.micromata.opengis.kml.v_2_2_0.Container;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import gov.nasa.worldwind.render.SurfacePolylines;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class S57ChartComponentImpl
        implements S57ChartComponent, S57ChartComponentServices, Driver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    LayersManagerServices layersManagerServices;
    @UsedService
    S57GlobalCatalogServices s57GlobalCatalogServices;

    ComponentManager cm;
    ComponentEventSubscribe<TransponderActivateEvent> transponderActivateEvent;

    private static final String NAME = "S57";
    private static final String EXTENSION_0 = ".000";
    private static final String EXTENSION_1 = ".001";
    private static final String EXTENSION_2 = ".002";
    private static final String EXTENSION_3 = ".003";
    protected static final String GROUP = "S57 charts";
    static private int i = 0;
    protected String CONFIG_FILE_NAME = System.getProperty("user.home") + "/.navisu/config/config.properties";
    protected Properties properties;
    protected S57ChartComponentController s57ChartComponentController;
    //  private SurveyZoneController surveyZoneController;
    protected List<Layer> layers;
    protected Layer layer;
    protected List<Layer> enabledLayers;
    protected List<CheckBoxTreeItem<GeoLayer>> rootItems;
    protected List<RenderableLayer> airspaceLayers;
    protected static final Logger LOGGER = Logger.getLogger(S57ChartComponentImpl.class.getName());
    protected WorldWindow wwd;
    protected Scene scene;
    private boolean first = true;
    protected Map<String, Pair<Integer, Integer>> clipConditions;
    protected Set<String> clipConditionsKeySet;
    protected float altitude;
    protected List<GeoLayer<Layer>> geoLayerList;
    protected List<String> groupNames = new ArrayList<>();
    protected boolean chartsOpen = false;
    protected String userDirPath = null;
    protected String shpDir;
    protected String dir;

    @SuppressWarnings("unchecked")
    @Override
    public void componentInitiated() {
        enabledLayers = new ArrayList<>();
        clipConditions = new HashMap<>();
        clipConditions.put("BUILDING", new Pair(0, 240000));
        clipConditions.put("BATHYMETRY", new Pair(0, 240000));
        clipConditionsKeySet = clipConditions.keySet();
        layerTreeServices.createGroup(GROUP);
        geoViewServices.getLayerManager().createGroup(GROUP);
        wwd = GeoWorldWindViewImpl.getWW();
        wwd.addPositionListener((PositionEvent event) -> {
            filter();
        });

        cm = ComponentManager.componentManager;
        transponderActivateEvent = cm.getComponentEventSubscribe(TransponderActivateEvent.class);
    }

    @SuppressWarnings("unchecked")
    private void filter() {
        enabledLayers.clear();
        CheckBoxTreeItem<GeoLayer> i = (CheckBoxTreeItem) layerTreeServices.search("S57 charts");
        List<TreeItem<GeoLayer>> childrens = i.getChildren();

        childrens.stream().forEach((t) -> {
            String value = t.getValue().getName().trim();
            if (clipConditionsKeySet.contains(value)) {
                layer = (Layer) t.getValue().getDisplayLayer();
                layer.setPickEnabled(true);
                if (((CheckBoxTreeItem<GeoLayer>) t).isSelected()) {
                    enabledLayers.add(layer);
                }
            }
        });
        altitude = ((int) wwd.getView().getCurrentEyePosition().getAltitude());
        enabledLayers.stream().forEach((l) -> {
            l.setPickEnabled(true);
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
    public boolean canOpen(String category, String file) {
        boolean canOpen = false;
        if (category.equals(NAME)
                && (file.toLowerCase().endsWith(EXTENSION_0)
                || file.toLowerCase().endsWith(EXTENSION_1)
                || file.toLowerCase().endsWith(EXTENSION_2)
                || file.toLowerCase().endsWith(EXTENSION_3))) {
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

    @SuppressWarnings("unchecked")
    protected void handleOpenFile(ProgressHandle pHandle, String fileName) {
        try {
            s57ChartComponentController = S57ChartComponentController.getInstance();
            if (first == true) {
                first = false;
                s57ChartComponentController.setTransponderActivateEvent(transponderActivateEvent);
                s57ChartComponentController.setLayersManagerServices(layersManagerServices);
                s57ChartComponentController.setGeoViewServices(geoViewServices);
                s57ChartComponentController.setGuiAgentServices(guiAgentServices);
            }
            /*
            // Test capture des evts par l'AreaController
            // only one shot
             surveyZoneController = new SurveyZoneController(KeyCode.Z, KeyCombination.CONTROL_DOWN);
             Platform.runLater(new Runnable() {
             @Override
             public void run() {
             guiAgentServices.getRoot().getChildren().add(surveyZoneController);
             }
             });
             chartS57Controller.setSurveyZoneController(surveyZoneController);
             }
             */
            s57ChartComponentController.subscribe(); // A chaque nouvelle carte car S57Controllers est modifie

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

            properties = new Properties();
            properties.load(new FileInputStream(CONFIG_FILE_NAME));
            String cmd0 = startCmd("ogr2ogr");

            String cmd;
            try {
                Path tmp = Paths.get(inputFile.toString());
                cmd = cmd0 + " -skipfailures -overwrite data/shp/shp_" + i + " " + tmp.toString();
                Proc.BUILDER.create()
                        .setCmd(cmd)
                        .execSh();
                inputFile = tmp;
            } catch (IOException | InterruptedException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
            try {
                Path tmp = Paths.get(inputFile.toString());
                cmd = cmd0 + " -nlt POINT25D -skipfailures -append data/shp/shp_" + i + "/soundg/SOUNDG.shp "
                        + tmp.toString() + " SOUNDG";
                Proc.BUILDER.create()
                        .setCmd(cmd)
                        .execSh();
                inputFile = tmp;
            } catch (IOException | InterruptedException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }

            s57ChartComponentController.init("data/shp/shp_" + i++);
            layers = s57ChartComponentController.getLayers();
            chartsOpen = true;
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

            //Controle fin de la camera
            ViewControlsLayer viewControlsLayer = new ViewControlsLayer();
            wwd.addSelectListener(new ViewControlsSelectListener(wwd, viewControlsLayer));
            geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(viewControlsLayer));
            /*
             airspaceLayers = chartS57Controller.getAirspaceLayers();
            airspaceLayers.stream().filter((l) -> (l != null)).map((l) -> {
                String name = l.getName();
                if (name.contains("LIGHTS")) {
                    l.setPickEnabled(true);
                } else {
                    l.setPickEnabled(false);
                }
              //  geoViewServices.getLayerManager().insertGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
                return l;
            }).forEach((l) -> {
             //   layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
            });
             */
        } catch (IOException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
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
    public void openChart(String file) {
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
           // Logger.getLogger(S57ChartComponentImpl.class.getName()).log(Level.INFO, "Clean tmp directories", ex);
        }

    }

    @Override
    public Set<S57Controller> getS57Controllers() {
        if (s57ChartComponentController != null) {
            return s57ChartComponentController.getS57Controllers();
        } else {
            return null;
        }
    }

    @Override
    public Set<NavigationData> getS57Charts() {
        return s57GlobalCatalogServices.getS57Charts();
    }

    public GeoViewServices getGeoViewServices() {
        return geoViewServices;
    }

    @Override
    public void componentStopped() {

    }

    @Override
    public boolean isChartsOpen() {
        return chartsOpen;
    }

    @Override
    public List<SurfacePolylines> getCoastalLines() {
        if (s57ChartComponentController != null) {
            return s57ChartComponentController.getCoastalSurfacePolylinesList();
        } else {
            return null;
        }
    }

    @Override
    public List<Path> getFilePaths(String rootFileNames, String kmlCatalog, String preFix) {
        List<Placemark> placemarks = getPlacemarkFromKmlCatalog(kmlCatalog);
        List<String> catalog = new ArrayList<>();
        placemarks.forEach((pm) -> {
            String fr = pm.getName();
            fr = fr.substring(0, 2);
            if (preFix.equals("ALL")) {
                if (fr.chars().allMatch(Character::isLetter)) {
                    catalog.add(pm.getName());
                }
            } else {
                if (fr.equals(preFix)) {
                    catalog.add(pm.getName());
                }
            }
        });
        //Only the first publication : .000
        List<Path> filePaths = filter(rootFileNames, catalog, "000");

        return filePaths;
    }

    //Search for Placemark from Kml catalog
    private List<Placemark> getPlacemarkFromKmlCatalog(String catalog) {
        Kml kml = Kml.unmarshal(new File(catalog));
        return getPlacemarks(kml.getFeature());
    }

    private ArrayList<Placemark> getPlacemarks(Feature root) {
        ArrayList<Placemark> Placemarks = new ArrayList<>();

        if (root instanceof Container) {
            if (root instanceof Document) {
                ((Document) root).getFeature().forEach((feature) -> {
                    if (feature instanceof Placemark) {
                        Placemarks.add((Placemark) feature);
                    } else if ((feature instanceof Document)
                            || (feature instanceof Folder)) {
                        Placemarks.addAll(getPlacemarks(feature));
                    }
                });
            } else if (root instanceof Folder) {
                ((Folder) root).getFeature().forEach((feature) -> {
                    if (feature instanceof Placemark) {
                        Placemarks.add((Placemark) feature);
                    } else if ((feature instanceof Document)
                            || (feature instanceof Folder)) {
                        Placemarks.addAll(getPlacemarks(feature));
                    }
                });
            }
        } else {
            if (root instanceof Placemark) {
                Placemarks.add((Placemark) root);
            }
        }
        return Placemarks;
    }

    //
    private List<Path> filter(String src, List<String> catalog, String criteria) {
        List<Path> filePaths = new ArrayList<>();
        try (Stream<Path> filePathStream = Files.walk(Paths.get(src))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    String[] tab = filePath.getFileName().toString().split("\\.");
                    if (tab != null) {
                        if (catalog.contains(tab[0]) && tab[1].equals(criteria)) {
                            filePaths.add(filePath);
                        }
                    }
                }

            });
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return filePaths;
    }

    @Override
    public String s57ToShapeFile(List<Path> paths) {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(CONFIG_FILE_NAME));
        } catch (IOException ex) {
            Logger.getLogger(S57ChartComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

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

        String cmd = startCmd("ogr2ogr");
        int j = 0;
        for (Path tmp : paths) {
            new File("data/shp").mkdir();
            new File("data/shp/shp_" + j).mkdir();

            String command = cmd
                    + " -skipfailures "
                    + " -overwrite "
                    + " -nlt PROMOTE_TO_MULTI"
                    + " data/shp/shp_" + j + " "
                    + tmp.toString();
            try {
                Proc.BUILDER.create()
                        .setCmd(command)
                        .execSh();
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(DepareView.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
            j++;
        }
        return dir;
    }

    private String startCmd(String command) {
        String cmd = null;
        if (OS.isWindows()) {
            cmd = "gdal\\win\\" + command;
        } else if (OS.isLinux()) {
            cmd = properties.getProperty("gdalPath") + "/" + command;
        } else {
            System.out.println("OS not found");
        }
        return cmd;
    }

    @Override
    public String s57FromCatalogToShapeFile(String rootFileNames, String kmlCatalog, String country, String epsg) {

        List<Path> paths = getFilePaths(rootFileNames, kmlCatalog, country);
        shpDir = s57ToShapeFile(paths);
        return shpDir;
    }

    @Override
    public void s57BuoyageView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
