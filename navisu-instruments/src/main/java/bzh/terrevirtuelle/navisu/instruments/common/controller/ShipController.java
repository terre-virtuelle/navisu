/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.common.controller;

import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.domain.ship.model.ShipBuilder;
import java.util.Properties;

/**
 *
 * @author serge
 */
public class ShipController {

    public static Ship createOwnerShip(Properties properties) {
        int mmsi = 0;
        float lat = 0.0f;
        float lon = 0.0f;
        float cog = 0.0f;
        float sog = 0.0f;
        float width = 0.0f;
        float length = 0.0f;
        float draught = 0.0f;
        int shipType = 0;
        int navigationalStatus = 0;
        try {
            if (properties.getProperty("mmsi") != null) {
                mmsi = new Integer(properties.getProperty("mmsi"));
            }
            if (properties.getProperty("latitude") != null) {
                lat = new Float(properties.getProperty("latitude"));
            }
            if (properties.getProperty("longitude") != null) {
                lon = new Float(properties.getProperty("longitude"));
            }
            if (properties.getProperty("cog") != null) {
                cog = new Float(properties.getProperty("cog"));
            }
            if (properties.getProperty("sog") != null) {
                sog = new Float(properties.getProperty("sog"));
            }
            if (properties.getProperty("width") != null) {
                width = new Float(properties.getProperty("width"));
            }
            if (properties.getProperty("length") != null) {
                length = new Float(properties.getProperty("length"));
            }
            if (properties.getProperty("length") != null) {
                length = new Float(properties.getProperty("length"));
            }
            if (properties.getProperty("draught") != null) {
                draught = new Float(properties.getProperty("draught"));
            }
            if (properties.getProperty("length") != null) {
                length = new Float(properties.getProperty("length"));
            }
            if (properties.getProperty("shipType") != null) {
                shipType = new Integer(properties.getProperty("shipType"));
            }
            if (properties.getProperty("navigationalStatus") != null) {
                navigationalStatus = new Integer(properties.getProperty("navigationalStatus"));
            }
        } catch (NumberFormatException e) {
        }

        Ship ship = ShipBuilder.create()
                .mmsi(mmsi)
                .name(properties.getProperty("name"))
                .latitude(lat)
                .longitude(lon)
                .cog(cog)
                .sog(sog)
                //  .heading(new Float(properties.getProperty("heading")))
                .country(properties.getProperty("country"))
                .width(width)
                .length(length)
                .draught(draught)
                .shipType(shipType)
                .navigationalStatus(navigationalStatus)
                .callSign(properties.getProperty("callSign"))
                .target(true)
                .build();
        return ship;
    }

}
