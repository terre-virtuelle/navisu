package bzh.terrevirtuelle.navisu.geometry.objects3D.obj.simple;

/**
 *
 * @author serge
 * @date Mar 8, 2016
 *
 */
public class Face {

    public final String materialName;
    public final VSpec[] vertices;

    public Face(int[] vertexIndices) {
        this(null, wrap(vertexIndices));
    }

    public Face(String mtlName, VSpec[] vertices) {
        materialName = mtlName;
        this.vertices = vertices;
    }

    public static VSpec[] wrap(int[] vertexIndices) {
        VSpec[] vertices = new VSpec[vertexIndices.length];
        for (int i = 0; i < vertexIndices.length; i++) {
            vertices[i] = new VSpec(vertexIndices[i]);
        }
        return vertices;
    }

    @Override
    public String toString() {
        StringBuilder rval = new StringBuilder("f ");
        for (VSpec spec : vertices) {
            rval.append(" ").append(spec);
        }

        rval.append("\n");
        return rval.toString();

    }

    public static class VSpec {

        public int vertexIndex;
        private int textureCoordIndex = 0;

        public VSpec(int vertexIndex) {

            this.vertexIndex = vertexIndex;
        }

        public VSpec(int vIdx, int textureCoordIndex) {
            vertexIndex = vIdx;

            this.textureCoordIndex = textureCoordIndex;
        }

        @Override
        public String toString() {
            StringBuilder rval = new StringBuilder();
            rval.append(vertexIndex);
            if (textureCoordIndex > 0) {
                rval.append("/").append(textureCoordIndex);
            }
            return rval.toString();
        }
    }
}
