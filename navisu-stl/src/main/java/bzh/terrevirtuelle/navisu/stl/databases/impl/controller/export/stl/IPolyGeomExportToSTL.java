/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.stl;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import java.util.List;
import java.util.Map;

/**
 *
 * @author serge
 */
public interface IPolyGeomExportToSTL {

    List<Point3D> export(String geometries, Map<String, String> labels);
}
