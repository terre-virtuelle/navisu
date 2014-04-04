package bzh.terrevirtuelle.navisu.app.guiagent.dock;

import bzh.tibus.javafx.tools.AnimationFactory;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * NaVisu
 *
 * @author tibus
 * @date 08/03/2014 17:07
 */
public class Main extends Application {

    public static final DockItem[] ICONS = new DockItem[]{
        DockItemFactory.newImageItem("AIS", "AIS.png", (e) -> System.out.println("AIS")),
        DockItemFactory.newImageItem("GPS", "GPS.png", (e) -> System.out.println("GPS")),
        DockItemFactory.newImageItem("Compass", "compass.png", (e) -> System.out.println("Compass")),
        DockItemFactory.newImageItem("Sounder", "sounder.png", (e) -> System.out.println("Sounder"))
    };

    @Override

    public void start(Stage stage) throws Exception {
        final ImageView basedock = new ImageView("dock3.png");
        final StackPane root = new StackPane();
        final Scene scene = new Scene(root, 600, 400, Color.ANTIQUEWHITE);
        final Dock dock = new Dock(ICONS);
        final Group group = new Group();

        group.getChildren().add(basedock);
        group.getChildren().add(dock);
        root.getChildren().add(group);
        dock.setLayoutX(85.0);
        dock.setLayoutY(35.0);
        dock.setOrientation(Orientation.HORIZONTAL);
        
        Animation downAnimation = AnimationFactory.newTranslateAnimation(group, 200, 300);
        Animation upAnimation = AnimationFactory.newTranslateAnimation(group, 200, 0);
        scene.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode() == KeyCode.DOWN) {
                downAnimation.play();
            }if (ke.getCode() == KeyCode.UP) {
                upAnimation.play();
            }
        });
         
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
