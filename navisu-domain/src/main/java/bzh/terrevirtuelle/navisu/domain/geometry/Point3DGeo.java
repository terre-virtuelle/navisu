/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.geometry;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @date 15 mars 2015
 * @author Serge Morvan
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "point3d", propOrder = {
    "latitude",
    "longitude",
    "elevation",
    "id"
})
@XmlRootElement
public class Point3DGeo
        implements NavigationData {

    private long id = 0;

    public double longitude = 0.0;

    public double latitude = 0.0;

    public double elevation = 0.0;

    public Point3DGeo() {
    }

    public Point3DGeo(Point3DGeo pt) {
        longitude = pt.getLongitude();
        latitude = pt.getLatitude();
        elevation = pt.getElevation();
    }

    public Point3DGeo(double lat, double lon) {
        this.longitude = lon;
        this.latitude = lat;
    }

    public Point3DGeo(double lat, double lon, double elevation) {
        this.longitude = lon;
        this.latitude = lat;
        this.elevation = elevation;
    }

    public Point3DGeo(int id, double lat, double lon) {
        this.id = id;
        this.longitude = lon;
        this.latitude = lat;
    }

    public Point3DGeo(int id, double lat, double lon, double elevation) {
        this.id = id;
        this.longitude = lon;
        this.latitude = lat;
        this.elevation = elevation;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    @Override
    public long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double lon) {
        this.longitude = lon;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double lat) {
        this.latitude = lat;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public static Point3DGeo[][] copy(Point3DGeo[][] tableau) {
        int nRows = tableau[0].length;
        int nColumns = tableau[0].length;
        Point3DGeo[][] newTab = new Point3DGeo[nRows][nColumns];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                newTab[i][j] = new Point3DGeo(tableau[i][j].getLatitude(),
                        tableau[i][j].getLongitude(), tableau[i][j].getElevation());
            }
        }
        return newTab;
    }

    public static Point3DGeo[][] toGrid(List<Point3DGeo> pts, int rows, int cols) {
        Point3DGeo[][] result = new Point3DGeo[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = new Point3DGeo(pts.get(i + j).getLatitude(),
                        pts.get(i + j).getLongitude(), pts.get(i + j).getElevation());
            }
        }
        return result;
    }

    public static Point3DGeo swapXY(Point3DGeo pt) {
        return new Point3DGeo(pt.getLongitude(), pt.getLatitude(), pt.getElevation());
    }

    public static Point3DGeo[][] suppress(Point3DGeo[][] tableau, int index) {
        int nRows = tableau[0].length;
        int nColumns = tableau[1].length;

        if (index >= nRows || index >= nColumns) {
            // Return exception ?
            return new Point3DGeo[0][0];
        }

        Point3DGeo[][] newTab = new Point3DGeo[nRows - 1][nColumns - 1];
        int newTabRow = 0;
        int newTabCol = 0;

        for (int i = 0; i < nRows; ++i) {
            if (i != index) {
                for (int j = 0; j < nColumns; ++j) {
                    if (j != index) {
                        newTab[newTabRow][newTabCol] = new Point3DGeo(tableau[i][j].getLatitude(),
                                tableau[i][j].getLongitude(), tableau[i][j].getElevation());
                        ++newTabCol;
                    }
                }
                ++newTabRow;
                newTabCol = 0;
            }
        }
        return newTab;
    }

    @Override
    public String toString() {
        // return "Point3D{" + "id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", elevation=" + elevation + '}';
        return latitude + " " + longitude + " " + elevation;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    public boolean equals(Point3DGeo obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        if (this.longitude != obj.longitude) {
            return false;
        }
        if (this.latitude != obj.latitude) {
            return false;
        }
        return this.elevation == obj.elevation;
    }
}
