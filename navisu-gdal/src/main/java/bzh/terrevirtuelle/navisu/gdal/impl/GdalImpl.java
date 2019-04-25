/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.gdal.impl;

import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.domain.raster.RasterInfo;
import bzh.terrevirtuelle.navisu.gdal.Gdal;
import bzh.terrevirtuelle.navisu.gdal.GdalServices;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentState;

/**
 *
 * @author serge
 * @date Apr 22, 2019
 */
public class GdalImpl
        implements Gdal, GdalServices, ComponentState {

    protected static final String SEP = File.separator;
    protected static final String USER_DIR = System.getProperty("user.dir");
    protected static final String DEST_IMAGE_DIR = USER_DIR + SEP + "privateData" + SEP + "tif";
    protected static final String LUT_DIR = USER_DIR + SEP + "data" + SEP + "lut";

    @Override
    public RasterInfo resample(RasterInfo rasterInfo) {
        RasterInfo result = null;
        String rootImage = rasterInfo.getName();
        String srcImageDir = rasterInfo.getImageDir();

        String out = rootImage.replace(".tif", "_30.tif");
        String command = "gdalwarp -tr 30 30 "
                + " -t_srs EPSG:4326 "
                + " -r cubic "
                + " -te " + rasterInfo.getLonMin() + " " + rasterInfo.getLatMin() + " " + rasterInfo.getLonMax() + " " + rasterInfo.getLatMax() + " "
                + srcImageDir + SEP + rootImage + "   " + DEST_IMAGE_DIR + SEP + out;
        try {
            Proc.BUILDER.create()
                    .setCmd(command)
                    .execSh();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(GdalImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        result = new RasterInfo(rootImage + "_30",
                rasterInfo.getLonMin(), rasterInfo.getLatMin(),
                rasterInfo.getLonMax(), rasterInfo.getLatMax(), srcImageDir, "EPSG:4326");
        return result;
    }

    @Override
    public RasterInfo resample(RasterInfo src, RasterInfo target) {
        RasterInfo result = null;
        String rootImage = target.getName();
        String srcImageDir = target.getImageDir();

        String out = rootImage.replace(".tif", "_sample.tif");
        String command = "gdalwarp "
                //   + " -tr -" + src.getPixelLonSize() + " " + src.getPixelLatSize()
                + " -t_srs EPSG:4326 "
                + " -r bilinear "
                + " -te " + src.getLonMin() + " " + src.getLatMin() + " " + src.getLonMax() + " " + src.getLatMax() + " "
                + " -ts " + src.getCol() + " " + src.getLine() + " "
                + srcImageDir + SEP + rootImage + "   " + DEST_IMAGE_DIR + SEP + out;
        try {
            Proc.BUILDER.create()
                    .setCmd(command)
                    .execSh();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(GdalImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        result = new RasterInfo(out,
                src.getLine(), src.getCol(),
                target.getLonMin(), target.getLatMin(),
                target.getLonMax(), target.getLatMax(),
                srcImageDir, "EPSG:4326");
        return result;
    }

    @Override
    public RasterInfo gdalInfo(RasterInfo rasterInfo) {
        String tmp = null;
        String rootImage = rasterInfo.getName();
        String srcImageDir = rasterInfo.getImageDir();
        Proc.process("gdalinfo " + srcImageDir + SEP + rootImage);
        try {
            tmp = new String(Files.readAllBytes(Paths.get(USER_DIR + SEP + "navisu.log")), "UTF-8");
        } catch (IOException ex) {
            Logger.getLogger(GdalImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        boolean start = false;
        List<String> content = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new StringReader(tmp))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.contains(rasterInfo.getName())) {
                    start = true;
                }
                if (start == true) {
                    content.add(line);
                }
                line = reader.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(GdalImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

        double pixelLatSize = 0;
        double pixelLonSize = 0;
        //Pixel Size = (0.001209408963665,-0.000806273506008)
        for (String s : content) {
            if (s.contains("Pixel Size")) {
                String[] pixels = s.split("=");
                String[] pix;
                if (pixels.length != 0) {
                    pixels[1] = pixels[1].replace("(", "");
                    pixels[1] = pixels[1].replace(")", "");
                    pix = pixels[1].split(",");
                    pixelLatSize = Double.parseDouble(pix[1].trim());
                    pixelLonSize = Double.parseDouble(pix[0].trim());
                }
            }
        }
        double latMin = 0.0;
        double lonMin = 0.0;
        for (String s : content) {
            if (s.contains("Lower Left")) {
                String[] latLonTab = s.split("\\(");
                String[] latLon;
                if (latLonTab.length != 0) {
                    latLonTab[1] = latLonTab[1].replace(")", "");
                    latLon = latLonTab[1].split(",");
                    latMin = Double.parseDouble(latLon[1].trim());
                    lonMin = Double.parseDouble(latLon[0].trim());
                }
            }
        }
        double latMax = 0.0;
        double lonMax = 0.0;
        for (String s : content) {
            if (s.contains("Upper Right")) {
                String[] latLonTab = s.split("\\(");
                String[] latLon;
                if (latLonTab.length != 0) {
                    latLonTab[1] = latLonTab[1].replace(")", "");
                    latLon = latLonTab[1].split(",");
                    latMax = Double.parseDouble(latLon[1].trim());
                    lonMax = Double.parseDouble(latLon[0].trim());
                }
            }
        }
        int line = 0;
        int col = 0;
        for (String s : content) {
            if (s.contains("Size is")) {
                s = s.replace("Size is", "");
                String[] lc = s.split(",");
                if (lc.length != 0) {
                    line = Integer.parseInt(lc[1].trim());
                    col = Integer.parseInt(lc[0].trim());
                }
            }
        }
        RasterInfo result = new RasterInfo(rootImage,
                pixelLatSize, pixelLonSize,
                line, col,
                latMin, lonMin,
                latMax, lonMax,
                srcImageDir, "EPSG:4326", rasterInfo.getDemColorRelief());
        return result;
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
