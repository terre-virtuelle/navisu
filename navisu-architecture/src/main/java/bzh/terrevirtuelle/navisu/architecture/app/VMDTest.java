
package bzh.terrevirtuelle.navisu.architecture.app;

import bzh.terrevirtuelle.navisu.architecture.impl.view.SceneSupport;
import java.awt.Image;
import java.awt.Point;
import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.vmd.VMDGraphScene;
import org.netbeans.api.visual.vmd.VMDNodeWidget;
import org.netbeans.api.visual.vmd.VMDPinWidget;
import org.netbeans.api.visual.widget.Widget;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * @author David Kaspar
 */
public class VMDTest {

    private static int nodeID = 1;
    private static int edgeID = 1;

    public static void main(String[] args) {
        final VMDGraphScene scene = new VMDGraphScene();
        VMDTest vmdTest = new VMDTest();
        vmdTest.runScene(scene);
    }

     void runScene(final VMDGraphScene scene) {

        Image IMAGE_LIST = new ImageIcon("resources/list_16.png").getImage();
        Image IMAGE_CANVAS = new ImageIcon("resources/custom_displayable_16.png").getImage();
        Image IMAGE_COMMAND = new ImageIcon("resources/command_16.png").getImage();
        Image IMAGE_ITEM = new ImageIcon("resources/item_16.png").getImage();
        Image GLYPH_PRE_CODE = new ImageIcon("resources/preCodeGlyph.png").getImage();
        Image GLYPH_POST_CODE = new ImageIcon("resources/postCodeGlyph.png").getImage();
        Image GLYPH_CANCEL = new ImageIcon("resources/cancelGlyph.png").getImage();

        String mobile = createNode(scene, 100, 100, IMAGE_LIST, "menu", "List", null);
        createPin(scene, mobile, "start", IMAGE_ITEM, "Start", "Element");
        createPin(scene, mobile, "resume", IMAGE_ITEM, "Resume", "Element");

        String menu = createNode(scene, 400, 400, IMAGE_LIST, "menu", "List", null);
        createPin(scene, menu, "game", IMAGE_ITEM, "New Game", "Element");
        createPin(scene, menu, "options", IMAGE_ITEM, "Options", "Element");
        createPin(scene, menu, "help", IMAGE_ITEM, "Help", "Element");
        createPin(scene, menu, "exit", IMAGE_ITEM, "Exit", "Element");
        createPin(scene, menu, "listCommand1", IMAGE_COMMAND, "Yes", "Command");
        createPin(scene, menu, "listCommand2", IMAGE_COMMAND, "No", "Command");

        String game = createNode(scene, 600, 100, IMAGE_CANVAS, "gameCanvas", "MyCanvas", Arrays.asList(GLYPH_PRE_CODE, GLYPH_CANCEL, GLYPH_POST_CODE));
        createPin(scene, game, "ok", IMAGE_COMMAND, "okCommand1", "Command");
        createPin(scene, game, "cancel", IMAGE_COMMAND, "cancelCommand1", "Command");

        createEdge(scene, "start", menu);
        createEdge(scene, "resume", menu);

        createEdge(scene, "game", game);
        createEdge(scene, "exit", mobile);

        createEdge(scene, "ok", menu);
        createEdge(scene, "cancel", menu);

        VMDNodeWidget widget = (VMDNodeWidget) scene.findWidget(menu);
        HashMap<String, List<Widget>> categories = new HashMap<>();
        categories.put("Elements", Arrays.asList(scene.findWidget("game"), scene.findWidget("options"), scene.findWidget("help"), scene.findWidget("exit")));
        categories.put("Commands", Arrays.asList(scene.findWidget("listCommand1"), scene.findWidget("listCommand2")));
        widget.sortPins(categories);

        scene.getActions().addAction(ActionFactory.createEditAction((Widget widget1) -> {
            scene.layoutScene();
        }));

        SceneSupport.show(scene);
    }

     String createNode(VMDGraphScene scene, int x, int y, Image image, String name, String type, List<Image> glyphs) {
        String nodeID = "toto" + VMDTest.nodeID++;
        VMDNodeWidget widget = (VMDNodeWidget) scene.addNode(nodeID);
        widget.setPreferredLocation(new Point(x, y));
        widget.setNodeProperties(image, name, type, glyphs);
        scene.addPin(nodeID, nodeID + VMDGraphScene.PIN_ID_DEFAULT_SUFFIX); 
        return nodeID;
    }

     void createPin(VMDGraphScene scene, String nodeID, String pinID, Image image, String name, String type) {
     //  System.out.println("scene : " + scene +" nodeID : " +nodeID + " pinID : " + pinID+" n : "+name);
         VMDPinWidget pinWidget = ((VMDPinWidget) scene.addPin(nodeID, pinID));
        pinWidget.setProperties(name, null);
      
    }

     void createEdge(VMDGraphScene scene, String sourcePinID, String targetNodeID) {
        System.out.println("sourcePinID : " + sourcePinID + " targetNodeID : " + targetNodeID);
         String edgeID = "edge" + VMDTest.edgeID++;
        scene.addEdge(edgeID);
        scene.setEdgeSource(edgeID, sourcePinID);
        scene.setEdgeTarget(edgeID, targetNodeID + VMDGraphScene.PIN_ID_DEFAULT_SUFFIX);

    }

}
