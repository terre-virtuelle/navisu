/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.app;

import bzh.terrevirtuelle.navisu.architecture.app.controlcommand.ControlFrame;
import bzh.terrevirtuelle.navisu.architecture.impl.controller.parser.ComponentParser;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.ComponentHandler;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.Handler;
import bzh.terrevirtuelle.navisu.architecture.impl.model.ComponentModelView;
import bzh.terrevirtuelle.navisu.architecture.impl.view.SceneSupport;
import bzh.terrevirtuelle.navisu.domain.architecture.Component;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.vmd.VMDGraphScene;
import org.netbeans.api.visual.vmd.VMDNodeWidget;
import org.netbeans.api.visual.vmd.VMDPinWidget;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Exceptions;

/**
 *
 * @author serge
 * @date Nov 6, 2017
 */
public class AppComponent {

    // private static int nodeID = 1;
    private static int edgeID = 1;
    final String COMPONENTS_LOG = "components.log";
    private final VMDGraphScene scene;
    private final List<Component> components;

    public AppComponent() {
        scene = new VMDGraphScene();

        Handler handler = new ComponentHandler();
        ComponentParser parser = new ComponentParser();
        String content = read(COMPONENTS_LOG);
        components = parser.parse(handler, content);

        Map<String, List<Component>> componentMap = filter(components);
        Map<String, List<ComponentModelView>> componentModelViewMap = runScene(scene, componentMap);
      
        ControlFrame controlFrame = new ControlFrame(componentModelViewMap);
        controlFrame.setVisible();
    }

    public final Map<String, List<Component>> filter(List<Component> components) {
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
        Set<String> keySet = componentMap.keySet();
        keySet.forEach((s) -> {
        });
        return componentMap;
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

    public final Map<String, List<ComponentModelView>> runScene(final VMDGraphScene scene, Map<String, List<Component>> components) {
        int index = 0;

        Map<String, List<ComponentModelView>> componentModelView = new HashMap<>();
/*
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
        Set<String> keySet = componentMap.keySet();
        keySet.forEach((s) -> {
        });
        */
        for (Component c : components.get("app")) {
            double radius = 500 * index / 20;
            double angle = 10 * Math.PI * index / 100;
            int x = (int) (200 + radius * Math.cos(angle));
            int y = (int) (350 + radius * Math.sin(angle));
            String nodeID = "node" + index;
            VMDNodeWidget widget = (VMDNodeWidget) scene.addNode(nodeID);
            widget.setPreferredLocation(new Point(x, y));
            widget.setNodeProperties(null, c.getName(), c.getModule(), null);
            
//componentModelView.put(nodeID, new ComponentModelView(c, widget));
           
createPin(scene, nodeID, "game", null, c.getShortName(c.getServicesProvided().get(0)), "Element");
            HashMap<String, List<Widget>> categories = new HashMap<>();
            categories.put("Events produced", null);
            categories.put("Services produced", null);

            int i = 0;
            for (String evt : c.getEventsProvided()) {
                if (i > 10) {
                    createPin(scene, nodeID, "game", null, c.getShortName(evt), "Element");
                    widget.collapseWidget();
                    i++;
                }
            }

            scene.addPin(nodeID, nodeID + VMDGraphScene.PIN_ID_DEFAULT_SUFFIX);
            index++;
            /*
            String edge = "Set no. " + setID + " - Edge " + index;
            scene.addEdge (edge);
            scene.setEdgeSource (edge, rootNode);
            scene.setEdgeTarget (edge, node);
             */
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

        SceneSupport.show(scene);
        return componentModelView;
    }

    void createPin(VMDGraphScene scene, String nodeID, String pinID, Image image, String name, String type) {

        VMDPinWidget pinWidget = ((VMDPinWidget) scene.addPin(nodeID, pinID));
        pinWidget.setProperties(name, null);

    }

    void createEdge(VMDGraphScene scene, String sourcePinID, String targetNodeID) {

        String edgeIDString = "edge" + AppComponent.edgeID++;
        scene.addEdge(edgeIDString);
        scene.setEdgeSource(edgeIDString, sourcePinID);
        scene.setEdgeTarget(edgeIDString, targetNodeID + VMDGraphScene.PIN_ID_DEFAULT_SUFFIX);

    }

    public static void main(String[] args) {
        AppComponent appComponent = new AppComponent();
    }

}
