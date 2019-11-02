/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.citygml;

import bzh.terrevirtuelle.navisu.domain.geometry.SolidGeo;
import java.util.List;
import org.capcaval.c3.component.ComponentService;
import org.citygml4j.model.citygml.building.Building;
import org.citygml4j.model.citygml.core.CityModel;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public interface CityGMLServices
        extends ComponentService {

    /**
     *
     * @param solid
     * @return
     */
    Building exportSolid(SolidGeo solid);

    /**
     *
     * @param solid
     * @return
     */
    List<Building> exportToGML(List<SolidGeo> solid);

    /**
     *
     * @param buildings
     * @return
     */
    CityModel createCityModel(List<Building> buildings);

    /**
     *
     * @param inFilename
     * @param outFilename
     * @param epsgSrc  example for Lambert93 : "EPSG:2154"
     * @param epsgdest example for WGS84 : "EPSG:4326"
     */
    void convertCoordinatesCityGMLFile(String inFilename, String outFilename,  String epsgSrc, String epsgdest, double latOffset, double lonOffset);

    /**
     *
     * @param inDirname
     * @param outDirname
     * @param epsgSrc
     * @param epsgdest
     */
    void convertCoordinatesCityGMLDir(String inDirname, String outDirname,  String epsgSrc, String epsgdest,double latOffset, double lonOffset);

    /**
     *
     * @param cityModel
     * @param name
     */
    void write(CityModel cityModel, String name);

}
