/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.architecture.ArchitectureComponent;
import bzh.terrevirtuelle.navisu.architecture.ArchitectureComponentServices;
import bzh.terrevirtuelle.navisu.architecture.impl.controller.ComponentController;
import bzh.terrevirtuelle.navisu.architecture.impl.view.ComponentViewer;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.capcaval.c3.component.ComponentState;
import org.openide.util.Exceptions;

/**
 *
 * @author serge
 * @date Nov 29, 2017
 */
public class ArchitectureComponentImpl
        //  extends Application
        implements ArchitectureComponent, ArchitectureComponentServices,
        InstrumentDriver, ComponentState {

    private static final String NAME = "Architecture";
    protected final String FXML = "componentsControl.fxml";
    protected final String TITLE = "Components control";
    protected final String NAVISU_HOME = System.getProperty("user.home") + "/.navisu";
    protected final String VIEW_GROUP_STYLE = "common.css";
    protected final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    protected final String COMPONENTS_LOG = NAVISU_HOME + "/logs/components.log";

    @FXML
    public StackPane root;

    // @Override
    public void start() {
        Stage primaryStage = new Stage();
        ComponentViewer componentViewer = new ComponentViewer(COMPONENTS_LOG);
        ComponentController componentController = new ComponentController(componentViewer);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
            fxmlLoader.setController(componentController);
            root = fxmlLoader.load();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        root.getStylesheets().add(CSS_STYLE_PATH + VIEW_GROUP_STYLE);
        Scene scene = new Scene(root);

        primaryStage.setTitle(TITLE);
        primaryStage.setScene(scene);
        primaryStage.setX(100);
        primaryStage.setY(100);

        primaryStage.show();
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public boolean canOpen(String category) {
        boolean canOpen = false;
        if (!category.equals(NAME)) {
        } else {
            canOpen = true;
        }
        return canOpen;
    }

    @Override
    public void on(String... files) {
        start();
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

}
