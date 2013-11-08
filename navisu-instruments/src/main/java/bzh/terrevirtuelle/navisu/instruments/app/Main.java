/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.app;

import javax.swing.SwingUtilities;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.navisu.instrument.controller.InstrumentsGlassPaneController;
import org.navisu.instrument.controller.simulator.GPSSimulator;
import org.navisu.instrument.controller.simulator.SounderSimulator;
import org.navisu.instrument.view.gps.GPS;
import org.navisu.instrument.view.gps.PrintGPSHandler;
import org.navisu.instrument.view.sounder.Sounder;
import org.navisu.widget.controller.WidgetsGlassPaneController;
import org.navisu.widget.model.Widget;
import org.navisu.widget.view.button.ButtonWidget;
import org.navisu.widget.view.button.StopWidget;

/**
 * @author Dom
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
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
