package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;




public class Spatial
        extends S57Object {

    public Spatial() {
        this.spatial = true;
    }

    /**
     * Recupere les donnees pertinentes a partir des champs de l'objet
     *
     * @return
     */
    @Override
    public S57Object setField(String fieldName, byte[] fieldValue) {
        switch (fieldName) {
            case "ATTV":
                this.decodATTV(fieldValue);
                break;
            case "VRPC":
                this.decodVRPC(fieldValue);
                break;
            case "VRPT":
                this.decodVRPT(fieldValue);
                break;
            case "SG2D":
                this.decodSG2D(fieldValue);
                break;
            case "SG3D":
                this.decodSG3D(fieldValue);
                break;
            case "SGCC":
                this.decodSGCC(fieldValue);
                break;
        }

        return this;
    }

    /**
     * Recupere les donnees du champ d'identification de l'objet et instancie un
     * objet spatial correspondant
     *
     * @param fieldValue Le tableau d'octets contenus dans le champ
     * @return le nouvel objet
     */
    public Spatial decodVRID(byte[] fieldValue) {
        try {
            int rcnm = fieldValue[0] & 0xFF;
            long id0;
            Spatial obj = null;
            id0 = rcnm;
            id0 = id0 * 256 + (fieldValue[4] & 0xFF);
            id0 = id0 * 256 + (fieldValue[3] & 0xFF);
            id0 = id0 * 256 + (fieldValue[2] & 0xFF);
            id0 = id0 * 256 + (fieldValue[1] & 0xFF);

            this.setId(id0);

            if (rcnm == 110) {
                obj = new IsolatedNode(this.getId());
            } else if (rcnm == 120) {
                obj = new ConnectedNode(this.getId());
            } else if (rcnm == 130) {
                obj = new Edge(this.getId());
            } else if (rcnm == 140) {
                obj = new Face(this.getId());
            }
            return obj;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }

    }

    /**
     * Non trait� actuellement
     *
     * @param fieldValue
     */
    /*
     * TODO R�cup�re les attributs spatiaux
     */
    public void decodATTV(byte[] fieldValue) {

    }

    /**
     * Non trait� actuellement
     *
     * @param fieldValue
     */
    public void decodVRPC(byte[] fieldValue) {
    }

    /**
     * R�cup�re les objets spatiaux associ�s � cet objet Trait� dans Edge, Face
     * et IsolatedNode
     *
     * @param fieldValue
     */
    public void decodVRPT(byte[] fieldValue) {
    }

    /**
     * Trait� dans les classes h�ritant de Spatial
     *
     * @param fieldValue
     */
    public void decodSG2D(byte[] fieldValue) {
    }

    /**
     * Non trait� actuellement
     *
     * @param fieldValue
     */
    public void decodSG3D(byte[] fieldValue) {
    }

    /**
     * Non trait� actuellement
     */
    public void decodSGCC(byte[] fieldValue) {
    }

    /**
     * Non trait� actuellement
     *
     * @param fieldValue
     */
    public void decodARCC(byte[] fieldValue) {
    }

}
