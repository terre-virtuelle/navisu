/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gpstrack.view.targets.impl;


import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.gpstrack.view.targets.Shape;

import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceCircle;
import javafx.scene.control.Button;

/**
 *
 * @author Serge
 */
public class Shape_0
        extends SurfaceCircle
        implements Shape {

    Button button;
    //  WorldWindow wwd;
    Ship ship;
    boolean first = true;

    public Shape_0(Ship ship, ShapeAttributes sa, LatLon latlon, double d) {
        super(sa, latlon, d);
        this.ship = ship;
        //  wwd = GeoWorldWindViewImpl.getWW();
        // pick();
    }

    @Override
    public void setPosition(Position position) {
        moveTo(position);
    }

    @Override
    public void setRotation(double cog) {
        // Nothing todo
    }

    @Override
    public Renderable[] getRenderables() {

        return new Renderable[]{this};
    }

    @Override
    public String toString() {
        return "Shape_1{" + super.toString() + '}';
    }
    /*
     private void pick() {
     wwd.addSelectListener((SelectEvent event) -> {
     if (event.getEventAction().equals(SelectEvent.HOVER) && event.getObjects() != null) {
     if (first == true) {
     first = false;
     if (button == null) {
     button = new Button();
     }
     button.setText("       Quit        ");
     button.setOnAction((ActionEvent evt) -> {
     System.exit(0);
     });
                    
     offset = wwd.getView().project(
     wwd.getModel().getGlobe().computePointFromLocation(
     new LatLon(Angle.fromDegrees(lat), Angle.fromDegrees(lon))));
     Platform.runLater(() -> {
     pane.getChildren().add(button);
     wwd.addPositionListener(Ship.this);
     });
     }
     }
     });
     }
     */

    @Override
    public Ship getShip() {
        return ship;
    }
}
