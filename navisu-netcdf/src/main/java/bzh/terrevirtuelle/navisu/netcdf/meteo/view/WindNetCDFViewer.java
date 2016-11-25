/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.meteo.view;

import bzh.terrevirtuelle.navisu.netcdf.common.view.NetCDFViewer;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.domain.netcdf.Netcdf;
import bzh.terrevirtuelle.navisu.domain.netcdf.common.TimeSeriesVectorField;
import bzh.terrevirtuelle.navisu.netcdf.common.view.symbols.Arrow;
import bzh.terrevirtuelle.navisu.netcdf.meteo.view.symbols.WindArrow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.ArrayList;
import java.util.List;
import ucar.nc2.Variable;

/**
 *
 * @author serge
 * @date Nov 24, 2016
 */
public class WindNetCDFViewer extends NetCDFViewer {

    public WindNetCDFViewer(GuiAgentServices guiAgentServices,
            RenderableLayer vectorLayer,
            RenderableLayer analyticLayer,
            RenderableLayer legendLayer,
            Netcdf netcdf,
            String dataInfo,
            List<Variable> variables,
            String name, String fileName,
            TimeSeriesVectorField timeSeriesVectorField) {
        super(guiAgentServices,
                vectorLayer, analyticLayer, legendLayer,
                netcdf,
                dataInfo, variables,
                name, fileName,
                timeSeriesVectorField);
    }

    @Override
    protected void createVectors() {
        List<Arrow> arrows = new ArrayList<>();
        int l = 0;
        for (int h = 0; h < latDimension; h += 5) {
            for (int w = 0; w < lonDimension; w += 5) {
                if ((!Double.isNaN(values[h + l + w]))) {
                    Arrow arrow = new WindArrow(latTab[h], lonTab[w], values[l + h + w]);
                    double alpha = -Math.toDegrees(directions[l + h + w]) + arrow.getRotation();
                    if (alpha < 0) {
                        alpha = 360 + alpha;
                    }
                    if (!Double.isNaN(alpha)) {
                        arrow.setRotation(alpha);
                        arrow.setValue(AVKey.DISPLAY_NAME, String.format("%.1f m/s , %.1f , %.1f , %.1f ",
                                values[l + h + w], latTab[h], lonTab[w], Math.toDegrees(directions[l + h + w])));  //+" m/s");
                        arrows.add(arrow);
                    }
                }
            }
            l += lonDimension;
        }
        vectorLayer.addRenderables(arrows);
    }
}
