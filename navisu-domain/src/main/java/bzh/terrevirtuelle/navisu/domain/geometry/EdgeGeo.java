/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.geometry;

import java.util.Objects;

/**
 *
 * @author serge
 * @date Aug 30, 2019
 */
public class EdgeGeo {

    public Point3DGeo x;

    public Point3DGeo y;

    public EdgeGeo() {
        x = new Point3DGeo(0.0, 0.0, 0.0);
        y = new Point3DGeo(0.0, 0.0, 0.0);
    }

    public EdgeGeo(Point3DGeo x, Point3DGeo y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the value of y
     *
     * @return the value of y
     */
    public Point3DGeo getY() {
        return y;
    }

    /**
     * Set the value of y
     *
     * @param y new value of y
     */
    public void setY(Point3DGeo y) {
        this.y = y;
    }

    /**
     * Get the value of x
     *
     * @return the value of x
     */
    public Point3DGeo getX() {
        return x;
    }

    /**
     * Set the value of x
     *
     * @param x new value of x
     */
    public void setX(Point3DGeo x) {
        this.x = x;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.x);
        hash = 37 * hash + Objects.hashCode(this.y);
        return hash;
    }

    public boolean isAdjacent(EdgeGeo obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.x.equals(obj.x) && this.y.equals(obj.y)) {
            return true;
        }
        if (this.x.equals(obj.y) && this.y.equals(obj.x)) {
            return true;
        }
        return false;
    }

    public boolean equals(EdgeGeo obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if (!this.x.equals(obj.x)) {
            return false;
        }
        if (!this.y.equals(obj.y)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;

    }

    public String printInv() {
        return "x=" + y + ", y=" + x + "\n"; //Permeutation pour controle lecture edge
    }
}
