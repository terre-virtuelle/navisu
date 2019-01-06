package bzh.terrevirtuelle.navisu.buildings.paysBrest3D.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.buildings.paysBrest3D.PaysBrest3DComponent;
import bzh.terrevirtuelle.navisu.buildings.paysBrest3D.PaysBrest3DComponentServices;
import org.capcaval.c3.component.ComponentState;

/**
 * @date Jan 3, 2019 10:09:49 AM
 * @author Serge Morvan
 */
public class PaysBrest3DComponentImpl
        implements PaysBrest3DComponent, PaysBrest3DComponentServices, InstrumentDriver, ComponentState {

    private static final String COMPONENT_KEY_NAME_0 = "PAYS_BREST_3D";

    @Override
    public boolean canOpen(String category) {
        boolean canOpen = false;

        if (category.equals(COMPONENT_KEY_NAME_0)) {
            canOpen = true;
        }
        return canOpen;
    }

    @Override
    public void on(String... files) {

    }

    @Override
    public void off() {

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
        return COMPONENT_KEY_NAME_0;
    }

}
