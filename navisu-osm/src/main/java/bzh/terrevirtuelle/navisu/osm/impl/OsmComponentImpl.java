package bzh.terrevirtuelle.navisu.osm.impl;

;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.osm.OsmComponent;
import bzh.terrevirtuelle.navisu.osm.OsmComponentServices;
import org.capcaval.c3.component.ComponentState;

/**
 * @author sbodmer
 * @date 24/12/2018 12:49
 */


public class OsmComponentImpl
        implements OsmComponent, OsmComponentServices, InstrumentDriver, ComponentState {

    private static final String NAME = "BUILDING";
    private static final String TYPE_0 = "OSM";

    @Override
    public boolean canOpen(String type) {
        boolean canOpen = false;
        if (type.equals(TYPE_0)) {
            canOpen = true;
        } else {

        }
        return canOpen;
    }

    @Override
    public void on(String... files) {

    }

    @Override
    public void componentInitiated() {

    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    public String getName() {
        return NAME;
    }

}
