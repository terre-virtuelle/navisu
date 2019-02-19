/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.agents.ship;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import gov.nasa.worldwind.layers.RenderableLayer;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @date 10 avr. 2015
 * @author Serge Morvan
 */
public interface ShipAgentServices
        extends ComponentService {

    void init(Ship ship,RenderableLayer layer, String filename);

    void moveTo(Point3D point);

    //void loadShip3D(RenderableLayer layer, String filename);

    void setShip(Ship ship);

    Ship getShip();

    double getHeading();

    double getLatitude();

    double getLongitude();

    void setHeading(double angle);

    void setlatitude(double latitude);

    void setLongitude(double longitude);

    void setPosition(double latitude, double longitude);

    void setScale(double x, double y, double z);
}
