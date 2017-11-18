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
import org.netbeans.api.visual.vmd.VMDNodeWidget;
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
    private static int pinID = 0;
    private static int nodeID = 1;
    final String COMPONENTS_LOG = NAVISU_HOME + "/logs/components.log";
    private VMDGraphScene graphScene;
    private List<Component> components;
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
        Map<String, List<ComponentView>> componentViewMap = runScene(graphScene, componentsMap);

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
        int col = 0;
        int line = 0;
        Map<String, List<ComponentView>> componentViewMap = new HashMap<>();
        Set<String> keys = componentsMap.keySet();
        for (String k : keys) {
            componentViewMap.put(k, new ArrayList<>());
            for (Component c : componentsMap.get(k)) {
                int x = col;
                int y = line;
                ComponentView componentView = new ComponentView(c, null, null, x, y);
                componentViewMap.get(c.getModule()).add(componentView);
                componentView.setScene(scene);
                c.getUsedServices().forEach((n) -> {
                    createPin(scene, componentView.getNodeID(),
                            Integer.toString(AppComponent.edgeID++),
                            c.getShortName(n));
                });
                line += 20;
            }
            col += 150;
            line = 0;
        }
        keys = componentViewMap.keySet();

        for (String k : keys) {
            for (ComponentView cv : componentViewMap.get(k)) {
                Component component = cv.getComponent();
                component.getUsedServices().forEach((n) -> {
                    for (Component c : componentsMap.get(k)) {
                        for (String s : c.getServicesProvided()) {
                            if (s.equals(n)) {
                                System.out.println("component : " + component.getUsedServices());
                                createEdge(scene, c.getName(), component.getShortName(n));
                            }
                        }
                    }
                });
            }
        }

        /*
        VMDNodeWidget widget = (VMDNodeWidget) scene.findWidget(menu);
        HashMap<String, List<Widget>> categories = new HashMap<>();
        categories.put("Elements", Arrays.asList(scene.findWidget("game"), scene.findWidget("options"), scene.findWidget("help"), scene.findWidget("exit")));
        categories.put("Commands", Arrays.asList(scene.findWidget("listCommand1"), scene.findWidget("listCommand2")));
        widget.sortPins(categories);
         */
        scene.getActions().addAction(ActionFactory.createEditAction((Widget widget1) -> {
            scene.layoutScene();
        }));

        return componentViewMap;
    }

    void createPin(VMDGraphScene scene, String nodeID, String pinID, String name) {
        //  System.out.println("scene : " + scene +" nodeID : " +nodeID + " pinID : " + pinID+" n : "+name);
        //  String id = "node" + AppComponent.nodeID++;
        try {
            VMDPinWidget pinWidget = ((VMDPinWidget) scene.addPin(nodeID, pinID));
            //    System.out.println("pinWidget : " + pinWidget);
            pinWidget.setProperties(name, null);
        } catch (Exception e) {
            System.out.println("e: " + e);
        }
    }

    void createEdge(VMDGraphScene scene, String sourcePinID, String targetNodeID) {
        System.out.println("sourcePinID : " + sourcePinID + " targetNodeID : " + targetNodeID);
        String edgeIDString = "edge" + AppComponent.edgeID++;
        scene.addEdge(edgeIDString);
      //  scene.setEdgeSource(edgeIDString, sourcePinID);
       // scene.setEdgeTarget(edgeIDString, targetNodeID + VMDGraphScene.PIN_ID_DEFAULT_SUFFIX);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
