package bzh.terrevirtuelle.navisu.main;

import bzh.terrevirtuelle.navisu.app.dpagent.impl.DpAgentImpl;
import bzh.terrevirtuelle.navisu.app.drivers.DriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.impl.DriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.impl.GuiAgentImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.I18nLangEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator;
import bzh.terrevirtuelle.navisu.charts.raster.kap.KapChartServices;
import bzh.terrevirtuelle.navisu.charts.raster.kap.impl.KapChartImpl;
import bzh.terrevirtuelle.navisu.charts.vector.s57.S57ChartServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.S57ChartImpl;
import bzh.terrevirtuelle.navisu.client.nmea.NmeaClientServices;
import bzh.terrevirtuelle.navisu.client.nmea.impl.vertx.NmeaClientImpl;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.grib.GribServices;
import bzh.terrevirtuelle.navisu.grib.impl.GribImpl;
import bzh.terrevirtuelle.navisu.kml.misc.KmlObjectServices;
import bzh.terrevirtuelle.navisu.kml.misc.impl.KmlObjectImpl;
import bzh.terrevirtuelle.navisu.server.DataServerServices;
import bzh.terrevirtuelle.navisu.server.impl.vertx.DataServerImpl;
import bzh.terrevirtuelle.navisu.locators.Widget3DServices;
import bzh.terrevirtuelle.navisu.locators.impl.Widget3DImpl;
import bzh.terrevirtuelle.navisu.loggers.LoggerServices;
import bzh.terrevirtuelle.navisu.loggers.impl.LoggerImpl;
import gov.nasa.worldwind.geom.Position;
import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
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
                        KmlObjectImpl.class,
                        DataServerImpl.class,
                        NmeaClientImpl.class,
                        Widget3DImpl.class,
                        LoggerImpl.class
                )
        );

        GuiAgentServices guiAgentServices = componentManager.getComponentService(GuiAgentServices.class);
        guiAgentServices.showGui(stage, 1280, 800);

        KapChartServices chartsServices = componentManager.getComponentService(KapChartServices.class);
        GribServices gribServices = componentManager.getComponentService(GribServices.class);
        S57ChartServices chartS57Services = componentManager.getComponentService(S57ChartServices.class);
        KmlObjectServices kmlObjectServices = componentManager.getComponentService(KmlObjectServices.class);

        DriverManagerServices driverServices = componentManager.getComponentService(DriverManagerServices.class);
        driverServices.init();
        
        driverServices.registerNewDriver(chartsServices.getDriver());
        driverServices.registerNewDriver(gribServices.getDriver());
        driverServices.registerNewDriver(chartS57Services.getDriver());
        driverServices.registerNewDriver(kmlObjectServices.getDriver());
        
         //------------------------------->
        // TESTS AGENT
        //
  /*      
         GObjectCUDProcessor proc = new TObjectProcessor();
  
         GeoViewServices geoViewServices = componentManager.getComponentService(GeoViewServices.class);
         geoViewServices.registerProcessor(proc);
         geoViewServices.getLayerManager().insertGeoLayer(proc.getLayer());

         DpAgentServices dpAgentServices = componentManager.getComponentService(DpAgentServices.class);

         guiServices.getJobsManager().newJob("Test job", pHandler -> {

         double lat = 48.390834d;
         double lon = -4.485556d;

         TObject tObject = TObject.newBasicTObject(1, lat, lon);
         dpAgentServices.create(tObject);

         pHandler.start(100);

         for (int i = 0; i < 100; i++) {

         lon += i / 1000.;
         tObject.setLocation(Location.factory.newLocation(lat, lon));
         dpAgentServices.update(tObject);

         pHandler.progress("Moving TObject...", i);

         try {
         Thread.sleep(500);
         } catch (InterruptedException e) {
         }
         }

         dpAgentServices.delete(tObject);
         });
         */
        //
        // END TESTS AGENT
        //------------------------------->
        //------------------------------->
        // TESTS SERVER
        //
        // Hack pendant le dev
        /*----Brest---*/
        GeoWorldWindViewImpl.getWW().getView().setEyePosition(Position.fromDegrees(48.40, -4.4853, 15000));
        /*----Gruissan---*/
        //GeoWorldWindViewImpl.getWW().getView().setEyePosition(Position.fromDegrees(43.14, 3.14, 5000));
        
        DataServerServices dataServerServices = componentManager.getComponentService(DataServerServices.class);

        // Initialisation des paramtètres de diffusion des data.
        dataServerServices.init("localhost", 8080);

        // Test connexion GPS 
        // dataServerServices.openSerialPort("COM5", 4800, 8, 1, 0);
        // dataServerServices.openSerialPort("COM4", 4800, 8, 1, 0);
        
        // Test connexion Gpsd 
        // dataServerServices.openGpsd("sinagot.net", 2947); // ou "fridu.net"
        // dataServerServices.openGpsd("hd-sf.com", 9009);
        
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
        widgetServices.createGpsLocator();
        widgetServices.createAisLocator();

        LoggerServices loggerServices = componentManager.getComponentService(LoggerServices.class);
       // loggerServices.createPrinter(new NMEA());

        //
        // END TESTS SERVER
        //------------------------------->
        
        //
        // TEST S57 Charts
        //
        /*
        chartS57Services.loadFile("I:\\cartes\\SHOM_OpenData\\S57_OPENDATA_IROISEE\\S57_OPENDATA_IROISEE\\FR571220\\FR571220.000");
        chartS57Services.addCoastlines();
        chartS57Services.addDepthAreas();
                */
        //
        // END TEST S57 Charts
        //
    }

    public static void main(String[] args) throws Exception {
        Application.launch();
    }
}
