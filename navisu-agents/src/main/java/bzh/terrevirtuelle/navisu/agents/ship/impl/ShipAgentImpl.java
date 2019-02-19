/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.agents.ship.impl;

import bzh.terrevirtuelle.navisu.agents.ship.ShipAgent;
import bzh.terrevirtuelle.navisu.agents.ship.ShipAgentServices;
import bzh.terrevirtuelle.navisu.agents.ship.impl.controller.ShipAgentController;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.kml.KmlComponentServices;
import gov.nasa.worldwind.layers.RenderableLayer;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * NaVisu
 *
 * @date 17 avr. 2015
 * @author Serge Morvan
 */
public class ShipAgentImpl
        implements ShipAgent, ShipAgentServices, ComponentState {

    @UsedService
    KmlComponentServices kmlComponentServices;
    ShipAgentController controller;
    Ship ship;
    RenderableLayer layer;

    @Override
    public void moveTo(Point3D point) {
        kmlComponentServices.moveTo(point);
    }

    @Override
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @Override
    public Ship getShip() {
        return ship;
    }

    @Override
    public void init(Ship ship, RenderableLayer layer, String filename) {
        this.ship = ship;
        this.layer = layer;
        controller = new ShipAgentController(kmlComponentServices);
        kmlComponentServices.openColladaFile(layer, filename);
    }

    @Override
    public double getHeading() {
        return kmlComponentServices.getHeading();
    }

    @Override
    public double getLatitude() {
        return kmlComponentServices.getLatitude();
    }

    @Override
    public double getLongitude() {
        return kmlComponentServices.getLongitude();
    }

    @Override
    public void setHeading(double angle) {
        kmlComponentServices.setHeading(angle);
    }

    @Override
    public void setlatitude(double latitude) {
        kmlComponentServices.setlatitude(latitude);
    }

    @Override
    public void setLongitude(double longitude) {
        kmlComponentServices.setLongitude(longitude);
    }

    @Override
    public void setPosition(double latitude, double longitude) {
        kmlComponentServices.setPosition(latitude, longitude);
    }

    @Override
    public void setScale(double x, double y, double z) {
        kmlComponentServices.setScale(x, y, z);
    }

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
