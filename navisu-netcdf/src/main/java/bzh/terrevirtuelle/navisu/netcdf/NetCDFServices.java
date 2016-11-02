/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf;

import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import gov.nasa.worldwind.layers.RenderableLayer;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author serge
 */
public interface NetCDFServices
        extends ComponentService {

    Driver getDriver();

    void loadFile(String path);

    int getLatitudeDimension();

    int getLongitudeDimension();

    int getTimeDimension();

    RenderableLayer getLayer();
}
