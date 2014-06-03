/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.S57Object;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class ChartS57Controller {

    protected String path;
    private Map<String, Map<Long, S57Object>> filePathList;

    public ChartS57Controller(String path) {
        this.path = path;
        // this.filePathList = new ArrayList<>();
        read();
    }

    public List<String> read() {
        File file = new File(path);
        File[] listOfFiles;
        if (file.isDirectory()) {
            listOfFiles = file.listFiles();
            for (File f : listOfFiles) {
                String s = f.getName();
                if (s.contains(".shp")) {
                    System.out.println(s.replace(".shp", ""));
                }
            }
        }
        return null;
    }
}
