/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.view;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;

/**
 *
 * @author serge
 * @date Jan 27, 2016
 *
 */
public abstract class NavigationView
        implements NavigationData {

    private double x;

    private double y;

    public abstract NavigationData getData();

    public NavigationView() {
    }

    public NavigationView(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the value of y
     *
     * @return the value of y
     */
    public double getY() {
        return y;
    }

    /**
     * Set the value of y
     *
     * @param y new value of y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Get the value of x
     *
     * @return the value of x
     */
    public double getX() {
        return x;
    }

    /**
     * Set the value of x
     *
     * @param x new value of x
     */
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "NavigationView{" + "x=" + x + ", y=" + y + '}';
    }

}
