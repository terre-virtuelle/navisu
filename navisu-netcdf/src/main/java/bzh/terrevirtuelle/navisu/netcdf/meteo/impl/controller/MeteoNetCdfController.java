/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.netcdf.meteo.impl.controller;

import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.domain.netcdf.Netcdf;
import bzh.terrevirtuelle.navisu.domain.netcdf.common.TimeSeriesVectorField;
import bzh.terrevirtuelle.navisu.netcdf.common.view.symbols.meteorology.Arrow;
import bzh.terrevirtuelle.navisu.netcdf.grib.impl.controller.AnalyticSurfaceController;
import bzh.terrevirtuelle.navisu.netcdf.grib.impl.controller.AnalyticSurfaceController_old;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ucar.ma2.Array;
import ucar.nc2.NCdumpW;
import ucar.nc2.Variable;

/**
 *
 * @author serge
 * @date Sep 20, 2016
 */
public class MeteoNetCdfController {

    private Netcdf netcdf;
    private List<Variable> variables;
    private Array u = null;
    private Array v = null;
    private Array latitudes = null;
    private Array longitudes = null;
    private Array height = null;
    private double[] values;
    private double[] directions;
    private final double[] latTab;
    private final double[] lonTab;
    private Array time = null;
    private Array reftime = null;
    private TimeSeriesVectorField timeSeriesVectorField = null;
    protected final String GROUP = "Meteo";
    private final String NAME0 = "NetCdfVector";
    private final String NAME1 = "NetCdfAnalytic";
    private final String NAME2 = "NetCdfLegend";
    protected RenderableLayer meteoLayerVector;
    protected RenderableLayer meteoLayerAnalytic;
    protected RenderableLayer meteoLayerLegend;
    private static final Logger LOGGER = Logger.getLogger(MeteoNetCdfController.class.getName());
    protected AnalyticSurfaceController_old analyticSurfaceController;

    public MeteoNetCdfController(LayersManagerServices layersManagerServices, String fileName) {
        netcdf = new Netcdf(fileName);
        /*
        System.out.println("GlobalAttributes");
        System.out.println(netcdf.getNetcdfDataset().getGlobalAttributes());
        System.out.println("DetailInfo");
        System.out.println(netcdf.getNetcdfDataset().getDetailInfo());
        System.out.println("Groups");
        System.out.println(netcdf.getNetcdfDataset().getRootGroup().getGroups());
        System.out.println("FileTypeDescription");
        System.out.println(netcdf.getNetcdfDataset().getFileTypeDescription());
        System.out.println("");
         */
        variables = netcdf.getVariables();

        meteoLayerVector = layersManagerServices.getInstance(GROUP, NAME0);
        meteoLayerAnalytic = layersManagerServices.getInstance(GROUP, NAME1);
        meteoLayerLegend = layersManagerServices.getInstance(GROUP, NAME2);

        try {
            u = netcdf.read("u-component_of_wind_height_above_ground");
        } catch (Exception e) {
            try {
                u = netcdf.read("u-component_of_wind_surface");
            } catch (Exception e1) {
                LOGGER.log(Level.SEVERE, "File not NetCDF compliant", e1);
            }
        }
        try {
            v = netcdf.read("v-component_of_wind_height_above_ground");
        } catch (Exception e) {
            try {
                v = netcdf.read("v-component_of_wind_surface");
            } catch (Exception e1) {
                LOGGER.log(Level.SEVERE, "File not NetCDF compliant", e1);
            }
        }
        try {
            latitudes = netcdf.read("lat");
            longitudes = netcdf.read("lon");
            height = netcdf.read("height_above_ground");
            time = netcdf.read("time");
            reftime = netcdf.read("reftime");
        } catch (Exception e1) {
            LOGGER.log(Level.SEVERE, "File not NetCDF compliant", e1);
        }

        timeSeriesVectorField = new TimeSeriesVectorField(time, latitudes, longitudes, height.getFloat(0), u, v);
        latTab = timeSeriesVectorField.getLatitudes();
        lonTab = timeSeriesVectorField.getLongitudes();
        values = timeSeriesVectorField.getValues(0);
        directions = timeSeriesVectorField.getDirections(0);

        //  System.out.println("variables : " + variables);
        System.out.println("u : " + u.getSize());
        System.out.println("lat : " + latitudes.getSize());
        System.out.println("lon : " + longitudes.getSize());
        System.out.println("time : " + time.getSize());
        System.out.println("values : " + values.length);
        // NCdumpW.printArray(u, "u-component_of_wind_height_above_ground", System.out, null);

        List<Arrow> arrows = new ArrayList<>();
        int l = 0;
        for (int h = 0; h < latTab.length; h += 1) {
            for (int w = 0; w < lonTab.length; w += 1) {
                Arrow arrow = new Arrow(latTab[h], lonTab[w], values[l + w]);
                double alpha = -Math.toDegrees(directions[l + w]) + arrow.getRotation();
                if (alpha < 0) {
                    alpha = 360 + alpha;
                }
                // arrow.setRotation(-Math.toDegrees(directions[l + w]) + arrow.getRotation());
                arrow.setRotation(alpha);
                System.out.println("alpha : " + values[l + w] + " " + alpha);
                arrow.setValue(AVKey.DISPLAY_NAME, String.format("%.1f m/s %.1f %.1f %.1f ", values[l + w], u.getDouble(l + w), v.getDouble(l + w), Math.toDegrees(directions[l + w])));  //+" m/s");
                arrows.add(arrow);
            }
            l += lonTab.length;
        }
        meteoLayerVector.addRenderables(arrows);

        new AnalyticSurfaceController(meteoLayerAnalytic, meteoLayerLegend,
                values,
                timeSeriesVectorField.getLatitudeDimension(),
                timeSeriesVectorField.getLongitudeDimension(),
                timeSeriesVectorField.getMinLatitude(), timeSeriesVectorField.getMaxLatitude(),
                timeSeriesVectorField.getMinLongitude(), timeSeriesVectorField.getMaxLongitude(),
                0.0, timeSeriesVectorField.getMaxValue(0),//min, max values in m/s
                .8,
                "Meteo", "Kts");
    }

    public int getLatitudeDimension() {
        return timeSeriesVectorField.getLatitudeDimension();
    }

    public int getLongitudeDimension() {
        return timeSeriesVectorField.getLongitudeDimension();
    }

    public int getTimeDimension() {
        return timeSeriesVectorField.getTimeDimension();
    }

    public double getMaxLatitude() {
        return timeSeriesVectorField.getMaxLatitude();
    }

    public double getMaxLongitude() {
        return timeSeriesVectorField.getMaxLongitude();
    }

    public double getMinLatitude() {
        return timeSeriesVectorField.getMinLatitude();
    }

    public double getMinLongitude() {
        return timeSeriesVectorField.getMinLongitude();
    }

    public RenderableLayer getLayer() {
        return meteoLayerVector;
    }

    public void setCurrentTime(int currentTimeIndex) {
        //this.currentTimeIndex = currentTimeIndex;
        values = timeSeriesVectorField.getValues(currentTimeIndex);
        directions = timeSeriesVectorField.getDirections(currentTimeIndex);
    }
}
