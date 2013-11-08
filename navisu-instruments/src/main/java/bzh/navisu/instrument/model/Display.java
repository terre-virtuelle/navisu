 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.instrument.model;

import org.navisu.widget.model.Widget;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Control;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuButtonBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.navisu.instrument.controller.events.QuitEventListener;
import org.navisu.instrument.controller.events.DayNightEvent;
import org.navisu.instrument.controller.events.DayNightEventListener;
import org.navisu.instrument.controller.events.QuitEvent;

/**
 *
 * @author Serge
 */
public class Display
        extends Widget
        implements DayNightEventListener {

    protected String rootDir = null;
    private String backgroundFileName;
    private Image backgroundImage;
    protected ImageView background;
    protected final String IMAGES = "/resources/images/";
    protected final String STYLE = "/resources/style/";
    private final String BACKGROUND_ID = "background";
    private String style = null;
    private final String MENU_ID = "menu";
    private final String PAGES_ID = "pages";
    private final String DAY_NIGHT_ID = "daynight";
    private final String QUIT_ID = "quit";
    private final String SUFFIX = ".png";
    private String id;
    private double initX;
    private double initY;
    private Point2D dragAnchor;
    protected final int LAYOUT_X = 137;
    protected final int LAYOUT_Y = 137;
    protected Button pageButton;
    protected Button dayNightButton;
    protected Button quitButton;
    protected MenuButton menuButton;
    protected boolean day = true;
    private static String version = "0.9_8";
    private List<DayNightEventListener> dayNightEventListeners;
    private List<QuitEventListener> quitEventListeners;

    public Display() {
    }

    public Display(final String id, final String backgroundFileName) {
        this.id = id;
        this.backgroundFileName = backgroundFileName;
        dayNightEventListeners = new ArrayList<>();
        quitEventListeners = new ArrayList<>();
        rootDir = getClass().getPackage().getName().replace(".", "/");
        createScene();
    }

    @Override
    protected void createScene() {

        fireDayNightEvent(new DayNightEvent(this, day));
        
        backgroundImage = new Image(rootDir + IMAGES + backgroundFileName);
        background = ImageViewBuilder.create()
                .id(BACKGROUND_ID)
                .pickOnBounds(true)
                .build();
        background.setImage(backgroundImage);
        getChildren().add(background);

        pageButton = ButtonBuilder.create()
                .id(PAGES_ID)
                .layoutX(LAYOUT_X - 20)
                .layoutY(LAYOUT_Y + 118)
                .minWidth(Control.USE_PREF_SIZE)
                .prefWidth(42.0)
                .minHeight(Control.USE_PREF_SIZE)
                .prefHeight(10.0)
                .graphic(new ImageView(new Image(rootDir + IMAGES + PAGES_ID + SUFFIX)))
                .build();
        getChildren().add(pageButton);

        dayNightButton = ButtonBuilder.create()
                .id(DAY_NIGHT_ID)
                .layoutX(LAYOUT_X - 92)
                .layoutY(LAYOUT_Y + 118)
                .minWidth(Control.USE_PREF_SIZE)
                .prefWidth(42.0)
                .minHeight(Control.USE_PREF_SIZE)
                .prefHeight(10.0)
                .graphic(new ImageView(new Image(rootDir + IMAGES + DAY_NIGHT_ID + SUFFIX)))
                .build();
        getChildren().add(dayNightButton);
        menuButton = MenuButtonBuilder.create()
                .id(MENU_ID)
                .layoutX(LAYOUT_X + 50)
                .layoutY(LAYOUT_Y + 118)
                .minWidth(Control.USE_PREF_SIZE)
                .prefWidth(42.0)
                .minHeight(Control.USE_PREF_SIZE)
                .prefHeight(10.0)
                .build();
        ImageView imageView = new ImageView(new Image(rootDir + IMAGES + MENU_ID + SUFFIX));
        imageView.setTranslateX(-11);
        menuButton.setGraphic(imageView);
        getChildren().add(menuButton);

        quitButton = ButtonBuilder.create()
                .id(QUIT_ID)
                .minWidth(Control.USE_PREF_SIZE)
                .prefWidth(1.0)
                .minHeight(Control.USE_PREF_SIZE)
                .prefHeight(1.0)
                .rotate(40)
                .graphic(new ImageView(new Image(rootDir + IMAGES + QUIT_ID + SUFFIX)))
                .layoutX(LAYOUT_X + 115)
                .layoutY(20)
                .build();
        getChildren().add(quitButton);
        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                fireQuitEvent(new QuitEvent(Display.this, true));
            }
        });

    }

    public void setDay(boolean dayOrNight) {
        day = dayOrNight;
        fireDayNightEvent(new DayNightEvent(this, dayOrNight));
    }

    public MenuButton getMenuButton() {
        return menuButton;
    }

    public Button getDayNightButton() {
        return dayNightButton;
    }

    public boolean isDay() {
        return day;
    }

    public static String getVersion() {
        return version;
    }

    public void addDayNightEventListener(DayNightEventListener listener) {
        dayNightEventListeners.add(listener);

    }

    public void removeDayNightEventListener(DayNightEventListener listener) {
        dayNightEventListeners.remove(listener);
    }

    public void fireDayNightEvent(DayNightEvent evt) {
        for (DayNightEventListener s : dayNightEventListeners) {
            s.update(evt);
        }
    }

    public void addQuitEventListener(QuitEventListener listener) {
        quitEventListeners.add(listener);

    }

    public void removeQuitEventListener(QuitEventListener listener) {
        quitEventListeners.remove(listener);
    }

    protected void fireQuitEvent(QuitEvent evt) {
        for (QuitEventListener s : quitEventListeners) {
            s.update(evt);
        }
    }

    public void setLocation(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
    }

    @Override
    public void update(DayNightEvent event) {
    }
}
