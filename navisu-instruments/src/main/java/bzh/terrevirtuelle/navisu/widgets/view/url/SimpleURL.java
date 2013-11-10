/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.view.url;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import bzh.terrevirtuelle.navisu.widgets.model.Widget;

/**
 *
 * @author Serge Morvan
 */
public class SimpleURL extends Widget {

    WebView webView;
    WebEngine webEngine;
    URL url;
    String urlName;
    int width;
    int height;
    private double initX;
    private double initY;
    private Point2D dragAnchor;

    public SimpleURL(String urlName, int width, int height) {
        this.height = height;
        this.width = width;
        this.urlName = urlName;
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
        webView.setMaxSize(width, height);
       // webView.setOnMousePressed(mouseHandler);
        webView.setMouseTransparent(false);
        webEngine = webView.getEngine();
        initWebViewEvt();
        try {
            url = new URL(urlName);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SimpleURL.class.getName()).log(Level.SEVERE, null, ex);
        }
        webEngine.load(url.toExternalForm());

        getChildren().add(webView);
    }
    
     protected  void initWebViewEvt() {
        webView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                toFront();
            }
        });
        webView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                initX = getTranslateX();
                initY = getTranslateY();
                dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
            }
        });
        webView.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (me != null && dragAnchor != null) {
                    setTranslateX((int) (initX + me.getSceneX() - dragAnchor.getX()));
                    setTranslateY((int) (initY + me.getSceneY() - dragAnchor.getY()));
                }
            }
        });
        
    }

}
