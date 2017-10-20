/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.impl.loader.dem;

import bzh.terrevirtuelle.navisu.stl.impl.loader.ElevationLoader;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.ElevationModel;
import java.util.List;

/**
 *
 * @author serge
 * @date Oct 19, 2017
 */
public class DemElevationLoader
        extends ElevationLoader {

    protected WorldWindow wwd;
    protected ElevationModel model;

    public DemElevationLoader(GeodesyServices geodesyServices, 
            List<? extends Position> positions, int index, 
            double tileSideX, double tileSideY, 
            double earthSpaceX, double earthSpaceY, 
            double bottom, double magnification,
            double offset) {
        super(geodesyServices, positions, index, tileSideX, tileSideY, earthSpaceX, earthSpaceY, bottom, magnification,offset);
        wwd = GeoWorldWindViewImpl.getWW();
        model = wwd.getModel().getGlobe().getElevationModel();
    }

    @Override
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
                elevations[u][v] = el+offset;
                latitude += latInc;
            }
            longitude += lonInc;
        }
        return elevations;
    }
}
