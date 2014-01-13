package bzh.terrevirtuelle.navisu.app;

import bzh.terrevirtuelle.navisu.app.dpagent.impl.DpAgentImpl;
import bzh.terrevirtuelle.navisu.app.drivers.charts.ChartsManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.charts.impl.ChartsManagerImpl;
import bzh.terrevirtuelle.navisu.app.drivers.DriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.impl.DriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.drivers.grib.GribServices;
import bzh.terrevirtuelle.navisu.app.drivers.grib.impl.GribImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.impl.GuiAgentImpl;

import bzh.terrevirtuelle.navisu.server.impl.vertx.DataServerImpl;
import bzh.terrevirtuelle.navisu.client.nmea.impl.vertx.NmeaClientImpl;

import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import bzh.terrevirtuelle.navisu.app.guiagent.utilities.I18nLangEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator;
import bzh.terrevirtuelle.navisu.client.nmea.NmeaClientServices;
import bzh.terrevirtuelle.navisu.server.DataServerServices;
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
                        ChartsManagerImpl.class,
                        GribImpl.class,
                        DataServerImpl.class,
                        NmeaClientImpl.class
                )
        );

        GuiAgentServices guiServices = componentManager.getComponentService(GuiAgentServices.class);
        guiServices.showGui(stage, 800, 500);

        ChartsManagerServices chartsServices = componentManager.getComponentService(ChartsManagerServices.class);
        GribServices gribServices = componentManager.getComponentService(GribServices.class);

        DriverManagerServices driverServices = componentManager.getComponentService(DriverManagerServices.class);
        driverServices.init();

        driverServices.registerNewDriver(chartsServices.getDriver());
        driverServices.registerNewDriver(gribServices.getDriver());

        /*
         //------------------------------->
         // TESTS AGENT
         //
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

         for(int i=0; i<100; i++) {

         lon += i/1000.;
         tObject.setLocation(Location.factory.newLocation(lat, lon));
         dpAgentServices.update(tObject);

         pHandler.progress("Moving TObject...", i);

         try {
         Thread.sleep(500);
         } catch (InterruptedException e) {}
         }

         dpAgentServices.delete(tObject);
         });
         //
         // END TESTS
         //------------------------------->
         */
        //------------------------------->
        // TESTS SERVER
        //
        DataServerServices dataServerServices = componentManager.getComponentService(DataServerServices.class);
        // Test avec choix des parametres de comm
        dataServerServices.init("localhost", 8080);

        /* Test connexion GPS */
        // dataServerServices.openSerialPort("COM5", 4800, 8, 1, 0);
        // dataServerServices.openSerialPort("COM4", 4800, 8, 1, 0);
        //dataServerServices.open();// Test avec les parametres dans properties/server.properties
        //dataServerServices.openSerialPort(); // idem
        /* Test connexion fichier */
       // dataServerServices.openFile("data/nmea/gps.txt");
       // dataServerServices.openFile("data/ais/ais.txt");
        dataServerServices.openFile("data/gpsd/gpsd.txt");
        // dataServerServices.openFile();// Test avec les parametres dans properties/server.properties

        /* Test instanciation d'un client */
        NmeaClientServices nmeaClientServices = componentManager.getComponentService(NmeaClientServices.class);
        // nmeaClientServices.open();// Test avec les parametres dans properties/client.properties
        nmeaClientServices.open("localhost", 8080, 100);
        nmeaClientServices.request();
        //
        // END TESTS
        //------------------------------->
    }

    public static void main(String[] args) throws Exception {
        Application.launch();
    }
}
