/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.geometry;

import com.vividsolutions.jts.geom.Geometry;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author serge
 * @date Jul 22, 2019
 */
public class SolidGeo {

    protected Set<FaceGeo> faces;
    protected int id;
    protected String name;
    protected Point3DGeo centroid;
    protected List<Point3DGeo> ground;
    protected SolidGeo roof;

    public SolidGeo() {
        this(0, "noname");
    }

    public SolidGeo(int id, String name) {
        this.id = id;
        this.name = name;
        faces = new HashSet<>();
    }

    public SolidGeo(Set<FaceGeo> faces) {
        this(faces, 0);
    }

    public SolidGeo(Set<FaceGeo> faces, int id) {
        this(faces, id, "noname");
    }

    public SolidGeo(Set<FaceGeo> faces, int id, String name) {
        this.faces = faces;
        this.id = id;
        this.name = name;
    }

    public SolidGeo getRoof() {
        return roof;
    }

    public void setRoof(SolidGeo roof) {
        this.roof = roof;
    }

    public void add(FaceGeo face) {
        faces.add(face);
    }

    public void addAll(Collection<? extends FaceGeo> faces) {
        this.faces.addAll(faces);
    }

    /**
     * Get the value of centroid
     *
     * @return the value of centroid
     */
    public Point3DGeo getCentroid() {
        return centroid;
    }

    /**
     * Set the value of centroid
     *
     * @param centroid new value of centroid
     */
    public void setCentroid(Point3DGeo centroid) {
        this.centroid = centroid;
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
     * Get the value of faces
     *
     * @return the value of faces
     */
    public Set<FaceGeo> getFaces() {
        return faces;
    }

    /**
     * Set the value of faces
     *
     * @param faces new value of faces
     */
    public void setFaces(Set<FaceGeo> faces) {
        this.faces = faces;
    }


    public List<Point3DGeo> getGround() {
        return ground;
    }

   
    public void setGround(List<Point3DGeo> ground) {    
        this.ground = ground;
    }

    @Override
    public String toString() {
        return "SolidGeo{" + "faces=" + faces + ", id=" + id + ", name=" + name + ", centroid=" + centroid + '}';
    }

}
