/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geo.raster;


import org.capcaval.c3.component.ComponentService;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public interface RasterServices
        extends ComponentService {

   

    String translateTif2XYZ(String in, String outDir);

    String translateAsc2XYZ(String in, String outDir);

    String translateAscLambert93ToTif(String in, String outDir);

    String warpTifLambert93ToTifWGS84(String in, String outDir);
}
