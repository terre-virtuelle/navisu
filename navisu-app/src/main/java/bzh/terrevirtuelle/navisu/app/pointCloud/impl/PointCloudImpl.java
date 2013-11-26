package bzh.terrevirtuelle.navisu.app.pointcloud.impl;

import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import bzh.terrevirtuelle.navisu.app.pointcloud.PointCloud;
import bzh.terrevirtuelle.navisu.app.pointcloud.PointCloudServices;
import org.capcaval.c3.component.ComponentState;

import java.util.logging.Logger;

/**
 * User: julien
 * Date: 26/11/13
 */
public class PointCloudImpl implements PointCloud, PointCloudServices, ComponentState {

    protected Driver driver;

    protected Logger LOGGER = Logger.getLogger(PointCloudImpl.class.getName());


    @Override
    public void componentInitiated() {
        this.driver = new Driver() {

            private static final String NAME = "PointCloud";
            private static final String EXTENSION = ".las";

            @Override
            public boolean canOpen(String file) {
                return file.toLowerCase().endsWith(EXTENSION);
            }

            @Override
            public void open(String... files) {
                for(String file : files) {
                    LOGGER.info("Opening " + file + " ...");
                    loadFile(files[0]); //Todo Make stuff for all files
                }
            }

            @Override
            public String getName() {
                return NAME;
            }

            @Override
            public String[] getExtensions() {
                return new String[] { "*" + EXTENSION };
            }
        };
    }

    @Override
    public void componentStarted() {}

    @Override
    public void componentStopped() {}

    @Override
    public Driver getDriver() {
        return this.driver;
    }

    @Override
    public void loadFile(String path) {

    }
}
