/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.common.controller;

import bzh.terrevirtuelle.navisu.core.util.analytics.AnalyticSurface;
import bzh.terrevirtuelle.navisu.core.util.analytics.AnalyticSurfaceLegend;
import gov.nasa.worldwind.geom.Extent;
import gov.nasa.worldwind.render.DrawContext;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.util.WWMath;

/**
 *
 * @author serge
 */
public class LegendController {

    AnalyticSurface surface;
    double surfaceMinScreenSize;
    AnalyticSurfaceLegend legend;

    public LegendController(AnalyticSurface surface, double surfaceMinScreenSize, AnalyticSurfaceLegend legend) {
        this.surface = surface;
        this.surfaceMinScreenSize = surfaceMinScreenSize;
        this.legend = legend;
        /*
        AnalyticSurfaceLegend legend = AnalyticSurfaceLegend.fromColorGradient(minValue, 40, minHue, maxHue,
                    AnalyticSurfaceLegend.createDefaultColorGradientLabels(minValue, 40, legendLabelFormat),
                    AnalyticSurfaceLegend.createDefaultTitle("Speed"));
            legend.setOpacity(0.8);
            legend.setScreenLocation(new Point(900, 300));
            legendLayer.addRenderable(createLegendRenderable(surface, maxValue, legend));
*/
    }

    public Renderable createLegendRenderable(final AnalyticSurface surface,
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
}
