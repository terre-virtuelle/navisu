/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.wkt;

/**
 *
 * @author serge
 * @date Jun 21, 2019
 */
public class DepareExportWKT {

    public String createWKT(String content) {

        String result = content;
        //LINESTRING(3 4,10 50,20 25)
        //M149,404 L149,409 150,412 152,413 154,414 158,413 162,409 163,406 162,403 158,400 z
        result = result.replace("L", "");
        result = result.replace("M", "POLYGON((");
        result = result.replace("z", ")");
        result = result.replace(" ", ";");
        result = result.replace(",", " ");
        result = result.replace(";", ",");
        result = result.replace(",)", "))");
        return result;
    }
}
