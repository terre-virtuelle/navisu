/*
 * Copyright (C) 2012 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */
package bzh.terrevirtuelle.navisu.netcdf.grib.impl.controller;

import bzh.terrevirtuelle.navisu.netcdf.common.view.analytics.AnalyticSurface;
import bzh.terrevirtuelle.navisu.netcdf.common.view.analytics.AnalyticSurfaceAttributes;
import bzh.terrevirtuelle.navisu.netcdf.common.view.analytics.AnalyticSurfaceLegend;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Extent;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.DrawContext;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.util.BufferFactory;
import gov.nasa.worldwind.util.BufferWrapper;
import gov.nasa.worldwind.util.WWMath;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import java.awt.Point;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
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
 * @version $Id: AnalyticSurfaceDemo.java 1171 2013-02-11 21:45:02Z dcollins $
 */
public class AnalyticSurfaceController {

    protected static final double HUE_BLUE = 240d / 360d;
    protected static final double HUE_RED = 0d / 360d;
    protected RenderableLayer analyticSurfaceLayer;

    public AnalyticSurfaceController() {

        this.analyticSurfaceLayer = new RenderableLayer();
        this.analyticSurfaceLayer.setPickEnabled(false);
        this.analyticSurfaceLayer.setName("Analytic Surfaces");

        createSurface(HUE_BLUE, HUE_RED,
                40, 40,
                0.0, 100.0,
                Sector.fromDegrees(50.000003814697266, 
                        46.000003814697266,1.0, -8.0),
                this.analyticSurfaceLayer);
    }

    protected static Renderable createLegendRenderable(final AnalyticSurface surface,
            final double surfaceMinScreenSize,
            final AnalyticSurfaceLegend legend) {
        return (DrawContext dc) -> {
            Extent extent = surface.getExtent(dc);
            if (!extent.intersects(dc.getView().getFrustumInModelCoordinates())) {
                return;
            }
            if (WWMath.computeSizeInWindowCoordinates(dc, extent) < surfaceMinScreenSize) {
                return;
            }
            legend.render(dc);
        };
    }

    //**************************************************************//
    //********************  Surface  ***************//
    //**************************************************************//
    protected static void createSurface(double minHue, double maxHue,
            int width, int height,
            double minValue,
            double maxValue,
            Sector sector,
            RenderableLayer outLayer) {

        AnalyticSurface surface = new AnalyticSurface();
        surface.setSector(sector);
        surface.setAltitude(10e2);
        surface.setDimensions(width, height);
        surface.setClientLayer(outLayer);
        outLayer.addRenderable(surface);

        BufferWrapper firstBuffer = randomGridValues(width, height, minValue, maxValue);
        BufferWrapper secondBuffer = randomGridValues(width, height, minValue * 2d, maxValue / 2d);
        mixValuesOverTime(firstBuffer, secondBuffer, minValue, maxValue, minHue, maxHue, surface);

        AnalyticSurfaceAttributes attr = new AnalyticSurfaceAttributes();
        attr.setShadowOpacity(1.0);
        attr.setInteriorOpacity(0.5);
        attr.setDrawOutline(true);
        attr.setOutlineMaterial(Material.BLACK);
        surface.setSurfaceAttributes(attr);

        Format legendLabelFormat = new DecimalFormat("##.# Kt") {
            @Override
            public StringBuffer format(double number, StringBuffer result, FieldPosition fieldPosition) {

                return super.format(number, result, fieldPosition);
            }
        };

        AnalyticSurfaceLegend legend = AnalyticSurfaceLegend.fromColorGradient(minValue, maxValue, minHue, maxHue,
                AnalyticSurfaceLegend.createDefaultColorGradientLabels(minValue, maxValue, legendLabelFormat),
                AnalyticSurfaceLegend.createDefaultTitle("Wind"));
        legend.setOpacity(0.8);
        legend.setScreenLocation(new Point(950, 300));
        outLayer.addRenderable(createLegendRenderable(surface, 300, legend));
    }

    protected static void mixValuesOverTime(
            final BufferWrapper firstBuffer,
            final BufferWrapper secondBuffer,
            final double minValue, final double maxValue,
            final double minHue, final double maxHue,
            final AnalyticSurface surface) {
        double t = 10.0;
        int ti = (int) Math.floor(t);
        double a = t - ti;
        if ((ti % 2) == 0) {
            a = 1d - a;
        }
        surface.setValues(createMixedColorGradientGridValues(
                a, firstBuffer, secondBuffer, minValue, maxValue, minHue, maxHue));
        if (surface.getClientLayer() != null) {
            surface.getClientLayer().firePropertyChange(AVKey.LAYER, null, surface.getClientLayer());
        }

    }

    public static Iterable<? extends AnalyticSurface.GridPointAttributes> createMixedColorGradientGridValues(double a,
            BufferWrapper firstBuffer, BufferWrapper secondBuffer, double minValue, double maxValue,
            double minHue, double maxHue) {
        ArrayList<AnalyticSurface.GridPointAttributes> attributesList
                = new ArrayList<>();

        long length = Math.min(firstBuffer.length(), secondBuffer.length());
        for (int i = 0; i < length; i++) {
            double value = WWMath.mixSmooth(a, firstBuffer.getDouble(i), secondBuffer.getDouble(i));
            attributesList.add(
                    AnalyticSurface.createColorGradientAttributes(value, minValue, maxValue, minHue, maxHue));
        }

        return attributesList;
    }

    //**************************************************************//
    //********************  Random Grid Construction  **************//
    //**************************************************************//
    protected static final int DEFAULT_RANDOM_ITERATIONS = 1000;
    protected static final double DEFAULT_RANDOM_SMOOTHING = 0.5d;

    public static BufferWrapper randomGridValues(int width, int height,
            double min, double max, int numIterations,
            double smoothness,
            BufferFactory factory) {
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

    public static BufferWrapper randomGridValues(int width, int height,
            double min, double max) {
        return randomGridValues(width, height, min, max, DEFAULT_RANDOM_ITERATIONS, DEFAULT_RANDOM_SMOOTHING,
                new BufferFactory.DoubleBufferFactory());
    }

    protected static void scaleValues(double[] values, int count,
            double minValue, double maxValue) {
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

    protected static void smoothValues(int width, int height,
            double[] values, double smoothness) {
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

    protected static void smoothBand(double[] values,
            int start, int stride, int count,
            double smoothness) {
        double prevValue = values[start];
        int j = start + stride;

        for (int i = 0; i < count - 1; i++) {
            values[j] = smoothness * prevValue + (1 - smoothness) * values[j];
            prevValue = values[j];
            j += stride;
        }
    }

    public RenderableLayer getLayer() {
        return analyticSurfaceLayer;
    }

}
