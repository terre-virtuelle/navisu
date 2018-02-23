/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.locator;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Vec4;
import gov.nasa.worldwind.ogc.collada.ColladaRoot;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * @date 6 mars 2015
 * @author Serge Morvan
 */
public class LocatorController
        extends Widget2DController {

    @FXML
    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    protected String viewgroupstyle = "common.css";
    public Group locatorPanel;
    @FXML
    public Pane quitPane;
    @FXML
    Text title;
    @FXML
    Button okButton;
    @FXML
    TextField latitudeTF;
    @FXML
    TextField longitudeTF;
    @FXML
    TextField headingTF;
    @FXML
    TextField heightTF;
    @FXML
    TextField scaleTF;
    String FXML = "LocatorPanel.fxml";
    ColladaRoot colladaRoot;
    private WorldWindow wwd;
    private double oldLat = 0.0;
    private double oldLon = 0.0;
    final static Pattern DOUBLE_FORMAT = Pattern.compile("^(\\-?)[0]*([0-9]+)(?:\\.([0-9]+?)[0]*)?$");

    public LocatorController(ColladaRoot colladaRoot) {
        this.colladaRoot = colladaRoot;
        wwd = GeoWorldWindViewImpl.getWW();
        setMouseTransparent(false);
        load();
    }

    public LocatorController(KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        setMouseTransparent(false);
        load();
    }

    private void load() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        String uri = CSS_STYLE_PATH + viewgroupstyle;
        locatorPanel.getStylesheets().add(uri);
        quitPane.setOnMouseClicked((MouseEvent event) -> {
            setVisible(false);
        });
        initGui();
    }

    private void initGui() {
        setLatitudeTF(String.valueOf(colladaRoot.getPosition().getLatitude().getDegrees()));
        setLongitudeTF(String.valueOf(colladaRoot.getPosition().getLongitude().getDegrees()));
//        setHeadingTF(String.valueOf(colladaRoot.getHeading().getDegrees()));
//        setScaleTF(String.valueOf(colladaRoot.getModelScale().getX()));
        latitudeTF.textProperty().addListener((final ObservableValue<? extends String> observable, final String oldValue, final String newValue) -> {
            //  System.out.println("********" + oldValue + "  " + newValue);
            if (!latitudeTF.getText().equals("")
                    && latitudeTF.getText() != null
                    && !heightTF.getText().equals("-")) {
                double val = Double.parseDouble(newValue.trim());
                if (val <= 90 && val >= -90) {
                    colladaRoot.setPosition(Position.fromDegrees(
                            val,
                            Double.parseDouble(longitudeTF.getText().trim()),
                            100.0));
                    oldLat = val;
                } else {
                    colladaRoot.setPosition(Position.fromDegrees(
                            oldLat,
                            Double.parseDouble(longitudeTF.getText().trim()),
                            100.0));

                }
                //  Platform.runLater(() -> {
                // System.out.println("Platform.runLater " + oldLat);
                latitudeTF.setText(Double.toString(oldLat));
                //  });
                wwd.redrawNow();
            }
        });
        longitudeTF.textProperty().addListener((final ObservableValue<? extends String> observable, final String oldValue, final String newValue) -> {
            // System.out.println("ooooooooooooo" + oldValue + "  " + newValue);
            if (!longitudeTF.getText().equals("")
                    && longitudeTF.getText() != null
                    && !heightTF.getText().equals("-")) {
                double val = Double.parseDouble(newValue.trim());
                if (val <= 180 && val >= -180) {
                    colladaRoot.setPosition(Position.fromDegrees(
                            Double.parseDouble(latitudeTF.getText().trim()),
                            val,
                            100.0));
                    oldLon = val;
                } else {
                    colladaRoot.setPosition(Position.fromDegrees(
                            Double.parseDouble(latitudeTF.getText().trim()),
                            oldLon,
                            100.0));
                    Platform.runLater(() -> {
                        longitudeTF.setText(Double.toString(oldLon));
                    });
                }
                wwd.redrawNow();
            }
        });
        headingTF.textProperty().addListener((final ObservableValue<? extends String> observable, final String oldValue, final String newValue) -> {
            if (!headingTF.getText().equals("")) {
                double val = Double.parseDouble(newValue.trim());
                if (val <= 360 && val >= 0) {
                    colladaRoot.setHeading(Angle.fromDegrees(val));
                    wwd.redrawNow();
                }
            }
        });
        scaleTF.textProperty().addListener((final ObservableValue<? extends String> observable, final String oldValue, final String newValue) -> {
            if (!scaleTF.getText().equals("")) {
                colladaRoot.setModelScale(new Vec4(new Double(newValue.trim())));
                wwd.redrawNow();
            }
        });
        heightTF.textProperty().addListener((final ObservableValue<? extends String> observable, final String oldValue, final String newValue) -> {
            if (!heightTF.getText().equals("") && !heightTF.getText().equals("-")) {
                colladaRoot.setPosition(Position.fromDegrees(
                        Double.parseDouble(latitudeTF.getText().trim()),
                        Double.parseDouble(longitudeTF.getText().trim()),
                        Double.parseDouble(heightTF.getText().trim())));
                wwd.redrawNow();
            }
        });
    }

    public void setTitle(Text title) {
        this.title = title;
    }

    public Text getTitle() {
        return title;
    }

    public Button getOkButton() {
        return okButton;
    }

    public TextField getLatitudeTF() {
        return latitudeTF;
    }

    public TextField getLongitudeTF() {
        return longitudeTF;
    }

    public TextField getHeadingTF() {
        return headingTF;
    }

    public TextField getScaleTF() {
        return scaleTF;
    }

    public void setLatitudeTF(String latitude) {
        this.latitudeTF.setText(latitude);
    }

    public void setLongitudeTF(String longitude) {
        this.longitudeTF.setText(longitude);
    }

    public void setHeadingTF(String heading) {
        this.headingTF.setText(heading);
    }

    public void setScaleTF(String scale) {
        this.scaleTF.setText(scale);
    }

}
