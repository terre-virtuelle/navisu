/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.impl.view;

import bzh.terrevirtuelle.navisu.architecture.app.AppComponent;
import bzh.terrevirtuelle.navisu.domain.architecture.Component;
import java.awt.Image;
import java.awt.Point;
import java.util.List;
import javax.swing.ImageIcon;
import org.netbeans.api.visual.vmd.VMDGraphScene;
import org.netbeans.api.visual.vmd.VMDNodeWidget;
import org.netbeans.api.visual.widget.Scene;

/**
 *
 * @author serge
 * @date Nov 6, 2017
 */
public class ComponentView
        extends VMDNodeWidget {

    Image IMAGE_LIST = new ImageIcon("resources/list_16.png").getImage();
    Image IMAGE_CANVAS = new ImageIcon("resources/custom_displayable_16.png").getImage();
    Image IMAGE_COMMAND = new ImageIcon("resources/command_16.png").getImage();
    Image IMAGE_ITEM = new ImageIcon("resources/item_16.png").getImage();
    Image GLYPH_PRE_CODE = new ImageIcon("resources/preCodeGlyph.png").getImage();
    Image GLYPH_POST_CODE = new ImageIcon("resources/postCodeGlyph.png").getImage();
    Image GLYPH_CANCEL = new ImageIcon("resources/cancelGlyph.png").getImage();

    public ComponentView(Scene scene) {
        super(scene);
        //   System.out.println("ComponentView");
    }

    public ComponentView(Scene scene, Component component) {
        super(scene);
        //  System.out.println("ComponentView");
    }

    public ComponentView(VMDGraphScene scene, int x, int y, Image image, String name, String type, List<Image> glyphs) {
        super(scene);
    }
    
    String createNode(VMDGraphScene scene, int x, int y, 
            Image image, String name, String type, int id,
            List<Image> glyphs) {
        String nodeID = "node" + id;
        VMDNodeWidget widget = (VMDNodeWidget) scene.addNode(nodeID);
        widget.setPreferredLocation(new Point(x, y));
        widget.setNodeProperties(image, name, type, glyphs);
        scene.addPin(nodeID, nodeID + VMDGraphScene.PIN_ID_DEFAULT_SUFFIX);
        return nodeID;
    }
}
