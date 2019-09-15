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

    Building exportSolid(SolidGeo solid);

    List<Building> exportToGML(List<SolidGeo> solid);

    CityModel createCityModel(List<Building> buildings);

    

    void write(CityModel cityModel, String name);

}
