/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.gps.plotter.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.S57GlobalCatalogServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57BasicBehavior;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57BuoyageController;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Controller;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.S57Chart;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconIsolatedDanger;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconLateral;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconSafeWater;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconSpecialPurpose;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyCardinal;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyInstallation;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyIsolatedDanger;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyLateral;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoySafeWater;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoySpecialPurpose;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.MooringWarpingFacility;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Gpx;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Highway;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.model.NavigationalWarnings;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl.controller.GpsPlotterController;
import bzh.terrevirtuelle.navisu.instruments.transponder.TransponderServices;
import bzh.terrevirtuelle.navisu.navigation.controller.catalog.AvurnavController;
import bzh.terrevirtuelle.navisu.navigation.controller.catalog.HighwayController;
import bzh.terrevirtuelle.navisu.navigation.controller.catalog.S57ChartController;
import bzh.terrevirtuelle.navisu.navigation.gps.plotter.impl.GpsPlotterWithRouteImpl;
import bzh.terrevirtuelle.navisu.util.io.IO;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import bzh.terrevirtuelle.navisu.widgets.textArea.TextAreaController;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.ogc.collada.ColladaRoot;
import gov.nasa.worldwind.pick.PickedObject;
import gov.nasa.worldwind.render.PointPlacemark;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import bzh.terrevirtuelle.navisu.kml.KmlComponentServices;
import gov.nasa.worldwind.render.Renderable;

/**
 * NaVisu
 *
 * @date 7 mai 2015
 * @author Serge Morvan
 */
