/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.controller.loader;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.util.Pair;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class BeaconCardinalDbLoader
        extends BuoyageDbLoader {

    public BeaconCardinalDbLoader(Connection connection,
            Map<Pair<Double, Double>, String> marsysMap) {
        super(connection, "BCNCAR", marsysMap);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Buoyage> retrieveIn(double latMin, double lonMin, double latMax, double lonMax) {
        List<Buoyage> buoyages = super.retrieveIn(latMin, lonMin, latMax, lonMax);
        int i = 0;
        try {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                String cat = resultSet.getString(7);
                if (cat == null) {
                    cat = "0";
                }
                buoyages.get(i++).setCategoryOfMark(cat);  
            }
        } catch (SQLException ex) {
            Logger.getLogger(BeaconCardinalDbLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        return buoyages;
    }

}
