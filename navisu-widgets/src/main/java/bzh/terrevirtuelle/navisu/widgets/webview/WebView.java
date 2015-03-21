/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.webview;

import bzh.terrevirtuelle.navisu.widgets.impl.WidgetImpl;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;

/**
 *
 * @author Serge
 */
public class WebView
        extends WidgetImpl {

    
    final ImageView selectedImage = new ImageView();
    final javafx.scene.web.WebView browser = new javafx.scene.web.WebView();
    final WebEngine webEngine = browser.getEngine();
    final Button showPrevDoc = new Button("Toggle Previous Docs");
    final javafx.scene.web.WebView smallView = new javafx.scene.web.WebView();
    final ComboBox comboBox = new ComboBox();

    public WebView(String url) {
        browser.setPrefSize(400, 300);
        setTranslateX(250);
        setTranslateY(-200);
        setMouseTransparent(false);
        browser.setMouseTransparent(false);
        webEngine.load(url);
        getChildren().add(browser);
    }

    public WebView() {
        browser.setPrefSize(400, 300);
        setTranslateX(250);
        setTranslateY(-200);
        setMouseTransparent(false);
        browser.setMouseTransparent(false);
        //apply the styles
        /*
         getStyleClass().add("browser");

         for (int i = 0; i < captions.length; i++) {
         // create hyperlinks
         Hyperlink hpl = hpls[i] = new Hyperlink(captions[i]);
         Image image = images[i]
         = new Image(getClass().getResourceAsStream(imageFiles[i]));
         hpl.setGraphic(new ImageView(image));
         final String url = urls[i];
         final boolean addButton = (hpl.getText().equals("Documentation"));

         // process event 
         hpl.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent e) {
         needDocumentationButton = addButton;
         webEngine.load(url);
         }
         });
         }

         comboBox.setPrefWidth(60);

         // create the toolbar
         toolBar = new HBox();
         toolBar.setAlignment(Pos.CENTER);
         toolBar.getStyleClass().add("browser-toolbar");
         toolBar.getChildren().add(comboBox);
         toolBar.getChildren().addAll(hpls);
         toolBar.getChildren().add(createSpacer());

         //set action for the button
         showPrevDoc.setOnAction(new EventHandler() {
         @Override
         public void handle(Event t) {
         webEngine.executeScript("toggleDisplay('PrevRel')");
         }
         });
         */
      //  smallView.setPrefSize(120, 80);
/*
         //handle popup windows
         webEngine.setCreatePopupHandler(
         new Callback<PopupFeatures, WebEngine>() {
         @Override
         public WebEngine call(PopupFeatures config) {
         smallView.setFontScale(0.8);
         if (!toolBar.getChildren().contains(smallView)) {
         toolBar.getChildren().add(smallView);
         }
         return smallView.getEngine();
         }
         }
         );
         */
        /*
         //process history
         final WebHistory history = webEngine.getHistory();
         history.getEntries().addListener(new ListChangeListener<WebHistory.Entry>() {
         @Override
         public void onChanged(ListChangeListener.Change<? extends WebHistory.Entry> c) {
         c.next();
         for (WebHistory.Entry e : c.getRemoved()) {
         comboBox.getItems().remove(e.getUrl());
         }
         for (WebHistory.Entry e : c.getAddedSubList()) {
         comboBox.getItems().add(e.getUrl());
         }
         }
         });
         */
        /*
         //set the behavior for the history combobox               
         comboBox.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent ev) {
         int offset
         = comboBox.getSelectionModel().getSelectedIndex()
         - history.getCurrentIndex();
         history.go(offset);
         }
         });
         */
        /*
         // process page loading
         webEngine.getLoadWorker().stateProperty().addListener(
         new ChangeListener<Worker.State>() {
         @Override
         public void changed(ObservableValue<? extends Worker.State> ov,
         Worker.State oldState, Worker.State newState) {
         toolBar.getChildren().remove(showPrevDoc);
         if (newState == Worker.State.SUCCEEDED) {
         JSObject win
         = (JSObject) webEngine.executeScript("window");
         win.setMember("app", new JavaApp());
         if (needDocumentationButton) {
         toolBar.getChildren().add(showPrevDoc);
         }
         }
         }
         }
         );
         */

        // load the home page        
        // webEngine.load("http://www.oracle.com/products/index.html");
        //webEngine.load("http://www.marinetraffic.com/fr/photos/of/ships/photo_keywords:9302009");
        // webEngine.load("http://webapp.navionics.com/?lang=fr");//OK
        //   webEngine.load("http://francois.lonchamp.free.fr/Navionics/Navionics.html");
        webEngine.load("http://www.shipspotting.com/gallery/photo.php?lid=2137261");
      // webEngine.load("http://maps.grade.de/");
        // webEngine.load("http://data.shom.fr/#affichage");//KO plugin non reconnu même pb avec GeoPortail
        // webEngine.load("http://marine.meteoconsult.fr/meteo-marine/de-brest-a-loctudy/france/prevision_meteo_de-brest-a-loctudy_france_zone_cotiere__6308_0.php");
        //webEngine.load("http://www.geoportail.gouv.fr/accueil");

        // webEngine.load(getClass().getResource("index.html").toExternalForm());
        //add components
        //  getChildren().add(toolBar);
        getChildren().add(browser);
    }

    // JavaScript interface object
    public class JavaApp {

        public void exit() {
            Platform.exit();
        }
    }

    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    /*  @Override
     protected void layoutChildren() {
     double w = getWidth();
     double h = getHeight();
     double tbHeight = toolBar.prefHeight(w);
     layoutInArea(browser, 0, 0, w, h - tbHeight, 0, HPos.CENTER, VPos.CENTER);
     layoutInArea(toolBar, 0, h - tbHeight, w, tbHeight, 0, HPos.CENTER, VPos.CENTER);
     }
     */
    @Override
    protected double computePrefWidth(double height) {
        return 350;
    }

    @Override
    protected double computePrefHeight(double width) {
        return 300;
    }
}
