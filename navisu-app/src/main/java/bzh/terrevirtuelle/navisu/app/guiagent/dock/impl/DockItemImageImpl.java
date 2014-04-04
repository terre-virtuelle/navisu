package bzh.terrevirtuelle.navisu.app.guiagent.dock.impl;


import bzh.terrevirtuelle.navisu.app.guiagent.dock.DockItem;
import bzh.tibus.javafx.tools.AnimationFactory;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Objects;

/**
 * NaVisu
 *
 * @author tibus
 * @date 08/03/2014 17:20
 */


public class DockItemImageImpl implements DockItem {

    public static final double DEFAULT_SIZE = 90.0;

    protected StackPane container;

    protected Text nameText;
    protected ParallelTransition nameAnimation;

    protected Rectangle nameTextBack;

    protected ImageView icon;
    protected Circle blurCircle;
    protected Rectangle back;
    protected Reflection reflection;
    protected Animation hoverAnimation;

    protected double size = DEFAULT_SIZE;
    protected double padding = 15;

    public DockItemImageImpl(String name, String iconPath, EventHandler<MouseEvent> callback) {

        Objects.requireNonNull(iconPath);
        // init reflection
        this.reflection = new Reflection();
        this.reflection.setFraction(0.30);
        this.reflection.setTopOffset(-10.0);

        // init. name text
        this.nameText = new Text(name);
        this.nameText.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        this.nameText.setFill(Color.WHITESMOKE);
        this.nameText.setStroke(Color.BLACK);
        this.nameText.setStrokeWidth(0.2);
        this.nameText.setTranslateX(-10);
        this.nameText.setTranslateY(-60);
        this.nameText.setOpacity(0);
        this.nameText.snapshot(null, null);

        // init. name text background
        this.nameTextBack = new Rectangle(this.nameText.getLayoutBounds().getWidth() + 10, 20, Color.BLACK);
        this.nameTextBack.setOpacity(0);
        this.nameTextBack.setTranslateX(-10);
        this.nameTextBack.setTranslateY(-60);
        this.nameTextBack.setArcHeight(15);
        this.nameTextBack.setArcWidth(15);

        this.nameAnimation = new ParallelTransition(
                AnimationFactory.newFadeAnimation(this.nameText, 300, 0, 1),
                AnimationFactory.newFadeAnimation(this.nameTextBack, 300, 0, 0.25)
        );
 System.out.println("iconPath " + iconPath);
        // init. icon
        this.icon = new ImageView(iconPath);
       
        this.icon.setFitWidth(size);
        this.icon.setFitHeight(size);
        this.icon.setEffect(reflection);

        this.icon.setOnMouseClicked(callback);

        // init. background (invisible but better click handle)
        this.back = new Rectangle(size + padding, size + padding, new Color(1, 1, 1, 0.01));
        this.back.setOnMouseClicked(callback);

        // init. blur circle
        this.blurCircle = new Circle(20d, Color.WHITESMOKE);

        BoxBlur blur = new BoxBlur();
        blur.setIterations(10);
        blur.setHeight(25);
        blur.setWidth(25);

        this.blurCircle.setEffect(blur);
        this.blurCircle.setOpacity(0);

        this.container = new StackPane(
                this.blurCircle,
                this.icon,
                this.back,
                this.nameTextBack,
                this.nameText
        );

        // create hover time line
        this.hoverAnimation = AnimationFactory.newScaleAnimation(this.icon, 500, 1.50d, 1.50d, 1);
        
        // initialize mouse events
        this.back.setOnMouseEntered((e) -> this.playHoverAnimation());
        this.back.setOnMouseExited((e) -> this.stopHoverAnimation());

        this.back.setOnMousePressed((e) -> this.setMousePressed(true));
        this.back.setOnMouseReleased((e) -> this.setMousePressed(false));
    }

    public final void playHoverAnimation() {
        this.hoverAnimation.play();
        this.blurCircle.setOpacity(1);

        this.nameAnimation.play();
    }

    public final void stopHoverAnimation() {
        this.hoverAnimation.stop();
        this.blurCircle.setOpacity(0);
        this.icon.setRotate(0);
        this.icon.setScaleX(1);
         this.icon.setScaleY(1);

        this.nameAnimation.stop();
        this.nameText.setOpacity(0);
        this.nameTextBack.setOpacity(0);
    }

    public final void setMousePressed(boolean pressed) {

        if (pressed) {
            this.blurCircle.setOpacity(0);
        } else {
            this.blurCircle.setOpacity(1);
        }
    }

    @Override
    public Node getDisplayNode() {
        return this.container;
    }

    @Override
    public double getSize() {
        return this.size + padding;
    }
}
