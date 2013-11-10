package bzh.terrevirtuelle.navisu.app.guiagent.options.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.options.OptionsController;
import bzh.terrevirtuelle.navisu.app.guiagent.options.OptionsManager;
import bzh.terrevirtuelle.navisu.app.guiagent.options.OptionsManagerServices;
import bzh.terrevirtuelle.navisu.core.utility.Checker;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
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

    protected Pane root;

    @Override
    public void componentInitiated() {
        LOGGER.info("Options Component Initiated");
        this.optionsControllers = new ArrayList<>();
        this.initStage();
    }

    private void initStage() {
        this.root = new Pane();
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


    @Override
    public void add(OptionsController... controllers) {

        Checker.notNull(controllers, "List of OptionsController is null");

        for(OptionsController controller : controllers) {
            this.optionsControllers.add(controller);
            this.addOptionViewTab(controller);
        }
    }

    private void addOptionViewTab(OptionsController controller) {

        TabPane tabPane = new TabPane();

        Checker.notNull(controller.getName(), "OptionsController must have a name");

        Tab tab = new Tab(controller.getName());
        tab.setContent(controller.getOptionsView().getDisplayable());
        tabPane.getTabs().add(tab);

        this.root.getChildren().add(tabPane);
    }

    @Override
    public void show() {
        this.stage.show();
    }

    @Override
    public void hide() {
       this.stage.hide();
    }
}
