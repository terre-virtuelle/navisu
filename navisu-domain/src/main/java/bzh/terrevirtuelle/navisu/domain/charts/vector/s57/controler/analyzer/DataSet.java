/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.controler.analyzer;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.DataSetGeographicReference;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Feature;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Spatial;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.app.Main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Serge
 */
public class DataSet {

    /**
     * Table des enregistrements de données indéxés par leur identifiant
     */
    private static HashMap<Long, Feature> featureObjects = new HashMap<>();
    /**
     * Table des enregistrements spatiaux indéxés par leur identifiant
     */
    private static HashMap<Long, Spatial> spatialObjects = new HashMap<>();
    ;
    /**
     * Table associant le code de l'attribut à son nom
     */
    private static final HashMap attributes = new HashMap();
    /**
     * Table associant le code d'un objet S57 à son nom
     */
    private static final HashMap objects = new HashMap();
    /**
     * Ensemble des objets
     */
    private static HashSet<Feature> _features;
    /**
     * Objet contenant des informations sur la carte traitée
     */
    private static DataSetGeographicReference _dataSet;

    public void init() {
        BufferedReader bR;
        String[] tmp;
        String s;
        try {
            bR = new BufferedReader(new FileReader(new File("properties/objects.txt")));
            do {
                s = bR.readLine();
                if (s != null) {
                    tmp = s.split(";");
                    objects.put(new Integer(tmp[0]), tmp[1]);
                }
            } while (s != null);
            bR.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            bR = new BufferedReader(new FileReader(new File("properties/attributes.txt")));
            do {
                s = bR.readLine();
                if (s != null) {
                    tmp = s.split(";");
                    attributes.put(new Integer(tmp[0]), tmp[1]);
                }
            } while (s != null);
            bR.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Enregistre l'objet de donnée associé à son identifiant
     *
     * @param key l'identifiant
     * @param value l'objet de donnée
     */
    public static void putFeatureObject(Long key, Feature value) {
        featureObjects.put(key, value);
        //  System.out.println("key : " + key + "  value : " + value);
    }

    /**
     * Enregistre l'objet spatial associé à son identifiant
     *
     * @param key l'identifiant
     * @param value l'objet spatial
     */
    public static void putSpatialObject(Long key, Spatial value) {
        spatialObjects.put(key, value);
        // System.out.println("key : " + key + "  value : " + value);
    }

    /**
     * Retourne l'objet de donnée associé à l'identifiant
     *
     * @param key l'identifiant
     * @return l'objet de donnée
     */
    public static Feature getFeatureObject(Long key) {
        return featureObjects.get(key);
    }

    public static HashMap<Long, Feature> getFeatureObjects() {
        return featureObjects;
    }

    /**
     * Retourne l'objet spatial associé à l'identifiant
     *
     * @param key l'identifiant
     * @return l'objet de spatial
     */
    public static Spatial getSpatialObject(Long key) {
        return spatialObjects.get(key);
    }

    public static HashMap<Long, Spatial> getSpatialObjects() {
        return spatialObjects;
    }

    public static void setFeatures(HashSet<Feature> features) {
        _features = features;
    }

    /**
     * Renvoie le nom de l'attribut associé au code
     *
     * @param key le code de l'attribut
     * @return nom de l'attribut
     */
    public static String getAttributesValue(int key) {
        return (String) attributes.get(key);
    }

    /**
     * Renvoie le nom de l'objet associé au code
     *
     * @param key le code de l'objet
     * @return nom de l'objet
     */
    public static String getObjectsValue(int key) {
        return (String) objects.get(key);
    }

    /**
     * Crée les liens entre les objets à partir des identifiants
     *
     */
    public static void linkObjects() {
        HashSet<Spatial> sp = new HashSet<>(spatialObjects.values());
        Iterator<Spatial> itSp = sp.iterator();
        while (itSp.hasNext()) {
            itSp.next().linkObjects();
        }

        Iterator<Feature> itFeat = _features.iterator();
        while (itFeat.hasNext()) {
            itFeat.next().linkObjects();
        }
    }

    /**
     * Renvoie le coefficient multiplicateur des données de sonde
     *
     * @return le coefficient multiplicateur des données de sonde
     */
    public static int getSOMF() {
        return _dataSet.getSomf();
    }

    /**
     * Enregistre le coefficient multiplicateur des données de sonde
     *
     * @param somf
     */
    public static void setSOMF(int somf) {
        if (_dataSet == null) {
            _dataSet = new DataSetGeographicReference();
        }
        _dataSet.setSomf(somf);
    }

    /**
     * Renvoie le coefficient multiplicateur des coordonnées 2D
     *
     * @return le coefficient multiplicateur des coordonnées 2D
     */
    public static int getCOMF() {
        return _dataSet.getComf();
    }

    /**
     * Enregistre le coefficient multiplicateur des coordonnées 2D
     *
     * @param comf
     */
    public static void setCOMF(int comf) {
        if (_dataSet == null) {
            _dataSet = new DataSetGeographicReference();
        }
        _dataSet.setComf(comf);
    }

    public static void setDataSet(DataSetGeographicReference dataSet) {
        _dataSet = dataSet;
    }
}
