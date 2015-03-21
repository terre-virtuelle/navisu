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

    private double gap = 5;
    private double length = 360;
    private double innerRadius = 70;
    private double outerRadius = 130;
    private final ObservableList<RadialMenuItem> rootItems = FXCollections.observableArrayList();
    private final List<RadialMenuContainer> containers0 = new ArrayList<>();
    private final List<RadialMenuContainer> containers1 = new ArrayList<>();
    private final List<RadialMenuItem> items = new ArrayList<>();
    private ImageView centerImage;
    private RadialMenuContainer radialMenuContainer0;
    private RadialMenuContainer radialMenuContainer1;

    public RadialMenuBuilder() {
    }

    public static RadialMenuBuilder create() {
        return new RadialMenuBuilder();
    }

    public RadialMenu build() {
        containers0.stream().forEach((r) -> {
            rootItems.add(r);
        });
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

    public RadialMenuBuilder stageItem(int stageId, String imgStage,
            int itemId, String imgItem,
            EventHandler<MouseEvent> callback) {
        RadialMenuItem radialMenuItem = new RadialMenuItem(callback);
        radialMenuItem.setImage(new ImageView(new Image(getClass().getResourceAsStream(imgItem))));

        if (stageId < containers0.size()) {
            radialMenuContainer0 = containers0.get(stageId);
        } else {
            radialMenuContainer0 = new RadialMenuContainer();
            containers0.add(stageId, radialMenuContainer0);
            radialMenuContainer0.setImage(new ImageView(new Image(getClass().getResourceAsStream(imgStage))));
        }
        radialMenuContainer0.addItem(radialMenuItem);
        return this;
    }

    public RadialMenuBuilder stageItem(int stage0Id, String img0Stage,
            int stage1Id, String img1Stage,
            int itemId, String imgItem,
            EventHandler<MouseEvent> callback) {

        RadialMenuItem radialMenuItem = new RadialMenuItem(callback);
        radialMenuItem.setImage(new ImageView(new Image(getClass().getResourceAsStream(imgItem))));

        if (stage1Id < containers1.size()) {
            radialMenuContainer1 = containers1.get(stage1Id);
        } else {
            radialMenuContainer1 = new RadialMenuContainer();
            containers1.add(stage1Id, radialMenuContainer1);
            radialMenuContainer1.setImage(new ImageView(new Image(getClass().getResourceAsStream(img1Stage))));
        }
        radialMenuContainer1.addItem(radialMenuItem);
        
        if (stage0Id < containers0.size()) {
            radialMenuContainer0 = containers0.get(stage0Id);
        } else {
            radialMenuContainer0 = new RadialMenuContainer();
            containers0.add(stage0Id, radialMenuContainer0);
            radialMenuContainer0.setImage(new ImageView(new Image(getClass().getResourceAsStream(img0Stage))));
        }
        radialMenuContainer0.addItem(radialMenuContainer1);
        
        
        
        return this;
    }

    public RadialMenuBuilder stageItem(int stageId,
            int itemId, String imgItem,
            EventHandler<MouseEvent> callback) {
        return stageItem(stageId, null, itemId, imgItem, callback);
    }

    public RadialMenuBuilder centralImage(String img) {
        this.centerImage = new ImageView(new Image(getClass().getResourceAsStream(img)));
        centerImage.setLayoutX((-centerImage.getImage().getWidth() / 2));
        centerImage.setLayoutY((-centerImage.getImage().getHeight() / 2));
        return this;
    }
}
