/*
 * Copyright (C) 2012 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */
package bzh.terrevirtuelle.navisu.currents.impl.controller;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.geom.*;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.util.*;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import gov.nasa.worldwindx.examples.analytics.AnalyticSurface;
import gov.nasa.worldwindx.examples.analytics.AnalyticSurfaceAttributes;
import java.util.ArrayList;

/**
 * Illustrates how to configure and display a 3D geographic grid of scalar data
 * using the World Wind <code>{@link
 * AnalyticSurface}</code>. Analytic surface defines a grid over a geographic
 * <code>{@link Sector}</code> at a specified altitude, and enables the caller
 * to specify the color and height at each grid point.
 * <p/>
 * This illustrates three key AnalyticSurface configurations: <ul>
 * <li>Displaying a static data set where each grid point uses color and height
 * to indicate the data's magnitude.</li> <li>Displaying data that varies by
 * color over time on the terrain surface.</li> <li>Displaying data that varies
 * by color and height over time at a specified altitude.</li> </ul>
 *
 * @author dcollins
 * @version $Id: AnalyticSurfaceDemo_1.java 1171 2013-02-11 21:45:02Z dcollins $
 */
public class AnalyticSurfaceDemo extends ApplicationTemplate {

    protected static final int DEFAULT_RANDOM_ITERATIONS = 1000;
    protected static final double DEFAULT_RANDOM_SMOOTHING = 0.6d;

    public static class AppFrame extends ApplicationTemplate.AppFrame {

        protected static final double HUE_BLUE = 240d / 360d;
        protected static final double HUE_RED = 0d / 360d;
        protected RenderableLayer analyticSurfaceLayer;

        public AppFrame() {
            this.initAnalyticSurfaceLayer();
        }

        protected void initAnalyticSurfaceLayer() {
            this.analyticSurfaceLayer = new RenderableLayer();
            this.analyticSurfaceLayer.setPickEnabled(false);
            this.analyticSurfaceLayer.setName("Analytic Surfaces");
            analyticSurfaceLayer.setMinActiveAltitude(0.0);
            insertAfterPlacenames(this.getWwd(), this.analyticSurfaceLayer);
            //   insertAfterPlacenames(this.getWwd(), this.analyticSurfaceLayer);
            this.getLayerPanel().update(this.getWwd());

            createRandomColorSurface(HUE_BLUE, HUE_RED, 1000, 1000, this.analyticSurfaceLayer);

        }
    }

    protected static void createRandomColorSurface(double minHue, double maxHue, int width, int height,
            RenderableLayer outLayer) {
        double minValue = -200e3;
        double maxValue = 200e3;
        /*
         minLat 48.2639  maxLat 48.5954
         minLon -5.37804 maxLon -4.75236
         tester le min et le max en lat et en lon
         */
        AnalyticSurface surface = new AnalyticSurface();

        surface.setSector(Sector.fromDegrees(48.2639, 48.5954, -5.37804, -4.75236));
        surface.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        surface.setDimensions(width, height);
        surface.setClientLayer(outLayer);
        outLayer.addRenderable(surface);
        outLayer.setEnabled(true);
     //   BufferWrapper firstBuffer = randomGridValues(width, height, minValue, maxValue);
        //Creation du buffer et peuplement du buffer
        BufferWrapper firstBuffer = loadGridValues(width, height, minValue, maxValue, 
                DEFAULT_RANDOM_ITERATIONS, DEFAULT_RANDOM_SMOOTHING,
                new BufferFactory.DoubleBufferFactory());
        //
        surface.setValues(createMixedColorGradientGridValues(1.0, firstBuffer, firstBuffer, minValue, maxValue, minHue, maxHue));
      
        //
        AnalyticSurfaceAttributes attr = new AnalyticSurfaceAttributes();
        attr.setDrawShadow(false);
        attr.setInteriorOpacity(1);
        attr.setDrawOutline(false);
        attr.setOutlineWidth(0);
        surface.setSurfaceAttributes(attr);
    }

