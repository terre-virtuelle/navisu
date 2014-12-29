/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.currents.impl.controller;

import java.util.List;

/**
 *
 * @author Serge
 */
public class GridFactory {

    private List<Double> latList;

    private List<Double> lonList;

    public GridFactory() {
    }

    public GridFactory(List<Double> latList, List<Double> lonList) {
        this.latList = latList;
        this.lonList = lonList;
    }

    public double getMax(List<Double> list) {
        return list.stream().max(Double::compare).get();
    }

    public double getMin(List<Double> list) {
        return list.stream().min(Double::compare).get();
    }

    /**
     * Get the value of lonList
     *
     * @return the value of lonList
     */
    public List<Double> getLonList() {
        return lonList;
    }

    /**
     * Set the value of lonList
     *
     * @param lonList new value of lonList
     */
    public void setLonList(List<Double> lonList) {
        this.lonList = lonList;
    }

    /**
     * Get the value of latList
     *
     * @return the value of latList
     */
    public List<Double> getLatList() {
        return latList;
    }

    /**
     * Set the value of latList
     *
     * @param latList new value of latList
     */
    public void setLatList(List<Double> latList) {
        this.latList = latList;
    }

}
