package bzh.terrevirtuelle.navisu.app.guiagent.options.server.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import org.capcaval.c3.component.ComponentState;

import java.util.logging.Logger;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.annotation.UsedService;
import bzh.terrevirtuelle.navisu.app.guiagent.options.server.impl.controller.ServerOptionsComponentController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import bzh.terrevirtuelle.navisu.app.guiagent.options.server.ServerOptionsComponent;
import bzh.terrevirtuelle.navisu.app.guiagent.options.server.ServerOptionsComponentServices;
import bzh.terrevirtuelle.navisu.server.DataServerServices;

/**
 *
 * @author serge
 * @date May 21, 2016
 *
 */
public class ServerOptionsComponentImpl
        implements ServerOptionsComponent, ServerOptionsComponentServices,
        ComponentState, InstrumentDriver {

    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    DataServerServices dataServerServices;
    
    private final String COMPONENT_KEY_NAME_0 = "ServerOptions";
    private ServerOptionsComponentController controller0;

    private final static Logger LOGGER = Logger.getLogger(ServerOptionsComponentImpl.class.getName());

    @Override
    public void componentInitiated() {
        LOGGER.info("Options Component Initiated");
    }

    @Override
    public void componentStarted() {
        LOGGER.info("Options Component Started");
    }

    @Override
    public void componentStopped() {
        //  LOGGER.info("Options Component Stopped");

    }

    @Override
    public void on(String... files) {
        String[] cmd = files;
        if (cmd != null) {
            if (cmd[0].equals(COMPONENT_KEY_NAME_0)) {
                controller0 = ServerOptionsComponentController.getInstance(this, KeyCode.O, KeyCombination.CONTROL_DOWN,
                        guiAgentServices, dataServerServices);
                controller0.setVisible(true);
            }
        }
    }

    @Override
    public boolean canOpen(String category) {
        boolean canOpen = false;

        if (category.equals(COMPONENT_KEY_NAME_0)) {
            canOpen = true;
        }
        return canOpen;
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public void off() {
        if (controller0 != null) {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, controller0);
            guiAgentServices.getRoot().getChildren().remove(controller0);
            controller0.setVisible(false);
        }
    }
}
