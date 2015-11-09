/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.photos.exif.Exif;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.RoutePhotoEditorImpl;
import bzh.terrevirtuelle.navisu.photos.exif.ExifComponentServices;
import bzh.terrevirtuelle.navisu.util.io.IO;
import static bzh.terrevirtuelle.navisu.util.io.IO.fileChooser;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import com.drew.imaging.ImageProcessingException;
import gov.nasa.worldwind.View;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.xml.bind.JAXBException;

/**
 * NaVisu
 *
 * @date 26 août 2015
 * @author Serge Morvan
 */
public class RoutePhotoEditorController
        extends Widget2DController {

    private ExifComponentServices exifComponentServices;
    private final S57ChartServices s57ChartServices;
    private GuiAgentServices guiAgentServices;
    private final RoutePhotoEditorImpl instrument;
    private View viewWW;
    private final String FXML = "routephotoeditor.fxml";
    private WorldWindow wwd;

    @FXML
    public Group view;
    @FXML
    public ImageView quit;
    @FXML
    public Slider opacitySlider;
    @FXML
    public TextField latTF;
    @FXML
    public TextField lonTF;
    @FXML
    public TextField altTF;
    @FXML
    public TextField focalLengthTF;
    @FXML
    public TextField headingTF;
    @FXML
    public TextField widthTF;
    @FXML
    public TextField curEyePointXTF;
    @FXML
    public TextField curEyePointYTF;
    @FXML
    public TextField curEyePointZTF;
    @FXML
    public TextField curEyePointTTF;
    @FXML
    public TextField curEyePositionXTF;
    @FXML
    public TextField curEyePositionYTF;
    @FXML
    public TextField curEyePositionZTF;
    @FXML
    public TextField farClipDistanceTF;
    @FXML
    public TextField fieldOfViewTF;
    @FXML
    public TextField forwardVectorXTF;
    @FXML
    public TextField forwardVectorYTF;
    @FXML
    public TextField forwardVectorZTF;
    @FXML
    public TextField forwardVectorTTF;
    @FXML
    public TextField horizonDistanceTF;
    @FXML
    public TextField nearClipDistanceTF;
    @FXML
    public TextField pitchTF;
    @FXML
    public TextField rollTF;
    @FXML
    public TextField upVectorXTF;
    @FXML
    public TextField upVectorYTF;
    @FXML
    public TextField upVectorZTF;
    @FXML
    public TextField upVectorTTF;
    @FXML
    public TextField viewportXTF;
    @FXML
    public TextField viewportYTF;
    @FXML
    public TextField viewportZTF;
    @FXML
    public TextField viewportTTF;
    @FXML
    public TextField routeTF;
    @FXML
    public TextField photoTF;
    @FXML
    public Button gotoButton;
    @FXML
    public Button saveButton;
    @FXML
    public Button routeChoiceButton;
    @FXML
    public Button photoChoiceButton;
    private double latitude;
    private double longitude;
    private double altitude;
    private int heading;
    private double fieldOfView;

    public RoutePhotoEditorController(RoutePhotoEditorImpl instrument,
            KeyCode keyCode, KeyCombination.Modifier keyCombination) {

        super(keyCode, keyCombination);
        this.instrument = instrument;
        this.s57ChartServices = instrument.getS57ChartServices();
        this.guiAgentServices = instrument.getGuiAgentServices();
        this.exifComponentServices = instrument.getExifComponentServices();
        wwd = GeoWorldWindViewImpl.getWW();
        viewWW = wwd.getView();
        load(FXML);
        // setTranslateX(225.0);

        quit.setOnMouseClicked((MouseEvent event) -> {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, this);
            guiAgentServices.getRoot().getChildren().remove(this);
            setVisible(false);

        });
        gotoButton.setOnMouseClicked((MouseEvent event) -> {
            viewWW.setHeading(Angle.fromDegrees(heading));
            viewWW.setFieldOfView(Angle.fromDegrees(fieldOfView));
            viewWW.setPitch(Angle.fromDegrees(90.0));
            viewWW.goTo(new Position(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude), altitude), altitude);
            updatePanel();
        });
        saveButton.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("saveButton not implemented");
        });
        routeChoiceButton.setOnMouseClicked((MouseEvent event) -> {
            File file = IO.fileChooser(instrument.getGuiAgentServices().getStage(), "privateData/nds", "Route files (*.nds)", "*.xml", "*.XML");
            NavigationDataSet navigationDataSet = new NavigationDataSet();
            try {
                navigationDataSet = ImportExportXML.imports(navigationDataSet, file);
                routeTF.setText(file.getName());
            } catch (FileNotFoundException | JAXBException ex) {
                Logger.getLogger(RoutePhotoEditorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        photoChoiceButton.setOnMouseClicked((MouseEvent event) -> {
            File file = IO.fileChooser(instrument.getGuiAgentServices().getStage(), "data/photos", "JPG files (*.jpg)", "*.jpg", "*.JPG");
            Exif exif = null;
            try {
                exif = exifComponentServices.create(file);
                photoTF.setText(file.getName());
            } catch (IOException | ImageProcessingException ex) {
                Logger.getLogger(RoutePhotoEditorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (exif != null) {
                latitude = exif.getGpsLatitude();
                longitude = exif.getGpsLongitude();
                altitude = exif.getGpsAltitude();
                heading = exif.getGpsImgDirection();
                //fieldOfView = Math.toDegrees(2 * Math.atan2(exif.getImageHeight() / 2.0, exif.getFocalLenth()));
                fieldOfView = 20;
                updatePanel();
            }

        });

        fieldOfViewTF.setOnAction((ActionEvent event) -> {
            fieldOfView = Double.parseDouble(fieldOfViewTF.getText());
            viewWW.setHeading(Angle.fromDegrees(heading));
            viewWW.setFieldOfView(Angle.fromDegrees(fieldOfView));
            viewWW.setPitch(Angle.fromDegrees(90.0));
            viewWW.goTo(new Position(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude), altitude), altitude);
            updatePanel();
        });

    }

    final void load(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        view.setOpacity(0.8);
        opacitySlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                view.setOpacity(opacitySlider.getValue());
            });
        });
    }

    private void updatePanel() {
        latTF.setText(Double.toString(latitude));
        lonTF.setText(Double.toString(longitude));
        altTF.setText(Double.toString(altitude));
        headingTF.setText(Integer.toString(heading));
        fieldOfViewTF.setText(Integer.toString((int) fieldOfView));
        curEyePointXTF.setText(Double.toString(viewWW.getEyePoint().x));
        curEyePointYTF.setText(Double.toString(viewWW.getEyePoint().y));
        curEyePointZTF.setText(Double.toString(viewWW.getEyePoint().z));
        curEyePointTTF.setText(Double.toString(viewWW.getEyePoint().w));
        curEyePositionXTF.setText(String.format("%2.4f", viewWW.getEyePosition().getLatitude().getDegrees()));
        curEyePositionYTF.setText(String.format("%2.4f", viewWW.getEyePosition().getLongitude().getDegrees()));
        curEyePositionZTF.setText(String.format("%2.4f", viewWW.getEyePosition().getAltitude()));
        farClipDistanceTF.setText(String.format("%8.4f", viewWW.getFarClipDistance()));
        forwardVectorXTF.setText(String.format("%2.4f", viewWW.getForwardVector().x));
        forwardVectorYTF.setText(String.format("%2.4f", viewWW.getForwardVector().y));
        forwardVectorZTF.setText(String.format("%2.4f", viewWW.getForwardVector().z));
        forwardVectorTTF.setText(String.format("%2.4f", viewWW.getForwardVector().w));
        horizonDistanceTF.setText(String.format("%8.1f", viewWW.getHorizonDistance()));
        nearClipDistanceTF.setText(String.format("%8.1f", viewWW.getNearClipDistance()));
        upVectorXTF.setText(Double.toString(viewWW.getUpVector().x));
        upVectorYTF.setText(Double.toString(viewWW.getUpVector().y));
        upVectorZTF.setText(Double.toString(viewWW.getUpVector().z));
        upVectorTTF.setText(Double.toString(viewWW.getUpVector().w));
        viewportXTF.setText(Double.toString(viewWW.getViewport().x));
        viewportYTF.setText(Double.toString(viewWW.getViewport().y));
        viewportZTF.setText(Double.toString(viewWW.getViewport().height));
        viewportTTF.setText(Double.toString(viewWW.getViewport().width));
    }
}
