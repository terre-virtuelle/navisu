package bzh.terrevirtuelle.navisu.app.pointcloud;

import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import org.capcaval.c3.component.ComponentService;

/**
 * User: julien
 * Date: 26/11/13
 */
public interface PointCloudServices extends ComponentService {

    Driver getDriver();

    void loadFile(String path);
}