public class GpsPlotterWithRouteController
        extends GpsPlotterController {

    private final String NAME2 = "Nautical documents";
    private final String NAME3 = "S57 Buoyage behavior";
    private final String NAME4 = "Nautical documents icons";
    // private final List<String> NAVIGATION_OBJECTS = Arrays.asList("Avurnav", "SailingDirections");
    private final List<Class> S57_CONTROLLER_TYPE_LIST = Arrays.asList(
            BeaconIsolatedDanger.class,
            BeaconLateral.class,
            BeaconSafeWater.class,
            BeaconSpecialPurpose.class,
            BuoyCardinal.class,
            BuoyInstallation.class,
            BuoyIsolatedDanger.class,
            BuoyLateral.class,
            BuoySafeWater.class,
            BuoySpecialPurpose.class,
            MooringWarpingFacility.class,
            Landmark.class);

    protected GpsPlotterWithRouteImpl component;
    protected S57GlobalCatalogServices s57GlobalCatalogServices;
    protected S57ChartComponentServices s57ChartComponentServices;
    protected TransponderServices transponderServices;

    protected RenderableLayer navigationPgonLayer;
    protected RenderableLayer navigationIconsLayer;
    protected RenderableLayer transponderZoneLayer;

    protected NavigationDataSet navigationDataSet;
    protected List<String> s57ControllerIdList;
    protected TextAreaController textAreaController;

    public GpsPlotterWithRouteController(GpsPlotterWithRouteImpl component,
            LayersManagerServices layersManagerServices,
            GuiAgentServices guiAgentServices,
            KmlComponentServices kmlObjectServices,
            S57ChartComponentServices s57ChartComponentServices,
            S57GlobalCatalogServices s57GlobalCatalogServices,
            TransponderServices transponderServices,
            String name) {
        super(layersManagerServices,
                guiAgentServices,
                kmlObjectServices,
                name);
        this.component = component;
        this.s57ChartComponentServices = s57ChartComponentServices;
        this.s57GlobalCatalogServices = s57GlobalCatalogServices;
        this.transponderServices = transponderServices;
        navigationPgonLayer = layersManagerServices.getLayer(GROUP, NAME2);
        navigationPgonLayer.setPickEnabled(false);
        navigationIconsLayer = layersManagerServices.getLayer(GROUP, NAME4);
        // transponderZoneLayer = layersManagerServices.getLayer(GROUP, NAME3);
    }

    @Override
    public void init(boolean subscribe) {
        super.init(subscribe);
        activateControllers();
        transponderServices.on();
        transponderServices.setShip(ownerShip);
    }

    @Override
    protected void addListeners() {
        wwd.addSelectListener((SelectEvent event) -> {
            Object o = event.getTopObject();
            if (event.isLeftClick() && o != null) {
                if (o.getClass() == ColladaRoot.class) {
                    Object object = ((ColladaRoot) o).getField("Ship");
                    if (object != null && object.getClass() == Ship.class) {
                        Ship ship = (Ship) object;
                        updateShipPanel(ship);
                    }
                }
            }
        });
        wwd.addSelectListener((SelectEvent event) -> {
            PickedObject po = event.getTopPickedObject();
            if (po != null && po.getObject() instanceof PointPlacemark) {
                if (event.getEventAction().equals(SelectEvent.LEFT_CLICK)) {
                    PointPlacemark placemark = (PointPlacemark) po.getObject();
                    if (placemark.getValue("TYPE") != null) {
                        String type = (String) placemark.getValue("TYPE");
                        /*
                        if (NAVIGATION_OBJECTS.contains(type)) {
                            Platform.runLater(() -> {
                                textAreaController = new TextAreaController();
                                textAreaController.getDataTextArea().setWrapText(true);
                                textAreaController.getTitleText().setText((String) placemark.getValue("TITLE"));
                                textAreaController.getDataTextArea().setText((String) placemark.getValue("TEXT"));
                                textAreaController.setVisible(true);
                                guiAgentServices.getRoot().getChildren().add(textAreaController);
                            });
                            event.consume();
                        }
                         */
                        if (type.equals("S57Chart")) {
                            Path path = s57GlobalCatalogServices.getChartPath((String) placemark.getValue(AVKey.DISPLAY_NAME));
                            s57ChartComponentServices.openChart(path.toString());
                            activateS57Controllers();
                            event.consume();
                            // transponderZoneLayer = layersManagerServices.getLayer(GROUP, NAME3);
                        }
                    }
                }

            }
        });
    }

    public final void activateControllers() {
        File file = IO.fileChooser(guiAgentServices.getStage(),
                "privateData/nds/", "NDS files (*.nds)", "*.nds", "*.NDS");
        navigationDataSet = new NavigationDataSet();
        try {
            navigationDataSet = ImportExportXML.imports(navigationDataSet, file);
        } catch (FileNotFoundException | JAXBException ex) {
            System.out.println("ex " + ex);
            Logger.getLogger(GpsPlotterWithRouteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //  activateS57Controllers();
        activateNavigationControllers();
    }

    @SuppressWarnings("unchecked")
    private void activateS57Controllers() {
        transponderZoneLayer = layersManagerServices.getLayer(GROUP, NAME3);
        List<NavigationData> s57NavigationDataList = new ArrayList<>();
        S57_CONTROLLER_TYPE_LIST.stream().forEach((claz) -> {
            s57NavigationDataList.addAll(navigationDataSet.get(claz));
        });
        s57ControllerIdList = new ArrayList<>();
        listeners.clear();
        s57NavigationDataList.stream().forEach((s) -> {
            s57ControllerIdList.add(Long.toString(s.getId()));
            //  System.out.println("s " + s.getClass().getSimpleName());
            S57BasicBehavior s57BasicBehavior = new S57BasicBehavior();
            //  System.out.println("s57BasicBehavior : " + s57BasicBehavior);
            S57Controller s57BuoyageController = new S57BuoyageController(s57BasicBehavior, true, s, 1000);
            //  System.out.println("GpsPlotterWithRouteController s57BuoyageController " + s57BuoyageController);
            s57BasicBehavior.setS57Controller(s57BuoyageController);

            listeners.add(s57BuoyageController);
            s57BuoyageController.setLayer(transponderZoneLayer);
            s57BuoyageController.activate();

        });

        /*
        listeners.forEach((sb) -> {
            System.out.println("sb " + sb);
        });
        System.out.println("GpsPlotterWithRouteController listeners size " + listeners.size());
         */
 /*
         S57Controller dummy = new S57BuoyageController(new S57Behavior() {
                    @Override
                    public void doIt(double distance, double azimuth) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                }, data, 0);
                for (S57Controller s57c : s57Controllers) {
                    if (s57c.equals(dummy) && s57c.getClass().getSimpleName().equals("S57BuoyageController")) {
                        System.out.println(((Buoyage) s57c.getNavigationData()).getObjectName());
                    }
                }
        component.notifyTransponderActivateEvent(transponderZoneLayer, s57NavigationDataList);
         */
    }

    private void activateNavigationControllers() {
        List<S57Chart> chartList = navigationDataSet.get(S57Chart.class);
        chartList.stream().forEach((S57Chart a) -> {

            Position orgPos = new Position(
                    Angle.fromDegrees(a.getLatitude()),
                    Angle.fromDegrees(a.getLongitude()), 0.0);

            String displayName = a.getNumber();
            String description = a.getDescription();
            S57ChartController sc = new S57ChartController(new S57BasicBehavior(),
                    guiAgentServices,
                    a, true,
                    926, displayName, description);
            sc.setLayer(navigationPgonLayer);
            sc.setIconsLayer(navigationIconsLayer);
            sc.activate();
        });

        List<NavigationalWarnings> avurnavList = navigationDataSet.get(NavigationalWarnings.class);
        avurnavList.stream().forEach((NavigationalWarnings a) -> {
            String displayName = "Avurnav N°" + Long.toString(a.getId());
            String description = a.getDescription();
            AvurnavController sc = new AvurnavController(new S57BasicBehavior(),
                    guiAgentServices, a, true,
                    926, displayName, description);
            sc.setLayer(navigationPgonLayer);
            sc.setIconsLayer(navigationIconsLayer);
            sc.activate();
        });

        /*
        List<SailingDirections> sailingDirectionsList = navigationDataSet.get(SailingDirections.class);
        sailingDirectionsList.stream().forEach((SailingDirections a) -> {
            String displayName = "SailingDirections N°" + Long.toString(a.getId());
            String description = a.getDescription();
            SailingDirectionsController sc = new SailingDirectionsController(new S57BasicBehavior(),
                    guiAgentServices, a,
                    926, displayName, description);
            sc.setLayer(navigationPgonLayer);
            sc.setIconsLayer(navigationIconsLayer);
            sc.activate();
        });
         */
        List<Gpx> gpxList = navigationDataSet.get(Gpx.class);
        gpxList.stream().forEach((Gpx a) -> {
            Highway highway = a.getHighway();
            String displayName = "Highway N°" + Long.toString(a.getId()) + "\n"
                    + highway.getDescription();
            HighwayController sc = new HighwayController(new S57BasicBehavior(),
                    guiAgentServices, highway, true,
                    926, displayName, highway.getDescription());
            sc.setLayer(navigationPgonLayer);
            sc.setIconsLayer(navigationIconsLayer);
            sc.activate();
        });
    }
}
