/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.app;

import bzh.terrevirtuelle.navisu.architecture.impl.controller.parser.ComponentParser;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.ComponentHandler;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.Handler;
import bzh.terrevirtuelle.navisu.architecture.impl.view.ObjectTest;
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
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.model.ObjectSceneEvent;
import org.netbeans.api.visual.model.ObjectSceneEventType;
import org.netbeans.api.visual.model.ObjectSceneListener;
import org.netbeans.api.visual.model.ObjectState;
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
public class AppComponent 
      //  extends ObjectTest 
       // implements ObjectSceneListener
{

    // private static int nodeID = 1;
    private static int edgeID = 1;
    final String COMPONENTS_LOG = "components.log";
    private final VMDGraphScene scene;
    private final List<Component> components;

    public AppComponent() {
        scene = new VMDGraphScene();
        JFrame frame= new JFrame();
        frame.setSize(200, 200);
        frame.setVisible(true);
        
        JMenuBar menubar = new JMenuBar();
        menubar.add(new JMenu("Menu"));
        frame.setJMenuBar(menubar);
        // System.out.println(content);
        // Handler handler = new PrintComponentHandler();
      //  addObjectSceneListener (this, ObjectSceneEventType.values ());
        Handler handler = new ComponentHandler();
        ComponentParser parser = new ComponentParser();
        String content = read(COMPONENTS_LOG);
        components = parser.parse(handler, content);
        // components.forEach((c) -> {
        //     System.out.println(c);
        // });
        
        Map<String, List<Component>> map = filter(components);
        runScene(scene, map);
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
           // System.out.println(componentMap.get(s));
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

    public final void runScene(final VMDGraphScene scene, Map<String, List<Component>> components) {
        int index = 0;

        // createPin(scene, mobile, "start", IMAGE_ITEM, "Start", "Element");
        // createPin(scene, mobile, "resume", IMAGE_ITEM, "Resume", "Element");
        /*for (Component c : components) {
            String nodeID = "node" + index;
            VMDNodeWidget widget = (VMDNodeWidget) scene.addNode(nodeID);
            widget.setPreferredLocation(new Point(x, y));
            widget.setNodeProperties(null, c.getName(), "", null);
            scene.addPin(nodeID, nodeID + VMDGraphScene.PIN_ID_DEFAULT_SUFFIX);
            x += 50;
            y += 50;
            index++;
        }
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
/*
    @Override
    public void objectAdded(ObjectSceneEvent ose, Object o) {
    }

    @Override
    public void objectRemoved(ObjectSceneEvent ose, Object o) {
    }

    @Override
    public void objectStateChanged(ObjectSceneEvent ose, Object o, ObjectState os, ObjectState os1) {
    }

    @Override
    public void selectionChanged(ObjectSceneEvent ose, Set<Object> set, Set<Object> set1) {
        System.out.println(set + " " + set1);
  
    }

    @Override
    public void highlightingChanged(ObjectSceneEvent ose, Set<Object> set, Set<Object> set1) {
    }

    @Override
    public void hoverChanged(ObjectSceneEvent ose, Object o, Object o1) {
        System.out.println("hoverChanged");
    }

    @Override
    public void focusChanged(ObjectSceneEvent ose, Object o, Object o1) {
    }
*/
}
