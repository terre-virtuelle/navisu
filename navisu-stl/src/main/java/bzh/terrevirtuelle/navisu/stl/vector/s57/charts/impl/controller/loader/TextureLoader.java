/*
 * Copyright (C) 2012 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.TiledImageLayer;
import gov.nasa.worldwind.render.DrawContext;
import gov.nasa.worldwind.render.Polygon;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class TextureLoader {

    protected Polygon polygon;
    protected String outDir;
    protected Sector selectedSector = null;
    protected WorldWindow wwd;

    public TextureLoader(String outFilename, Polygon polygon) {
        this.polygon = polygon;
        this.outDir = outFilename;
        List<? extends Position> positions = polygon.getBoundaries().get(0);
        selectedSector = new Sector(positions.get(0).getLatitude(), positions.get(3).getLatitude(),
                positions.get(0).getLongitude(), positions.get(1).getLongitude());
        wwd = GeoWorldWindViewImpl.getWW();

    }

    public final void doSaveImage() {
        TiledImageLayer currentLayer = null;

        LayerList list = wwd.getModel().getLayers();
        DrawContext dc = wwd.getSceneController().getDrawContext();
        ListIterator iterator = list.listIterator();

        while (iterator.hasNext()) {
            Object o = iterator.next();
            if (o instanceof TiledImageLayer) {
                TiledImageLayer layer = (TiledImageLayer) o;
                if (layer.isEnabled() && layer.isLayerActive(dc) && layer.isLayerInView(dc)) {
                    currentLayer = layer;
                }
            }
        }
        if (null == currentLayer) {
            return;
        }
        final TiledImageLayer activeLayer = currentLayer;
        BufferedImage image;
        try {
            image = captureImage(activeLayer, selectedSector, 2048);
            image = flip(image);
            writeImageToFile(selectedSector, image, outDir);
        } catch (Exception ex) {
            Logger.getLogger(TextureLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private BufferedImage captureImage(TiledImageLayer layer, Sector sector, int minSize)
            throws Exception {
        int[] size = this.adjustSize(sector, minSize);
        int width = size[0], height = size[1];

        String mimeType = layer.getDefaultImageFormat();
        if (layer.isImageFormatAvailable("image/png")) {
            mimeType = "image/png";
        } else if (layer.isImageFormatAvailable("image/jpg")) {
            mimeType = "image/jpg";
        }
        return layer.composeImageForSector(this.selectedSector, width, height, 1d, -1, mimeType, true, null, 30000);
    }

    private int[] adjustSize(Sector sector, int desiredSize) {
        int[] size = new int[]{desiredSize, desiredSize};

        if (null != sector && desiredSize > 0) {
            LatLon centroid = sector.getCentroid();
            Angle dLat = LatLon.greatCircleDistance(new LatLon(sector.getMinLatitude(), sector.getMinLongitude()),
                    new LatLon(sector.getMaxLatitude(), sector.getMinLongitude()));
            Angle dLon = LatLon.greatCircleDistance(new LatLon(centroid.getLatitude(), sector.getMinLongitude()),
                    new LatLon(centroid.getLatitude(), sector.getMaxLongitude()));

            double max = Math.max(dLat.radians, dLon.radians);
            double min = Math.min(dLat.radians, dLon.radians);

            int minSize = (int) ((min == 0d) ? desiredSize : ((double) desiredSize * min / max));

            if (dLon.radians > dLat.radians) {
                size[0] = desiredSize;      // width
                size[1] = minSize;  // height
            } else {
                size[0] = minSize;  // width
                size[1] = desiredSize;      // height
            }
        }
        return size;
    }

    private BufferedImage flip(BufferedImage in) {
        BufferedImage out = new BufferedImage(in.getWidth(null),
                in.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics gb = out.getGraphics();
        gb.drawImage(in, 0, 0, null);
        gb.dispose();
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-in.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        out = op.filter(out, null);
        return out;
    }

    private void writeImageToFile(Sector sector, BufferedImage image, String outDir) {
        File myNewJPegFile = new File(outDir + "/image.jpg");
        try {
            ImageIO.write(image, "jpg", myNewJPegFile);
        } catch (IOException ex) {
            Logger.getLogger(TextureLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
