/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.wms.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.WebDriver;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.impl.WebDriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.widgets.textlist.TextListController;
import bzh.terrevirtuelle.navisu.wms.WMS;
import bzh.terrevirtuelle.navisu.wms.WMSServices;
import gov.nasa.worldwind.Factory;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.avlist.AVListImpl;
import gov.nasa.worldwind.globes.ElevationModel;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.ogc.wms.WMSCapabilities;
import gov.nasa.worldwind.ogc.wms.WMSLayerCapabilities;
import gov.nasa.worldwind.ogc.wms.WMSLayerStyle;
import gov.nasa.worldwind.terrain.CompoundElevationModel;
import gov.nasa.worldwind.util.WWUtil;
import gov.nasa.worldwind.wms.Capabilities;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * @date 4 mars 2015
 * @author Serge Morvan
 */
public class WMSImpl
        implements WMS, WMSServices, WebDriver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    GuiAgentServices guiAgentServices;

    protected static final String GROUP = "WMS";
    protected static final String NAME_0 = "WMS";
    protected static final String NAME_1 = "WMS_CATALOG";
    protected WorldWindow wwd = null;
    protected URI serverURI = null;
    protected WMSCapabilities caps;
    protected StackPane root;
    protected String server;
    protected TextListController textListController;
    protected int i = 0;
    protected GridPane gridPane;
    protected final TreeSet<WMSImpl.LayerInfo> layerInfos = new TreeSet<>((WMSImpl.LayerInfo infoA, WMSImpl.LayerInfo infoB) -> {
        String nameA = infoA.getName();
        String nameB = infoB.getName();
        return nameA.compareTo(nameB);
    });

    @Override
    public void init() {
        wwd = GeoWorldWindViewImpl.getWW();
        root = guiAgentServices.getRoot();
        layerTreeServices.createGroup(GROUP);
    }

    @Override
    public void componentInitiated() {

    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public void load(String server) {
        this.server = server;
        textListController = new TextListController(KeyCode.A, KeyCombination.CONTROL_DOWN);
        gridPane = textListController.getGridPane();
        guiAgentServices.getJobsManager().newJob(server, (progressHandle) -> {
            try {
                this.serverURI = new URI(server.trim());
                caps = WMSCapabilities.retrieve(this.serverURI);
                caps.parse();
                Platform.runLater(() -> {
                    root.getChildren().add(textListController);
                    textListController.setTitle(new Text(server));
                });
                final List<WMSLayerCapabilities> namedLayerCaps = caps.getNamedLayers();
                if (namedLayerCaps == null) {
                    return;
                }
                namedLayerCaps.forEach((lc) -> {
                    Set<WMSLayerStyle> styles = lc.getStyles();
                    if (styles == null || styles.isEmpty()) {
                        WMSImpl.LayerInfo layerInfo = createLayerInfo(caps, lc, null);
                        WMSImpl.this.layerInfos.add(layerInfo);
                    } else {
                        styles.stream().map((style) -> createLayerInfo(caps, lc, style)).forEachOrdered((layerInfo) -> {
                            WMSImpl.this.layerInfos.add(layerInfo);
                        });
                    }
                });
                Platform.runLater(() -> {
                    layerInfos.stream().forEach((layerInfo) -> {
                        addLayerInfoPanel(layerInfo);
                    });
                });
            } catch (Exception ex) {
                Logger.getLogger(WebDriverManagerImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        });

    }

    protected void addLayerInfoPanel(WMSImpl.LayerInfo linfo) {
        final CheckBox checkBox = new CheckBox(linfo.getAbstract());

        checkBox.setTooltip(new Tooltip(linfo.getAbstract()));
        checkBox.setText(linfo.getTitle());
        gridPane.addRow(i++, checkBox);
        WMSImpl.LayerInfoAction action = new WMSImpl.LayerInfoAction(linfo, wwd);
        checkBox.setUserData(action);
        checkBox.selectedProperty().addListener((ObservableValue<? extends Boolean> obs, Boolean wasSelected, Boolean isSelected) -> {
            if (isSelected) {
                ((WMSImpl.LayerInfoAction) checkBox.getUserData()).create();
            } else {
                ((WMSImpl.LayerInfoAction) checkBox.getUserData()).update();
            }
        });
    }

    protected WMSImpl.LayerInfo createLayerInfo(WMSCapabilities caps, WMSLayerCapabilities layerCaps, WMSLayerStyle style) {
        WMSImpl.LayerInfo linfo = new WMSImpl.LayerInfo();
        linfo.caps = caps;
        linfo.params = new AVListImpl();
        linfo.params.setValue(AVKey.LAYER_NAMES, layerCaps.getName());
        if (style != null) {
            linfo.params.setValue(AVKey.STYLE_NAMES, style.getName());
        }
        String abs = layerCaps.getLayerAbstract();
        if (!WWUtil.isEmpty(abs)) {
            linfo.params.setValue(AVKey.LAYER_ABSTRACT, abs);
        }
        linfo.params.setValue(AVKey.DISPLAY_NAME, makeTitle(caps, linfo));
        return linfo;
    }

    protected static String makeTitle(WMSCapabilities caps, WMSImpl.LayerInfo layerInfo) {
        String layerNames = layerInfo.params.getStringValue(AVKey.LAYER_NAMES);
        String styleNames = layerInfo.params.getStringValue(AVKey.STYLE_NAMES);
        String[] lNames = layerNames.split(",");
        String[] sNames = styleNames != null ? styleNames.split(",") : null;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lNames.length; i++) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            String layerName = lNames[i];
            WMSLayerCapabilities lc = caps.getLayerByName(layerName);
            String layerTitle = lc.getTitle();
            sb.append(layerTitle != null ? layerTitle : layerName);
            if (sNames == null || sNames.length <= i) {
                continue;
            }
            String styleName = sNames[i];
            WMSLayerStyle style = lc.getStyleByName(styleName);
            if (style == null) {
                continue;
            }
            sb.append(" : ");
            String styleTitle = style.getTitle();
            sb.append(styleTitle != null ? styleTitle : styleName);
        }
        return sb.toString();
    }

    protected static Object createComponent(WMSCapabilities caps, AVList params) {
        AVList configParams = params.copy(); // Copy to insulate changes from the caller.
        // Some wms servers are slow, so increase the timeouts and limits used by world wind's retrievers.
        configParams.setValue(AVKey.URL_CONNECT_TIMEOUT, 30000);
        configParams.setValue(AVKey.URL_READ_TIMEOUT, 30000);
        configParams.setValue(AVKey.RETRIEVAL_QUEUE_STALE_REQUEST_LIMIT, 60000);
        try {
            String factoryKey = getFactoryKeyForCapabilities(caps);
            Factory factory = (Factory) WorldWind.createConfigurationComponent(factoryKey);
            return factory.createFromConfigSource(caps, configParams);
        } catch (IllegalStateException | IllegalArgumentException e) {
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    protected void updateComponent(Object component, boolean enable, boolean first) {
        if (component instanceof Layer) {
            Layer layer = (Layer) component;
            LayerList layers = this.wwd.getModel().getLayers();
            layer.setEnabled(enable);
            layer.setPickEnabled(true);
            layer.setMinActiveAltitude(1000);
            layer.setMaxActiveAltitude(20000000);
            if (enable) {
                if (!layers.contains(layer)) {
                    GeoLayer<Layer> geoLayer = GeoLayer.factory.newWorldWindGeoLayer(layer);
                    geoViewServices
                            .getLayerManager()
                            .insertGeoLayer(geoLayer);
                    if (first == true) {
                        layerTreeServices.addGeoLayer("WMS Layers", GeoLayer.factory.newWorldWindGeoLayer(layer));
                    }
                }
            } else {
                layers.remove(layer);
            }
        } else if (component instanceof ElevationModel) {
            ElevationModel model = (ElevationModel) component;
            CompoundElevationModel compoundModel
                    = (CompoundElevationModel) this.wwd.getModel().getGlobe().getElevationModel();
            if (enable) {
                if (!compoundModel.getElevationModels().contains(model)) {
                    compoundModel.addElevationModel(model);
                }
            }
        }
    }

    protected static String getFactoryKeyForCapabilities(WMSCapabilities caps) {
        boolean hasApplicationBilFormat = false;
        Set<String> formats = caps.getImageFormats();
        for (String s : formats) {
            if (s.contains("application/bil")) {
                hasApplicationBilFormat = true;
                break;
            }
        }
        return hasApplicationBilFormat ? AVKey.ELEVATION_MODEL_FACTORY : AVKey.LAYER_FACTORY;
    }

    @Override
    public WebDriver getDriver() {
        return this;
    }

    @Override
    public Capabilities GetCapabilities() {
        return null;
    }

    @Override
    public boolean canOpen(String category) {
        return category.equalsIgnoreCase("WMS");
    }

    @Override
    public void open(ProgressHandle progressHandle, String url) {
        load(url);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void load() {
    }

    protected static class LayerInfo {

        protected WMSCapabilities caps;
        protected AVListImpl params = new AVListImpl();

        protected String getTitle() {
            return params.getStringValue(AVKey.DISPLAY_NAME);
        }

        protected String getName() {
            return params.getStringValue(AVKey.LAYER_NAMES);
        }

        protected String getAbstract() {
            return params.getStringValue(AVKey.LAYER_ABSTRACT);
        }
    }

    protected class LayerInfoAction {

        protected WorldWindow wwd;
        protected WMSImpl.LayerInfo layerInfo;
        protected Object component;
        protected String title;
        protected boolean first = true;

        public LayerInfoAction(WMSImpl.LayerInfo linfo, WorldWindow wwd) {
            title = linfo.getTitle();
            this.wwd = wwd;
            this.layerInfo = linfo;
        }

        public void create() {
            if (this.component == null) {
                this.component = createComponent(layerInfo.caps, layerInfo.params);
            }
            updateComponent(this.component, true, first);
            first = false;
            wwd.redraw();
        }

        public void update() {
            if (this.component != null) {
                updateComponent(this.component, true, first);
            }
            wwd.redraw();
        }

        public String getTitle() {
            return title;
        }
    }

}
