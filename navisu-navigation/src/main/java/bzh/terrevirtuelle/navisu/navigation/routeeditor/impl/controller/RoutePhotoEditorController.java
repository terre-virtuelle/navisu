/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Gpx;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Highway;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Point;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.photos.exif.Exif;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.RoutePhotoViewerServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.RoutePhotoEditorImpl;
import bzh.terrevirtuelle.navisu.photos.exif.ExifComponentServices;
import bzh.terrevirtuelle.navisu.util.io.IO;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import com.drew.imaging.ImageProcessingException;
import com.vividsolutions.jts.geom.Coordinate;
import gov.nasa.worldwind.View;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
import javafx.scene.image.Image;
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

    private final String FXML = "routephotoeditor.fxml";
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

    private final ExifComponentServices exifComponentServices;
    private final S57ChartServices s57ChartServices;
    private final GuiAgentServices guiAgentServices;
    private final LayerTreeServices layerTreeServices;
    private final GeoViewServices geoViewServices;
    private final RoutePhotoViewerServices routePhotoViewerServices;
    private final RoutePhotoEditorImpl instrument;
    private final View viewWW;
    private final WorldWindow wwd;
    private final String layerName;
    private final String groupName;
    private RenderableLayer layer;
    private double latitude;
    private double longitude;
    private double altitude;
    private int heading;
    private double fieldOfView;
    private double nearClipDistance;
    private Image image = null;
    private NavigationDataSet navigationDataSet;
    private List<Position> highwayPathPositions;
    private List<Highway> highways;
    private Polygon highway;

    public RoutePhotoEditorController(RoutePhotoEditorImpl instrument,
            GeoViewServices geoViewServices,
            LayerTreeServices layerTreeServices,
            GuiAgentServices guiAgentServices,
            S57ChartServices s57ChartServices,
            RoutePhotoViewerServices routePhotoViewerServices,
            ExifComponentServices exifComponentServices,
            String groupName, String layerName,
            KeyCode keyCode, KeyCombination.Modifier keyCombination) {

        super(keyCode, keyCombination);
        this.instrument = instrument;
        this.geoViewServices = geoViewServices;
        this.layerTreeServices = layerTreeServices;
        this.guiAgentServices = guiAgentServices;
        this.s57ChartServices = s57ChartServices;
        this.routePhotoViewerServices = routePhotoViewerServices;
        this.exifComponentServices = exifComponentServices;
        this.groupName = groupName;
        this.layerName = layerName;
        this.wwd = GeoWorldWindViewImpl.getWW();
        this.viewWW = wwd.getView();
        load(FXML);
        initPanel();
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

    private void initPanel() {
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
            routePhotoViewerServices.load(image);
        });
        saveButton.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("saveButton not implemented");
        });
        routeChoiceButton.setOnMouseClicked((MouseEvent event) -> {
            fillHighway();
            layer = initLayer(layerTreeServices, geoViewServices, groupName, layerName);
            layer.addRenderable(highway);
        });
        photoChoiceButton.setOnMouseClicked((MouseEvent event) -> {
            File file = IO.fileChooser(instrument.getGuiAgentServices().getStage(), "data/photos", "JPG files (*.jpg)", "*.jpg", "*.JPG");
            Exif exif = null;
            try {
                if (file != null) {
                    image = new Image("file:" + file.getAbsolutePath());
                }
                photoTF.setText(file.getName());
                exif = exifComponentServices.create(file);
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
        nearClipDistanceTF.setOnAction((ActionEvent event) -> {
            nearClipDistance = Double.parseDouble(nearClipDistanceTF.getText());

            System.out.println("getDistance " + viewWW.getFrustumInModelCoordinates().getNear().getDistance());
            viewWW.goTo(new Position(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude), altitude), altitude);
            updatePanel();
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
        curEyePositionXTF.setText(String.format(Locale.US, "%2.4f", viewWW.getEyePosition().getLatitude().getDegrees()));
        curEyePositionYTF.setText(String.format(Locale.US, "%2.4f", viewWW.getEyePosition().getLongitude().getDegrees()));
        curEyePositionZTF.setText(String.format(Locale.US, "%2.4f", viewWW.getEyePosition().getAltitude()));
        farClipDistanceTF.setText(String.format(Locale.US, "%8.4f", viewWW.getFarClipDistance()));
        forwardVectorXTF.setText(String.format(Locale.US, "%2.4f", viewWW.getForwardVector().x));
        forwardVectorYTF.setText(String.format(Locale.US, "%2.4f", viewWW.getForwardVector().y));
        forwardVectorZTF.setText(String.format(Locale.US, "%2.4f", viewWW.getForwardVector().z));
        forwardVectorTTF.setText(String.format(Locale.US, "%2.4f", viewWW.getForwardVector().w));
        horizonDistanceTF.setText(String.format(Locale.US, "%8.0f", viewWW.getHorizonDistance()));
        nearClipDistanceTF.setText(String.format(Locale.US, "%8.1f", viewWW.getNearClipDistance()));
        upVectorXTF.setText(String.format(Locale.US, "%8.4f", viewWW.getUpVector().x));
        upVectorYTF.setText(String.format(Locale.US, "%8.4f", viewWW.getUpVector().y));
        upVectorZTF.setText(String.format(Locale.US, "%8.4f", viewWW.getUpVector().z));
        upVectorTTF.setText(String.format(Locale.US, "%8.4f", viewWW.getUpVector().w));
        viewportXTF.setText(String.format(Integer.toString(viewWW.getViewport().x)));
        viewportYTF.setText(String.format(Integer.toString(viewWW.getViewport().y)));
        viewportZTF.setText(String.format(Integer.toString(viewWW.getViewport().height)));
        viewportTTF.setText(String.format(Integer.toString(viewWW.getViewport().width)));
    }

    private RenderableLayer initLayer(LayerTreeServices layerTreeServices,
            GeoViewServices geoViewServices, String groupName, String layerName) {
        List<String> groups = layerTreeServices.getGroupNames();
        if (!groups.contains(groupName)) {
            layerTreeServices.createGroup(groupName);
            geoViewServices.getLayerManager().createGroup(groupName);
        }
        boolean layerExist = false;
        RenderableLayer layer = null;
        List<GeoLayer<Layer>> layers = geoViewServices.getLayerManager().getGroup(groupName);
        for (GeoLayer<Layer> g : layers) {
            if (g.getName().contains(layerName)) {
                layer = (RenderableLayer) g.getDisplayLayer();
                layerExist = true;
            }
        }
        if (!layerExist) {
            layer = new RenderableLayer();
            layer.setName(layerName);
            geoViewServices.getLayerManager().insertGeoLayer(groupName, GeoLayer.factory.newWorldWindGeoLayer(layer));
            layerTreeServices.addGeoLayer(groupName, GeoLayer.factory.newWorldWindGeoLayer(layer));
        }
        return layer;
    }

    private void fillHighway() {
        File file = IO.fileChooser(instrument.getGuiAgentServices().getStage(), "privateData/nds", "Route files (*.nds)", "*.xml", "*.XML");
        navigationDataSet = new NavigationDataSet();
        try {
            navigationDataSet = ImportExportXML.imports(navigationDataSet, file);
            routeTF.setText(file.getName());
        } catch (FileNotFoundException | JAXBException ex) {
            Logger.getLogger(RoutePhotoEditorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        highwayPathPositions = new ArrayList<>();
        highways = new ArrayList<>();
        List<Gpx> tmp = navigationDataSet.get(Gpx.class);
        tmp.stream().forEach((g) -> {
            highways.add(g.getHighway());
        });
        highways.stream().map((h) -> h.getBounds()).forEach((pts) -> {
            pts.stream().forEach((p) -> {
                highwayPathPositions.add(Position.fromDegrees(p.getLatitude(), p.getLongitude(), 5));
            });
        });
        if (!highwayPathPositions.isEmpty()) {
            highway = new Polygon(highwayPathPositions);
        }
        if (highway != null) {
            ShapeAttributes offsetNormalAttributes = new BasicShapeAttributes();
            offsetNormalAttributes.setOutlineMaterial(Material.RED);
            offsetNormalAttributes.setOutlineWidth(2);
            offsetNormalAttributes.setDrawOutline(true);
            offsetNormalAttributes.setDrawInterior(false);
            offsetNormalAttributes.setEnableLighting(true);
            highway.setAttributes(offsetNormalAttributes);

            ShapeAttributes offsetHighlightAttributes = new BasicShapeAttributes(offsetNormalAttributes);
            offsetHighlightAttributes.setOutlineMaterial(Material.GREEN);
            offsetHighlightAttributes.setOutlineOpacity(1);
            highway.setHighlightAttributes(offsetHighlightAttributes);
        }
        String[] str = null;
        if (file.getName() != null) {
            str = file.getName().split("\\.");
        }
        highway.setValue(AVKey.DISPLAY_NAME, str[0]);
    }
}
