package bzh.terrevirtuelle.navisu.main;

import bzh.terrevirtuelle.navisu.app.drivers.directorydriver.impl.DirectoryDriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.dpagent.impl.DpAgentImpl;
import bzh.terrevirtuelle.navisu.app.drivers.databasedriver.DatabaseDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.databasedriver.impl.DatabaseDriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.drivers.driver.DriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.driver.impl.DriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.impl.InstrumentDriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.WebDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.impl.WebDriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.impl.GuiAgentImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.impl.LayersManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.options.ConfigurationComponentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.options.impl.ConfigurationComponentImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.options.server.impl.ServerOptionsComponentImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.I18nLangEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator;
import bzh.terrevirtuelle.navisu.bathymetry.catalog.local.BathymetryLocalCatalogServices;
import bzh.terrevirtuelle.navisu.bathymetry.catalog.local.impl.BathymetryLocalCatalogImpl;
import bzh.terrevirtuelle.navisu.bathymetry.charts.BathymetryServices;
import bzh.terrevirtuelle.navisu.bathymetry.charts.impl.BathymetryImpl;
import bzh.terrevirtuelle.navisu.bathymetry.controller.eventsProducer.BathymetryEventProducerServices;
import bzh.terrevirtuelle.navisu.bathymetry.controller.eventsProducer.impl.BathymetryEventProducerImpl;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.impl.BathymetryDBImpl;
import bzh.terrevirtuelle.navisu.charts.earth.ProjectionsComponentServices;
import bzh.terrevirtuelle.navisu.charts.earth.impl.ProjectionsComponentImpl;
import bzh.terrevirtuelle.navisu.charts.raster.geotiff.GeoTiffChartServices;
import bzh.terrevirtuelle.navisu.charts.raster.geotiff.impl.GeoTiffChartImpl;
import bzh.terrevirtuelle.navisu.charts.raster.kap.KapChartServices;
import bzh.terrevirtuelle.navisu.charts.raster.kap.impl.KapChartImpl;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.S57GlobalCatalogServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.impl.S57GlobalCatalogImpl;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.local.S57LocalCatalogServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.S57ChartComponentImpl;
import bzh.terrevirtuelle.navisu.client.nmea.NmeaClientServices;
import bzh.terrevirtuelle.navisu.client.nmea.impl.vertx.NmeaClientImpl;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.currents.CurrentsServices;
import bzh.terrevirtuelle.navisu.currents.impl.CurrentsImpl;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.database.app.TestDBServices;
import bzh.terrevirtuelle.navisu.database.app.impl.TestDBImpl;
import bzh.terrevirtuelle.navisu.database.relational.impl.DatabaseImpl;
import bzh.terrevirtuelle.navisu.geometry.curves2D.bezier.Bezier2DServices;
import bzh.terrevirtuelle.navisu.geometry.curves2D.bezier.impl.Bezier2DImpl;
import bzh.terrevirtuelle.navisu.gpx.GpxObjectServices;
import bzh.terrevirtuelle.navisu.gpx.impl.GpxObjectImpl;
import bzh.terrevirtuelle.navisu.instruments.ais.base.AisServices;
import bzh.terrevirtuelle.navisu.instruments.ais.base.impl.AisImpl;
import bzh.terrevirtuelle.navisu.instruments.ais.logger.AisLoggerServices;
import bzh.terrevirtuelle.navisu.instruments.ais.logger.impl.AisLoggerImpl;
import bzh.terrevirtuelle.navisu.instruments.ais.plotter.AisPlotterServices;
import bzh.terrevirtuelle.navisu.instruments.ais.plotter.impl.AisPlotterImpl;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.AisRadarServices;
import bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.AisRadarImpl;
import bzh.terrevirtuelle.navisu.instruments.clocks.ClocksServices;
import bzh.terrevirtuelle.navisu.instruments.clocks.impl.ClocksImpl;
import bzh.terrevirtuelle.navisu.instruments.sonar.SonarServices;
import bzh.terrevirtuelle.navisu.instruments.sonar.impl.SonarImpl;
import bzh.terrevirtuelle.navisu.instruments.template.InstrumentTemplateServices;
import bzh.terrevirtuelle.navisu.instruments.template.impl.InstrumentTemplateImpl;
import bzh.terrevirtuelle.navisu.instruments.compass.CompassServices;
import bzh.terrevirtuelle.navisu.instruments.compass.impl.CompassImpl;
import bzh.terrevirtuelle.navisu.instruments.gps.logger.GpsLoggerServices;
import bzh.terrevirtuelle.navisu.instruments.gps.logger.impl.GpsLoggerImpl;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.GpsPlotterServices;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl.GpsPlotterImpl;
import bzh.terrevirtuelle.navisu.instruments.gps.track.GpsTrackServices;
import bzh.terrevirtuelle.navisu.instruments.gps.track.impl.GpsTrackImpl;
import bzh.terrevirtuelle.navisu.navigation.measuretools.MeasureToolsServices;
import bzh.terrevirtuelle.navisu.navigation.measuretools.impl.MeasureToolsImpl;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.RouteEditorServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.RouteEditorImpl;
import bzh.terrevirtuelle.navisu.instruments.webview.WebViewServices;
import bzh.terrevirtuelle.navisu.instruments.webview.impl.WebViewImpl;
import bzh.terrevirtuelle.navisu.kml.impl.KmlComponentImpl;
import bzh.terrevirtuelle.navisu.server.DataServerServices;
import bzh.terrevirtuelle.navisu.server.impl.vertx.DataServerImpl;
import bzh.terrevirtuelle.navisu.magnetic.MagneticServices;
import bzh.terrevirtuelle.navisu.magnetic.impl.MagneticImpl;
import bzh.terrevirtuelle.navisu.media.sound.SoundServices;
import bzh.terrevirtuelle.navisu.media.sound.impl.SoundImpl;
import bzh.terrevirtuelle.navisu.navigation.gps.plotter.GpsPlotterWithRouteServices;
import bzh.terrevirtuelle.navisu.navigation.gps.plotter.impl.GpsPlotterWithRouteImpl;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.RouteDataEditorServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.RoutePhotoEditorServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.RoutePhotoViewerServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.RouteDataEditorImpl;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.RoutePhotoEditorImpl;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.RoutePhotoViewerImpl;
import bzh.terrevirtuelle.navisu.instruments.transponder.TransponderServices;
import bzh.terrevirtuelle.navisu.instruments.transponder.impl.TransponderImpl;
import bzh.terrevirtuelle.navisu.photos.exif.ExifComponentServices;
import bzh.terrevirtuelle.navisu.photos.exif.impl.ExifComponentImpl;
import bzh.terrevirtuelle.navisu.sedimentology.SedimentologyServices;
import bzh.terrevirtuelle.navisu.sedimentology.impl.SedimentologyImpl;
import bzh.terrevirtuelle.navisu.shapefiles.ShapefileObjectServices;
import bzh.terrevirtuelle.navisu.shapefiles.impl.ShapefileObjectImpl;
import bzh.terrevirtuelle.navisu.speech.SpeakerServices;
import bzh.terrevirtuelle.navisu.speech.impl.SpeakerImpl;
import bzh.terrevirtuelle.navisu.system.files.FilesServices;
import bzh.terrevirtuelle.navisu.system.files.impl.FilesImpl;
import bzh.terrevirtuelle.navisu.wms.WMSServices;
import bzh.terrevirtuelle.navisu.wms.impl.WMSImpl;
import gov.nasa.worldwind.geom.Position;

