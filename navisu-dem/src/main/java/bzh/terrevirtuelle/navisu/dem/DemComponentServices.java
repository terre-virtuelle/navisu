package bzh.terrevirtuelle.navisu.dem;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import gov.nasa.worldwind.WorldWindow;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 14/02/2018 12:49
 */
public interface DemComponentServices
        extends ComponentService {

    void on(String... files);

    boolean canOpen(String category);

    InstrumentDriver getDriver();

    Point3D[][] retrieveElevations(WorldWindow wwd, Point3D[][] latlons, double targetResolution);

}