    public static Iterable<? extends AnalyticSurface.GridPointAttributes> createMixedColorGradientGridValues(double a,
            BufferWrapper firstBuffer, BufferWrapper secondBuffer, double minValue, double maxValue,
            double minHue, double maxHue) {
        ArrayList<AnalyticSurface.GridPointAttributes> attributesList = new ArrayList<>();

        long length = Math.min(firstBuffer.length(), secondBuffer.length());
        for (int i = 0; i < length; i++) {
            double value = WWMath.mixSmooth(a, firstBuffer.getDouble(i), secondBuffer.getDouble(i));
            attributesList.add(AnalyticSurface.createColorGradientAttributes(value, minValue, maxValue, minHue, maxHue));
        }

        return attributesList;
    }

    //**************************************************************//
    //********************  Random Grid Construction  **************//
    //**************************************************************//
    public static BufferWrapper loadGridValues(int width, int height, double min, double max, int numIterations,
            double smoothness, BufferFactory factory) {
        int numValues = width * height;
        double[] values = new double[numValues];

        for (int i = 0; i < numIterations; i++) {
            double offset = 1d - (i / (double) numIterations);

            int x1 = (int) Math.round(Math.random() * (width - 1));
            int x2 = (int) Math.round(Math.random() * (width - 1));
            int y1 = (int) Math.round(Math.random() * (height - 1));
            int y2 = (int) Math.round(Math.random() * (height - 1));
            int dx1 = x2 - x1;
            int dy1 = y2 - y1;

            for (int y = 0; y < height; y++) {
                int dy2 = y - y1;
                for (int x = 0; x < width; x++) {
                    int dx2 = x - x1;

                    if ((dx2 * dy1 - dx1 * dy2) >= 0) {
                        values[x + y * width] += offset;
                    }
                }
            }
        }

        smoothValues(width, height, values, smoothness);
        scaleValues(values, numValues, min, max);
        BufferWrapper buffer = factory.newBuffer(numValues);
        buffer.putDouble(0, values, 0, numValues);

        return buffer;
    }
/*
    public static BufferWrapper randomGridValues(int width, int height, double min, double max) {
        return randomGridValues(width, height, min, max, DEFAULT_RANDOM_ITERATIONS, DEFAULT_RANDOM_SMOOTHING,
                new BufferFactory.DoubleBufferFactory());
    }
*/
    protected static void scaleValues(double[] values, int count, double minValue, double maxValue) {
        double min = Double.MAX_VALUE;
        double max = -Double.MAX_VALUE;
        for (int i = 0; i < count; i++) {
            if (min > values[i]) {
                min = values[i];
            }
            if (max < values[i]) {
                max = values[i];
            }
        }

        for (int i = 0; i < count; i++) {
            values[i] = (values[i] - min) / (max - min);
            values[i] = minValue + values[i] * (maxValue - minValue);
        }
    }

    protected static void smoothValues(int width, int height, double[] values, double smoothness) {
        // top to bottom
        for (int x = 0; x < width; x++) {
            smoothBand(values, x, width, height, smoothness);
        }

        // bottom to top
        int lastRowOffset = (height - 1) * width;
        for (int x = 0; x < width; x++) {
            smoothBand(values, x + lastRowOffset, -width, height, smoothness);
        }

        // left to right
        for (int y = 0; y < height; y++) {
            smoothBand(values, y * width, 1, width, smoothness);
        }

        // right to left
        int lastColOffset = width - 1;
        for (int y = 0; y < height; y++) {
            smoothBand(values, lastColOffset + y * width, -1, width, smoothness);
        }
    }

    protected static void smoothBand(double[] values, int start, int stride, int count, double smoothness) {
        double prevValue = values[start];
        int j = start + stride;

        for (int i = 0; i < count - 1; i++) {
            values[j] = smoothness * prevValue + (1 - smoothness) * values[j];
            prevValue = values[j];
            j += stride;
        }
    }

    public static void main(String[] args) {
        ApplicationTemplate.start("World Wind Analytic Surface", AppFrame.class);
    }
}
