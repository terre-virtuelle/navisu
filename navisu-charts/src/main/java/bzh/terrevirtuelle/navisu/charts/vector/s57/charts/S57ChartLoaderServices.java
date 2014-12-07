/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.ShapefileLoader;

/**
 *
 * @author Serge
 */
public interface S57ChartLoaderServices {

    void load(ShapefileLoader shapefileLoader, String acronym);
}
