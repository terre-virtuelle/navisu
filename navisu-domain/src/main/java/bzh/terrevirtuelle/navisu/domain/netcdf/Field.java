/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.netcdf;

/**
 *
 * @author serge
 * @date Sep 8, 2016
 */
public class Field {

    private double height;

    public Field(double height) {
        this.height = height;
    }

    /**
     * Get the value of height
     *
     * @return the value of height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set the value of height
     *
     * @param height new value of height
     */
    final public void setHeight(double height) {
        this.height = height;
    }
    
}
