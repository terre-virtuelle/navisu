package bzh.terrevirtuelle.navisu.weather.app;


import bzh.terrevirtuelle.navisu.app.guiagent.svg.SVGContent;
import bzh.terrevirtuelle.navisu.app.guiagent.svg.SVGLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MeteoIcones extends Application {

    @Override
    public void start(Stage stage) {
        SVGContent content = SVGLoader.load(getClass().getResource("meteoicones/Cloud-Download.svg").toString());
        
        Scene scene = new Scene(content, 1024, 768);
        
        stage.setScene(scene);
        stage.setTitle("SVGLoader Sample");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