import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;

import org.capcaval.c3.componentmanager.ComponentManager;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.extensions.camera.CameraComponentServices;
import bzh.terrevirtuelle.navisu.extensions.camera.impl.CameraComponentImpl;
import bzh.terrevirtuelle.navisu.extensions.commands.NavigationCmdComponentServices;
import bzh.terrevirtuelle.navisu.extensions.commands.impl.NavigationCmdComponentImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.options.server.ServerOptionsComponentServices;
import bzh.terrevirtuelle.navisu.architecture.ArchitectureComponentServices;
import bzh.terrevirtuelle.navisu.architecture.impl.ArchitectureComponentImpl;
import bzh.terrevirtuelle.navisu.media.bathysounds.BathySoundsServices;
import bzh.terrevirtuelle.navisu.media.bathysounds.impl.BathySoundsImpl;
import bzh.terrevirtuelle.navisu.bathymetry.view.DisplayBathymetryServices;
import bzh.terrevirtuelle.navisu.bathymetry.view.impl.DisplayBathymetryImpl;
import bzh.terrevirtuelle.navisu.cartography.projection.Pro4JServices;
import bzh.terrevirtuelle.navisu.cartography.projection.impl.Pro4JImpl;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.S57DBComponentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.S57DBComponentImpl;
import bzh.terrevirtuelle.navisu.core.util.OS;
import bzh.terrevirtuelle.navisu.dem.db.impl.DemDBImpl;
import bzh.terrevirtuelle.navisu.extensions.server.NavigationServerServices;
import bzh.terrevirtuelle.navisu.extensions.server.impl.NavigationServerImpl;
import bzh.terrevirtuelle.navisu.gazetteer.GazetteerComponentServices;
import bzh.terrevirtuelle.navisu.gazetteer.impl.GazetteerComponentImpl;
import bzh.terrevirtuelle.navisu.geometry.delaunay.DelaunayServices;
import bzh.terrevirtuelle.navisu.geometry.delaunay.impl.DelaunayImpl;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.geometry.geodesy.impl.GeodesyImpl;
import bzh.terrevirtuelle.navisu.geometry.jts.JTSServices;
import bzh.terrevirtuelle.navisu.geometry.jts.impl.JTSImpl;
import bzh.terrevirtuelle.navisu.leapmotion.LeapMotionComponentServices;
import bzh.terrevirtuelle.navisu.leapmotion.impl.LeapMotionComponentImpl;
import bzh.terrevirtuelle.navisu.netcdf.NetCDFServices;
import bzh.terrevirtuelle.navisu.netcdf.impl.NetCDFImpl;
import bzh.terrevirtuelle.navisu.kml.KmlComponentServices;
import bzh.terrevirtuelle.navisu.cartography.projection.lambert.LambertServices;
import bzh.terrevirtuelle.navisu.cartography.projection.lambert.impl.LambertImpl;
import bzh.terrevirtuelle.navisu.stl.charts.impl.StlChartComponentImpl;
import bzh.terrevirtuelle.navisu.visualization.view.DisplayServices;
import bzh.terrevirtuelle.navisu.visualization.view.impl.DisplayImpl;
import bzh.terrevirtuelle.navisu.weather.WeatherComponentServices;
import bzh.terrevirtuelle.navisu.weather.impl.WeatherComponentImpl;
import gov.nasa.worldwind.WorldWindow;
import bzh.terrevirtuelle.navisu.tools.ToolsComponentServices;
import bzh.terrevirtuelle.navisu.tools.impl.ToolsComponentImpl;
import bzh.terrevirtuelle.navisu.topology.TopologyServices;
import bzh.terrevirtuelle.navisu.topology.impl.TopologyImpl;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.FileHandler;
import bzh.terrevirtuelle.navisu.stl.charts.StlChartComponentServices;
import bzh.terrevirtuelle.navisu.stl.databases.StlDBComponentServices;
import bzh.terrevirtuelle.navisu.stl.databases.impl.StlDBComponentImpl;
import bzh.terrevirtuelle.navisu.dem.db.DemDBServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.obj.ObjComponentServices;
import bzh.terrevirtuelle.navisu.geometry.objects3D.obj.impl.ObjComponentImpl;
import bzh.terrevirtuelle.navisu.osm.OsmComponentServices;
import bzh.terrevirtuelle.navisu.osm.impl.OsmComponentImpl;
import bzh.terrevirtuelle.navisu.stl.StlComponentServices;
import bzh.terrevirtuelle.navisu.stl.impl.StlComponentImpl;

