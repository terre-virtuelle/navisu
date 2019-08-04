/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.citygml.impl;

import bzh.terrevirtuelle.navisu.citygml.CityGML;
import bzh.terrevirtuelle.navisu.citygml.CityGMLServices;
import bzh.terrevirtuelle.navisu.domain.geometry.SolidGeo;
import java.util.ArrayList;
import java.util.List;
import org.capcaval.c3.component.ComponentState;
import org.citygml4j.model.citygml.building.Building;

/**
 * @date 13 mars 2015
 * @author Serge Morvan
 */
public class CityGMLImpl
        implements CityGML, CityGMLServices, ComponentState {

    @Override
    public Building importSolid(SolidGeo solid) {
        Building result = null;
        return result;
    }

    @Override
    public List<Building> importSolid(List<SolidGeo> solids) {
        List<Building> result = new ArrayList<>();
        for (SolidGeo g : solids) {

        }
        return result;
    }

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
