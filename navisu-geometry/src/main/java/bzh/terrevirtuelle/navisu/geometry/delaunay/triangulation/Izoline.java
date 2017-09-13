/*
 *    Geotools2 - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2008, Geotools Project Managment Committee (PMC)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *    
 *    @author      Josef Bezdek
 *	  @version     %I%, %G%
 *    @since JDK1.3 
 */


package bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation;

public class Izoline {
    // start point of triangle
    public Point_dt A;
    // end point of triangle
    public Point_dt B;
    // elevation
    public int elevation;

    /***********************************************************
     * Constructor
     *
     * @param A         - start point
     * @param B         - end point
     * @param elevation - elevation
     */
    public Izoline(Point_dt A, Point_dt B, int elevation) throws RuntimeException {
        if (A == null || B == null || A == B) {
            throw new RuntimeException("A == B or either A or B null");
        }
        this.A = A;
        this.B = B;
        this.elevation = elevation;
    }

    public Izoline(Point_dt a, Point_dt b) {
        A = a;
        B = b;
        elevation = Integer.MIN_VALUE;
    }

    @Override
    public int hashCode() {
        return A.hashCode() + B.hashCode() + elevation;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Izoline) {
            Izoline line = (Izoline) o;
            if (line.elevation == elevation &&
                    ((line.A.equals(A) && line.B.equals(B)) ||
                            (line.A.equals(B) && line.B.equals(A)))) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return " " + elevation + ": " + A.toString() + B.toString();
    }

    public void reverse() {
        Point_dt tmp = A;
        A = B;
        B = tmp;
    }

    public double length() {
        return A.distance(B);
    }
}
