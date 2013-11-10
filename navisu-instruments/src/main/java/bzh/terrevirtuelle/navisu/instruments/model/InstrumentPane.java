/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.model;

import java.util.HashMap;
import java.util.Map;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Serge Morvan
 */
public class InstrumentPane
        extends Group {

    protected Display display;
    protected MenuButton menuButton;
    protected String rootDir = null;
    protected String backgroundFileName;
    protected String foregroundFileName;
    protected String backgroundNightFileName;
    protected String foregroundNightFileName;
    protected Image backgroundImage;
    protected Image foregroundImage;
    protected ImageView background;
    protected ImageView foreground;
    protected int LAYOUT_X = 137;
    protected int LAYOUT_Y = 137;
    protected Map<String, String> attibutes;
    protected Group root;
    protected final String IMAGES = "/resources/images/";
    protected final String STYLE = "/resources/style/";
    protected final String FONTS = "resources/fonts/";

    public InstrumentPane(Display display) {
        this.display = display;
        attibutes = new HashMap<>();
    }

    public Map<String, String> getAttributes() {
        return attibutes;
    }
    protected final void createBasicScene(){
        root = new Group();
        backgroundImage = new Image(rootDir + IMAGES + backgroundFileName);
        background = ImageViewBuilder.create()
                .layoutX(28)
                .layoutY(26)
                .pickOnBounds(true)
                .image(backgroundImage)
                .build();
        root.getChildren().add(background);
        foregroundImage = new Image(rootDir + IMAGES + foregroundFileName);
        foreground = ImageViewBuilder.create()
                .layoutX(28)
                .layoutY(28)
                .pickOnBounds(true)
                .image(foregroundImage)
                .build();
        root.getChildren().add(foreground);
        getChildren().add(root);
    }
    public  void initPage() {
        if (display.isDay() == true) {
            backgroundImage = new Image(rootDir + IMAGES + backgroundFileName);
        } else {
            backgroundImage = new Image(rootDir + IMAGES + backgroundNightFileName);
        }
        background.setImage(backgroundImage);
        display.getDayNightButton().setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (display.isDay() == true) {
                    backgroundImage = new Image(rootDir + IMAGES + backgroundNightFileName);
                    display.setDay(false);
                } else {
                    backgroundImage = new Image(rootDir + IMAGES + backgroundFileName);
                    display.setDay(true);
                }
                background.setImage(backgroundImage);
            }
        });   
    }
}
