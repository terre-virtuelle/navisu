/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.geometry;

import com.vividsolutions.jts.geom.Geometry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author serge
 * @date Jul 22, 2019
 */
public class SolidGeo {

    protected int id;
    protected String name;
    protected List<FaceGeo> faces;
    protected Point3DGeo centroid;
    protected List<Point3DGeo> ground;
    protected Geometry groundGeom;
    protected List<FaceGeo> roof;

    public SolidGeo() {
        this(0, "noname");
    }

    public SolidGeo(int id, String name) {
        this.id = id;
        this.name = name;
        faces = new ArrayList<>();
        roof = new ArrayList<>();
    }

    public SolidGeo(List<FaceGeo> faces) {
        this(faces, 0);
    }

    public SolidGeo(List<FaceGeo> faces, int id) {
        this(faces, id, "noname");
    }

    public SolidGeo(List<FaceGeo> faces, int id, String name) {
        this.faces = faces.stream().collect(Collectors.toList());
        this.id = id;
        this.name = name;
        roof = new ArrayList<>();
    }

    public Geometry getGroundGeom() {
        return groundGeom;
    }

    public void setGroundGeom(Geometry groundGeom) {
        this.groundGeom = groundGeom;
    }

    public List<FaceGeo> getRoof() {
        return roof;
    }

    public void setRoof(List<FaceGeo> roof) {
        this.roof = roof.stream().collect(Collectors.toList());
    }

    public void addRoof(List<FaceGeo> faces) {
        roof.addAll(faces);
    }

    public void add(FaceGeo face) {
        faces.add(face);
    }

    public void addAll(Collection<? extends FaceGeo> faces) {
        this.faces.addAll(faces.stream().collect(Collectors.toList()));
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
    public List<FaceGeo> getFaces() {
        return faces;
    }

    /**
     * Set the value of faces
     *
     * @param faces new value of faces
     */
    public void setFaces(List<FaceGeo> faces) {
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
        return "SolidGeo{" + "id=" + id + ", name=" + name + ", faces=" + faces + ", centroid=" + centroid + ", ground=" + ground + ", roof=" + roof + '}';
    }

}
