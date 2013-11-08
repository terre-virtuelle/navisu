/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.core;

import bzh.terrevirtuelle.navisu.core.view.geoview.GeoView;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.GeoWorldWindView;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.worldwind.WorldWindLayerManager;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.worldwind.WorldWindLayers;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public class CoreMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        StackPane pane = new StackPane();
        Scene scene = new Scene(pane, 800, 600, Color.ALICEBLUE);

        final GeoWorldWindView geoView = GeoView.factory.newWorldWindGeo3DView();

        final WorldWindLayerManager layerManager = geoView.getLayerManager();

        layerManager.createGroup("On-earth layers",
                WorldWindLayers.Stars.newInstance(),
                WorldWindLayers.SkyGradient.newInstance(),

                WorldWindLayers.BlueMarbleImage.newInstance(),
                WorldWindLayers.Fog.newInstance(),
                WorldWindLayers.BlueMarbleWMS.newInstance(),
                WorldWindLayers.LandsatICubed.newInstance(),
                WorldWindLayers.BingImagery.newInstance(),
                WorldWindLayers.EarthAtNight.newInstance(),
                WorldWindLayers.OpenStreetMap.newInstance(),

                WorldWindLayers.CountryBoundaries.newInstance(),
                WorldWindLayers.PlaceName.newInstance(),

                WorldWindLayers.LatLonGraticule.newInstance()
        );

        layerManager.createGroup("On-screen layers",
                WorldWindLayers.WorldMap.newInstance(),
                WorldWindLayers.ScaleBar.newInstance(),
                WorldWindLayers.Compass.newInstance()
        );

        pane.getChildren().add(geoView.getDisplayable());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(CoreMain.class, args);
    }
}
