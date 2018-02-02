package bzh.terrevirtuelle.navisu.tools.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.tools.ToolsComponent;
import bzh.terrevirtuelle.navisu.tools.ToolsComponentServices;
import bzh.terrevirtuelle.navisu.tools.impl.controller.ToolsComponentController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * @author Serge Morvan
 * @date 1/02/2018 12:49
 */
public class ToolsComponentImpl
        implements ToolsComponent, ToolsComponentServices, InstrumentDriver, ComponentState {

    @UsedService
    GuiAgentServices guiAgentServices;

    private final String COMPONENT_KEY_NAME_0 = "DbS57";
    private final String COMPONENT_KEY_NAME_1 = "DbBathy";
    ToolsComponentController controller;

    @SuppressWarnings("unchecked")
    @Override
    public void componentInitiated() {

    }

    @Override
    public void on(String... files) {
        String[] cmd = files;
        if (cmd != null) {
            if (cmd[0].equals(COMPONENT_KEY_NAME_0) || cmd[0].equals(COMPONENT_KEY_NAME_1)) {
                controller = new ToolsComponentController(this, KeyCode.T, KeyCombination.CONTROL_DOWN,
                        guiAgentServices);
                controller.setVisible(true);
            }
        }
    }

    @Override
    public boolean canOpen(String category) {
        boolean canOpen = false;

        if (category.equals(COMPONENT_KEY_NAME_0)
                || category.equals(COMPONENT_KEY_NAME_1)
                ) {
            canOpen = true;
        }
        return canOpen;
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public void componentStarted() {

    }

    @Override
    public void componentStopped() {

    }

}
