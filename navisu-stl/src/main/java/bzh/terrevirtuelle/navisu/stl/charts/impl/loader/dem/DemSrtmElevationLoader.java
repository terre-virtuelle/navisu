/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.charts.impl.loader.dem;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.globes.Earth;
import gov.nasa.worldwind.globes.ElevationModel;
import gov.nasa.worldwind.terrain.HighResolutionTerrain;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 * @date Oct 19, 2017
 */
public class DemSrtmElevationLoader {

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

    public Point3D[][] getElevations(double latMin, double lonMin, double latMax, double lonMax, double range) {
        
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
        Point3D[][] points = new Point3D[ptsCountX + 1][ptsCountY + 1];
        double latitude = latMin;
        double longitude = lonMin;
        for (int i = 0; i <= ptsCountY; i++) {
            for (int j = 0; j <= ptsCountX; j++) {
                points[i][j] = new Point3D(Math.round(latitude * 100000.0) / 100000.0,
                        Math.round(longitude * 100000.0) / 100000.0, 0.0);
                longitude += lonInc;
            }
            latitude += latInc;
            longitude = lonMin;
        }
        //Crete a List fo read the HighResolutionTerrain

        List<Position> positions = new ArrayList<>();
        for (int i = 0; i <= ptsCountY; i++) {
            for (int j = 0; j <= ptsCountX; j++) {
                positions.add(Position.fromDegrees(points[i][j].getLatitude(), points[i][j].getLongitude(), 0.0));
            }
        }
        System.out.println(positions);
        Sector sector = new Sector(Angle.fromDegreesLatitude(latMin), Angle.fromDegreesLatitude(latMax),
                Angle.fromDegreesLongitude(lonMin), Angle.fromDegreesLongitude(lonMax));

        System.out.println("sector : "+sector);
        HighResolutionTerrain highResolutionTerrain = new HighResolutionTerrain(new Earth(), sector, null, 1.0);
        List<Position> realPositions = new ArrayList<>();

        positions.forEach((latLon) -> {
            Double elevation =highResolutionTerrain.getElevation(latLon);
           if(elevation==null )
               elevation=0.0;
            realPositions.add(new Position(latLon, Math.round(elevation * 100000.0) / 100000.0));
        });

        int k = 0;
        for (int i = 0; i <= ptsCountY; i++) {
            for (int j = 0; j <= ptsCountX; j++) {
                points[i][j] = new Point3D(realPositions.get(k).getLatitude().getDegrees(),
                        realPositions.get(k).getLongitude().getDegrees(),
                        realPositions.get(k).getElevation());
                k++;
            }

        }
        return points;
    }
}
