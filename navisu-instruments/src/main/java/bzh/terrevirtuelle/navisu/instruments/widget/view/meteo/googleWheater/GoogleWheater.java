/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.widget.view.meteo.googleWheater;

import java.net.URL;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import bzh.terrevirtuelle.navisu.instruments.widget.model.Widget;

/**
 *
 * @author Serge Morvan
 */
public class GoogleWheater extends Widget {

    WebView webView;
    WebEngine webEngine;
    URL url;

    public GoogleWheater() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                createScene();
            }
        });
    }

    @Override
    protected void createScene() {
        webView = new WebView();
        webView.setMaxSize(500, 300);
        webEngine = webView.getEngine();
        url = getClass().getResource("resources/html/weather.html");
        webEngine.load(url.toExternalForm());

        getChildren().add(webView);
    }
}
