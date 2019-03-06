/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geo.raster.impl;

import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.geo.raster.Raster;
import bzh.terrevirtuelle.navisu.geo.raster.RasterServices;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentState;

/**
 *
 * @author serge
 */
public class RasterImpl
        implements Raster, RasterServices, ComponentState {

    protected static final Logger LOGGER = Logger.getLogger(RasterImpl.class.getName());
    protected final String SEP = File.separator;

    @Override
    public String translateTif2XYZ(String in, String outDir) {
        System.out.println("in : " + in + " outDir : " + outDir);
        File file = new File(in);
        String out = file.getName();
        if (in.endsWith(".tif")) {
            out = out.replace("tif", "glz");
            String command = "gdal_translate";
            command += " -of XYZ "
                    + in + " "
                    + outDir + SEP + out;
            try {
                Proc.BUILDER.create()
                        .setCmd(command)
                        .execSh();
            } catch (IOException | InterruptedException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        }
        return out;
    }

    @Override
    public String translateAsc2XYZ(String in, String outDir) {
        File file = new File(in);
        String out = file.getName();
        if (in.endsWith(".asc")) {
            out = out.replace("asc", "glz");
            String command = "gdal_translate";
            command += " -of XYZ "
                    + in + " "
                    + outDir + SEP + out;
            try {
                Proc.BUILDER.create()
                        .setCmd(command)
                        .execSh();
            } catch (IOException | InterruptedException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        }
        return out;
    }

    @Override
    public String translateAscLambert93ToTif(String in, String outDir) {
        File file = new File(in);
        String out = file.getName();
        if (in.endsWith(".asc")) {
            out = out.replace("asc", "tif");
            String command = "gdal_translate";
            command += " -of GTiff -a_srs EPSG:2154 "
                    + in + " "
                    + outDir + SEP + out;
            try {
                Proc.BUILDER.create()
                        .setCmd(command)
                        .execSh();
            } catch (IOException | InterruptedException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        }
        return out;
    }

    @Override
    public String warpTifLambert93ToTifWGS84(String in, String outDir) {
        File file = new File(in);
        String out = file.getName();
        if (in.endsWith(".tif")) {
            out = out.replace(".tif", "");
            out = out + "_wgs84.tif";
            String command = "gdalwarp ";
            command += " -s_srs EPSG:2154 -t_srs EPSG:4326  "
                    + in + " "
                    + outDir + SEP + out;
            try {
                Proc.BUILDER.create()
                        .setCmd(command)
                        .execSh();
            } catch (IOException | InterruptedException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        }
        return out;
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
