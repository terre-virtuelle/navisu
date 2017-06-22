package bzh.terrevirtuelle.navisu.kml;

import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.ogc.collada.ColladaRoot;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public interface KmlComponentServices
        extends ComponentService {

    Driver getDriver();

    void openFile(String file);

    ColladaRoot openColladaFile(RenderableLayer layer, String filename);

    double getHeading();

    double getLatitude();

    double getLongitude();

    void setHeading(double angle);

    void setlatitude(double latitude);

    void setLongitude(double longitude);

    void setPosition(double latitude, double longitude);
    
    void setScale(double x, double y, double z);
}
