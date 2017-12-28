/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.weather.impl.metoffice.view;

import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author serge
 */
public class WebViewPane extends Pane {

    public WebViewPane() {
        VBox.setVgrow(this, Priority.ALWAYS);
        setMaxWidth(Double.MAX_VALUE);
        setMaxHeight(Double.MAX_VALUE);

        WebView view = new WebView();
        view.setMinSize(500, 400);
        view.setPrefSize(500, 400);
        final WebEngine eng = view.getEngine();
        //  eng.load("https://upload.wikimedia.org/wikipedia/commons/1/1b/Phare_de_Kermorvan_vu_de_la_presqu%27%C3%AEle.jpg");
        // final TextField locationField = new TextField("https://upload.wikimedia.org/wikipedia/commons/1/1b/Phare_de_Kermorvan_vu_de_la_presqu%27%C3%AEle.jpg");
        // final TextField locationField = new TextField("http://www.vision-environnement.com/webcams/player/RESP_raz.php");
        //final TextField locationField = new TextField("https://earth.google.com/web/@48.38912192,-4.43305933,4.16265535a,0d,39.41822025y,53.19151175h,87.34535618t,0r/data=CgAiGgoWVFBTRFo4d0g4ZUxtNXBERkF5aEZwQRAC");
        // final TextField locationField = new TextField("https://demo.openpathview.fr/Hermione_48b.html");
        // final TextField locationField = new TextField("https://webapp.navionics.com/#boating@6&key=kfjfHjfkZ");
        final TextField locationField = new TextField(" http://www.westwind.ch/?link=ukmb,http://www.wetterzentrale.de/pics/,.gif,bracka,brack0,brack0a,brack1,brack1a,brack2,brack2a,brack3,brack4");
        // final TextField locationField = new TextField("https://www.metoffice.gov.uk/public/weather/surface-pressure/#?tab=surfacePressureColour&fcTime=1514379500");//Voir sur le site
        //https://www.metoffice.gov.uk/public/weather/surface-pressure/#?tab=surfacePressureColour&fcTime=1514286000
        locationField.setMaxHeight(Double.MAX_VALUE);
        Button goButton = new Button("Go");
        goButton.setDefaultButton(true);
        EventHandler<ActionEvent> goAction = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eng.load(locationField.getText());
            }
        };
        goButton.setOnAction(goAction);
        locationField.setOnAction(goAction);
        eng.locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                locationField.setText(newValue);
            }
        });
        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);
        GridPane.setConstraints(locationField, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.SOMETIMES);
        GridPane.setConstraints(goButton, 1, 0);
        GridPane.setConstraints(view, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        grid.getColumnConstraints().addAll(
                new ColumnConstraints(100, 100, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true),
                new ColumnConstraints(40, 40, 40, Priority.NEVER, HPos.CENTER, true)
        );
        grid.getChildren().addAll(locationField, goButton, view);
        getChildren().add(grid);
    }

    @Override
    protected void layoutChildren() {
        List<Node> managed = getManagedChildren();
        double width = getWidth();
        double height = getHeight();
        double top = getInsets().getTop();
        double right = getInsets().getRight();
        double left = getInsets().getLeft();
        double bottom = getInsets().getBottom();
        for (int i = 0; i < managed.size(); i++) {
            Node child = managed.get(i);
            layoutInArea(child, left, top,
                    width - left - right, height - top - bottom,
                    0, Insets.EMPTY, true, true, HPos.CENTER, VPos.CENTER);
        }
    }
}
