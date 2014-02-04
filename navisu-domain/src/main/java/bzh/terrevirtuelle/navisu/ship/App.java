/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.ship;

import java.util.GregorianCalendar;

/**
 *
 * @author Serge
 */
public class App {

    public static void main(String[] args) {
        Ship ship = ShipBuilder.create()
                .eta(new GregorianCalendar())
                .cog(102)
                .build();
        System.out.println("Default ship : " + ship);
    }
}
