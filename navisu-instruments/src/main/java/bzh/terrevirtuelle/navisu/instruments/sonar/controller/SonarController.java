/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.sonar.controller;

import bzh.terrevirtuelle.navisu.bathymetry.controller.events.BathymetryEvent;
import bzh.terrevirtuelle.navisu.domain.bathymetry.model.Bathymetry;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.instruments.sonar.impl.SonarImpl;
import bzh.terrevirtuelle.navisu.widgets.sonar.sonar3D.Points3D;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * NaVisu
 *
 * @date 4 avr. 2015
 * @author Serge Morvan
 */
public class SonarController {

    ComponentManager cm = ComponentManager.componentManager;
    ComponentEventSubscribe<BathymetryEvent> bathyES = cm.getComponentEventSubscribe(BathymetryEvent.class);

    private SonarImpl sonar;
    private Stage stage;
    double minLat = 90.0;
    double maxLat = 0.0;
    double minLon = 0.0;
    double maxLon = -90.0;
    double minElevation = 10000.0;
    double maxElevation = -20.0;

    public SonarController(SonarImpl sonar, Stage stage) {
        this.stage = stage;
        this.sonar = sonar;

        subscribe();
    }

    private void subscribe() {
        bathyES.subscribe(new BathymetryEvent() {

            @Override
            public void notifyBathymetryMessageChanged(Bathymetry data) {
                if (data.size() != 0) {
                    List<Point3D> points = data.getGrid();
                    minLat = 90.0;
                    maxLat = 0.0;
                    minLon = 0.0;
                    maxLon = -90.0;
                    minElevation = 10000.0;
                    maxElevation = -20.0;
                    points.stream().map((p) -> {
                        double tmp = p.getLat();
                        if (tmp < minLat) {
                            minLat = tmp;
                        }
                        if (tmp > maxLat) {
                            maxLat = tmp;
                        }
                        tmp = p.getLon();
                        if (tmp < minLon) {
                            minLon = tmp;
                        }
                        if (tmp > maxLon) {
                            maxLon = tmp;
                        }
                        tmp = p.getElevation();
                        return tmp;
                    }).map((tmp) -> {
                        if (tmp < minElevation) {
                            minElevation = tmp;
                        }
                        return tmp;
                    }).filter((tmp) -> (tmp > maxElevation)).forEach((tmp) -> {
                        maxElevation = tmp;
                    });
                   // System.out.println(minLat + " " + maxLat + " " + minLon + " " + maxLon + " " + minElevation + " " + maxElevation);
                    List<javafx.geometry.Point3D> list = new ArrayList<>();
                    for (Point3D p : points) {
                        list.add(new javafx.geometry.Point3D(p.getLat(), p.getLon(), p.getElevation()));
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            new Points3D(stage, list,
                                    minLat, maxLat,
                                    minLon, maxLon,
                                    minElevation, maxElevation);
                        }

                    });
                }
            }
        });
    }

    /**
     * Get the value of sonar
     *
     * @return the value of sonar
     */
    public SonarImpl getSonar() {
        return sonar;
    }

    /**
     * Set the value of sonar
     *
     * @param sonar new value of sonar
     */
    public void setSonar(SonarImpl sonar) {
        this.sonar = sonar;
    }

}
