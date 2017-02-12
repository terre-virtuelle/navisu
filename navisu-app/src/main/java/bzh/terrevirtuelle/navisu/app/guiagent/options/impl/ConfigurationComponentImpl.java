package bzh.terrevirtuelle.navisu.app.guiagent.options.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import org.capcaval.c3.component.ComponentState;

import java.util.logging.Logger;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.annotation.UsedService;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import bzh.terrevirtuelle.navisu.app.guiagent.options.impl.controller.ConfigurationComponentController;
import bzh.terrevirtuelle.navisu.server.DataServerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.options.ConfigurationComponent;
import bzh.terrevirtuelle.navisu.app.guiagent.options.ConfigurationComponentServices;

/**
 *
 * @author serge
 * @date May 21, 2016
 *
 */
public class ConfigurationComponentImpl
        implements ConfigurationComponent, ConfigurationComponentServices,
        ComponentState, InstrumentDriver {

    @UsedService
    GuiAgentServices guiAgentServices;
    
    private final String COMPONENT_KEY_NAME_0 = "Configuration";
    private ConfigurationComponentController controller0;

    private final static Logger LOGGER = Logger.getLogger(ConfigurationComponentImpl.class.getName());

    @Override
    public void componentInitiated() {
       // LOGGER.info("Options Component Initiated");
    }

    @Override
    public void componentStarted() {
      //  LOGGER.info("Options Component Started");
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
                controller0 = ConfigurationComponentController.getInstance(this, KeyCode.O, KeyCombination.CONTROL_DOWN,
                        guiAgentServices);
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

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void restoreDefault() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
