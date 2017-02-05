package bzh.terrevirtuelle.navisu.texteditor.app;


import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PageFlipper extends Application {
    
    private static final double WIDTH = 1024;
    private static final double HEIGHT = 680;
    private static final int COUNT = 16;
    
    @Override
    public void start(Stage stage) {
        Group root = new Group();
        List<Node> images = new ArrayList<>();
        IntStream.range(0, COUNT)
                .forEach(n -> {
                    ImageView image;
                    try {
                        image = new ImageView(new Image(getClass().getResource("images/image" + n + ".png").toURI().toString()));
                        images.add(image);
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(PageFlipper.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
        for (int i = images.size() - 1; i >= 0; i--) {
            root.getChildren().add(images.get(i));
        }
        FlipTransition transition = new FlipTransition(WIDTH, HEIGHT);
        root.setOnMousePressed(e -> {
            transition.play(root);
        });
        stage.setResizable(false);
        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.show();
    }
    
    public static void main(String... args) {
        launch(args);
    }
}
