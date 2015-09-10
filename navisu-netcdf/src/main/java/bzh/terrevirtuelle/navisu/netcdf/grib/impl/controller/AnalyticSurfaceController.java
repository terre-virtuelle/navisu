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
    protected double[] velocityField;

    public AnalyticSurfaceController(double[] values, int lonDimension, int latDimension,
            double minLat, double maxLat, double minLon, double maxLon,
            double minValue, double maxValue, double opacity,
            String legendTitle, String units) {
        this.velocityField = values;
        this.analyticSurfaceLayer = new RenderableLayer();
        this.analyticSurfaceLayer.setPickEnabled(false);
        this.analyticSurfaceLayer.setName("Meteorology");

        createSurface(HUE_BLUE, HUE_RED,
                lonDimension, latDimension,
                minValue, maxValue,
                Sector.fromDegrees(minLat, maxLat, minLon, maxLon),
                this.analyticSurfaceLayer,
                opacity,
                legendTitle, units);
    }

    protected Renderable createLegendRenderable(final AnalyticSurface surface,
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
    final void createSurface(double minHue, double maxHue,
            int width, int height,
            double minValue,
            double maxValue,
            Sector sector,
            RenderableLayer outLayer,
            double opacity,
            String legendTitle, String units) {
        
        AnalyticSurface surface = new AnalyticSurface();
        surface.setSector(sector);
        surface.setAltitude(10e2);
        surface.setDimensions(width, height);
        surface.setClientLayer(outLayer);
        outLayer.addRenderable(surface);

        BufferWrapper firstBuffer = AnalyticSurfaceController.this.gridValues(width, height, minValue, maxValue);
        BufferWrapper secondBuffer = AnalyticSurfaceController.this.gridValues(width, height, minValue, maxValue);

        mixValuesOverTime(firstBuffer, secondBuffer, minValue, maxValue, minHue, maxHue, surface);

        AnalyticSurfaceAttributes attr = new AnalyticSurfaceAttributes();
        //attr.setShadowOpacity(1.0);
        attr.setInteriorOpacity(opacity);
        attr.setDrawOutline(true);
        attr.setOutlineMaterial(Material.BLACK);
        surface.setSurfaceAttributes(attr);

        Format legendLabelFormat = new DecimalFormat("##.#" + units) {
            @Override
            public StringBuffer format(double number, StringBuffer result, FieldPosition fieldPosition) {
                return super.format(number, result, fieldPosition);
            }
        };

        AnalyticSurfaceLegend legend = AnalyticSurfaceLegend.fromColorGradient(minValue, maxValue, minHue, maxHue,
                AnalyticSurfaceLegend.createDefaultColorGradientLabels(minValue, maxValue, legendLabelFormat),
                AnalyticSurfaceLegend.createDefaultTitle(legendTitle));
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
    //********************  Grid Construction  **************//
    //**************************************************************//
    protected static final double DEFAULT_SMOOTHING = 0.5d;

    public BufferWrapper gridValues(
            int width, int height,
            double min, double max,
            double smoothness,
            BufferFactory factory) {
        int numValues = velocityField.length;

        smoothValues(width, height, velocityField, smoothness);
        scaleValues(velocityField, numValues, min, max);
        BufferWrapper buffer = factory.newBuffer(numValues);
        buffer.putDouble(0, velocityField, 0, numValues);

        return buffer;
    }

    public BufferWrapper gridValues(int width, int height,
            double min, double max) {
        return gridValues(width, height, min, max,
                DEFAULT_SMOOTHING,
                new BufferFactory.DoubleBufferFactory());
    }

    protected void scaleValues(double[] values, int count,
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

    protected void smoothValues(int width, int height,
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

    protected void smoothBand(double[] values,
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
