/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.view;

import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.ais.view.targets.ShipTypeColor;
import javafx.scene.shape.Circle;

/**
 *
 * @author Serge
 */
public class GRShipImpl
        extends Circle
        implements GRShip {

    private final Ship ship;

    public GRShipImpl(Ship ship, int x, int y, double radius) {
        this.ship = ship;
        setCenterX(x);
        setCenterY(y);

        setRadius(radius);
        setFill(ShipTypeColor.COLOR.get(ship.getShipType()));
    }

    @Override
    public Ship getShip() {
        return ship;
    }
}
