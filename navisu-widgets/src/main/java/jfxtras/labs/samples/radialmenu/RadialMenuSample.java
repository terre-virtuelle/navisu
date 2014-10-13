/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxtras.labs.samples.radialmenu;

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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.LinearGradientBuilder;
import javafx.scene.paint.StopBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.labs.samples.JFXtrasLabsSampleBase;
import jfxtras.labs.scene.control.radialmenu.RadialContainerMenuItem;
import jfxtras.labs.scene.control.radialmenu.RadialMenu;
import jfxtras.labs.scene.control.radialmenu.RadialMenuItem;

/**
 *
 * @author MrLoNee
 */
public class RadialMenuSample extends JFXtrasLabsSampleBase {

    protected RadialMenu radialMenu;
    protected Label actionPerformedLabel = new Label();
    protected boolean show;
    protected double lastOffsetValue;
    protected double lastInitialAngleValue;
    private double gestureAngle = 0;
    private Slider initialAngleSlider;
    private FadeTransition textFadeTransition;

    public RadialMenuSample() {
        radialMenu = this.createRadialMenu();
    }

    // private void hideRadialMenu() {
    // this.lastInitialAngleValue = this.radialMenu.getInitialAngle();
    // this.lastOffsetValue = this.radialMenu.getOffset();
    //
    // final FadeTransition fade = FadeTransitionBuilder.create()
    // .node(this.radialMenu).fromValue(1).toValue(0)
    // .duration(Duration.millis(300))
    // .onFinished(new EventHandler<ActionEvent>() {
    //
    // @Override
    // public void handle(final ActionEvent arg0) {
    // RadialMenuSample.this.radialMenu.setVisible(false);
    // }
    // }).build();
    //
    // final ParallelTransition transition = ParallelTransitionBuilder
    // .create().children(fade).build();
    //
    // transition.play();
    // }
    private void hideRadialMenu() {
        final FadeTransition fade = FadeTransitionBuilder.create()
                .node(RadialMenuSample.this.radialMenu).fromValue(1).toValue(0)
                .duration(Duration.millis(300))
                .onFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(final ActionEvent arg0) {
                        RadialMenuSample.this.radialMenu.setVisible(false);
                    }
                }).build();

        final ParallelTransition transition = ParallelTransitionBuilder
                .create().children(fade).build();

        transition.play();
    }

    private void showRadialMenu(final double x, final double y) {
        if (this.radialMenu.isVisible()) {
            this.lastInitialAngleValue = this.radialMenu.getInitialAngle();
            this.lastOffsetValue = this.radialMenu.getOffset();
            this.radialMenu.setVisible(false);
        }
        this.radialMenu.setTranslateX(x);
        this.radialMenu.setTranslateY(y);
        this.radialMenu.setVisible(true);

        final FadeTransition fade = FadeTransitionBuilder.create()
                .node(this.radialMenu).duration(Duration.millis(400))
                .fromValue(0).toValue(1.0).build();

        final Animation offset = new Timeline(new KeyFrame(Duration.ZERO,
                new KeyValue(this.radialMenu.offsetProperty(), 0)),
                new KeyFrame(Duration.millis(300), new KeyValue(this.radialMenu
                                .offsetProperty(), this.lastOffsetValue)));

        final Animation angle = new Timeline(new KeyFrame(Duration.ZERO,
                new KeyValue(this.radialMenu.initialAngleProperty(),
                        this.lastInitialAngleValue + 20)), new KeyFrame(
                        Duration.millis(300), new KeyValue(
                                this.radialMenu.initialAngleProperty(),
                                this.lastInitialAngleValue)));

        final ParallelTransition transition = ParallelTransitionBuilder
                .create().children(fade, offset, angle).build();

        transition.play();
    }

    public RadialMenu createRadialMenu() {

        final LinearGradient background = LinearGradientBuilder
                .create()
                .startX(0)
                .startY(0)
                .endX(1.0)
                .endY(1.0)
                .cycleMethod(CycleMethod.NO_CYCLE)
                .stops(StopBuilder.create().offset(0.0).color(Color.LIGHTGREY)
                        .build(),
                        StopBuilder.create().offset(0.6)
                        .color(Color.LIGHTGREY.darker()).build())
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
                        .color(Color.LIGHTGREY.darker()).build())
                .build();

        this.radialMenu = new RadialMenu(-37, 30, 100, 5, background,
                backgroundMouseOn, Color.DARKGREY.darker().darker(),
                Color.DARKGREY.darker(), false, RadialMenu.CenterVisibility.ALWAYS, null);

        this.radialMenu.setTranslateX(200);
        this.radialMenu.setTranslateY(200);

        final ImageView play = ImageViewBuilder
                .create()
                .image(new Image(this.getClass().getResourceAsStream(
                                        "RadialMenuSamplePlayIcon.png"))).build();

        final ImageView stop = ImageViewBuilder
                .create()
                .image(new Image(this.getClass().getResourceAsStream(
                                        "RadialMenuSampleStopIcon.png"))).build();
        final ImageView pause = ImageViewBuilder
                .create()
                .image(new Image(this.getClass().getResourceAsStream(
                                        "RadialMenuSamplePauseIcon.png"))).build();

        final ImageView forward = ImageViewBuilder
                .create()
                .image(new Image(this.getClass().getResourceAsStream(
                                        "RadialMenuSampleForwardIcon.png"))).build();

        final ImageView backward = ImageViewBuilder
                .create()
                .image(new Image(this.getClass().getResourceAsStream(
                                        "RadialMenuSampleBackwardIcon.png"))).build();

        final EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {

            @Override
            public synchronized void handle(final ActionEvent paramT) {
                final RadialMenuItem item = (RadialMenuItem) paramT.getSource();
                if (RadialMenuSample.this.textFadeTransition != null
                        && RadialMenuSample.this.textFadeTransition.getStatus() != Animation.Status.STOPPED) {
                    RadialMenuSample.this.textFadeTransition.stop();
                    RadialMenuSample.this.actionPerformedLabel.setOpacity(1.0);
                }
                RadialMenuSample.this.actionPerformedLabel.setText(item
                        .getText());
                RadialMenuSample.this.actionPerformedLabel.setVisible(true);
                RadialMenuSample.this.textFadeTransition = FadeTransitionBuilder
                        .create()
                        .node(RadialMenuSample.this.actionPerformedLabel)
                        .delay(Duration.seconds(1))
                        .duration(Duration.millis(400)).fromValue(1.0)
                        .toValue(0).onFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(final ActionEvent paramT) {
                                RadialMenuSample.this.actionPerformedLabel
                                .setVisible(false);
                                RadialMenuSample.this.actionPerformedLabel
                                .setOpacity(1.0);
                            }
                        }).build();
                RadialMenuSample.this.textFadeTransition.play();
                System.out.println("............");
            }

        };

        final ImageView fiveSec = ImageViewBuilder
                .create()
                .image(new Image(this.getClass().getResourceAsStream(
                                        "RadialMenuSample5SecIcon.png"))).build();
        final ImageView tenSec = ImageViewBuilder
                .create()
                .image(new Image(this.getClass().getResourceAsStream(
                                        "RadialMenuSample10SecIcon.png"))).build();
        final ImageView twentySec = ImageViewBuilder
                .create()
                .image(new Image(this.getClass().getResourceAsStream(
                                        "RadialMenuSample20SecIcon.png"))).build();
        final RadialContainerMenuItem forwardItem = new RadialContainerMenuItem(
                50, "forward", forward);
        forwardItem.addMenuItem(new RadialMenuItem(30, "forward 5'", fiveSec,
                handler));
        forwardItem.addMenuItem(new RadialMenuItem(30, "forward 10'", tenSec,
                handler));
        forwardItem.addMenuItem(new RadialMenuItem(30, "forward 20'",
                twentySec, handler));

        this.radialMenu.addMenuItem(forwardItem);

        this.radialMenu.addMenuItem(new RadialMenuItem(50, "pause", pause,
                handler));

        this.radialMenu.addMenuItem(new RadialMenuItem(50, "play", play,
                handler));

        this.radialMenu.addMenuItem(new RadialMenuItem(50, "stop", stop,
                handler));

        final RadialContainerMenuItem backwardItem = new RadialContainerMenuItem(
                50, "backward", backward);
        final ImageView fiveSecBack = ImageViewBuilder
                .create()
                .image(new Image(this.getClass().getResourceAsStream(
                                        "RadialMenuSample5SecIcon.png"))).build();
        final ImageView tenSecBack = ImageViewBuilder
                .create()
                .image(new Image(this.getClass().getResourceAsStream(
                                        "RadialMenuSample10SecIcon.png"))).build();
        final ImageView twentySecBack = ImageViewBuilder
                .create()
                .image(new Image(this.getClass().getResourceAsStream(
                                        "RadialMenuSample20SecIcon.png"))).build();
        backwardItem.addMenuItem(new RadialMenuItem(30, "backward 5'",
                fiveSecBack, handler));
        backwardItem.addMenuItem(new RadialMenuItem(30, "bacward 10'",
                tenSecBack, handler));
        backwardItem.addMenuItem(new RadialMenuItem(30, "bacward 20'",
                twentySecBack, handler));

        this.radialMenu.addMenuItem(backwardItem);

        return this.radialMenu;
    }

    @Override
    public String getSampleName() {
        return getClass().getSimpleName();
    }

    @Override
    public Node getPanel(final Stage stage) {
        AnchorPane root = new AnchorPane();
        root.setPrefSize(600, 600);
        root.getChildren().addAll(radialMenu);
        return root;

    }

    @Override
    public String getJavaDocURL() {
        return "http://jfxtras.org/doc/8.0labs/jfxtras/labs/scene/control/radialmenu/RadialMenu.html";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
