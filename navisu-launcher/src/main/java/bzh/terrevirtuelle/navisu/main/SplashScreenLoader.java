/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.main;

import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author serge
 * @date Apr 26, 2016
 *
 */
public class SplashScreenLoader extends Preloader {

    ProgressBar bar;
    Stage stage;

    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();

    private Scene createPreloaderScene() {
        bar = new ProgressBar();
        StackPane psplash = new StackPane();
        Pane splashscreen = new Pane();
        String splashstyle = CSS_STYLE_PATH + "splash.css";
        psplash.getStylesheets().add(splashstyle);
        splashscreen.setId("psplashscreen");
        splashscreen.setPrefSize(400, 420);
        splashscreen.getChildren().add(bar);
        bar.setId("splashprogressbar");
        bar.setLayoutY(400);
        bar.setLayoutX(50);
        psplash.getChildren().add(splashscreen);
        Scene splashScene = new Scene(psplash, 400, 420);
        splashScene.setFill(null);
        return splashScene;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(createPreloaderScene());
        stage.show();
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        bar.setProgress(pn.getProgress());
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        System.out.println("evt : " + evt.getType() );
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }

}