/**
 * @author Serge Morvan <morvan at enib.fr>
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 * @author Dominique Marques <dom.marques at free.fr>
 */
public class AppMain extends Application {

    private static final Logger LOGGER = Logger.getLogger(AppMain.class.getName());
    private final String DATA_S57_CATALOG_1 = "data/charts/vector/s57/catalog/ENC_NP1.kml";
    private final String DATA_S57_CATALOG_2 = "data/charts/vector/s57/catalog/ENC_NP2.kml";
    private final String DATA_S57_CATALOG_3 = "data/charts/vector/s57/catalog/ENC_NP3.kml";
    private final String DATA_S57_CATALOG_4 = "data/charts/vector/s57/catalog/ENC_NP4.kml";
    private final String DATA_S57_CATALOG_5 = "data/charts/vector/s57/catalog/ENC_NP5.kml";
    private final String DATA_S57_CATALOG_6 = "data/charts/vector/s57/catalog/ENC_NP6.kml";
    /*
    1 - Overview 	< 1 : 1 500 000
    2 - General 	1 : 350 000 à 1 : 1 500 000
    3 - Coastal 	1 : 90 000 à 1 : 350 000
    4 - Approach 	1 : 22 000 à 1 : 90 000
    5 - Harbour 	1 : 4 000 à 1 : 22 000
    6 - Berthing 	> 1 : 4 000
     */
    private WorldWindow wwd;
    private final String NAVISU_HOME = System.getProperty("user.home") + "/.navisu";
    private final String USER_DIR = System.getProperty("user.dir");

