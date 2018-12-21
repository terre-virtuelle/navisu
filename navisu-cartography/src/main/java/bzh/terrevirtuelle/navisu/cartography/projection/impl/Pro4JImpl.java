/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.cartography.projection.impl;

import bzh.terrevirtuelle.navisu.cartography.projection.Pro4J;
import bzh.terrevirtuelle.navisu.cartography.projection.Pro4JServices;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import java.util.ArrayList;
import java.util.List;
import org.capcaval.c3.component.ComponentState;
import org.osgeo.proj4j.BasicCoordinateTransform;
import org.osgeo.proj4j.CRSFactory;
import org.osgeo.proj4j.CoordinateReferenceSystem;
import org.osgeo.proj4j.ProjCoordinate;

/**
 * @date @author Serge Morvan
 */
public class Pro4JImpl
        implements Pro4J, Pro4JServices, ComponentState {

    @Override
    public Point3D convertLambert93ToWGS84(double lat, double lon) {
        CRSFactory factory = new CRSFactory();
        CoordinateReferenceSystem dstCrs = factory.createFromName("EPSG:4326");//4326
        CoordinateReferenceSystem srcCrs = factory.createFromName("EPSG:2154");//Lambert93

        BasicCoordinateTransform transform = new BasicCoordinateTransform(srcCrs, dstCrs);

        // Note these are x, y so lng, lat
        ProjCoordinate srcCoord = new ProjCoordinate(lon, lat);
        ProjCoordinate dstCoord = new ProjCoordinate();

        transform.transform(srcCoord, dstCoord);
        return new Point3D(dstCoord.y, dstCoord.x, 0.0);
    }

    @Override
    public Point3D convertLambert93ToWGS84(Point3D pt) {
        return convertLambert93ToWGS84(pt.getLatitude(), pt.getLongitude());

    }

    @Override
    public List<Point3D> convertLambert93ToWGS84(List<Point3D> pts) {
        List<Point3D> result = new ArrayList<>();
        for (Point3D p : pts) {
            result.add(convertLambert93ToWGS84(p));
        }
        return result;
    }

    @Override
    public Point3D convertCoordinates(String epsgSrc, String epsgdest, Point3D pt) {
        CRSFactory factory = new CRSFactory();
        CoordinateReferenceSystem srcCrs = factory.createFromName(epsgSrc);
        CoordinateReferenceSystem dstCrs = factory.createFromName(epsgdest);
        
        BasicCoordinateTransform transform = new BasicCoordinateTransform(srcCrs, dstCrs);

        // Note these are x, y so lng, lat
        ProjCoordinate srcCoord = new ProjCoordinate(pt.getLongitude(), pt.getLatitude());
        ProjCoordinate dstCoord = new ProjCoordinate();

        transform.transform(srcCoord, dstCoord);
        return new Point3D(dstCoord.y, dstCoord.x, 0.0);
    }

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
