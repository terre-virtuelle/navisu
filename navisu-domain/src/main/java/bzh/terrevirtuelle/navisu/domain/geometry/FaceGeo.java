/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author serge
 * @date Jul 22, 2019
 */
public class FaceGeo {

    public EdgeGeo a;

    public EdgeGeo b;

    public EdgeGeo c;

    private int id;

    private String name;
    private int index = 0;

    public FaceGeo(List<Point3DGeo> pts) {
        Point3DGeo pt0 = new Point3DGeo(pts.get(0));
        Point3DGeo pt1 = new Point3DGeo(pts.get(0));
        Point3DGeo pt2 = new Point3DGeo(pts.get(0));
        a.setX(pt0);
        c.setY(pt0);
        b.setX(pt1);
        a.setY(pt1);
        c.setX(pt2);
        b.setY(pt2);
    }

    public FaceGeo() {
        a = new EdgeGeo();
        b = new EdgeGeo();
        c = new EdgeGeo();
    }

    public FaceGeo(int id) {
        a = new EdgeGeo();
        b = new EdgeGeo();
        c = new EdgeGeo();
        this.id = id;
    }

    public FaceGeo(String name) {
        a = new EdgeGeo();
        b = new EdgeGeo();
        c = new EdgeGeo();
        this.name = name;
    }

    public void add(Point3DGeo pt) {
        if (index == 0) {
            a.x = pt;
            c.y = pt;
        } else {
            if (index == 1) {
                b.x = pt;
                a.y = pt;
            } else {
                if (index == 2) {
                    c.x = pt;
                    b.y = pt;
                }
            }
        }
        a = new EdgeGeo(a.x, a.y);
        b = new EdgeGeo(b.x, b.y);
        c = new EdgeGeo(c.x, c.y);
        index++;
        index %= 3;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
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

    /**
     * Get the value of c
     *
     * @return the value of c
     */
    public EdgeGeo getC() {
        return c;
    }

    /**
     * Set the value of c
     *
     * @param c new value of c
     */
    public void setC(EdgeGeo c) {
        this.c = c;
    }

    /**
     * Get the value of b
     *
     * @return the value of b
     */
    public EdgeGeo getB() {
        return b;
    }

    /**
     * Set the value of b
     *
     * @param b new value of b
     */
    public void setB(EdgeGeo b) {
        this.b = b;
    }

    /**
     * Get the value of a
     *
     * @return the value of a
     */
    public EdgeGeo getA() {
        return a;
    }

    /**
     * Set the value of a
     *
     * @param a new value of a
     */
    public void setA(EdgeGeo a) {
        this.a = a;
    }

    public List<Point3DGeo> getVertices() {
        List<Point3DGeo> result = new ArrayList<>();
        result.add(a.x);
        result.add(b.x);
        result.add(c.x);
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.a);
        hash = 71 * hash + Objects.hashCode(this.b);
        hash = 71 * hash + Objects.hashCode(this.c);
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
        final FaceGeo other = (FaceGeo) obj;
        if (!Objects.equals(this.a, other.a)) {
            return false;
        }
        if (!Objects.equals(this.b, other.b)) {
            return false;
        }
        if (!Objects.equals(this.c, other.c)) {
            return false;
        }
        return true;
    }

    public boolean isAdjacent(FaceGeo obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (a.isAdjacent(obj.a) || a.isAdjacent(obj.b) || a.isAdjacent(obj.c)) {
            return true;
        }
        if (b.isAdjacent(obj.a) || b.isAdjacent(obj.b) || b.isAdjacent(obj.c)) {
            return true;
        }
        if (c.isAdjacent(obj.a) || c.isAdjacent(obj.b) || c.isAdjacent(obj.c)) {
            return true;
        }
        return false;
    }

    public List<FaceGeo> getAdjacents(List<FaceGeo> faces) {
        List<FaceGeo> result = new ArrayList<>();
        for (FaceGeo f : faces) {
            if (!this.equals(f) && this.isAdjacent(f)) {
                result.add(f);
            }
        }
        return result;
    }

    public static List<FaceGeo> getAdjacents(FaceGeo face , List<FaceGeo> faces) {
        List<FaceGeo> result = new ArrayList<>();
        for (FaceGeo f : faces) {
            if (!face.equals(f) && face.isAdjacent(f)) {
                result.add(f);
            }
        }
        return result;
    }
    public static List<FaceGeo> getAdjacents(List<FaceGeo> adList, List<FaceGeo> faces, List<FaceGeo> faceSet) {
        // System.out.println(adList);
        List<FaceGeo> l = null;
        for (FaceGeo f : adList) {
            l = f.getAdjacents(f, faces);
            if (!faceSet.contains(f)) {
                faceSet.add(f);
            }
        }
        System.out.println("l : "+ l);
        if (l.isEmpty()) {
            return null;
        }
        return getAdjacents(l, faces, faceSet);
    }

    public EdgeGeo getGround() {
        EdgeGeo result = a;
        if (b.x.getElevation() < a.x.getElevation()
                && b.y.getElevation() < a.y.getElevation()
                && b.x.getElevation() < a.y.getElevation()
                && b.y.getElevation() < a.x.getElevation()) {
            result = b;
        }
        if (c.x.getElevation() < a.x.getElevation()
                && c.y.getElevation() < a.y.getElevation()
                && c.x.getElevation() < a.y.getElevation()
                && c.y.getElevation() < a.x.getElevation()) {
            result = c;
        }
        if (b.x.getElevation() < c.x.getElevation()
                && b.y.getElevation() < c.y.getElevation()
                && b.x.getElevation() < c.y.getElevation()
                && b.y.getElevation() < c.x.getElevation()) {
            result = b;
        }
        return result;
    }

    @Override
    public String toString() {
        // return id +" a=" + a + ", b=" + b + ", c=" + c + '}';
       // return id + " " + a + "\n " + b + "\n " + c + "\n";
      return id +" ";
    }

    public String printInv() {
        // return id +" a=" + a + ", b=" + b + ", c=" + c + '}';
        return a.printInv() + " " + b.printInv() + " " + c.printInv();
    }
}
