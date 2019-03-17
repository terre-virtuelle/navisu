/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.charts.impl.loader.dem;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.globes.Earth;
import gov.nasa.worldwind.globes.ElevationModel;
import gov.nasa.worldwind.terrain.HighResolutionTerrain;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date Oct 19, 2017
 */
public class DemSrtmElevationLoader {

    protected static final Logger LOGGER = Logger.getLogger(DemSrtmElevationLoader.class.getName());
    protected WorldWindow wwd;
    protected ElevationModel model;
    protected GeodesyServices geodesyServices;

    public DemSrtmElevationLoader(GeodesyServices geodesyServices) {
        this.geodesyServices = geodesyServices;
        wwd = GeoWorldWindViewImpl.getWW();
        model = wwd.getModel().getGlobe().getElevationModel();
    }

    public double[][] getElevations(List<? extends Position> positions,
            int ptsCountX, int ptsCountY,
            double latInc, double lonInc,
            double offset) {
        double longitude = positions.get(0).getLongitude().getDegrees();
        double latitude;
        double[][] elevations = new double[ptsCountX][ptsCountY];
        for (int u = 0; u < ptsCountX; u++) {
            latitude = positions.get(0).getLatitude().getDegrees();
            for (int v = 0; v < ptsCountY; v++) {
                double el = model.getElevation(Angle.fromDegrees(latitude), Angle.fromDegrees(longitude));
                if (el < 0) {
                    el = 0;
                }
                elevations[u][v] = el + offset;
                latitude += latInc;
            }
            longitude += lonInc;
        }
        return elevations;
    }

    public Point3DGeo[][] getElevations(double latMin, double lonMin, double latMax, double lonMax, double range) {

// Range in meters
        double gridX = geodesyServices.getDistanceM(latMin, lonMin, latMin, lonMax);
        double gridY = geodesyServices.getDistanceM(latMin, lonMin, latMax, lonMin);

        int ptsCountX = (int) (gridX / range);
        int ptsCountY = (int) (gridY / range);

        if (ptsCountX < ptsCountY) {
            ptsCountY = ptsCountX;
        } else {
            ptsCountX = ptsCountY;
        }

        double lonInc = Math.abs((lonMax - lonMin) / ptsCountX);
        double latInc = Math.abs((latMax - latMin) / ptsCountY);

        //Crete the grid
        LOGGER.info("In Create the grid");
        Point3DGeo[][] points = new Point3DGeo[ptsCountX + 1][ptsCountY + 1];
        double latitude = latMin;
        double longitude = lonMin;
        for (int i = 0; i <= ptsCountY; i++) {
            for (int j = 0; j <= ptsCountX; j++) {
                points[i][j] = new Point3DGeo(Math.round(latitude * 100000.0) / 100000.0,
                        Math.round(longitude * 100000.0) / 100000.0, 0.0);
                longitude += lonInc;
            }
            latitude += latInc;
            longitude = lonMin;
        }
        LOGGER.info("Out Create the grid");
        //Crete a List fo read the HighResolutionTerrain

        List<Position> positions = new ArrayList<>();
        for (int i = 0; i <= ptsCountY; i++) {
            for (int j = 0; j <= ptsCountX; j++) {
                positions.add(Position.fromDegrees(points[i][j].getLatitude(), points[i][j].getLongitude(), 0.0));
            }
        }

        Sector sector = new Sector(Angle.fromDegreesLatitude(latMin), Angle.fromDegreesLatitude(latMax),
                Angle.fromDegreesLongitude(lonMin), Angle.fromDegreesLongitude(lonMax));

        HighResolutionTerrain highResolutionTerrain = new HighResolutionTerrain(new Earth(), sector, null, 1.0);
        List<Position> realPositions = new ArrayList<>();
        LOGGER.info("In highResolutionTerrain.getElevation");
        positions.forEach((latLon) -> {

            Double elevation = highResolutionTerrain.getElevation(latLon);
            if (elevation == null) {
                elevation = 0.0;
            }
            //in( "elevation : " + Double.toString(elevation)+" ");
            realPositions.add(new Position(latLon, Math.round(elevation * 100000.0) / 100000.0));
        });
        LOGGER.info("Out highResolutionTerrain.getElevation");
        
        LOGGER.info("In create tab Point3D");
        int k = 0;
        for (int i = 0; i <= ptsCountY; i++) {
            for (int j = 0; j <= ptsCountX; j++) {
                points[i][j] = new Point3DGeo(realPositions.get(k).getLatitude().getDegrees(),
                        realPositions.get(k).getLongitude().getDegrees(),
                        realPositions.get(k).getElevation());
                k++;
            }

        }
        LOGGER.info("Out create tab Point3D");
        return points;
    }

    public Point3DGeo getElevation(double lat, double lon) {
        //Creation d'un secteur à partir du point a mesurer
        Sector sector = new Sector(Angle.fromDegreesLatitude(lat), Angle.fromDegreesLatitude(lat + 0.001),
                Angle.fromDegreesLongitude(lon), Angle.fromDegreesLongitude(lon + 0.001));

        HighResolutionTerrain highResolutionTerrain = new HighResolutionTerrain(new Earth(), sector, null, 1.0);

        Double elevation = highResolutionTerrain.getElevation(new LatLon(Angle.fromDegrees(lat), Angle.fromDegrees(lon)));
        if (elevation == null) {
            elevation = 0.0;
        }
        return new Point3DGeo(lat, lon, elevation);
    }

    protected void in(String comment) {
        Logger.getLogger(getClass().getSimpleName()).log(Level.INFO, comment);
    }

    protected void out(String comment) {
        Logger.getLogger(getClass().getSimpleName()).log(Level.INFO, comment);
    }
}