    @Override
    @SuppressWarnings({"unchecked", "varargs"})
    public void start(Stage stage) throws Exception {
        clearTmpDirs(USER_DIR + "/data/sql", ".sql", true);
        clearTmpDirs(USER_DIR, ".log", false);
        clearTmpDirs(USER_DIR + "/tmp", "*", false);
        clearTmpDirs(USER_DIR + "/cmd", "*", false);
        clearTmpDirs(USER_DIR + "/privateData/ulhysses", "*", false);
        clearTmpDirs(USER_DIR + "/privateData/elevation", "*", false);
        clearTmpDirs(USER_DIR + "/privateData/stl", "*", false);
        clearTmpDirs(USER_DIR + "/privateData/kml", "*", false);
        clearTmpDirs(USER_DIR + "/privateData/asc", "*", false);

        wwd = GeoWorldWindViewImpl.getWW();

        Translator.setLang(I18nLangEnum.FRENCH);

        LogManager.getLogManager().readConfiguration(new FileInputStream("conf/logging.properties"));

        Configuration.init();

        final ComponentManager componentManager = ComponentManager.componentManager;

        FileHandler fh = new FileHandler(NAVISU_HOME + "/logs/" + "components.log");
        LOGGER.addHandler(fh);
        LOGGER.setUseParentHandlers(false);
        /* Deploy components */
        LOGGER.info("\n"
                + componentManager.startApplication(GuiAgentImpl.class,//in first
                        AisImpl.class,
                        AisLoggerImpl.class,
                        AisPlotterImpl.class,
                        AisRadarImpl.class,
                        ArchitectureComponentImpl.class,
                        BathymetryDBImpl.class,
                        BathymetryEventProducerImpl.class,
                        BathymetryImpl.class,
                        BathymetryLocalCatalogImpl.class,
                        BathySoundsImpl.class,
                        Bezier2DImpl.class,
                        CameraComponentImpl.class,
                        ClocksImpl.class,
                        CompassImpl.class,
                        ConfigurationComponentImpl.class,
                        CurrentsImpl.class,
                        DataServerImpl.class,
                        DatabaseImpl.class,
                        DatabaseDriverManagerImpl.class,
                        DelaunayImpl.class,
                        DemDBImpl.class,
                        DirectoryDriverManagerImpl.class,
                        DpAgentImpl.class,
                        DriverManagerImpl.class,
                        DisplayImpl.class,
                        DisplayBathymetryImpl.class,
                        ExifComponentImpl.class,
                        FilesImpl.class,
                        GazetteerComponentImpl.class,
                        GeodesyImpl.class,
                        GeoTiffChartImpl.class,
                        GpsLoggerImpl.class,
                        GpsTrackImpl.class,
                        GpsPlotterImpl.class,
                        GpsPlotterWithRouteImpl.class,
                        GpxObjectImpl.class,
                        InstrumentDriverManagerImpl.class,
                        InstrumentTemplateImpl.class,
                        JTSImpl.class,
                        KapChartImpl.class,
                        KmlComponentImpl.class,
                        LambertImpl.class,
                        LayersManagerImpl.class,
                        LeapMotionComponentImpl.class,
                        MagneticImpl.class,
                        MeasureToolsImpl.class,
                        NetCDFImpl.class,
                        NavigationServerImpl.class,
                        NavigationCmdComponentImpl.class,
                        NmeaClientImpl.class,
                        ObjComponentImpl.class,
                        OsmComponentImpl.class,
                        ProjectionsComponentImpl.class,
                        Pro4JImpl.class,
                        RouteDataEditorImpl.class,
                        RouteEditorImpl.class,
                        RoutePhotoEditorImpl.class,
                        RoutePhotoViewerImpl.class,
                        SedimentologyImpl.class,
                        ServerOptionsComponentImpl.class,
                        ShapefileObjectImpl.class,
                        SonarImpl.class,
                        SoundImpl.class,
                        SpeakerImpl.class,
                        StlComponentImpl.class,
                        StlDBComponentImpl.class,
                        S57ChartComponentImpl.class,
                        S57DBComponentImpl.class,
                        S57GlobalCatalogImpl.class,
                        StlChartComponentImpl.class,
                        TestDBImpl.class,
                        ToolsComponentImpl.class,
                        TopologyImpl.class,
                        TransponderImpl.class,
                        WeatherComponentImpl.class,
                        WebDriverManagerImpl.class,
                        WMSImpl.class,
                        WebViewImpl.class
                )
        );
        /* Services */
        AisServices aisServices = componentManager.getComponentService(AisServices.class);
        AisLoggerServices aisLoggerServices = componentManager.getComponentService(AisLoggerServices.class);
        AisPlotterServices aisPlotterServices = componentManager.getComponentService(AisPlotterServices.class);
        AisRadarServices aisRadarServices = componentManager.getComponentService(AisRadarServices.class);
        ArchitectureComponentServices architectureComponentServices = componentManager.getComponentService(ArchitectureComponentServices.class);

        BathymetryServices bathymetryServices = componentManager.getComponentService(BathymetryServices.class);
        BathymetryLocalCatalogServices bathymetryLocalCatalogServices = componentManager.getComponentService(BathymetryLocalCatalogServices.class);
        BathymetryDBServices bathymetryDBServices = componentManager.getComponentService(BathymetryDBServices.class);
        BathymetryEventProducerServices bathymetryEventProducerServices = componentManager.getComponentService(BathymetryEventProducerServices.class);
        BathySoundsServices bathySoundsServices = componentManager.getComponentService(BathySoundsServices.class);
        Bezier2DServices bezier2DServices = componentManager.getComponentService(Bezier2DServices.class);

        CameraComponentServices cameraComponentServices = componentManager.getComponentService(CameraComponentServices.class);
        ClocksServices clocksServices = componentManager.getComponentService(ClocksServices.class);

        CompassServices compassServices = componentManager.getComponentService(CompassServices.class);
        ConfigurationComponentServices configurationComponentServices = componentManager.getComponentService(ConfigurationComponentServices.class);
        CurrentsServices currentsServices = componentManager.getComponentService(CurrentsServices.class);

        DatabaseServices databaseServices = componentManager.getComponentService(DatabaseServices.class);
        DataServerServices dataServerServices = componentManager.getComponentService(DataServerServices.class);
        DelaunayServices delaunayServices = componentManager.getComponentService(DelaunayServices.class);
        DemDBServices demComponentServices = componentManager.getComponentService(DemDBServices.class);
        DisplayServices displayServices = componentManager.getComponentService(DisplayServices.class);
        DisplayBathymetryServices displayBathymetryServices = componentManager.getComponentService(DisplayBathymetryServices.class);

        ExifComponentServices exifComponentServices = componentManager.getComponentService(ExifComponentServices.class);

        FilesServices filesServices = componentManager.getComponentService(FilesServices.class);

        GazetteerComponentServices gazetteerComponentServices = componentManager.getComponentService(GazetteerComponentServices.class);
        GeodesyServices geodesyServices = componentManager.getComponentService(GeodesyServices.class);
        GeoTiffChartServices geoTiffChartServices = componentManager.getComponentService(GeoTiffChartServices.class);
        GpsLoggerServices gpsLoggerServices = componentManager.getComponentService(GpsLoggerServices.class);
        GpsTrackServices gpsTrackServices = componentManager.getComponentService(GpsTrackServices.class);
        GpsPlotterServices gpsPlotterServices = componentManager.getComponentService(GpsPlotterServices.class);
        GpsPlotterWithRouteServices gpsPlotterWithRouteServices = componentManager.getComponentService(GpsPlotterWithRouteServices.class);
        GpxObjectServices gpxObjectServices = componentManager.getComponentService(GpxObjectServices.class);

        GuiAgentServices guiAgentServices = componentManager.getComponentService(GuiAgentServices.class);
        // ***************** size of screen *********************************************        
        guiAgentServices.showGui(stage, 1080, 700);
        //guiAgentServices.showGui(stage, 1500, 972);
        //********************************************************************************

        InstrumentTemplateServices instrumentTemplateServices = componentManager.getComponentService(InstrumentTemplateServices.class);

        JTSServices jtsServices = componentManager.getComponentService(JTSServices.class);

        KapChartServices chartsServices = componentManager.getComponentService(KapChartServices.class);
        KmlComponentServices kmlComponentServices = componentManager.getComponentService(KmlComponentServices.class);

        LambertServices lambertServices = componentManager.getComponentService(LambertServices.class);
        LayersManagerServices layersManagerServices = componentManager.getComponentService(LayersManagerServices.class);
        LeapMotionComponentServices leapMotionComponentServices = componentManager.getComponentService(LeapMotionComponentServices.class);

        MagneticServices magneticServices = componentManager.getComponentService(MagneticServices.class);
        MeasureToolsServices measureToolsServices = componentManager.getComponentService(MeasureToolsServices.class);
        NetCDFServices meteoNetCdfServices = componentManager.getComponentService(NetCDFServices.class);

        NmeaClientServices nmeaClientServices = componentManager.getComponentService(NmeaClientServices.class);
        NavigationServerServices navigationServerServices = componentManager.getComponentService(NavigationServerServices.class);
        NavigationCmdComponentServices navigationCmdComponentServices = componentManager.getComponentService(NavigationCmdComponentServices.class);
        navigationCmdComponentServices.init();

        OsmComponentServices osmComponentServices= componentManager.getComponentService(OsmComponentServices.class);
        ObjComponentServices objComponentServices = componentManager.getComponentService(ObjComponentServices.class);

        ProjectionsComponentServices projectionsComponentServices = componentManager.getComponentService(ProjectionsComponentServices.class);
        Pro4JServices pro4JServices = componentManager.getComponentService(Pro4JServices.class);

        RouteEditorServices routeEditorServices = componentManager.getComponentService(RouteEditorServices.class);
        RouteDataEditorServices routeDataEditorServices = componentManager.getComponentService(RouteDataEditorServices.class);
        RoutePhotoEditorServices routePhotoEditorServices = componentManager.getComponentService(RoutePhotoEditorServices.class);
        RoutePhotoViewerServices routePhotoViewerServices = componentManager.getComponentService(RoutePhotoViewerServices.class);

        SedimentologyServices sedimentologyServices = componentManager.getComponentService(SedimentologyServices.class);
        ServerOptionsComponentServices serverOptionsComponentServices = componentManager.getComponentService(ServerOptionsComponentServices.class);
        ShapefileObjectServices shapefileObjectServices = componentManager.getComponentService(ShapefileObjectServices.class);
        SonarServices sonarServices = componentManager.getComponentService(SonarServices.class);
        SoundServices soundServices = componentManager.getComponentService(SoundServices.class);
        SpeakerServices speakerServices = componentManager.getComponentService(SpeakerServices.class);
        StlComponentServices stlComponentServices = componentManager.getComponentService(StlComponentServices.class);
        StlChartComponentServices stlChartComponentServices = componentManager.getComponentService(StlChartComponentServices.class);
        StlDBComponentServices stlDBComponentServices = componentManager.getComponentService(StlDBComponentServices.class);
        S57ChartComponentServices s57ChartComponentServices = componentManager.getComponentService(S57ChartComponentServices.class);
        S57DBComponentServices s57DBComponentServices = componentManager.getComponentService(S57DBComponentServices.class);
        S57GlobalCatalogServices s57GlobalCatalogServices = componentManager.getComponentService(S57GlobalCatalogServices.class);
        S57LocalCatalogServices catalogS57Services = componentManager.getComponentService(S57LocalCatalogServices.class);

        TestDBServices testDBServices = componentManager.getComponentService(TestDBServices.class);
        TopologyServices topologyServices = componentManager.getComponentService(TopologyServices.class);
        ToolsComponentServices toolsComponentServices = componentManager.getComponentService(ToolsComponentServices.class);
        TransponderServices transponderServices = componentManager.getComponentService(TransponderServices.class);

        WeatherComponentServices weatherComponentServices = componentManager.getComponentService(WeatherComponentServices.class);
        WMSServices wmsServices = componentManager.getComponentService(WMSServices.class);
        wmsServices.init();
        WebViewServices webViewServices = componentManager.getComponentService(WebViewServices.class);

        DatabaseDriverManagerServices databaseDriverManagerServices = componentManager.getComponentService(DatabaseDriverManagerServices.class);
        databaseDriverManagerServices.registerNewDriver(bathymetryDBServices.getDriver());
        databaseDriverManagerServices.registerNewDriver(demComponentServices.getDriver());
        //  DirectoryDriverManagerServices ddriverServices = componentManager.getComponentService(DirectoryDriverManagerServices.class);
        //     ddriverServices.init();
        //     ddriverServices.registerNewDriver(catalogS57Services.getDriver());
        DriverManagerServices driverServices = componentManager.getComponentService(DriverManagerServices.class);
        driverServices.init();
        driverServices.registerNewDriver(bathymetryLocalCatalogServices.getDriver());
        driverServices.registerNewDriver(bathymetryServices.getDriver());
        driverServices.registerNewDriver((Driver) chartsServices.getDriver());
        driverServices.registerNewDriver(s57ChartComponentServices.getDriver());
        driverServices.registerNewDriver(currentsServices.getDriver());
        driverServices.registerNewDriver((Driver) geoTiffChartServices.getDriver());
        driverServices.registerNewDriver(gpxObjectServices.getDriver());
        driverServices.registerNewDriver(kmlComponentServices.getDriver());
        driverServices.registerNewDriver(magneticServices.getDriver());
        driverServices.registerNewDriver((Driver) meteoNetCdfServices.getDriver());
       // driverServices.registerNewDriver(osmComponentServices.getDriver());
        driverServices.registerNewDriver(sedimentologyServices.getDriver());
        driverServices.registerNewDriver(shapefileObjectServices.getDriver());

        driverServices.registerNewDriver(s57GlobalCatalogServices.getDriver());
        driverServices.registerNewDriver(filesServices.getDriver());

        InstrumentDriverManagerServices instrumentDriverManagerServices = componentManager.getComponentService(InstrumentDriverManagerServices.class);
        instrumentDriverManagerServices.init();
        instrumentDriverManagerServices.registerNewDriver(aisLoggerServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(aisPlotterServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(aisRadarServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(architectureComponentServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(bathySoundsServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(cameraComponentServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(compassServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(configurationComponentServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(clocksServices.getDriver());
        // instrumentDriverManagerServices.registerNewDriver(displayServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(displayBathymetryServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(gpsLoggerServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(gpsPlotterServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(gpsPlotterWithRouteServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(gpsTrackServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(instrumentTemplateServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(leapMotionComponentServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(measureToolsServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(osmComponentServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(projectionsComponentServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(routeDataEditorServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(routeEditorServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(routePhotoEditorServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(serverOptionsComponentServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(s57DBComponentServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(stlChartComponentServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(stlDBComponentServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(sonarServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(soundServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(toolsComponentServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(webViewServices.getDriver());
        instrumentDriverManagerServices.registerNewDriver(weatherComponentServices.getDriver());

        WebDriverManagerServices webDriverServices = componentManager.getComponentService(WebDriverManagerServices.class);
        //   webDriverServices.init("http://sextant.ifremer.fr/geonetwork/srv/fre/csw?SERVICE=CSW&REQUEST=GetCapabilities&VERSION=2.0.2");
        webDriverServices.registerNewDriver(wmsServices.getDriver());

        //Loading S57 catalog
        s57GlobalCatalogServices.load(DATA_S57_CATALOG_1,
                DATA_S57_CATALOG_2,
                DATA_S57_CATALOG_3,
                DATA_S57_CATALOG_4,
                DATA_S57_CATALOG_5,
                DATA_S57_CATALOG_6);

        wwd.getView().setEyePosition(Position.fromDegrees(48.40, -4.4853, 120000));
        /*
        // Get the current elevation model.
            ElevationModel currentElevationModel = wwd.getModel().getGlobe().getElevationModel();
            // Wrap it with the no-bathymetry elevation model.
            BathymetryFilterElevationModel noDepthModel = new BathymetryFilterElevationModel(currentElevationModel);
            // Have the globe use the no-bathymetry elevation model.
            wwd.getModel().getGlobe().setElevationModel(noDepthModel);
         */

        // Initialisation du serveur
        dataServerServices.init("localhost", 8585);

        /* Instanciation d'un client */
        nmeaClientServices.open("localhost", 8585);//Attention même valeurs que le serveur !
        nmeaClientServices.request(500); // periode

        /* Test connexion GPS / AIS */
        // dataServerServices.openSerialPort("COM5", 4800, 8, 1, 0);
        // dataServerServices.openSerialPort("COM4", 4800, 8, 1, 0);
        //   dataServerServices.openSerialPort("/dev/ttyUSB0", 4800, 8, 1, 0);
        //dataServerServices.openSerialPort("/dev/ttyACM1", 38400, 8, 1, 0);
        /* Test connexion Gpsd */
 /*
        Fulup 12 avril 2016
        Pour info, le service de simulation AIS de Sinagot.net est toujours dispo 
        mais les ports ont changés.
        - tcp://sinagot.net:5001 Simulateur AIS+NMEA
        - tcp://sinagot.net:5002 Simulateur NMEA
        - tcp://sinagot.net:5003 Simulateur AIS
        - gpsd://sinagot.net:2947 AIS zone Bretagne 
         */
        // dataServerServices.openGpsd("sinagot.net", 5947);
        // dataServerServices.openGpsd("sinagot.net", 2947);//OK
        // dataServerServices.openGpsd("sinagot.net", 5003);
        // dataServerServices.openGpsd("sinagot.net", 5121);
        //dataServerServices.openGpsd("hd-sf.com", 9009);

        /* Test serveur Web Http */
        // dataServerServices.openHttpServer("localhost", 8181);
        /* Test clients à l'écoute des événements Nmea */
        // aisServices.on();
        //  aisLoggerServices.on();
        // aisPlotterServices.on();
        //aisRadarServices.on();
        //gpsLoggerServices.on("data/nmea/test2.txt");
        //gpsPlotterServices.on();

        /* Test Bezier, approximation trajectoire */
 /*List<Pair<Double, Double>> data = bezier2DServices.readCsv("data/saved/", "savedPath.csv");
         bezier2DServices.toKML("path.kml", data);

         List<Pair<Double, Double>> bezSi = bezier2DServices.leastSquare(data, 8);
         List<Pair<Double, Double>> bez = bezier2DServices.compute(bezSi, 0.01);
         bezier2DServices.toKML("data/kml/", "testBezier.kml", bez, "5000FF14", "2");
        
         // La liste headings est utile si on souhaite récupérer le cap en chaque point de la trajectoire
         // sous la forme [[Lat, Lon], heading]
         // si ce n'est pas nécessaire mettre null
         List<Pair<Pair<Double, Double>, Double>> headings = new ArrayList<>();
         List<Pair<Pair<Double, Double>, Pair<Double, Double>>> vectorTg
         = bezier2DServices.vectorTangentCompute(bezSi, 0.01, 0.5,  headings);
         bezier2DServices.toKML2("data/kml/", "testTgBezier.kml", vectorTg, "5014F0FF", "2");
         System.out.println(headings);*/

 /* Test CPA zone et reconnaissance de trajectoire */
        // dataServerServices.openFile("data/ais/ais.txt");  //AIS
        /* Test cibles AIS en direct */
        // dataServerServices.openGpsd("sinagot.net", 5947);
        //dataServerServices.openGpsd("fridu.net", 2947);

        /* Test DB */
        //testDBServices.connectDerby("data/databases/TestJdbcDB", "navisu", "!!navisu??");
        // testDBServices.runJdbcDerby();//OK
        //String dbName, String hostName, String protocol, String port, String driverName, String userName, String passwd
        // testDBServices.connectDerby("inpolygons", "localhost", "jdbc:mysql://", "3306", "com.mysql.jdbc.Driver", "root", "lithops");
        // testDBServices.runJdbcMySql();
        //Pas de connectDerby() pour JPA, la DB est NavisuDB dans data/databases
        //Modifier le mode ddl classe DatabaseImpl avant le test
        //testDBServices.runJPA();//OK
        // Tests Neo4J
        // Neo4J embedded
        // Pas de connectDerby() pour GraphDB, la DB est TestNeo4JDB dans data/databases
        // testDBServices.runEmbeddedNeo4J("data/databases/TestNeo4JDB");
        // Neo4J serveur externe
        // Connection con = testDBServices.connectDerby("localhost", "jdbc:neo4j://", "7474", "org.neo4j.jdbc.Driver", "root", "lithops");
        // System.out.println("con : " + con);
        //
        //bathymetryDBServices.connectDerby("BathyShomDB", "localhost", "jdbc:postgresql://",
        //          "5432", "org.postgresql.Driver", "admin", "admin");
        // bathymetryDBServices.create("/home/serge/Data/bathymetry/data/shom/MNT100M_ATL/Atlantique_100_WGS84_prof_positive.glz");
        //bathymetryDBServices.createIndex();
        /* Test speech */
        //speakerServices.read("data/text", "installation.txt", null);// local par defaut
        //  speakerServices.read("data/text", "installation.txt", "fr_FR");//en_GB, en_US
        // speakerServices.read("naVisu est un logiciel de visualisation et de simulation de données maritimes.");//OK
        /* Test  ontology  DataAccess */
        //dataAccessServices.test();//OK
        /* Test Exif file reading from jpg photo : Test OK*/
        // Metadata read and creation of a Exif object
        /*
         String NAME = "LaGrandeVinotiere_1510";
         Exif exif0 = exifComponentServices.create("data/photos/" + NAME + ".jpg");
         // Save it in xml file
         try {
         ImportExportXML.exports(exif0, "data/photos/" + NAME + ".xml");
         } catch (JAXBException | FileNotFoundException ex) {
         Logger.getLogger(AppMain.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
        // Load from a xml file
        /*
         Exif exif1 = new Exif();
         try {
         exif1 = ImportExportXML.imports(exif1, new File("data/photos/" + NAME + ".xml"));
         } catch (JAXBException | FileNotFoundException ex) {
         Logger.getLogger(AppMain.class.getName()).log(Level.SEVERE, null, ex);
         }
         System.out.println(exif1);
         */
        // Test Navigation  Communication with external client 
        // navigationServerServices.init(9090);
        // Start Leap Motion 
        // leapMotionComponentServices.on();
        // Test Gazeteer services
        /* Decommenter si l'indexation n'a pas été faite. (1 fois)
         Telecharger les donnees . 
         http://download.geonames.org/export/dump/allCountries.zip
         */
 /*
         String gazeteerPath = "/home/serge/Data/allCountries/allCountries.txt";
         String indexPath = "/home/serge/Data/allCountries/geoIndex";
         gazeteerComponentServices.buildIndex(gazeteerPath, indexPath, true);
         */
        // Si un index est creee 
        /*
         Location location = gazeteerComponentServices.searchGeoName("TOULOUSE", "FR");
        if (location != null) {
            wwd.getView().setEyePosition(Position.fromDegrees(location.getLatitude(), location.getLongitude(), 15000));
        }
         */
 /*
         /*
       // Test load all S57 from one category of scale in DB
        String ENC_HOME = "/home/serge/Data/cartography/data/ENC/FR";
        String S57_DB = "s57NP5DB";
        String country = "FR";
        String version = "000";
        String EPSG = "4326";
        //List files filtered
        List<Path> paths = chartS57ComponentServices.getFilePaths(ENC_HOME, DATA_S57_CATALOG_5, country, version);
        //Create files at data/shp/... first, with ogr2ogr
        //Load in DB with ogr2ogr
        chartS57ComponentServices.loadDataBase(paths, S57_DB, EPSG);
         */
 /* 
        //Test recherche balisage dans la DB
        s57DBComponentServices.on("ReqDbS57_OFF");//Activation s57DB services, pas d'affichage de l'IHM
        Connection connection = databaseServices.connect("s57NP5DB",
                "localhost", "jdbc:postgresql://", "5432", "org.postgresql.Driver", "admin", "admin");
        List<Buoyage> buoyages = s57DBComponentServices.retrieveBuoyagesIn(connection, 48.338745, -4.575862, 10000);
        RenderableLayer layer = layersManagerServices.getLayer("S57 charts", "BUOYAGE");
        s57ChartComponentServices.s57BuoyageView(layer, buoyages);
         */
        //test cartographic services Lambert93
        /*
        Pt3D pt = lambertServices.convertToWGS84Deg(74962.5, 6750037.5, Lambert93);
        System.out.println("pt : " + pt);
         */
        //Test new viewer services of STL
        // stlComponentServices.viewSTL("../privateData/stl/out.stl");
// Stop Applicaton 
        stage.setOnCloseRequest(e -> {
            LOGGER.info("Stop Application.........");
            LOGGER.info("Databases closed");
            databaseServices.close();
            LOGGER.info("Components Stopped");
            ComponentManager.componentManager.stopApplication();
            LOGGER.info("System exit");
            System.exit(0);
        });
    }

    public static void main(String[] args) throws Exception {
        String userDirPath = System.getProperty("user.dir");
        String dir = "";
        if (OS.isWindows()) {
            dir = "/lib-external/natives/win/x64";
        } else if (OS.isLinux()) {
            dir = "/lib-external/natives/linux/x64";
        } else {
            System.out.println("OS not supported");
        }
        Configuration.addLibraryPath(userDirPath + dir);
        Application.launch();
    }

    private void clearTmpDirs(String dir, String extension, boolean rmDir) {
        try {
            Path directory = Paths.get(dir);

            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().endsWith(extension)) {
                        Files.delete(file);
                    }
                    if (extension.equals("*")) {
                        Files.delete(file);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (rmDir == true) {
                        Files.delete(dir);
                    }
                    return FileVisitResult.CONTINUE;
                }

            });
        } catch (IOException ex) {
            //Nothing if dir don't exist
        }
    }
}
