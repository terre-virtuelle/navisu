/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.shapefiles.impl;

import bzh.terrevirtuelle.navisu.shapefiles.impl.controller.ShapefileController;
import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.dbase.app.DBaseApp;
import bzh.terrevirtuelle.navisu.shapefiles.ShapefileObject;
import bzh.terrevirtuelle.navisu.shapefiles.ShapefileObjectServices;
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.layers.Layer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author Serge Morvan
 * @date 10 nov. 2014 NaVisu project
 */
public class ShapefileObjectImpl
        implements ShapefileObject, ShapefileObjectServices, Driver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayerTreeServices layerTreeServices;

    private static final String NAME = "SHP";
    private static final String EXTENSION_0 = ".shp";
    protected static final String GROUP = "Shape files";

    protected List<Layer> layers;
    protected static final Logger LOGGER = Logger.getLogger(ShapefileObjectImpl.class.getName());
    protected WorldWindow wwd;

    @Override
    public void openFile(String file) {
        this.open(null, file);
    }

    @Override
    public boolean canOpen(String file) {
        boolean canOpen = false;

        if (file.toLowerCase().endsWith(EXTENSION_0)) {
            canOpen = true;
        }

        return canOpen;
    }

    @Override
    public void open(ProgressHandle progressHandle, String... files) {
        for (String file : files) {
            this.handleOpenFile(progressHandle, file);
        }
    }

    @SuppressWarnings("unchecked")
    protected void handleOpenFile(ProgressHandle pHandle, String fileName) {
        List<List<String>> dbList;
        DBFReader reader;
        Map<String, Integer> keys;
        dbList = new ArrayList<>();
        keys = new HashMap<>();
        String[] tab = fileName.split("\\.");
        String dbFileName = tab[0];
        dbFileName += ".dbf";
        try {
            InputStream inputStream = new FileInputStream(dbFileName);
            reader = new DBFReader(inputStream);
            int numberOfFields = reader.getFieldCount();
            for (int i = 0; i < numberOfFields; i++) {
                DBFField field = reader.getField(i);
                keys.put(field.getName().trim(), i);
            }
            Object[] rowObjects;
            while ((rowObjects = reader.nextRecord()) != null) {
                List<String> tmp = new ArrayList<>();
                for (Object rowObject : rowObjects) {
                    tmp.add(rowObject.toString());
                }
                dbList.add(tmp);
            }
            /*
            // lecture des element repondant a une cle
            int index = keys.get("auteurs");
            dbList.forEach((l) -> {
                System.out.println(l.get(index));
            });
             */
        } catch (DBFException | FileNotFoundException ex) {
            Logger.getLogger(ShapefileObjectImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        LOGGER.log(Level.INFO, "Opening {0} ...", fileName);
        ShapefileController shapefileController = ShapefileController.getInstance();
        if (!dbList.isEmpty()) {
            layers = shapefileController.init(fileName, keys, dbList);
        } else {
            layers = shapefileController.init(fileName);
        }
        layers.stream().filter((l) -> (l != null)).map((l) -> {
            String name = l.getName();
            geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(l));
            return l;
        }).forEach((l) -> {
            layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
        });
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String[] getExtensions() {
        return new String[]{"*" + EXTENSION_0};
    }

    @Override
    public void componentInitiated() {
        layerTreeServices.createGroup(GROUP);
        wwd = GeoWorldWindViewImpl.getWW();
        wwd.addPositionListener((PositionEvent event) -> {
            float altitude = ((int) wwd.getView().getCurrentEyePosition().getAltitude());
            if (altitude >= 3000) {
                clip();
            } else {
                unClip();
            }
        });
    }

    private void clip() {
        if (layers != null) {
            layers.stream().filter((l) -> (l.getName().contains(NAME))).forEach((l) -> {
                l.setEnabled(false);
            });
        }
    }

    private void unClip() {
        if (layers != null) {
            layers.stream().filter((l) -> (l.getName().contains(NAME))).forEach((l) -> {
                l.setEnabled(true);
            });
        }
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
}
