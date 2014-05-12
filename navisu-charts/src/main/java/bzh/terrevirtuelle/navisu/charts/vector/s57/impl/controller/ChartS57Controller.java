/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller;

import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.model.ChartS57Model;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.view.ChartS57Layer;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class ChartS57Controller {

    protected String path;

    private ChartS57Model model;

    private ChartS57Layer chartLayer;


    public ChartS57Controller(String path) {
        this.path = path;
    }

    /**
     * Get the value of chartLayer
     *
     * @return the value of chartLayer
     */
    public ChartS57Layer getChartS57Layer() {
        return chartLayer;
    }

    /**
     * Set the value of chartLayer
     *
     * @param chartLayer new value of chartLayer
     */
    public void setChartLayer(ChartS57Layer chartLayer) {
        this.chartLayer = chartLayer;
    }

    /**
     * Get the value of model
     *
     * @return the value of model
     */
    public ChartS57Model getModel() {
        return model;
    }

    /**
     * Set the value of model
     *
     * @param model new value of model
     */
    public void setModel(ChartS57Model model) {
        this.model = model;
    }

    /**
     * Get the value of path
     *
     * @return the value of path
     */
    public String getPath() {
        return path;
    }

    /**
     * Set the value of path
     *
     * @param path new value of path
     */
    public void setPath(String path) {
        this.path = path;
    }

}
