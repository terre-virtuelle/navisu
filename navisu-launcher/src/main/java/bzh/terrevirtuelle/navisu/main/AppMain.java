package bzh.terrevirtuelle.navisu.main;

import bzh.terrevirtuelle.navisu.agents.media.MediaServices;
import bzh.terrevirtuelle.navisu.agents.media.impl.MediaImpl;
import bzh.terrevirtuelle.navisu.app.ddriver.impl.DDriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.dpagent.impl.DpAgentImpl;
import bzh.terrevirtuelle.navisu.app.drivers.DriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.impl.DriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.impl.GuiAgentImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.I18nLangEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator;
import bzh.terrevirtuelle.navisu.bathymetry.catalog.local.BathymetryLocalCatalogServices;
import bzh.terrevirtuelle.navisu.bathymetry.catalog.local.impl.BathymetryLocalCatalogImpl;
import bzh.terrevirtuelle.navisu.bathymetry.charts.BathymetryServices;
import bzh.terrevirtuelle.navisu.bathymetry.charts.impl.BathymetryImpl;
import bzh.terrevirtuelle.navisu.charts.raster.geotiff.GeoTiffChartServices;
import bzh.terrevirtuelle.navisu.charts.raster.geotiff.impl.GeoTiffChartImpl;
import bzh.terrevirtuelle.navisu.charts.raster.kap.KapChartServices;
import bzh.terrevirtuelle.navisu.charts.raster.kap.impl.KapChartImpl;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.S57GlobalCatalogServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.impl.S57GlobalCatalogImpl;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.local.S57LocalCatalogServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.S57ChartImpl;
import bzh.terrevirtuelle.navisu.client.nmea.NmeaClientServices;
import bzh.terrevirtuelle.navisu.client.nmea.impl.vertx.NmeaClientImpl;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.currents.CurrentsServices;
import bzh.terrevirtuelle.navisu.currents.impl.CurrentsImpl;
import bzh.terrevirtuelle.navisu.gpx.GpxObjectServices;
import bzh.terrevirtuelle.navisu.gpx.impl.GpxObjectImpl;
import bzh.terrevirtuelle.navisu.grib.GribServices;
import bzh.terrevirtuelle.navisu.grib.impl.GribImpl;
import bzh.terrevirtuelle.navisu.kml.KmlObjectServices;
import bzh.terrevirtuelle.navisu.kml.impl.KmlObjectImpl;
import bzh.terrevirtuelle.navisu.server.DataServerServices;
import bzh.terrevirtuelle.navisu.server.impl.vertx.DataServerImpl;
import bzh.terrevirtuelle.navisu.widgets.Widget3DServices;
import bzh.terrevirtuelle.navisu.locators.impl.Widget3DImpl;
import bzh.terrevirtuelle.navisu.loggers.LoggerServices;
import bzh.terrevirtuelle.navisu.loggers.impl.LoggerImpl;
import bzh.terrevirtuelle.navisu.magnetic.MagneticServices;
import bzh.terrevirtuelle.navisu.magnetic.impl.MagneticImpl;
import bzh.terrevirtuelle.navisu.radar.RadarServices;
import bzh.terrevirtuelle.navisu.radar.impl.RadarImpl;
import bzh.terrevirtuelle.navisu.sedimentology.SedimentologyServices;
import bzh.terrevirtuelle.navisu.sedimentology.impl.SedimentologyImpl;
import bzh.terrevirtuelle.navisu.shapefiles.ShapefileObjectServices;
import bzh.terrevirtuelle.navisu.shapefiles.impl.ShapefileObjectImpl;
import gov.nasa.worldwind.geom.Position;
import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * @author Serge Morvan <morvan at enib.fr>
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 * @author Dominique Marques <dom.marques at free.fr>
 */
public class AppMain extends Application {

    private static final Logger LOGGER = Logger.getLogger(AppMain.class.getName());

