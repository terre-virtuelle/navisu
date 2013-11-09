/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.widget.view.radialMenu;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.LinearGradientBuilder;
import javafx.scene.paint.StopBuilder;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import jfxtras.labs.scene.control.radialmenu.RadialContainerMenuItem;
import jfxtras.labs.scene.control.radialmenu.RadialMenu;
import jfxtras.labs.scene.control.radialmenu.RadialMenuItem;
import bzh.terrevirtuelle.navisu.instruments.instrument.controller.InstrumentsGlassPaneController;
import bzh.terrevirtuelle.navisu.instruments.instrument.controller.simulator.SounderSimulator;
import bzh.terrevirtuelle.navisu.instruments.instrument.view.sounder.Sounder;
import bzh.terrevirtuelle.navisu.instruments.widget.model.Widget;

public class WRadialMenu extends Widget {
    
    protected RadialMenu radialMenu;
    protected boolean show;
    protected double lastOffsetValue;
    protected double lastInitialAngleValue;
    private double gestureAngle = 0;
    public Double menuSize = 15.0;
    public Double containerSize = 30.0;
    public Double initialAngle = 90.0;
    public Double innerRadius = 50.0;
    public Double radius = 150.0;
    public Double offset = 5.0;
    private Slider initialAngleSlider;
    private InstrumentsGlassPaneController intrumentsGlassPaneController;
    
    public WRadialMenu(InstrumentsGlassPaneController intrumentsGlassPaneController) {
        this.intrumentsGlassPaneController = intrumentsGlassPaneController;
        createScene();
    }
    
    @Override
    protected void createScene() {
        createRadialMenu();
        getChildren().add(radialMenu);
    }
    
