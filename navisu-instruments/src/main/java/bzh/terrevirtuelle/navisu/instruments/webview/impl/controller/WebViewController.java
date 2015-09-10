/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.webview.impl.controller;

import bzh.terrevirtuelle.navisu.instruments.webview.impl.WebViewImpl;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * NaVisu
 *
 * @date 27 juil. 2015
 * @author Serge Morvan
 */
public class WebViewController
        extends Widget2DController
        implements Initializable {

    protected WebViewImpl instrument;
    protected ComponentManager cm = ComponentManager.componentManager;
    private final String FXML = "WebView.fxml";
    private LoadingState initState;
    private LoadingState startLoadState;
    private LoadingState startCancelState;
    private LoadingState loadCompletedState;
    private LoadingState loadFailedState;
    private LoadingState loadCanceledState;

    @FXML
    public Group view;
    @FXML
    public ImageView quit;
    private WebEngine engine;
    @FXML
    TextField urlText;
    @FXML
    WebView webView;
    @FXML
    Label loadState;
    @FXML
    ProgressIndicator indicator;
    @FXML
    Button loadButton;
    @FXML
    Button cancelButton;

    public WebViewController(WebViewImpl instrument, KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        this.instrument = instrument;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        quit.setOnMouseClicked((MouseEvent event) -> {
            instrument.off();
        });
/*
        setLoadingState();
        initState.apply();
        initState.setScreenCommonState(this);
        this.engine = webView.getEngine();
        // binding the progressIndicator to the Web LoadWorker
        this.indicator.progressProperty().bind(this.engine.getLoadWorker().progressProperty());
        this.engine.getLoadWorker().stateProperty().addListener(
                (ov, oldState, newState) -> {
                    switch (newState) {
                        case SUCCEEDED:
                            loadCompletedState.apply();
                            loadCompletedState.setScreenCommonState(this);
                            break;
                        case CANCELLED:
                            loadCanceledState.apply();
                            loadCanceledState.setScreenCommonState(this);
                            break;
                        case FAILED:
                            loadFailedState.apply();
                            loadFailedState.setScreenCommonState(this);
                            break;
                    };
                });
        */
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * set the loading states
     */
    private void setLoadingState() {
        initState = () -> {
            loadState.setText("");
            webView.setVisible(false);
        };
        startLoadState = () -> {
            urlText.setDisable(true);
            loadButton.setDisable(true);
            cancelButton.setDisable(false);
            loadState.setTextFill(Color.BLACK);
            loadState.setText("loading...");
            webView.setVisible(false);
            indicator.setVisible(true);
        };
        startCancelState = () -> {
            cancelButton.setDisable(true);
            loadState.setTextFill(Color.BLACK);
            loadState.setText("cancelling....");
        };
        loadCompletedState = () -> {
            loadState.setTextFill(Color.BLACK);
            loadState.setText("completed!");
            webView.setVisible(true);
            System.out.println("webView " + webView.getEngine().getLocation());
            System.out.println("............." + engine.locationProperty());
        };
        loadFailedState = () -> {
            loadState.setTextFill(Color.RED);
            loadState.setText("failed!!!");
            webView.setVisible(false);
        };
        loadCanceledState = () -> {
            loadState.setTextFill(Color.ORANGE);
            loadState.setText("canceled!");
            webView.setVisible(false);
            System.out.println("webView " + webView.getEngine().getLocation());
        };
    }

    /**
     * Action for the load button
     *
     * @param event
     */
    @FXML
    private void loadURL(Event event) {
        startLoadState.apply();
        String url = urlText.getText();
        //  url = "http://commons.wikimedia.org/wiki/Special:FilePath/Phare_de_Kermorvan_vu_de_la_presqu'île.jpg";
        url = "http://earth.nullschool.net/#current/wind/surface/level/orthographic=-1.54,48.10,1821";
        this.engine.load(url);
        System.out.println("engine.getLocation( " + engine.getLocation());
        System.out.println("............." + engine.locationProperty());

    }

    /**
     * Action for the canel load button
     *
     * @param event
     */
    @FXML
    private void cancelLoad(Event event) {
        startCancelState.apply();
        this.engine.getLoadWorker().cancel();
    }

    /**
     * interface for the loading State
     *
     * @author tomo
     */
    @FunctionalInterface
    interface LoadingState {

        void apply();

        default void setScreenCommonState(WebViewController controller) {
            controller.urlText.setDisable(false);
            controller.loadButton.setDisable(false);
            controller.cancelButton.setDisable(true);
            controller.indicator.setVisible(false);
        }
    }
}
