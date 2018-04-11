package bzh.terrevirtuelle.navisu.tools.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.database.relational.DatabaseServices;
import bzh.terrevirtuelle.navisu.tools.ToolsComponent;
import bzh.terrevirtuelle.navisu.tools.ToolsComponentServices;
import bzh.terrevirtuelle.navisu.tools.impl.controller.ToolsComponentController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
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
    @UsedService
    S57ChartComponentServices s57ChartComponentServices;
    @UsedService
    DatabaseServices databaseServices;
    @UsedService
    BathymetryDBServices bathymetryDBServices;
    @UsedService
    InstrumentDriverManagerServices instrumentDriverManagerServices;
    
    private final String COMPONENT_KEY_NAME_0 = "DbS57";
    private final String COMPONENT_KEY_NAME_1 = "DbBathy";
    private String componentKeyName;
    ToolsComponentController controller;

    @SuppressWarnings("unchecked")
    @Override
    public void componentInitiated() {

    }

    @Override
    public void on(String... files) {
        
        
        String[] cmd = files;
        if (cmd != null) {
            componentKeyName = cmd[0];
            System.out.println("componentKeyName : "+componentKeyName);
            if (cmd[0].equals(COMPONENT_KEY_NAME_0) || cmd[0].equals(COMPONENT_KEY_NAME_1)) {
                controller = new ToolsComponentController(this, componentKeyName, KeyCode.T, KeyCombination.CONTROL_DOWN,
                        guiAgentServices, s57ChartComponentServices, 
                        databaseServices, bathymetryDBServices,
                        instrumentDriverManagerServices);
                controller.setVisible(true);
            }
        }
    }

    @Override
    public void off() {
        if (controller != null) {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, controller);
            guiAgentServices.getRoot().getChildren().remove(controller);
            controller.setVisible(false);
        }
    }

    @Override
    public boolean canOpen(String category) {
        boolean canOpen = false;

        if (category.equals(COMPONENT_KEY_NAME_0)
                || category.equals(COMPONENT_KEY_NAME_1)) {
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

    public String getComponentKeyName() {
        return componentKeyName;
    }

}
