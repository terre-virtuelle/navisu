/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.view.meteo.openWheaterMap;

import java.net.URL;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import bzh.terrevirtuelle.navisu.widgets.model.Widget;

/**
 *
 * @author Serge Morvan
 */
public class OpenWheaterMap
        extends Widget {

    WebView webView;
    WebEngine webEngine;
    URL url;
    private ImageView background;

    public OpenWheaterMap() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                createScene();
            }
        });
    }

    @Override
    protected void createScene() {
        BorderPane border = new BorderPane();
        webView = new WebView();
        webView.setMaxSize(1200, 800);
        webEngine = webView.getEngine();
        url = getClass().getResource("resources/html/tiles_0.xhtml");
        webEngine.load(url.toExternalForm());
        border.setCenter(webView);

        background = ImageViewBuilder.create()
                .image(new Image(getClass().getResourceAsStream("resources/images/logo.png")))
                .build();
       // getChildren().add(background);
        //Label label = new Label("Open Wheater Map");
        //label.setMaxSize(500, 10);
        border.setTop(background);

        getChildren().add(border);
    }
}
