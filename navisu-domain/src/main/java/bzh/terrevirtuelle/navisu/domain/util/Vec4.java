/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.util;

/**
 *
 * @author serge
 * @date Jan 14, 2016
 *
 */
public class Vec4 {

    private double x;
    private double y;
    private double z;
    private double w;

    public Vec4(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec4(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec4(double x, double y, double z, double w) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Get the value of z
     *
     * @return the value of z
     */
    public double getZ() {
        return z;
    }

    /**
     * Set the value of z
     *
     * @param z new value of z
     */
    public void setZ(double z) {
        this.z = z;
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

    /**
     * Get the value of w
     *
     * @return the value of w
     */
    public double getW() {
        return w;
    }

    /**
     * Set the value of w
     *
     * @param w new value of w
     */
    public void setW(double w) {
        this.w = w;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.w) ^ (Double.doubleToLongBits(this.w) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vec4 other = (Vec4) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
            return false;
        }
        if (Double.doubleToLongBits(this.w) != Double.doubleToLongBits(other.w)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vec4{" + "x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + '}';
    }

}
