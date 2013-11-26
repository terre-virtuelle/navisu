package bzh.terrevirtuelle.navisu.app.pointCloud.impl;

import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import bzh.terrevirtuelle.navisu.app.pointCloud.PointCloud;
import bzh.terrevirtuelle.navisu.app.pointCloud.PointCloudServices;
import org.capcaval.c3.component.ComponentState;

/**
 * User: julien
 * Date: 26/11/13
 */
public class PointCloudImpl implements PointCloud, PointCloudServices, ComponentState {

    @Override
    public void componentInitiated() {

    }

    @Override
    public void componentStarted() {}

    @Override
    public void componentStopped() {}

    @Override
    public Driver getDriver() {
        return null;
    }

    @Override
    public void loadFile(String path) {

    }
}
