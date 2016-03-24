package bzh.terrevirtuelle.navisu.geometry.objects3D.obj;

import java.awt.geom.Point2D;
import java.util.*;

/**
 *
 * @author serge
 * @date Mar 8, 2016
 *
 */
public class WavefrontOBJBuilder {

    private List<Point3D> vertices;
    private Map<Point3D, Integer> vertexMap;
    private List<Face> faces;
    private List<Point2D> textureCoords;
    private Map<Point2D, Integer> textureCoordMap;
    private MaterialLib mLib;

    public WavefrontOBJBuilder() {
        this(null);
    }

    

    /**
     *
     * @param mLib
     */
    public WavefrontOBJBuilder(MaterialLib mLib) {
        this.mLib = mLib;
        vertices = new ArrayList<>();
        vertexMap = new HashMap<>();
        faces = new ArrayList<>();
        textureCoords = new ArrayList<>();
        textureCoordMap = new HashMap<>();
    }

    public void addVertice(double x, double y, double z) {
        vertices.add(new Point3D(x, y, z));
    }

    /**
     *
     * @param vertexIndices
     */
    public void addFace(int... vertexIndices) {
        faces.add(new Face(vertexIndices));
    }

    /**
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public int indexForVertex(double x, double y, double z) {
        Point3D p = new Point3D(x, y, z);

        return indexForVertex(p);
    }

    /**
     * the indices start at 1, not 0 like C folks are used to
     *
     * @param p
     * @return
     */
    public int indexForVertex(Point3D p) {
        Integer rval = vertexMap.get(p);
        if (null == rval) {
            vertices.add(p);
            rval = vertices.size();
            vertexMap.put(p, rval);
        }
        return rval;
    }

    /**
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public LazyIndex lazyIndexForVertex(double x, double y, double z) {
        Point3D p = new Point3D(x, y, z);
        return new LazyIndex(p);
    }

    /**
     *
     * @return
     */
    public CharSequence dump() {
        StringBuilder rval = new StringBuilder();

        vertices.stream().forEach((v) -> {
            rval.append("v ").append(v.x).append(" ").append(v.y).append(" ").append(v.z).append("\n");
        });
        rval.append("\n");
        if (!textureCoords.isEmpty()) {
            textureCoords.stream().forEach((textureCoord) -> {
                rval.append("vt ").append(textureCoord.getX()).append(" ").append(textureCoord.getY()).append("\n");
            });
            rval.append("\n");
        }
        String prevMN = null;
        for (Face face : faces) {
            String mn = face.materialName;
            if (mn != null) {
                if (!mn.equals(prevMN)) {
                    rval.append("usemtl ").append(mn).append("\n");
                    prevMN = mn;
                }
            }
            rval.append(face.toString());
        }
        return rval;
    }

    /**
     *
     * @param mtlName
     * @param vertices
     */
    public void addFaceTexture(String mtlName, IndexTexcoord... vertices) {
        Face.VSpec[] vspecs = new Face.VSpec[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            vspecs[i] = new Face.VSpec(vertices[i].vIdx, getTextureCoordIndex(vertices[i].u, vertices[i].v));
        }
        faces.add(new Face(mtlName, vspecs));
    }

    private int getTextureCoordIndex(double u, double v) {
        Point2D key = new Point2D.Double(u, v);

        Integer rval = textureCoordMap.get(key);
        if (null == rval) {
            textureCoords.add(key);
            rval = textureCoords.size();
            textureCoordMap.put(key, rval);
        }
        return rval;
    }

    @Override
    public String toString() {
        return dump().toString();
    }

    /**
     *
     */
    public class LazyIndex {

        private final Point3D p;
        private Integer idx = null;

        /**
         *
         * @param p
         */
        public LazyIndex(Point3D p) {
            this.p = p;
        }

        /**
         *
         * @return
         */
        public int get() {
            if (idx == null) {
                idx = indexForVertex(p);
            }
            return idx;
        }
    }
}
