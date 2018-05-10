/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.charts.impl.controller;

import bzh.terrevirtuelle.navisu.stl.charts.impl.controller.charts.StlChartController;
import gov.nasa.worldwind.geom.Position;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date Sep 18, 2017
 */
public class StlController {

    protected Path outPathname;
    protected Charset charset = Charset.forName("UTF-8");
    protected ArrayList<String> lines;
    protected int tilesCount;
    protected int index;
    protected List<? extends Position> positions;
    protected double tileSideX;
    protected double tileSideY;
    protected double earthSpaceX;
    protected double earthSpaceY;
    protected double bottom;
    protected double magnification;
    protected double offset;


    public StlController(Path outPathname,
            int tilesCount, int index,
            List<? extends Position> positions,
            double tileSideX, double tileSideY,
            double earthSpaceX, double earthSpaceY,
            double bottom,
            double magnification,
            double offset) {
        this.outPathname = outPathname;
        this.tilesCount = tilesCount;
        this.index = index;
        this.positions = positions;
        this.tileSideX = tileSideX;
        this.tileSideY = tileSideY;
        this.earthSpaceX = earthSpaceX;
        this.earthSpaceY = earthSpaceY;
        this.bottom = bottom;
        this.magnification = magnification;
        this.offset = offset;
    }

    public StlController(Path outPathname) {
        this.outPathname = outPathname;
    }

    public StlController() {
    }

    protected void write(String str) {
        lines = new ArrayList<>();
        lines.add(str);
        try {
            Files.write(outPathname, lines, charset, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(StlChartController.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }

    public Path getOutPathname() {
        return outPathname;
    }

    public void setOutPathname(Path outPathname) {
        this.outPathname = outPathname;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    public void setLines(ArrayList<String> lines) {
        this.lines = lines;
    }

    public int getTilesCount() {
        return tilesCount;
    }

    public void setTilesCount(int tilesCount) {
        this.tilesCount = tilesCount;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<? extends Position> getPositions() {
        return positions;
    }

    public void setPositions(List<? extends Position> positions) {
        this.positions = positions;
    }

}
