/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.wkt;

import bzh.terrevirtuelle.navisu.domain.svg.SVGPath3D;
import bzh.terrevirtuelle.navisu.stl.databases.impl.controller.export.svg.DepareExportToSVG;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date Jun 21, 2019
 */
public class DepareExportWKT {

    protected static final String SEP = File.separator;
    protected final String filenameRoot;
    protected final String filename;

    public DepareExportWKT(String filenameRoot, String filename) {
        this.filenameRoot = filenameRoot;
        this.filename = filename;
    }

    public void write(Map<Double, List<SVGPath3D>> svgMap) {
        List<SVGPath3D> shapeList = new ArrayList<>();
        Collection<List<SVGPath3D>> collShapesList = svgMap.values();
        collShapesList.forEach((l) -> {
            shapeList.addAll(l);
        });
        for (SVGPath3D svg : shapeList) {
            String height = Integer.toString((int) svg.getHeight());
            String path = filenameRoot + SEP + filename + "_" + height + "_" + svg.getId() + ".wkt";
            String content = createPolygonWKT(svg.getContent());
            try {
                Files.write(Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(DepareExportToSVG.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        String wktAll = "MULTIPOLYGON(";
        for (SVGPath3D svg : shapeList) {
            String content = createMultiPolygonWKT(svg.getContent());
            wktAll += content;
        }
        wktAll += ")";
        wktAll = wktAll .replace(")),)", ")))");
        String path = filenameRoot + SEP + filename + "_all" + ".wkt";
        try {
            Files.write(Paths.get(path), wktAll.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(DepareExportToSVG.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }

    }

    public String createPolygonWKT(String content) {
        String result = content;
        result = result.replace("L", "");
        result = result.replace("M", "POLYGON((");
        result = result.replace("z", ")");
        result = result.replace(" ", ";");
        result = result.replace(",", " ");
        result = result.replace(";", ",");
        result = result.replace(",)", "))");
        return result;
    }

    public String createMultiPolygonWKT(String content) {
        String result = content;
        result = result.replace("L", "");
        result = result.replace("M", "((");
        result = result.replace("z", ")");
        result = result.replace(" ", ";");
        result = result.replace(",", " ");
        result = result.replace(";", ",");
        result = result.replace(",)", ")),");
        return result;
    }
}
