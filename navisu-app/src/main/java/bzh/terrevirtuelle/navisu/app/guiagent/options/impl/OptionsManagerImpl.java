package bzh.terrevirtuelle.navisu.app.guiagent.options.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.options.OptionsController;
import bzh.terrevirtuelle.navisu.app.guiagent.options.OptionsManager;
import bzh.terrevirtuelle.navisu.app.guiagent.options.OptionsManagerServices;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.capcaval.c3.component.ComponentState;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * User: Jordan
 * Date: 08/11/2013
 */
public class OptionsManagerImpl implements OptionsManager, OptionsManagerServices, ComponentState {

    private final static Logger LOGGER = Logger.getLogger(OptionsManagerImpl.class.getName());

    protected List<OptionsController> optionsControllers;

    protected Stage stage;

    @Override
    public void componentInitiated() {
        LOGGER.info("Options Component Initiated");
        this.optionsControllers = new ArrayList<>();
        this.initStage();

    }

    private void initStage() {
        Group root = new Group();
        root.getChildren().add(this.getDisplay().getDisplayable());
        Scene scene = new Scene(root, 500, 500);

        this.stage = new Stage();
        this.stage.setTitle("Options");
        this.stage.setScene(scene);
    }

    @Override
    public void componentStarted() {
        LOGGER.info("Options Component Started");
    }

    @Override
    public void componentStopped() {
        LOGGER.info("Options Component Stopped");
        this.stage.close();
    }


    private Display<Node> getDisplay() {

        TabPane tabPane = new TabPane();
        for(OptionsController optionController : optionsControllers) {

            Tab tab = new Tab(optionController.getName());
            tab.setContent(optionController.getOptionsView().getDisplayable());
            tabPane.getTabs().add(tab);
        }

        return Display.factory.newDisplayNode(tabPane);
    }

    @Override
    public void show() {
        this.stage.show();;
    }

    @Override
    public void hide() {
       this.stage.hide();
    }

    @Override
    public List<OptionsController> getOptionsControllers() {
        return this.optionsControllers;
    }
}
