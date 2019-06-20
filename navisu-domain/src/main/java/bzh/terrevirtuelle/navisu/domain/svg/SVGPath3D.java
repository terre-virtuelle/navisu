/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.svg;

import javafx.scene.shape.SVGPath;

/**
 *
 * @author serge
 * @date Jun 19, 2019
 */
public class SVGPath3D
        extends SVGPath {

    private double height;

    public SVGPath3D() {
        this.height = 0.0;
    }

    public SVGPath3D(double height) {
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
    public void setHeight(double height) {
        this.height = height;
    }

}
