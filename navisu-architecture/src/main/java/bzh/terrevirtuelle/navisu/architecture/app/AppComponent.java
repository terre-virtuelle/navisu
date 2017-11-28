/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.app;

import bzh.terrevirtuelle.navisu.domain.architecture.Component;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.openide.util.Exceptions;

/**
 *
 * @author serge
 */
public class AppComponent extends Application {

    private final String FXML = "componentsControl.fxml";
    private final String NAVISU_HOME = System.getProperty("user.home") + "/.navisu";
    protected String VIEW_GROUP_STYLE = "common.css";
    protected final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    final String COMPONENTS_LOG = NAVISU_HOME + "/logs/components.log";
    
    Map<String, List<Component>> componentsMap;

    @FXML
    public StackPane root;

    @Override
    public void start(Stage primaryStage) {
        ComponentViewer componentViewer = new ComponentViewer(COMPONENTS_LOG);
        componentsMap = componentViewer.getComponentsMap();

        ComponentController componentController = new ComponentController(componentsMap);
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
            fxmlLoader.setController(componentController);
            root = fxmlLoader.load();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

        root.getStylesheets().add(CSS_STYLE_PATH + VIEW_GROUP_STYLE);
        Scene scene = new Scene(root);

        primaryStage.setTitle("Components control");
        primaryStage.setScene(scene);
        primaryStage.setX(100);
        primaryStage.setY(100);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
