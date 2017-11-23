/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.impl.view;

import bzh.terrevirtuelle.navisu.domain.architecture.Component;
import java.awt.Image;
import java.awt.Point;
import java.util.List;
import javax.swing.ImageIcon;
import org.netbeans.api.visual.vmd.VMDGraphScene;
import org.netbeans.api.visual.vmd.VMDNodeWidget;

/**
 *
 * @author serge
 * @date Nov 6, 2017
 */
public class ComponentView {
    
    Image IMAGE_LIST = new ImageIcon("resources/list_16.png").getImage();
    Image IMAGE_CANVAS = new ImageIcon("resources/custom_displayable_16.png").getImage();
    Image IMAGE_COMMAND = new ImageIcon("resources/command_16.png").getImage();
    Image IMAGE_ITEM = new ImageIcon("resources/item_16.png").getImage();
    Image GLYPH_PRE_CODE = new ImageIcon("resources/preCodeGlyph.png").getImage();
    Image GLYPH_POST_CODE = new ImageIcon("resources/postCodeGlyph.png").getImage();
    Image GLYPH_CANCEL = new ImageIcon("resources/cancelGlyph.png").getImage();
    
    private final Component component;
    private VMDGraphScene scene;
    private VMDNodeWidget widget;
    Image image;
    int x;
    int y;
    List<Image> glyphs;
    String type;
    String nodeID;
    
    public ComponentView(Component component,
            Image image, List<Image> glyphs,
            int x, int y) {
        this.component = component;
        this.image = image;
        this.x = x;
        this.y = y;
        this.glyphs = glyphs;
        nodeID = component.getName();
    }
    
    public void setScene(VMDGraphScene scene) {
        this.scene = scene;
        //   System.out.println("scene : " + scene);
        widget = (VMDNodeWidget) this.scene.addNode(nodeID);
        widget.setPreferredLocation(new Point(x, y));
        widget.setNodeProperties(image, nodeID, component.getModule(), glyphs);
        scene.addPin(nodeID, nodeID + VMDGraphScene.PIN_ID_DEFAULT_SUFFIX);
        // System.out.println("nodeID : " + nodeID);
        
    }
    
    public VMDNodeWidget getWidget() {
        return widget;
    }
    
    public void setWidget(VMDNodeWidget widget) {
        this.widget = widget;
    }
    
    public Image getImage() {
        return image;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getNodeID() {
        return nodeID;
    }
    
    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }
    
    public Component getComponent() {
        return component;
    }

    public void setVisible(boolean visible) {
        widget.setVisible(visible);
    }
}
