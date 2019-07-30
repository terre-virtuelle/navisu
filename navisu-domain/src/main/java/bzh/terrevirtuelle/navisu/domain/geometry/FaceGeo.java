/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 * @date Jul 22, 2019
 */
public class FaceGeo {

    protected List<Point3DGeo> vertices;
    protected int id;
    protected String name;


    public FaceGeo() {
        vertices = new ArrayList<>();
        id = 0;
        name = "name";
    }

    public FaceGeo(int id) {
        vertices = new ArrayList<>();
        this.id = id;
        name = "name";
    }

    public FaceGeo(String name) {
        vertices = new ArrayList<>();
        this.id = 0;
        this.name = name;
    }

    public FaceGeo(List<Point3DGeo> vertices) {
        this(vertices, 0, "noname");
    }

    public FaceGeo(List<Point3DGeo> vertices, int id) {
        this(vertices, id, "noname");
    }

    public FaceGeo(List<Point3DGeo> vertices, int id, String name) {
        this.vertices = vertices;
        this.id = id;
        this.name = name;
    }

    public void add(Point3DGeo pt) {
        vertices.add(pt);
    }

    public List<Point3DGeo> remove(Point3DGeo pt) {
        vertices.remove(pt);
        return vertices;
    }

    public boolean contains(Point3DGeo pt) {
        boolean result = false;
        for (Point3DGeo p : vertices) {
            if (p.equals(pt)) {
                result = true;
            }
        }
        return result;
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

    public List<Point3DGeo> getVertices() {
        return vertices;
    }

    public void setVertices(List<Point3DGeo> vertices) {
        this.vertices = vertices;
    }

    @Override
    public String toString() {
        return  vertices.toString() ;
    }

}
