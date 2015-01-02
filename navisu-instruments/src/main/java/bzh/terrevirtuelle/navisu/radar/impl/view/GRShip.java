/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.radar.impl.view;

import bzh.terrevirtuelle.navisu.domain.ship.Ship;
import bzh.terrevirtuelle.navisu.locators.view.ShipTypeColor;
import javafx.scene.shape.Circle;

/**
 *
 * @author Serge
 */
public class GRShip extends Circle {

    private final Ship ship;

    public GRShip(Ship ship, int x, int y, double radius) {
        this.ship = ship;
        setCenterX(x);
        setCenterY(y);
        
        setRadius(radius);
        setFill(ShipTypeColor.COLOR.get(ship.getType()));
    }

    public Ship getShip() {
        return ship;
    }
}
