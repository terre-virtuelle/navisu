/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import bzh.terrevirtuelle.navisu.domain.geometry.SolidGeo;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 * @date Aug 3, 2019
 */
public class BuildingsExportToSTL {

    private final BathymetryDBServices bathymetryDBServices;
    private final GeodesyServices geodesyServices;
    private final FaceGeoToSTL faceGeoToSTL;
    private double latMin;
    private double lonMin;
    private double latMax;
    private double lonMax;

    public BuildingsExportToSTL(BathymetryDBServices bathymetryDBServices, GeodesyServices geodesyServices) {
        this.bathymetryDBServices = bathymetryDBServices;
        this.geodesyServices = geodesyServices;
        faceGeoToSTL = new FaceGeoToSTL(geodesyServices);
    }

    @SuppressWarnings("unchecked")
    public List<SolidGeo> read(Connection buildingsConnection, Point3DGeo[][] g) {
        latMin = g[0][0].getLatitude();
        lonMin = g[0][0].getLongitude();
        latMax = g[g.length - 1][g[0].length - 1].getLatitude();
        lonMax = g[g.length - 1][g[0].length - 1].getLongitude();

        List<SolidGeo> solids = bathymetryDBServices.retrieveInSolid(buildingsConnection, "solid", latMin, lonMin, latMax, lonMax);
        
        return solids;
    }

    @SuppressWarnings("unchecked")
    public void export(List<SolidGeo> solids, String filename, double latScale, double lonScale, double tileSideZ, double maxdepth) {
        String building = "";
        for (SolidGeo s : solids) {
            Point3DGeo centroid = s.getCentroid();
            double lat = centroid.getLatitude();
            building += faceGeoToSTL.exportSTL(new ArrayList(s.getFaces()), s.getName() + " " + centroid.getLatitude() + ", " +centroid.getLongitude(),
                    latMin, lonMin, latScale, lonScale,
                    tileSideZ, maxdepth);
            building += faceGeoToSTL.exportSTL(new ArrayList(s.getRoof()), s.getName() + " " + centroid.getLatitude() + ", " +centroid.getLongitude(),
                    latMin, lonMin, latScale, lonScale,
                    tileSideZ, maxdepth);
        }
        faceGeoToSTL.write(filename, building);
    }
}
