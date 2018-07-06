/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view.BuoyageView;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.List;

/**
 *
 * @author serge
 * @date 6/7/2018
 *
 */
public class BuoyageExportKML
        extends BuoyageView {

    String filename;
    public BuoyageExportKML(String filename) {
        this.filename=filename;
        System.out.println("BuoyageExportKML : " + filename);
    }


    public void export(List<Buoyage> buoyages) {

    }
}
