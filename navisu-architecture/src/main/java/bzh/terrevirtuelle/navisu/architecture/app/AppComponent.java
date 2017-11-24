/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.app;

import bzh.terrevirtuelle.navisu.architecture.impl.controller.parser.ComponentParser;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.ComponentHandler;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.Handler;
import bzh.terrevirtuelle.navisu.architecture.impl.view.ComponentView;
import bzh.terrevirtuelle.navisu.architecture.impl.view.SceneSupport;
import bzh.terrevirtuelle.navisu.domain.architecture.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.vmd.VMDGraphScene;
import org.netbeans.api.visual.vmd.VMDPinWidget;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Exceptions;

/**
 *
 * @author serge
 */
public class AppComponent extends Application {

    private final String NAVISU_HOME = System.getProperty("user.home") + "/.navisu";
    protected String VIEW_GROUP_STYLE = "common.css";
    protected static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    private static int edgeID = 0;
    final String COMPONENTS_LOG = NAVISU_HOME + "/logs/components.log";
    private VMDGraphScene graphScene;
    private List<Component> components;
    private VMDPinWidget widget;

    @FXML
    private StackPane root;

    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("componentsControl.fxml"));
            root = fxmlLoader.load();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        root.getStylesheets().add(CSS_STYLE_PATH + VIEW_GROUP_STYLE);
        Scene scene = new Scene(root);

        graphScene = new VMDGraphScene();

        Handler handler = new ComponentHandler();
        ComponentParser parser = new ComponentParser();
        String content = read(COMPONENTS_LOG);
        components = parser.parse(handler, content);

        Map<String, List<Component>> componentsMap = createComponentsMap(components);
        runScene(graphScene, componentsMap);

        primaryStage.setTitle("Components control");
        primaryStage.setScene(scene);
        primaryStage.setX(100);
        primaryStage.setY(100);

        primaryStage.show();
        SceneSupport.show(graphScene);
    }

    public final String read(String fileName) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return content;
    }

    public final Map<String, List<Component>> createComponentsMap(List<Component> components) {
        Map<String, List<Component>> componentMap = new HashMap<>();
        Set<String> modules = new HashSet<>();
        components.forEach((c) -> {
            modules.add(c.getModule());
        });
        modules.forEach((m) -> {
            componentMap.put(m, new ArrayList<>());
        });
        components.forEach((c) -> {
            componentMap.get(c.getModule()).add(c);
        });
        return componentMap;
    }

    public final Map<String, List<ComponentView>> runScene(final VMDGraphScene scene,
            Map<String, List<Component>> componentsMap) {

        Map<String, List<ComponentView>> componentViewMap = new HashMap<>();
        Set<String> keys = componentsMap.keySet();

        //Creation de la map de view
        keys.forEach((k) -> {
            componentViewMap.put(k, new ArrayList<>());
        });

        // Cas particulier pour un module
        // On cree un Set des comosant out concernés pour les afficher
        String k = "instruments";
        Set<Component> componentProviderServicesSet = new HashSet<>();
        componentsMap.get(k).forEach((component) -> {
            component.getUsedServices().forEach((n) -> {
                components.forEach((c) -> {
                    c.getServicesProvided().forEach((nn) -> {
                        if (n.equals(nn) && !component.getModule().equals(c.getModule())) {
                            componentProviderServicesSet.add(c);
                        }
                    });
                });
            });
        });

        // Affichage des view concernes
        // Affichage des services utilises
        int col = 0;
        int line = 0;
        createScene(scene, componentViewMap, componentProviderServicesSet, line, col);

        Set<Component> componentUserServicesSet = new HashSet<>();
        componentsMap.get(k).forEach((component) -> {
            componentUserServicesSet.add(component);
        });

        line += 100;
        col = 0;
        createScene(scene, componentViewMap, componentUserServicesSet, line, col);

        Set<Component> componentSet = new HashSet<>();
        componentSet.addAll(componentUserServicesSet);
        componentSet.addAll(componentProviderServicesSet);

        //  componentsMap.get(k).forEach((component) -> {
        componentSet.forEach((component) -> {
            component.getUsedServices().forEach((n) -> {
                componentSet.forEach((c) -> {
                    c.getServicesProvided().forEach((nn) -> {
                        if (n.equals(nn) && component.getModule().equals(k)) {
                            createEdge(scene, component.getShortName(nn) + "_P", component.getName());
                        }
                    });
                });
            });
        });

        scene.getActions().addAction(ActionFactory.createEditAction((Widget widget1) -> {
            scene.layoutScene();
        }));

        return componentViewMap;
    }

    VMDPinWidget createPin(VMDGraphScene scene, String nodeID, String pinID, String name) {
        VMDPinWidget pinWidget = ((VMDPinWidget) scene.addPin(nodeID, pinID));
        pinWidget.setProperties(name, null);
        return pinWidget;
    }

    void createEdge(VMDGraphScene scene, String sourcePinID, String targetNodeID) {
        String edgeIDString = "edge" + AppComponent.edgeID++;
        scene.addEdge(edgeIDString);
        scene.setEdgeSource(edgeIDString, sourcePinID);
        scene.setEdgeTarget(edgeIDString, targetNodeID + VMDGraphScene.PIN_ID_DEFAULT_SUFFIX);
    }

    void createScene(final VMDGraphScene scene,
            Map<String, List<ComponentView>> componentViewMap, Set<Component> components, int line, int col) {
        List<Widget> pinProviderWidget = new ArrayList<>();
        List<Widget> pinUsedWidget = new ArrayList<>();
        HashMap<String, List<Widget>> categories = new HashMap<>();

        for (Component c : components) {
            pinProviderWidget.clear();
            pinUsedWidget.clear();
            categories.clear();
            int x = col;
            int y = line;
            ComponentView componentView = new ComponentView(c, null, null, x, y);
            componentViewMap.get(c.getModule()).add(componentView);
            componentView.setScene(scene);
            c.getServicesProvided().forEach((n) -> {
                widget = createPin(scene, componentView.getNodeID(), c.getShortName(n) + "_P", c.getShortName(n));
                pinProviderWidget.add(widget);
            });
            c.getUsedServices().forEach((n) -> {
                widget = createPin(scene, componentView.getNodeID(), c.getShortName(n) + "_U", c.getShortName(n));
                pinUsedWidget.add(widget);
            });
            line += 20;
            col += 120;
            categories.put("Provided Services", pinProviderWidget);
            categories.put("Used Services", pinUsedWidget);
            componentView.getWidget().sortPins(categories);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
