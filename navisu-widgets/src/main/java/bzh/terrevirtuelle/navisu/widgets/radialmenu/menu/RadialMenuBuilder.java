/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.radialmenu.menu;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * @date 20 mars 2015
 * @author Serge Morvan
 */
public class RadialMenuBuilder {
    
    private double gap = 2;
    private double length = 360;
    private double innerRadius = 70;
    private double outerRadius = 130;
    private final ObservableList<RadialMenuItem> rootItems = FXCollections.observableArrayList();
    private final List<List<RadialMenuContainer>> containers = new ArrayList<>();
    private final List<RadialMenuContainer> roots = new ArrayList<>();
    private final int NUM = 5;
    private ImageView centerImage;
    
    public RadialMenuBuilder() {
        for (int i = 0; i < NUM; i++) {
            List<RadialMenuContainer> l = new ArrayList<>();
            for (int j = 0; j < NUM; j++) {
                l.add(new RadialMenuContainer());
            }
            containers.add(l);
            roots.add(new RadialMenuContainer());
        }
    }
    
    public static RadialMenuBuilder create() {
        return new RadialMenuBuilder();
    }
    
    public RadialMenu build() {
        for (int i = 0; i < NUM; i++) {
            if (roots.get(i).getChildren().size() > 1) {
                rootItems.add(roots.get(i));
                for (int j = 0; j < NUM; j++) {
                    if (containers.get(i).get(j).getChildren().size() > 1) {
                        roots.get(i).addItem(containers.get(i).get(j));
                    }
                }
            }
        }
        RadialMenu radialMenu = new RadialMenu(innerRadius, outerRadius, length, gap, rootItems);
        radialMenu.getChildren().add(centerImage);
        radialMenu.setManaged(false);
        radialMenu.setVisible(false);
        return radialMenu;
    }
    
    public RadialMenuBuilder gap(double gap) {
        this.gap = gap;
        return this;
    }
    
    public RadialMenuBuilder length(double length) {
        this.length = length;
        return this;
    }
    
    public RadialMenuBuilder innerRadius(double innerRadius) {
        this.innerRadius = innerRadius;
        return this;
    }
    
    public RadialMenuBuilder outerRadius(double outerRadius) {
        this.outerRadius = outerRadius;
        return this;
    }
    
    public RadialMenuBuilder createNode(int idRoot, String imgRoot,
            int idChild, String imgChild,
            int idItem, String imgItem,
            EventHandler<MouseEvent> callback) {
        roots.get(idRoot).setImage(new ImageView(new Image(getClass().getResourceAsStream(imgRoot))));
        
        RadialMenuItem radialMenuItem = new RadialMenuItem(callback);
        radialMenuItem.setImage(new ImageView(new Image(getClass().getResourceAsStream(imgItem))));
        
        RadialMenuContainer r = containers.get(idRoot).get(idChild);
        r.setImage(new ImageView(new Image(getClass().getResourceAsStream(imgChild))));
        r.addItem(radialMenuItem);
        
        return this;
    }
    
    public RadialMenuBuilder centralImage(String img) {
        this.centerImage = new ImageView(new Image(getClass().getResourceAsStream(img)));
        centerImage.setLayoutX((-centerImage.getImage().getWidth() / 2));
        centerImage.setLayoutY((-centerImage.getImage().getHeight() / 2));
        return this;
    }
}
