/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.locators.ais.view;

import gov.nasa.worldwind.render.ShapeAttributes;

/**
 *
 * @author Serge
 */
public interface ShipView {

    void setCog(double angle);

    void setHeading(double angle);

    void setLatLon(double latitude, double longitude);

    void setLatitude(double latitude);

    void setLongitude(double longitude);

    void setAttributes(ShapeAttributes pathAttrs);

    void setType(int type);

}
