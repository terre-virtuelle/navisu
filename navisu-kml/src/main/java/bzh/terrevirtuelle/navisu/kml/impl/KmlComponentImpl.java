package bzh.terrevirtuelle.navisu.kml.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.collada.impl.controller.ColladaComponentController;

import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.kml.impl.controller.KmlComponentController;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.ogc.collada.ColladaRoot;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.logging.Logger;
import javafx.scene.layout.StackPane;
import javax.xml.stream.XMLStreamException;
import bzh.terrevirtuelle.navisu.kml.KmlComponentServices;
import bzh.terrevirtuelle.navisu.kml.KmlComponent;
import de.micromata.opengis.kml.v_2_2_0.Container;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import gov.nasa.worldwind.geom.Vec4;
import gov.nasa.worldwind.ogc.collada.impl.ColladaController;
import java.util.ArrayList;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class KmlComponentImpl
        implements KmlComponent, KmlComponentServices, Driver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayersManagerServices layersManagerServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    GuiAgentServices guiAgentServices;

    private static final String NAME_0 = "KML";
    private static final String NAME_1 = "DAE";
    private static final String EXTENSION_0 = ".kmz";
    private static final String EXTENSION_1 = ".kml";
    private static final String EXTENSION_2 = ".dae";
    private static final String EXTENSION_3 = ".DAE";
    protected static final String GROUP = "KML files";
    protected String category;
    protected List<Layer> layers;
    protected static final Logger LOGGER = Logger.getLogger(KmlComponentImpl.class.getName());
    protected WorldWindow wwd;
    protected ColladaRoot colladaRoot = null;
    protected StackPane root;

    @Override
    public void componentInitiated() {
        layerTreeServices.createGroup(GROUP);
        wwd = GeoWorldWindViewImpl.getWW();
        wwd.addPositionListener((PositionEvent event) -> {
            float altitude = ((int) wwd.getView().getCurrentEyePosition().getAltitude());
            if (altitude >= 3000) {
                //  clip();
            } else {
                //  unClip();
            }
        });

    }

    @Override
    public boolean canOpen(String category, String file) {
        this.category = category;
        boolean canOpen = false;
        if (file.toLowerCase().endsWith(EXTENSION_0)
                || file.toLowerCase().endsWith(EXTENSION_1)
                || file.toLowerCase().endsWith(EXTENSION_2)
                || file.toLowerCase().endsWith(EXTENSION_3)) {
            canOpen = true;
        }
        return canOpen;
    }

    @Override
    public void open(ProgressHandle pHandle, String... files) {
        for (String file : files) {
            this.handleOpenFile(pHandle, file);
        }
    }

    @Override
    public ColladaRoot openColladaFile(RenderableLayer layer, String filename) {
        if (layer != null) {
            layer.removeAllRenderables();
            File file = new File(filename);
            try {
                colladaRoot = ColladaRoot.createAndParse(file);
                ColladaController colladaController = new ColladaController(colladaRoot);
                layer.addRenderable(colladaController);
            } catch (IOException | XMLStreamException ex) {
                Logger.getLogger(KmlComponentImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        return colladaRoot;
    }

    @SuppressWarnings("unchecked")
    protected void handleOpenFile(ProgressHandle pHandle, String fileName) {
        switch (this.category) {
            case "KML":
                LOGGER.log(Level.INFO, "Opening {0} ***", fileName);
                KmlComponentController kmlController = KmlComponentController.getInstance();
                layers = kmlController.init(fileName);
                layers.stream().filter((l) -> (l != null)).map((l) -> {
                    geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(l));
                    return l;
                }).forEach((l) -> {
                    layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
                });
                break;
            case "DAE":
                ColladaComponentController colladaComponentController
                        = new ColladaComponentController(layersManagerServices, this, fileName, guiAgentServices.getRoot());

                break;
        }
    }

    private void clip() {
        if (layers != null) {
            layers.stream().filter((l) -> (l.getName().contains(NAME_0))).forEach((l) -> {
                l.setEnabled(false);
            });
        }
    }

    private void unClip() {
        if (layers != null) {
            layers.stream().filter((l) -> (l.getName().contains(NAME_0))).forEach((l) -> {
                l.setEnabled(true);
            });
        }
    }

    @Override
    public String getName() {
        return NAME_0;
    }

    @Override
    public String[] getExtensions() {
        return new String[]{"*" + EXTENSION_0,
            "*" + EXTENSION_1,
            "*" + EXTENSION_2,
            "*" + EXTENSION_3
        };
    }

    @Override
    public void openFile(String file) {
        this.open(null, file);
    }

    @Override
    public Driver getDriver() {
        return this;
    }

    @Override
    public void componentStarted() {
        /* Nothing to do here */ }

    @Override
    public void componentStopped() {
        /* Nothing to do here */ }

    public ColladaRoot getColladaRoot() {
        return colladaRoot;
    }

    @Override
    public double getHeading() {
        return colladaRoot.getHeading().getDegrees();
    }

    @Override
    public double getLatitude() {
        return colladaRoot.getPosition().getLatitude().getDegrees();
    }

    @Override
    public double getLongitude() {
        return colladaRoot.getPosition().getLongitude().getDegrees();
    }

    @Override
    public void setHeading(double angle) {
        colladaRoot.setHeading(Angle.fromDegrees(angle));
    }

    @Override
    public void setlatitude(double latitude) {
        colladaRoot.setPosition(new Position(Angle.fromDegrees(latitude),
                Angle.fromDegrees(getLongitude()),
                0.0));
    }

    @Override
    public void setLongitude(double longitude) {
        colladaRoot.setPosition(new Position(Angle.fromDegrees(getLatitude()),
                Angle.fromDegrees(longitude),
                0.0));
    }

    /**
     *
     * @param latitude
     * @param longitude
     */
    @Override
    public void setPosition(double latitude, double longitude) {
        colladaRoot.setPosition(new Position(Angle.fromDegrees(latitude),
                Angle.fromDegrees(longitude),
                0.0));
    }

    @Override
    public void setScale(double x, double y, double z) {
        colladaRoot.setModelScale(new Vec4(x, y, z));
    }

    @Override
    public ArrayList<Placemark> getPlacemarkFromKmlCatalog(String catalog) {
        Kml kml = Kml.unmarshal(new File(catalog));
        return getPlacemarks(kml.getFeature()); 
    }

    public ArrayList<Placemark> getPlacemarks(Feature root) {
        ArrayList<Placemark> Placemarks = new ArrayList<>();

        if (root instanceof Container) {
            if (root instanceof Document) {
                ((Document) root).getFeature().forEach((feature) -> {
                    if (feature instanceof Placemark) {
                        Placemarks.add((Placemark) feature);
                    } else if ((feature instanceof Document)
                            || (feature instanceof Folder)) {
                        Placemarks.addAll(getPlacemarks(feature));
                    }
                });
            } else if (root instanceof Folder) {
                ((Folder) root).getFeature().forEach((feature) -> {
                    if (feature instanceof Placemark) {
                        Placemarks.add((Placemark) feature);
                    } else if ((feature instanceof Document)
                            || (feature instanceof Folder)) {
                        Placemarks.addAll(getPlacemarks(feature));
                    }
                });
            }
        } else {
            if (root instanceof Placemark) {
                Placemarks.add((Placemark) root);
            }
        }
        return Placemarks;
    }
}