    public void createRadialMenu() {
        // Color slightlyTrans = new Color(0.4023, 0.4687, 0.4062, 0.7);
        Color slightlyTrans = new Color(0.09411, 0.31372, 0.31764, 0.7);
        final LinearGradient transBackground = LinearGradientBuilder
                .create()
                .startX(0)
                .startY(0)
                .endX(1.0)
                .endY(1.0)
                .cycleMethod(CycleMethod.NO_CYCLE)
                .stops(StopBuilder.create().offset(0.0).color(slightlyTrans)
                .build(),
                StopBuilder.create().offset(0.6)
                .color(slightlyTrans).build())
                .build();
        
        final LinearGradient backgroundMouseOn = LinearGradientBuilder
                .create()
                .startX(0)
                .startY(0)
                .endX(1.0)
                .endY(1.0)
                .cycleMethod(CycleMethod.NO_CYCLE)
                .stops(StopBuilder.create().offset(0.0).color(Color.LIGHTGREY)
                .build(),
                StopBuilder.create().offset(0.8)
                .color(slightlyTrans.darker()).build())
                .build();
        Circle circle = new Circle(20.0,new Color(1.0, 0., 0., 1.0));
        circle.setFill(new Color(1.0, 0., 0., 1.0));

        radialMenu = new RadialMenu(initialAngle, innerRadius, radius, offset,
                transBackground, backgroundMouseOn,
                slightlyTrans.darker().darker(), slightlyTrans.darker(),
                false, RadialMenu.CenterVisibility.ALWAYS, new Circle(20.0,new Color(1.0, 0., 0., 1.0)));//OK
              //  false, RadialMenu.CenterVisibility.ALWAYS, circle);//KO
        radialMenu.setTranslateX(400);
        radialMenu.setTranslateY(400);
        
        Image image = new Image(getClass().getResourceAsStream("resources/images/cardinalWest.png"));
        
        ImageView filler = ImageViewBuilder.create()
                .image(image)
                .build();
        
        RadialContainerMenuItem level1Container = new RadialContainerMenuItem(containerSize, "Level 1 Container", null);
        
       final RadialMenuItemSP level1Item = new RadialMenuItemSP(menuSize,
                "Sounder",
                filler,
                true,
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Sounder sounder = new Sounder();
                intrumentsGlassPaneController.addInstrument(sounder);
                intrumentsGlassPaneController.startParallelTransition(sounder, 0f, 0f, 200f, 200f, 0.0f, 0.0f, 1.f, 1.f);
                new SounderSimulator(sounder, 5000.0f, 3.0f).execute();
            }
        });
        
        level1Item.getGraphic().setScaleX(4);
        level1Item.getGraphic().setScaleY(4);
        
      
        RadialContainerMenuItem level2Container = new RadialContainerMenuItem(containerSize, "Level 2 Container", null);
        RadialMenuItem level2Item = new RadialMenuItem(menuSize, "B", null);
        
        RadialContainerMenuItem level3Container = new RadialContainerMenuItem(containerSize, "Level 3 Container", null);
        
        
        
        Circle circle1 = new Circle();
        circle1.setCenterX(10.0f);
        circle1.setCenterY(10.0f);
        circle1.setRadius(10.0f);
        
        RadialMenuItem level3Item = new RadialMenuItemSP(menuSize,
                "level 3 item",
                circle,
                true,
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("level 3 item");
            }
        });
        
        RadialMenuItem level4Item = new RadialMenuItemSP(menuSize,
                "level 4 item",
                new Circle(2.0),
                true,
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.println("level 4 item");
            }
        });
        level3Item.setOpacity(1.0);
        //Add all your items in a nested order      
        level1Container.addMenuItem(level2Item);
        level1Container.addMenuItem(level2Container);

        //level2Container.addMenuItem(level3Item);
        //level2Container.addMenuItem(level3Container);

        level3Container.addMenuItem(level4Item);
        
        radialMenu.addMenuItem(level1Item);
       // radialMenu.addMenuItem(level1Container);
        //   radialMenu.addMenuItem(level3Item);
       // radialMenu.addMenuItem(level3Container);
        ((Path)level1Item.getChildren().get(0)).setFill(new Color(1.0,0.0,0.0,1.0));
        radialMenu.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
              ((Path)level1Item.getChildren().get(0)).setFill(new Color(1.0,0.0,0.0,1.0));  
            }
        });
       
    }
    
    private void hideRadialMenu() {
        final FadeTransition fade = FadeTransitionBuilder.create()
                .node(this.radialMenu).fromValue(1).toValue(0)
                .duration(Duration.millis(300))
                .onFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent arg0) {
                setVisible(false);
            }
        }).build();
        
        final ParallelTransition transition = ParallelTransitionBuilder
                .create().children(fade).build();
        
        transition.play();
    }
    
    private void showRadialMenu(final double x, final double y) {
        if (radialMenu.isVisible()) {
            lastInitialAngleValue = radialMenu.getInitialAngle();
            lastOffsetValue = radialMenu.getOffset();
            radialMenu.setVisible(false);
        }
        radialMenu.setTranslateX(x);
        radialMenu.setTranslateY(y);
        radialMenu.setVisible(true);
        
        final FadeTransition fade = FadeTransitionBuilder.create()
                .node(radialMenu).duration(Duration.millis(400))
                .fromValue(0).toValue(1.0).build();
        
        final Animation offset = new Timeline(new KeyFrame(Duration.ZERO,
                new KeyValue(radialMenu.offsetProperty(), 0)),
                new KeyFrame(Duration.millis(300), new KeyValue(radialMenu
                .offsetProperty(), lastOffsetValue)));
        
        final Animation angle = new Timeline(new KeyFrame(Duration.ZERO,
                new KeyValue(radialMenu.initialAngleProperty(),
                lastInitialAngleValue + 20)), new KeyFrame(
                Duration.millis(300), new KeyValue(
                radialMenu.initialAngleProperty(),
                lastInitialAngleValue)));
        
        final ParallelTransition transition = ParallelTransitionBuilder
                .create().children(fade, offset, angle).build();
        
        transition.play();
    }
}
