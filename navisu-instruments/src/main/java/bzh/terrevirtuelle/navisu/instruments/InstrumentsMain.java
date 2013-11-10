/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments;

import bzh.terrevirtuelle.navisu.instruments.controller.InstrumentsGlassPaneController;
import bzh.terrevirtuelle.navisu.instruments.controller.simulator.CompassSimulator;
import bzh.terrevirtuelle.navisu.instruments.controller.simulator.GPSSimulator;
import bzh.terrevirtuelle.navisu.instruments.controller.simulator.SounderSimulator;
import bzh.terrevirtuelle.navisu.instruments.view.compass.Compass;
import bzh.terrevirtuelle.navisu.instruments.view.gps.GPS;
import bzh.terrevirtuelle.navisu.instruments.view.gps.PrintGPSHandler;
import bzh.terrevirtuelle.navisu.instruments.view.sounder.Sounder;
import bzh.terrevirtuelle.navisu.widgets.controller.WidgetsGlassPaneController;
import bzh.terrevirtuelle.navisu.widgets.model.Widget;
import bzh.terrevirtuelle.navisu.widgets.view.button.StopWidget;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Dom
 */
public class InstrumentsMain extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        StackPane root = new StackPane();

        GPS gps = new GPS(new PrintGPSHandler());
        new GPSSimulator(gps, 48.43771f, -4.4977236f, 0.0005f).execute();
        root.getChildren().add(gps);

        /*
        Sounder sounder = new Sounder();
        new SounderSimulator(sounder, 5000.0f, 3.0f).execute();
        root.getChildren().add(sounder);
        */

        Compass compass = new Compass();
        new CompassSimulator(compass).execute();
        root.getChildren().add(compass);

        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    static void tmp() {
        InstrumentsGlassPaneController intrumentsGlassPaneController = new InstrumentsGlassPaneController(1200, 1000);
        WidgetsGlassPaneController widgetsGlassPaneController = new WidgetsGlassPaneController(1200, 1000);

        GPS gps = new GPS(new PrintGPSHandler());
        intrumentsGlassPaneController.addInstrument(gps);
        intrumentsGlassPaneController.startParallelTransition(gps, 0f, 0f, 300f, 300f, 0.0f, 0.0f, 1.f, 1.f);
        new GPSSimulator(gps, 48.43771f, -4.4977236f, 0.0005f).execute();
                /*
                 WRadialMenu wRadialMenu = new WRadialMenu(intrumentsGlassPaneController);
                 widgetsGlassPaneController.add(wRadialMenu);
                 widgetsGlassPaneController.startParallelTransition(wRadialMenu, 0f, 0f, 100f, 200f, 0.0f, 0.0f, 1.f, 1.f);
                 intrumentsGlassPaneController.add(wRadialMenu);
                 intrumentsGlassPaneController.startParallelTransition(wRadialMenu, 0f, 0f, 100f, 200f, 0.0f, 0.0f, 1.f, 1.f);
                 */
        Sounder sounder = new Sounder();
        intrumentsGlassPaneController.addInstrument(sounder);
        intrumentsGlassPaneController.startParallelTransition(sounder, 0f, 0f, 200f, 200f, 0.0f, 0.0f, 1.f, 1.f);
        new SounderSimulator(sounder, 5000.0f, 3.0f).execute();
                /*
                 Compass compass = new Compass();
                 intrumentsGlassPaneController.addInstrument(compass);
                 intrumentsGlassPaneController.startParallelTransition(compass, 0f, 0f, 100f, 100f, 0.0f, 0.0f, 1.f, 1.f);
                 new CompassSimulator(compass).execute();

                 SimpleURL urlWidget = new SimpleURL("http://www.previmer.org/", 900, 800);
                 SimpleURL urlWidget = new SimpleURL("http://www.ruwenzori.net/earth/Earth_EMEA+Atlantic_800x600.html", 900, 800);
                 */
        //     SimpleURL urlWidget = new SimpleURL("http://www.marinetraffic.com/ais/", 900, 800);
        //    widgetsGlassPaneController.add(urlWidget);
        //    widgetsGlassPaneController.startParallelTransition(urlWidget, 0f, 0f, 100f, 200f, 0.0f, 0.0f, 1.f, 1.f);

              /*
                 Widget button = new ButtonWidget();
                 widgetsGlassPaneController.add(button);
                 widgetsGlassPaneController.startParallelTransition(button, 0f, 0f, 100f, 100f, 0.0f, 0.0f, 1.f, 1.f);
*/

        Widget stopW = new StopWidget();
        widgetsGlassPaneController.add(stopW);
        widgetsGlassPaneController.startParallelTransition(stopW, 0f, 0f, 100f, 100f, 0.0f, 0.0f, 1.f, 1.f);
                /*
                 Widget cube3D = new Cube3D();
                 widgetsGlassPaneController.add(cube3D);
                 widgetsGlassPaneController.startParallelTransition(cube3D, 0f, 0f, 100f, 100f, 0.0f, 0.0f, 1.f, 1.f);
                 */
                /*
                 GoogleWheater googleWheater = new GoogleWheater();
                 widgetsGlassPaneController.add(googleWheater);
                 widgetsGlassPaneController.startParallelTransition(googleWheater, 0f, 0f, 100f, 200f, 0.0f, 0.0f, 1.f, 1.f);
                 */
        //  OpenWheaterMap openWheaterMap = new OpenWheaterMap();
        //widgetsGlassPaneController.add(openWheaterMap);
        // widgetsGlassPaneController.startParallelTransition(openWheaterMap, 0f, 0f, 50f, 50f, 0.0f, 0.0f, 1.f, 1.f);
        // intrumentsGlassPaneController.add(openWheaterMap);
        // intrumentsGlassPaneController.startParallelTransition(openWheaterMap, 0f, 0f, 50f, 50f, 0.0f, 0.0f, 1.f, 1.f);
             /*
                 DigitalClock digitalClock = new DigitalClock();
                 intrumentsGlassPaneController.add(digitalClock);
                 intrumentsGlassPaneController.startParallelTransition(digitalClock, 0f, 0f, 100f, 200f, 0.0f, 0.0f, 1.f, 1.f);
                 */
                /*
                 ClockGauges clockGauges = new ClockGauges();
                 clockGauges.setScale(0.5);
                 intrumentsGlassPaneController.add(clockGauges);
                 intrumentsGlassPaneController.startParallelTransition(clockGauges, 0f, 0f, 0f, 200f, 0.0f, 0.0f, 1.f, 1.f);
                 */

        //   BuoyWidget buoyWidget = new BuoyWidget();
        //    intrumentsGlassPaneController.add(buoyWidget);
        //    intrumentsGlassPaneController.startParallelTransition(buoyWidget, 0f, 0f, 0f, 200f, 0.0f, 0.0f, 1.f, 1.f);

    }
}
