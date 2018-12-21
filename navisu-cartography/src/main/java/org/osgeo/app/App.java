/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.osgeo.app;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import org.osgeo.proj4j.BasicCoordinateTransform;
import org.osgeo.proj4j.CRSFactory;
import org.osgeo.proj4j.CoordinateReferenceSystem;
import org.osgeo.proj4j.ProjCoordinate;

/**
 *
 * @author serge
 */
public class App {

    public static void main(String[] args) {
       /*
        CRSFactory factory = new CRSFactory();
        CoordinateReferenceSystem srcCrs = factory.createFromName("EPSG:4326");
        CoordinateReferenceSystem dstCrs = factory.createFromName("EPSG:4141");

        BasicCoordinateTransform transform = new BasicCoordinateTransform(srcCrs, dstCrs);

// Note these are x, y so lng, lat
        ProjCoordinate srcCoord = new ProjCoordinate(1029705.083, 272723.849);//(-76.0, 45.0);
        ProjCoordinate dstCoord = new ProjCoordinate();
*/
       CRSFactory factory = new CRSFactory();
        CoordinateReferenceSystem dstCrs = factory.createFromName("EPSG:4326");
        CoordinateReferenceSystem srcCrs = factory.createFromName("EPSG:2154");//Lambert93

        BasicCoordinateTransform transform = new BasicCoordinateTransform(srcCrs, dstCrs);

// Note these are x, y so lng, lat
        ProjCoordinate srcCoord = new ProjCoordinate(1029705.083, 272723.849);//(-76.0, 45.0);
        ProjCoordinate dstCoord = new ProjCoordinate();
       
       
// Writes result into dstCoord
        transform.transform(srcCoord, dstCoord);
        System.out.println("dstCoord : " + dstCoord);
        
    }
}