    @Override
    public void start(Stage stage) throws Exception {

        // initialize translation
        Translator.setLang(I18nLangEnum.FRENCH);

        // initialize logging
        LogManager.getLogManager().readConfiguration(new FileInputStream("conf/logging.properties"));

        final ComponentManager componentManager = ComponentManager.componentManager;

        // deploy components
        LOGGER.info("\n"
                + componentManager.startApplication(
                        DpAgentImpl.class,
                        GuiAgentImpl.class,
                        DriverManagerImpl.class,
                        KapChartImpl.class,
                        GribImpl.class,
                        S57ChartImpl.class,
                        S57GlobalCatalogImpl.class,
                        GeoTiffChartImpl.class,
                        ShapefileObjectImpl.class,
                        CurrentsImpl.class,
                        BathymetryImpl.class,
                        BathymetryLocalCatalogImpl.class,
                        KmlObjectImpl.class,
                        GpxObjectImpl.class,
                        DataServerImpl.class,
                        NmeaClientImpl.class,
                        Widget3DImpl.class,
                        MediaImpl.class,
                        DDriverManagerImpl.class,
                        MagneticImpl.class,
                        SedimentologyImpl.class,
                        RadarImpl.class,
                        LoggerImpl.class
                )
        );

        GuiAgentServices guiAgentServices = componentManager.getComponentService(GuiAgentServices.class);
        guiAgentServices.showGui(stage, 1080, 700);
        RadarServices radarServices = componentManager.getComponentService(RadarServices.class);
      //  radarServices.on();
        KapChartServices chartsServices = componentManager.getComponentService(KapChartServices.class);
        GribServices gribServices = componentManager.getComponentService(GribServices.class);
        S57LocalCatalogServices catalogS57Services = componentManager.getComponentService(S57LocalCatalogServices.class);
        S57GlobalCatalogServices s57GlobalCatalogServices = componentManager.getComponentService(S57GlobalCatalogServices.class);
        S57ChartServices chartS57Services = componentManager.getComponentService(S57ChartServices.class);
        GeoTiffChartServices geoTiffChartServices = componentManager.getComponentService(GeoTiffChartServices.class);
        ShapefileObjectServices shapefileObjectServices = componentManager.getComponentService(ShapefileObjectServices.class);
        CurrentsServices currentsServices = componentManager.getComponentService(CurrentsServices.class);
        BathymetryServices bathymetryServices = componentManager.getComponentService(BathymetryServices.class);
        BathymetryLocalCatalogServices bathymetryLocalCatalogServices = componentManager.getComponentService(BathymetryLocalCatalogServices.class);
        KmlObjectServices kmlObjectServices = componentManager.getComponentService(KmlObjectServices.class);
        GpxObjectServices gpxObjectServices = componentManager.getComponentService(GpxObjectServices.class);
        MediaServices mediaServices = componentManager.getComponentService(MediaServices.class);
        MagneticServices magneticServices = componentManager.getComponentService(MagneticServices.class);
        SedimentologyServices sedimentologyServices = componentManager.getComponentService(SedimentologyServices.class);

        DriverManagerServices driverServices = componentManager.getComponentService(DriverManagerServices.class);
        driverServices.init();
        driverServices.registerNewDriver(chartS57Services.getDriver());
        driverServices.registerNewDriver(s57GlobalCatalogServices.getDriver());
        driverServices.registerNewDriver(chartsServices.getDriver());
        driverServices.registerNewDriver(gribServices.getDriver());
        driverServices.registerNewDriver(geoTiffChartServices.getDriver());
        driverServices.registerNewDriver(currentsServices.getDriver());
        driverServices.registerNewDriver(bathymetryServices.getDriver());
        driverServices.registerNewDriver(bathymetryLocalCatalogServices.getDriver());
        driverServices.registerNewDriver(kmlObjectServices.getDriver());
        driverServices.registerNewDriver(gpxObjectServices.getDriver());
        driverServices.registerNewDriver(mediaServices.getDriver());
        driverServices.registerNewDriver(magneticServices.getDriver());
        driverServices.registerNewDriver(sedimentologyServices.getDriver());
        driverServices.registerNewDriver(shapefileObjectServices.getDriver());

        //   DDriverManagerServices ddriverServices = componentManager.getComponentService(DDriverManagerServices.class);
        //    ddriverServices.init();
        //    ddriverServices.registerNewDriver(catalogS57Services.getDriver());

        /*----Brest---*/
        GeoWorldWindViewImpl.getWW().getView().setEyePosition(Position.fromDegrees(48.40, -4.4853, 15000));

        DataServerServices dataServerServices = componentManager.getComponentService(DataServerServices.class);
        // Initialisation des paramtètres de diffusion des data.
        dataServerServices.init("localhost", 8080);

        // Test connexion GPS 
        // dataServerServices.openSerialPort("COM5", 4800, 8, 1, 0);
        // dataServerServices.openSerialPort("COM4", 4800, 8, 1, 0);
        // Test connexion Gpsd 
        //dataServerServices.openGpsd("sinagot.net", 2947); // ou "fridu.net"
        // dataServerServices.openGpsd("sinagot.net", 4001); 
        // dataServerServices.openGpsd("hd-sf.com", 9009);
        // A tester, ref OCPN
        //tcp://sinagot.net:4002 NMEA/GPRMC
        //tcp://sinagot.net:4003 AIS 
        // Test connexion fichier 
        // dataServerServices.openFile("data/nmea/gpsLostennic.txt"); //NMEA0183 //gps.txt
        dataServerServices.openFile("data/ais/ais.txt");  //AIS
        // dataServerServices.openFile("data/gpsd/gpsd.txt");//AIS Gpsd
        //dataServerServices.openFile("data/n2k/out1.json");//N2K
        //dataServerServices.openFile("data/n2k/sample.json");//N2K

        // Test serveur Web Http 
        // dataServerServices.openHttpServer("localhost", 8181);
        // Test instanciation d'un client 
        NmeaClientServices nmeaClientServices = componentManager.getComponentService(NmeaClientServices.class);
        nmeaClientServices.open("localhost", 8080);
        nmeaClientServices.request(500);

        // Test clients à l'écoute des événements Nmea 
        Widget3DServices widgetServices = componentManager.getComponentService(Widget3DServices.class);
     //   widgetServices.createGpsLocator();
        widgetServices.createAisLocator();

        LoggerServices loggerServices = componentManager.getComponentService(LoggerServices.class);
        // loggerServices.createPrinter(new NMEA());

    }
    public static void main(String[] args) throws Exception {
        Application.launch();
    }
}
