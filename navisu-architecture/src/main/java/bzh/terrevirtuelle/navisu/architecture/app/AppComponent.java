/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.app;

import bzh.terrevirtuelle.navisu.architecture.impl.controller.parser.ComponentsLexer;
import bzh.terrevirtuelle.navisu.architecture.impl.controller.parser.ComponentsParser;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.ComponentHandler;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.Handler;
import bzh.terrevirtuelle.navisu.architecture.impl.view.SceneSupport;
import bzh.terrevirtuelle.navisu.domain.architecture.Component;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
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
        // System.out.println(content);
        // Handler handler = new PrintComponentHandler();
        Handler handler = new ComponentHandler();
        String content = read(COMPONENTS_LOG);
        components = parse(handler, content);
        System.out.println(handler.getComponents());
        runScene(scene, components);
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

    public final List<Component> parse(Handler handler, String content) {

        ANTLRStringStream in = new ANTLRStringStream(content);
        ComponentsLexer lexer = new ComponentsLexer(in);
        lexer.setHandler(handler);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ComponentsParser parser = new ComponentsParser(tokens);
        try {
            parser.entry();
        } catch (RecognitionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return handler.getComponents();
    }

    public final void runScene(final VMDGraphScene scene, List<Component> components) {
        int x = 100;
        int y = 100;
        int index=0;
       
        // createPin(scene, mobile, "start", IMAGE_ITEM, "Start", "Element");
        // createPin(scene, mobile, "resume", IMAGE_ITEM, "Resume", "Element");
        for (Component c : components) {
            String nodeID = "node" + index;
            VMDNodeWidget widget = (VMDNodeWidget) scene.addNode(nodeID);
            widget.setPreferredLocation(new Point(x, y));
            widget.setNodeProperties(null, c.getName(), "", null);
            scene.addPin(nodeID, nodeID + VMDGraphScene.PIN_ID_DEFAULT_SUFFIX);
            x += 50;
            y += 50;
            index++;
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
}
