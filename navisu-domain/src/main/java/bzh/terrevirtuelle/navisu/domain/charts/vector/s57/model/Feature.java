package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

import java.io.Serializable;
import java.util.*;
import java.lang.reflect.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Recuperation des objets de donnees
 *
 * @author PFE
 */
@XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
public class Feature
        extends S57Object
        implements Serializable {

    /**
     * Table des m�thodes de l'objet en cours
     */
    @XmlTransient
    protected HashMap<String, Method> methods;
    /**
     * Primitive geometrique de l'objet (1->Point, 2->Ligne, 3->Aire 255->Rien)
     */
    @XmlTransient
    protected int prim;
    /**
     * Enregistrements spatiaux associes a l'objet reperes par leurs
     * identifiants
     */
    @XmlTransient
    protected HashMap<Long, VectorUsage> spatialRecordById;
    /**
     * Enregistrements spatiaux associes a l'objet
     */
    @XmlTransient
    protected HashMap<Spatial, VectorUsage> spatialRecord;
    
    @XmlTransient
    protected int innerBoundaryIndex = 0;

    public Feature(long id) {
        super(id);
    }

    /**
     * Constructeur
     */
    public Feature() {
        this.spatialRecord = new HashMap<>();
        this.spatialRecordById = new HashMap<>();
        this.methods = new HashMap<>();
        this.feature = true;
    }

    /**
     * Retourne l'indice de la premiere frontiere interieure
     *
     * @return
     */
    public int getInnerBoundaryIndex() {
        return innerBoundaryIndex;
    }

    /**
     * Recupere les donnees pertinentes a partir des champs de l'objet
     *
     * @return
     */
    @Override
    public S57Object setField(String fieldName, byte[] fieldValue) {
        switch (fieldName) {
            case "ATTF":
                this.decodATTF(fieldValue);
                break;
            case "FSPT":
                this.decodFSPT(fieldValue);
                break;
        }
        /*
         else if(fieldName.equals("NATF"))
         {
         this.decodNATF(fieldValue);
         }
         else if(fieldName.equals("FFPC"))
         {
         this.decodFFPC(fieldValue);
         }
         else if(fieldName.equals("FFPT"))
         {
         this.decodFFPT(fieldValue);
         }
         else if(fieldName.equals("FSPC"))
         {
         this.decodFSPC(fieldValue);
         }
         else if(fieldName.equals("FOID"))
         {
         this.decodFOID(fieldValue);
         }
         */
        return this;
    }

    public int getPrim() {
        return this.prim;
    }

    public void setPrim(int prim) {
        /*
         *	prim==1		-> Point
         *	prim==2		-> Ligne
         *	prim==3		-> Aire
         *	prim==255	-> Aucun objet spatial
         */
        this.prim = prim;
    }

    /**
     * Recupere les donnees du champ des attributs de l'objet de donnee
     *
     * @param fieldValue Le tableau d'octets contenus dans le champ
     */
    protected void decodATTF(byte[] fieldValue) {
        try {
            int code = 0;
            for (int i = 0; i < fieldValue.length; i++) {
                String value = "";
                code = (fieldValue[i++] & 0xFF) + 256 * (fieldValue[i++] & 0xFF);
                while (fieldValue[i] != 31) {
                    value += Character.toString((char) fieldValue[i++]);
                }
                this.setAttribute(code, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Non traite actuellement
     *
     * @param fieldValue
     */
    protected void decodNATF(byte[] fieldValue) {
    }

    /**
     * Non traite actuellement
     *
     * @param fieldValue
     */
    /*
     * TODO Recupere les donnees du champs de contrele des champs de pointeurs
     * sur objets de donnees (pour mise a jour)
     */
    protected void decodFFPC(byte[] fieldValue) {
    }

    /**
     * Non traite actuellement
     */
    /*
     * TODO Recupere les donnees des champs de pointeurs sur objets de donnees 
     */
    protected void decodFFPT(byte[] fieldValue) {
    }

    /**
     * Non traite actuellement
     *
     * @param fieldValue
     */
    /*
     * TODO Recupere les donnees du champs de controle des champs de pointeurs
     *  sur objets spatiaux (pour mise a jour)
     */
    protected void decodFSPC(byte[] fieldValue) {
    }

    /**
     * Recupere les donnees des champs de pointeurs sur objets spatiaux
     *
     * @param fieldValue
     */
    protected void decodFSPT(byte[] fieldValue) {
        for (int i = 0; i * 8 < fieldValue.length; i++) {
            long id0;
            id0 = fieldValue[i * 8] & 0xFF;
            id0 = id0 * 256 + (fieldValue[i * 8 + 4] & 0xFF);
            id0 = id0 * 256 + (fieldValue[i * 8 + 3] & 0xFF);
            id0 = id0 * 256 + (fieldValue[i * 8 + 2] & 0xFF);
            id0 = id0 * 256 + (fieldValue[i * 8 + 1] & 0xFF);

            spatialRecordById.put(id0, new VectorUsage(fieldValue[i * 8 + 5], fieldValue[i * 8 + 6], fieldValue[i * 8 + 7], i + 1));
            if (innerBoundaryIndex == 0 && fieldValue[i * 8 + 6] == 2) {
                innerBoundaryIndex = i + 1;
            }
        }
    }

    /**
     * Non traite actuellement
     *
     * @param fieldValue
     */
    protected void decodFOID(byte[] fieldValue) {
    }

    /**
     * Recupere les donnees du champ d'identification de l'objet et instancie un
     * objet de donnee correspondant
     *
     * @param fieldValue Le tableau d'octets contenus dans le champ
     * @return le nouvel objet
     */
    @SuppressWarnings("unchecked")
    protected Feature decodFRID(byte[] fieldValue) {
        long id0 = 0;
        id0 = fieldValue[0];
        id0 = id0 * 256 + fieldValue[4];
        id0 = id0 * 256 + fieldValue[3];
        id0 = id0 * 256 + fieldValue[2];
        id0 = id0 * 256 + fieldValue[1];

        this.setId(id0);

        int objectCode = (fieldValue[7] & 0xFF) + 256 * (fieldValue[8] & 0xFF);
        try {
            String objName = S57Model.getObjectsValue(objectCode);

            //Objet non traite
            if (objName == null) {
                return null;
            }

            Class classObject = Class.forName("bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo." + objName);
            // System.out.println("\nClasse: " + classObject.getSimpleName());

            Feature obj = (Feature) classObject
                    .getConstructor(new Class[]{Long.class})
                    .newInstance(new Object[]{getId()});
            obj.setPrim(fieldValue[5]);
            obj.fillMethods();
            //  System.out.println("obj : " + obj);
            return obj;
        } catch (ClassNotFoundException e) {
            //objet non trouve
            System.err.println("Feature" + e);
            return null;
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            System.err.println(e);
            return null;
        }

    }

    /**
     * Enregistre l'attribut dans les proprietes de l'objet en cours
     *
     * @param code Le code de l'attribut
     * @param value La valeur de l'attribut
     */
    protected void setAttribute(int code, String value) {

        String acronym = S57Model.getAttributesValue(code);
        try {
            Method setMethod = (Method) methods.get(("set" + acronym));
            Class[] params = setMethod.getParameterTypes();

            switch (params[0].getName()) {
                case "int": {
                    Object[] p = {new Integer(value)};
                    setMethod.invoke((Object) this, p);
                    break;
                }
                case "float": {
                    Object[] p = {new Float(value)};
                    setMethod.invoke((Object) this, p);
                    break;
                }
                default: {
                    Object[] p = {value};
                    setMethod.invoke((Object) this, p);
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.err.println(this.getClass().getSimpleName() + ": Attribut inexistant: " + acronym + " " + code);
        } catch (NumberFormatException e) {

        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Feature.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Recupere les accesseurs de l'objet en cours
     */
    protected void fillMethods() {
        Method[] m = this.getClass().getMethods();
        for (Method m1 : m) {
            methods.put(m1.getName(), m1);
        }

    }

    @Override
    public String toString() {
        return super.toString() + "  Feature{" + "prim=" + prim
                + "\n    ,spatialRecordById=" + spatialRecordById
                + "\n    ,spatialRecord=" + spatialRecord
                + "\n    ,innerBoundaryIndex=" + innerBoundaryIndex + '\n' + "  }\n";
    }

    @Override
    public void linkObjects() {
        if (!spatialRecordById.isEmpty()) {
            HashSet<Long> spatials = new HashSet<>(spatialRecordById.keySet());
            Iterator<Long> it = spatials.iterator();
            while (it.hasNext()) {
                Long id0 = it.next();
                spatialRecord.put(S57Model.getSpatialObject(id0), this.spatialRecordById.get(id0));
            }
        }
    }

    public HashMap<Spatial, VectorUsage> getSpatialRecord() {
        return spatialRecord;
    }

    public void setSpatialRecord(HashMap<Spatial, VectorUsage> spatialRecord) {
        try {
            this.spatialRecord = spatialRecord;
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
