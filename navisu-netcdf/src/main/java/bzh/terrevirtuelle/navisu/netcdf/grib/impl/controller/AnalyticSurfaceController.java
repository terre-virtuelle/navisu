/*
 * Copyright (C) 2012 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */
package bzh.terrevirtuelle.navisu.netcdf.grib.impl.controller;

import bzh.terrevirtuelle.navisu.netcdf.common.view.analytics.AnalyticSurface;
import bzh.terrevirtuelle.navisu.netcdf.common.view.analytics.AnalyticSurfaceAttributes;
import bzh.terrevirtuelle.navisu.netcdf.common.view.analytics.AnalyticSurfaceLegend;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.geom.Extent;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.DrawContext;
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
 * @author dcollins
 * @author Serge Morvan
 */
public class AnalyticSurfaceController {

    protected static final double HUE_BLUE = 240d / 360d;
    protected static final double HUE_RED = 0d / 360d;
    protected RenderableLayer layer;
    protected double[] vectorField;

    public AnalyticSurfaceController(RenderableLayer layer, RenderableLayer legendLayer,
            final double[] data,
            int latDimension, int lonDimension,
            double minLat, double maxLat, double minLon, double maxLon,
            double minValue, double maxValue,
            double opacity,
            String legendTitle, String units) {
        this.layer = layer;
        this.vectorField = data;
        //
        //this.vectorField = new double[latDimension * lonDimension];
        //  System.arraycopy(data, 0, vectorField, 0, data.length);

        this.layer.setPickEnabled(false);
        this.layer.setName("Gradient");

        createSurface(vectorField,
                HUE_BLUE, HUE_RED,
                minLat, maxLat, minLon, maxLon,
                minValue, maxValue,
                layer, legendLayer,
                legendTitle, units);
    }

    final void createSurface(double[] vectorField,
            double minHue, double maxHue,
            double minLat, double maxLat, double minLon, double maxLon,
            double minVal, double maxVal,
            RenderableLayer outLayer, RenderableLayer legendLayer,
            String legendTitle, String units) {
        double minValue = minVal;
        double maxValue = maxVal;

        AnalyticSurface surface = new AnalyticSurface();

        Sector sector = Sector.fromDegrees(minLat, maxLat, minLon, maxLon);
        surface.setSector(sector);
        surface.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
        surface.setClientLayer(outLayer);
        outLayer.addRenderable(surface);

        BufferWrapper firstBuffer = new BufferFactory.DoubleBufferFactory().newBuffer(vectorField.length);
        firstBuffer.putDouble(0, vectorField, 0, vectorField.length);
        //   BufferWrapper secondBuffer = randomGridValues(minValue * 2d, maxValue / 2d);

        mixValuesOverTime(2L, firstBuffer, firstBuffer, minValue, maxValue, minHue, maxHue, surface);

        AnalyticSurfaceAttributes attr = new AnalyticSurfaceAttributes();
        attr.setDrawShadow(false);
        attr.setInteriorOpacity(0.6);
        attr.setOutlineWidth(1);
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
        legendLayer.addRenderable(createLegendRenderable(surface, 300, legend));
    }

    protected void mixValuesOverTime(
            final long timeToMix,
            final BufferWrapper firstBuffer, final BufferWrapper secondBuffer,
            final double minValue, final double maxValue, final double minHue, final double maxHue,
            final AnalyticSurface surface) {
        /*   
        Timer timer = new Timer(20, new ActionListener()
        {
            protected long startTime = -1;

            public void actionPerformed(ActionEvent e)
            {
                if (this.startTime < 0)
                    this.startTime = System.currentTimeMillis();

                double t = (double) (e.getWhen() - this.startTime) / (double) timeToMix;
                int ti = (int) Math.floor(t);

                double a = t - ti;
                if ((ti % 2) == 0)
                    a = 1d - a;
         */
        surface.setValues(createMixedColorGradientGridValues(//a
                0, firstBuffer, secondBuffer, minValue, maxValue, minHue, maxHue));

        /*
                if (surface.getClientLayer() != null)
                    surface.getClientLayer().firePropertyChange(AVKey.LAYER, null, surface.getClientLayer());
            }
        });
        timer.start();
         */
    }

    public Iterable<? extends AnalyticSurface.GridPointAttributes> createMixedColorGradientGridValues(double a,
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

    protected Renderable createLegendRenderable(final AnalyticSurface surface, final double surfaceMinScreenSize,
            final AnalyticSurfaceLegend legend) {
        return new Renderable() {
            public void render(DrawContext dc) {
                Extent extent = surface.getExtent(dc);
                if (!extent.intersects(dc.getView().getFrustumInModelCoordinates())) {
                    return;
                }
                if (WWMath.computeSizeInWindowCoordinates(dc, extent) < surfaceMinScreenSize) {
                    return;
                }
                legend.render(dc);
            }
        };
    }
}
